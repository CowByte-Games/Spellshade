package com.cowbytegames.spellshade.Game.Pieces.Common

interface Piece {
    val player: Int
    val pieceName: String

    val maxHealth: Int
    val health: Int
    val maxShield: Int
    val shield: Int
    val damage: Int
    val heal: Int

    val isStun: Boolean
    val isBuffed: Boolean

    val currPos: Array<Int>

    fun move(position: Array<Int>)
    fun attack(position: Array<Int>)
    fun heal(position: Array<Int>)
    fun shield(position: Array<Int>)
    fun stun(position: Array<Int>)
}