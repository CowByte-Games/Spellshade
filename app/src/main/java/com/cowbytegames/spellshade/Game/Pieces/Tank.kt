package com.cowbytegames.spellshade.Game.Pieces

import com.cowbytegames.spellshade.Game.Pieces.Common.Piece

class Tank(
    override val currPos: Array<Int>, override val player: Int
) : Piece  {

    override val pieceName: String = "Tank"

    override val maxHealth: Int = 10
    override val health: Int = 10
    override val maxShield: Int = 2
    override val shield: Int = 0
    override val damage: Int = 2
    override val heal: Int = 0

    override val isStun: Boolean = false
    override val isBuffed: Boolean = false

    override fun move(position: Array<Int>) {
        TODO("Not yet implemented")
    }

    override fun attack(position: Array<Int>) {
        TODO("Not yet implemented")
    }

    override fun heal(position: Array<Int>) {
        TODO("Not yet implemented")
    }

    override fun shield(position: Array<Int>) {
        TODO("Not yet implemented")
    }

    override fun stun(position: Array<Int>) {
        TODO("Not yet implemented")
    }
}