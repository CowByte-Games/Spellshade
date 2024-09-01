package com.cowbytegames.spellshade.Game.Pieces.Common

import com.cowbytegames.spellshade.Game.Board

interface Piece {
    var player: Int
    var pieceName: String

    var maxHealth: Int
    var health: Int
    var shieldStrength: Int
    var shield: Int
    var baseDamage: Int
    var damage: Int
    var heal: Int
    var moveCost: Int
    var attackCost: Int

    var isStunned: Boolean
    var stunnedDuration: Int

    var currPos: Pair<Int,Int>

    var isMovePhase: Boolean

    fun move(position: Pair<Int, Int>, board: Board) {
        board.set(currPos.first, currPos.second, null)
        currPos = position
        board.set(currPos.first, currPos.second, this)
        isMovePhase = false
    }
    fun availableMoves(board: Board): ArrayList<Pair<Int, Int>>
    fun attack(position: Pair<Int, Int>, board: Board) {
        board.get(position.first, position.second)?.takeDamage(damage, board)
        board.useActionPoints(attackCost)
    }
    fun availableAttacks(board: Board): ArrayList<Pair<Int, Int>>
    fun heal(heal: Int) {
        health += heal
        health = minOf(health, maxHealth)
    }
    fun shield(position: Pair<Int, Int>)
    fun stun(position: Pair<Int, Int>) {
        isStunned = true
        stunnedDuration = 2
    }
    fun passive(board: Board)
    fun takeDamage(damage: Int, board: Board) {
        health -= damage
        if (health <= 0) {
            board.set(currPos.first, currPos.second, null)
            board.renderPieces()
        }
    }

    fun isTurn(board: Board): Boolean {
        return board.getActivePlayer() == player
    }
}