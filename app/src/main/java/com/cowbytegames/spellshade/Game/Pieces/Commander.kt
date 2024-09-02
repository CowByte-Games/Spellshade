package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Commander(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Commander"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var shieldStrength: Int = 0
    override var shield: Int = 0
    override var baseDamage: Int = 2
    override var damage: Int = 2
    override var heal: Int = 0
    override var moveCost: Int = 0
    override var attackCost: Int = 2

    override var isStunned: Boolean = false
    override var stunnedDuration: Int = 0

    override var isMovePhase: Boolean = true

    override fun move(position: Pair<Int, Int>, board: Board) {
        super.move(position, board)

        board.useActionPoints(moveCost)
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        if (!isTurn(board) || !isMovePhase || isStunned) {
            return squares
        }

        for (i in currPos.first+1 until 7) {
            if(board.get(i, currPos.second) == null) {
                squares.add(Pair(i, currPos.second))
            }
            else { break }
        }
        for (i in currPos.first-1 downTo 0) {
            if(board.get(i, currPos.second) == null) {
                squares.add(Pair(i, currPos.second))
            }
            else { break }
        }
        for (i in currPos.second+1 until 7) {
            if(board.get(currPos.first, i) == null) {
                squares.add(Pair(currPos.first, i))
            }
            else { break }
        }
        for (i in currPos.second-1 downTo 0) {
            if(board.get(currPos.first, i) == null) {
                squares.add(Pair(currPos.first, i))
            }
            else { break }
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

    }
}