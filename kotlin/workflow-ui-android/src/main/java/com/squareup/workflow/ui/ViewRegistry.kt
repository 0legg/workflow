/*
 * Copyright 2019 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.workflow.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.KClass

/**
 * A collection of [ViewBinding]s that can be used to display the stream of renderings
 * from a workflow tree.
 *
 * Two concrete [ViewBinding] implementations are provided:
 *
 *  - [LayoutRunner.Binding], allowing the easy pairing of Android XML layout resources with
 *    [LayoutRunner]s to drive them.
 *
 *  - [BuilderBinding], which can build views from code.
 *
 *  Registries can be assembled via concatenation, making it easy to snap together screen sets.
 *  For example:
 *
 *     val AuthViewBindings = ViewRegistry(
 *         AuthorizingLayoutRunner, LoginLayoutRunner, SecondFactorLayoutRunner
 *     )
 *
 *     val TicTacToeViewBindings = ViewRegistry(
 *         NewGameLayoutRunner, GamePlayLayoutRunner, GameOverLayoutRunner
 *     )
 *
 *     val ApplicationViewBindings = ViewRegistry(ApplicationLayoutRunner) +
 *         AuthViewBindings + TicTacToeViewBindings
 *
 * In the above example, note that the `companion object`s of the various [LayoutRunner] classes
 * honor a convention of implementing [ViewBinding], in aid of this kind of assembly. See the
 * class doc on [LayoutRunner] for details.
 *
 * A binding for [UniquedRendering]`<*>` is built in, which delegates to the renderer
 * for [UniquedRendering.wrapped].
 */
@ExperimentalWorkflowUi
class ViewRegistry private constructor(
  private val bindings: Map<KClass<*>, ViewBinding<*>>
) {
  /** [bindings] plus any built-ins. Segregated to keep dup checking simple. */
  private val allBindings = bindings + (UniquedBinding.type to UniquedBinding)

  constructor(vararg bindings: ViewBinding<*>) : this(
      bindings.map { it.type to it }.toMap().apply {
        check(keys.size == bindings.size) {
          "${bindings.map { it.type }} must not have duplicate entries."
        }
      }
  )

  constructor(vararg registries: ViewRegistry) : this(
      registries.map { it.bindings }
          .reduce { left, right ->
            val duplicateKeys = left.keys.intersect(right.keys)
            check(duplicateKeys.isEmpty()) { "Must not have duplicate entries: $duplicateKeys." }
            left + right
          }
  )

  /**
   * Creates a [View] to display [initialRendering], which can be updated via calls
   * to [View.showRendering].
   */
  fun <RenderingT : Any> buildView(
    initialRendering: RenderingT,
    contextForNewView: Context,
    container: ViewGroup? = null
  ): View {
    @Suppress("UNCHECKED_CAST")
    return (allBindings[initialRendering::class] as? ViewBinding<RenderingT>)
        ?.buildView(this, initialRendering, contextForNewView, container)?.apply {
          check(showRenderingTag?.showing != null) {
            "View.bindShowRendering must be called for $this."
          }
        }
        ?: throw IllegalArgumentException(
            "A binding for ${initialRendering::class.qualifiedName} must be registered " +
                "to display $initialRendering."
        )
  }

  /**
   * Creates a [View] to display [initialRendering], and which can handle calls
   * to [View.showRendering].
   */
  fun <RenderingT : Any> buildView(
    initialRendering: RenderingT,
    container: ViewGroup
  ): View {
    return buildView(initialRendering, container.context, container)
  }

  operator fun <RenderingT : Any> plus(binding: ViewBinding<RenderingT>): ViewRegistry {
    check(binding.type !in bindings.keys) {
      "Already registered ${bindings[binding.type]} for ${binding.type}, cannot accept $binding."
    }
    return ViewRegistry(bindings + (binding.type to binding))
  }

  operator fun plus(registry: ViewRegistry): ViewRegistry {
    return ViewRegistry(this, registry)
  }
}

@UseExperimental(ExperimentalWorkflowUi::class)
private object UniquedBinding : ViewBinding<UniquedRendering<*>>
by BuilderBinding(
    type = UniquedRendering::class,
    viewConstructor = { viewRegistry, initialRendering, contextForNewView, container ->
      val view = viewRegistry.buildView(initialRendering.wrapped, contextForNewView, container)
      view.apply {
        val wrappedUpdater = showRenderingTag!!.showRendering
        bindShowRendering(initialRendering) {
          wrappedUpdater.invoke(it.wrapped)
        }
      }
    }
)
