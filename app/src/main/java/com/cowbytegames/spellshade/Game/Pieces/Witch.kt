package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Witch(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Witch"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var shield: Int = 0
    override var baseDamage: Int = 2
    override var damage: Int = 2
    override var moveCost: Int = 2
    override var attackCost: Int = 2

    override var isStunned: Boolean = false
    override var stunnedDuration: Int = 0

    override var isMovePhase: Boolean = true
    override var isAttackPhase: Boolean = false

    override fun move(position: Pair<Int, Int>, board: Board) {
        super.move(position, board)

        board.useActionPoints(moveCost)
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        if (!isTurn(board) || !isMovePhase || board.getActionPoints() < moveCost || isStunned) {
            return squares
        }

        val directions = listOf(
            Pair(0, -1), Pair(0, -2), Pair(0, 1), Pair(0, 2),
            Pair(1, 0), Pair(2, 0), Pair(-1, 0), Pair(-2, 0)
        )

        for ((rowOffset, colOffset) in directions) {
            val newRow = currPos.first + rowOffset
            val newCol = currPos.second + colOffset

            if ((newRow < 7 && newCol < 7) && (newRow >= 0 && newCol >= 0)) {
                if (board.get(newRow, newCol) == null) {
                    squares.add(Pair(newRow, newCol))
                }
            }
        }

        return squares
    }

    override fun attack(position: Pair<Int, Int>, board: Board) {
        board.get(position.first, position.second)?.stun()
        board.useActionPoints(attackCost)
        board.renderPieces()
    }

    override fun availableAttacks(board: Board): ArrayList<Pair<Int, Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        if (!isTurn(board) || isMovePhase || board.getActionPoints() < attackCost || isStunned) {
            return squares
        }

        val directions = listOf(
            Pair(1, -1),
            Pair(1, 1),
            Pair(-1, 1),
            Pair(-1, -1)
        )

        for ((rowOffset, colOffset) in directions) {
            val newRow = currPos.first + rowOffset
            val newCol = currPos.second + colOffset

            if ((newRow < 7 && newCol < 7) && (newRow >= 0 && newCol >= 0)) {
                val piece = board.get(newRow, newCol)
                if (piece != null && piece.player != this.player) {
                    squares.add(Pair(newRow, newCol))
                }
            }
        }

        return squares
    }

    override fun shield(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun passive(board: Board) {

    }
}