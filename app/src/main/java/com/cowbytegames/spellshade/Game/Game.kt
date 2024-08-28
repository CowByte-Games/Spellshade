package com.cowbytegames.spellshade.Game

class Game(val board: Board){
    fun endTurn() {
        executeDamagePassives()
        resetBuffsAndDebuffs()
        executeNonDamagePassives()
        board.flipActivePlayer()
    }

    private fun resetBuffsAndDebuffs() {

    }

    private fun executeDamagePassives() {

    }

    private fun executeNonDamagePassives() {

    }
}