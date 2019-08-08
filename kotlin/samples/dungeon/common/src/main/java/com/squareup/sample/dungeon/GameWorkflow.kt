package com.squareup.sample.dungeon

import com.squareup.sample.dungeon.Board.Location
import com.squareup.sample.dungeon.Direction.DOWN
import com.squareup.sample.dungeon.Direction.LEFT
import com.squareup.sample.dungeon.Direction.RIGHT
import com.squareup.sample.dungeon.Direction.UP
import com.squareup.workflow.RenderContext
import com.squareup.workflow.Snapshot
import com.squareup.workflow.StatefulWorkflow
import com.squareup.workflow.WorkflowAction.Companion.enterState
import java.util.EnumSet

class GameWorkflow(
  private val playerWorkflow: PlayerWorkflow
) : StatefulWorkflow<Unit, Game, Nothing, GameRendering>() {

  override fun initialState(
    input: Unit,
    snapshot: Snapshot?
  ) = Game(
      board = Board(
          width = 16,
          height = 16,
          cells = Board.EMPTY
      ),

      player = Player("zach", BoardCell("👩🏻‍🎤")),
      playerLocation = Location(8, 8)
  )

  override fun render(
    input: Unit,
    state: Game,
    context: RenderContext<Game, Nothing>
  ): GameRendering {
    return GameRendering(
        board = state.withPlayer(),
        player = context.renderChild(playerWorkflow, state) { movePlayerDirections ->
          enterState(state.movePlayer(movePlayerDirections))
        }
    )
  }

  override fun snapshotState(state: Game): Snapshot = Snapshot.EMPTY

  private fun Game.movePlayer(directions: EnumSet<Direction>): Game {
    var (x, y) = playerLocation
    if (LEFT in directions) x -= 1
    if (RIGHT in directions) x += 1
    // Don't let the player leave the board.
    x = x.coerceIn(0 until board.width)
    // Don't allow collisions with obstacles on the board.
    if (!board[x, y].isEmpty) x = playerLocation.x

    if (UP in directions) y -= 1
    if (DOWN in directions) y += 1
    y = y.coerceIn(0 until board.height)
    if (!board[x, y].isEmpty) y = playerLocation.y

    return copy(playerLocation = Location(x, y))
  }
}
