package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Witch(
    override var currPos: Pair<Int,Int>, override var player: Int
) : Piece  {

    override var pieceName: String = "Witch"

    override var maxHealth: Int = 10
    override var health: Int = 10
    override var shieldStrength: Int = 0
    override var shield: Int = 0
    override var baseDamage: Int = 2
    override var damage: Int = 2
    override var heal: Int = 0

    override var isStunned: Boolean = false
    override var stunnedDuration: Int = 0

    override fun move(position: Pair<Int, Int>, board: Board) {
        board.set(currPos.first, currPos.second, null)
        currPos = position
        board.set(currPos.first, currPos.second, this)
    }

    override fun availableMoves(board: Board): ArrayList<Pair<Int,Int>> {
        return arrayListOf()
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