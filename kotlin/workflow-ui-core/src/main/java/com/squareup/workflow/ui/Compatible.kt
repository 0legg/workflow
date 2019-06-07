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

/**
 * Returns true if [me] and [you] are instances of the same class, unless
 * that class implements [Compatible]. In that case
 * `me.`[canUpdateFrom][Compatible.isCompatibleWith]`(you)` must also be true.
 *
 * A convenient way to take control over the matching behavior of objects that
 * don't implement [Compatible] is to wrap them with [Uniqued].
 */
fun compatible(
  me: Any,
  you: Any
): Boolean {
  return when {
    me::class != you::class -> false
    me !is Compatible<*> -> true
    // If you see a casting error here, it's a lie. https://youtrack.jetbrains.com/issue/KT-31823
    else -> me.isCompatibleWith(you as Compatible<*>)
  }
}

/**
 * Helper for [List]s that are used as back-stacks, where the last element is considered
 * to be the top / current value of the stack.
 *
 * Returns a transformation of the receiver by popping back to the first element
 * that is [compatible] with [next]. If no matching frame is found, pushes [next]
 * on [top].
 */
fun <T : Any> List<T>.goTo(next: T): List<T> {
  val splicePoint = indexOfLast { compatible(it, next) }
  return if (splicePoint == -1) this + next else slice(0..splicePoint)
}

/**
 * Implemented by objects whose [compatibility][compatible] requires more nuance
 * than just being of the same type.
 *
 * Renderings that don't implement this interface directly can be distinguished
 * by wrapping them with [Uniqued].
 */
interface Compatible<out T : Compatible<T>> {
  fun isCompatibleWith(another: @UnsafeVariance T): Boolean
}
