package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import kotlin.math.max

class Healer(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Healer"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var shield: Int = 0
    override var baseDamage: Int = 2
    override var damage: Int = 2
    override var moveCost: Int = 2
    override var attackCost: Int = 2

    override var isStunned: Boolean = true
    override var stunnedDuration: Int = 1

    override var isMovePhase: Boolean = true

    var healStrength: Int = 1

    override fun move(position: Pair<Int, Int>, board: Board) {
        super.move(position, board)

        board.useActionPoints(moveCost)
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        if (!isTurn(board) || !isMovePhase || board.getActionPoints() < 2 || isStunned) {
            return squares
        }

        for (i in 0 until 7) {
            for (j in 0 until 7) {
                if (board.get(i, j) == null) {
                    squares.add(Pair(i,j))
                }
            }
        }

        return squares
    }

    override fun attack(position: Pair<Int, Int>, board: Board) {
        TODO("Not yet implemented")
    }

    override fun availableAttacks(board: Board): ArrayList<Pair<Int, Int>> {
        return arrayListOf()
    }

    override fun shield(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun passive(board: Board) {
        if (isStunned) {
            return
        }

        for (i in currPos.first-1..currPos.first+1) {
            for (j in currPos.second-1..currPos.second+1) {
                val piece = board.get(i, j)
                if (piece != null && piece.player == player) {
                    piece.health = minOf(maxHealth, piece.health + healStrength)
                }
            }
        }
    }
}