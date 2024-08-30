package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Healer(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Healer"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var shieldStrength: Int = 0
    override var shield: Int = 0
    override var baseDamage: Int = 2
    override var damage: Int = 2
    override var heal: Int = 0

    override var isStunned: Boolean = true
    override var stunnedDuration: Int = 1

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        val squares : ArrayList<Pair<Int, Int>> = arrayListOf()

        if (!isTurn(board) || board.getActionPoints() < 2 || isStunned) {
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

    override fun attack(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun heal(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun shield(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun stun(position: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override fun passive(board: Board) {

    }
}