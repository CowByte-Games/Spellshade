package com.cowbytegames.spellshade.Game

import android.widget.ImageView
import androidx.gridlayout.widget.GridLayout
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import com.cowbytegames.spellshade.Game.Pieces.*
import com.cowbytegames.spellshade.R
import kotlin.math.ceil

class Board {
    val board: Array<Array<Piece?>> =  Array(7) { arrayOfNulls<Piece>(7) }
    private var activePlayer = 0

    init {
        board[1][1] = Warrior(arrayOf(1,1), 1)
        board[1][3] = Warrior(arrayOf(1,3), 1)
        board[1][5] = Warrior(arrayOf(1,5), 1)
        board[5][1] = Warrior(arrayOf(5,1), 2)
        board[5][3] = Warrior(arrayOf(5,3), 2)
        board[5][5] = Warrior(arrayOf(5,5), 2)

        board[1][2] = Witch(arrayOf(1,2), 1)
        board[5][4] = Witch(arrayOf(5,4), 2)

        board[1][4] = Wizard(arrayOf(1,4), 1)
        board[5][2] = Wizard(arrayOf(5,2), 2)

        board[0][0] = Assassin(arrayOf(0,0), 1)
        board[0][6] = Assassin(arrayOf(0,0), 1)
        board[6][0] = Assassin(arrayOf(6,0), 2)
        board[6][6] = Assassin(arrayOf(6,6), 2)

        board[0][1] = Archer(arrayOf(0,1), 1)
        board[0][5] = Archer(arrayOf(0,5), 1)
        board[6][1] = Archer(arrayOf(6,1), 2)
        board[6][5] = Archer(arrayOf(6,5), 2)

        board[1][0] = Healer(arrayOf(1,0), 1)
        board[1][6] = Healer(arrayOf(1,6), 1)
        board[5][0] = Healer(arrayOf(5,0), 2)
        board[5][6] = Healer(arrayOf(5,6), 2)

        board[0][2] = Tank(arrayOf(0,2), 1)
        board[0][4] = Tank(arrayOf(0,4), 1)
        board[6][2] = Tank(arrayOf(6,2), 2)
        board[6][4] = Tank(arrayOf(6,4), 2)

        board[0][3] = Commander(arrayOf(0,3), 1)
        board[6][3] = Commander(arrayOf(6,3), 2)
    }

    fun get(row: Int, col: Int): Piece? {
        return board.getOrNull(row)?.getOrNull(col)
    }

    fun renderPieces(gridLayout: GridLayout, boardView: ImageView) {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val index = i * 7 + j

                val imageView = gridLayout.getChildAt(index) as ImageView

                val size = ceil(boardView.width / 7.0).toInt()
                val layoutParams = imageView.layoutParams
                layoutParams.width = size
                layoutParams.height = size
                imageView.layoutParams = layoutParams

                val piece = board[i][j]

                if (piece is Warrior) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_warrior) }
                    else { imageView.setImageResource(R.drawable.red_warrior) }
                }
                if (piece is Witch) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_witch) }
                    else { imageView.setImageResource(R.drawable.red_witch) }
                }
                if (piece is Wizard) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_wizard) }
                    else { imageView.setImageResource(R.drawable.red_wizard) }
                }
                if (piece is Assassin) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_assassin) }
                    else { imageView.setImageResource(R.drawable.red_assassin) }
                }
                if (piece is Archer) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_archer) }
                    else { imageView.setImageResource(R.drawable.red_archer) }
                }
                if (piece is Healer) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_healer) }
                    else { imageView.setImageResource(R.drawable.red_healer) }
                }
                if (piece is Tank) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_tank) }
                    else { imageView.setImageResource(R.drawable.red_tank) }
                }
                if (piece is Commander) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_commander) }
                    else { imageView.setImageResource(R.drawable.red_commander) }
                }
            }
        }
    }

    fun getActivePlayer(): Int {
        return activePlayer
    }
    fun flipActivePlayer() {
        if (activePlayer == 1) {
            activePlayer = 2
        } else {
            activePlayer = 1
        }
    }
}