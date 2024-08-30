package com.cowbytegames.spellshade.Game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.gridlayout.widget.GridLayout
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import com.cowbytegames.spellshade.Game.Pieces.*
import com.cowbytegames.spellshade.R
import kotlin.math.ceil

class Board(val imageViews: Array<ImageView>) {
    val board: Array<Array<Piece?>> =  Array(7) { arrayOfNulls<Piece>(7) }
    private var activePlayer = 1
    private var actionPoints = 2

    init {
        board[1][1] = Warrior(Pair(1,1), 1)
        board[1][3] = Warrior(Pair(1,3), 1)
        board[1][5] = Warrior(Pair(1,5), 1)
        board[5][1] = Warrior(Pair(5,1), 2)
        board[5][3] = Warrior(Pair(5,3), 2)
        board[5][5] = Warrior(Pair(5,5), 2)

        board[1][2] = Witch(Pair(1,2), 1)
        board[5][4] = Witch(Pair(5,4), 2)

        board[1][4] = Wizard(Pair(1,4), 1)
        board[5][2] = Wizard(Pair(5,2), 2)

        board[0][0] = Assassin(Pair(0,0), 1)
        board[0][6] = Assassin(Pair(0,0), 1)
        board[6][0] = Assassin(Pair(6,0), 2)
        board[6][6] = Assassin(Pair(6,6), 2)

        board[0][1] = Archer(Pair(0,1), 1)
        board[0][5] = Archer(Pair(0,5), 1)
        board[6][1] = Archer(Pair(6,1), 2)
        board[6][5] = Archer(Pair(6,5), 2)

        board[1][0] = Healer(Pair(1,0), 1)
        board[1][6] = Healer(Pair(1,6), 1)
        board[5][0] = Healer(Pair(5,0), 2)
        board[5][6] = Healer(Pair(5,6), 2)

        board[0][2] = Tank(Pair(0,2), 1)
        board[0][4] = Tank(Pair(0,4), 1)
        board[6][2] = Tank(Pair(6,2), 2)
        board[6][4] = Tank(Pair(6,4), 2)

        board[0][3] = Commander(Pair(0,3), 1)
        board[6][3] = Commander(Pair(6,3), 2)
    }

    fun get(row: Int, col: Int): Piece? {
        return board.getOrNull(row)?.getOrNull(col)
    }

    fun set(row: Int, col: Int, piece: Piece?) {
        board[row][col] = piece
    }

    fun fixCellImageSize(gridLayout: GridLayout, boardView: ImageView) {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val index = i * 7 + j

                val imageView = gridLayout.getChildAt(index) as ImageView

                val size = ceil(boardView.width / 7.0).toInt()
                val layoutParams = imageView.layoutParams
                layoutParams.width = size
                layoutParams.height = size
                imageView.layoutParams = layoutParams
            }
        }
    }

    fun renderPieces() {
        for (i in 0 until 7) {
            for (j in 0 until 7) {
                val index = i * 7 + j

                val imageView = imageViews[index]
                imageView.setImageResource(0)

                val piece = board[i][j]

                if (piece != null){
                    if (piece.isStunned) {
                        imageView.setBackgroundColor(Color.BLUE)
                    } else {
                        imageView.setBackgroundColor(0)
                    }
                }

                if (piece is Warrior) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_warrior) }
                    else { imageView.setImageResource(R.drawable.red_warrior) }
                }
                else if (piece is Witch) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_witch) }
                    else { imageView.setImageResource(R.drawable.red_witch) }
                }
                else if (piece is Wizard) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_wizard) }
                    else { imageView.setImageResource(R.drawable.red_wizard) }
                }
                else if (piece is Assassin) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_assassin) }
                    else { imageView.setImageResource(R.drawable.red_assassin) }
                }
                else if (piece is Archer) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_archer) }
                    else { imageView.setImageResource(R.drawable.red_archer) }
                }
                else if (piece is Healer) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_healer) }
                    else { imageView.setImageResource(R.drawable.red_healer) }
                }
                else if (piece is Tank) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_tank) }
                    else { imageView.setImageResource(R.drawable.red_tank) }
                }
                else if (piece is Commander) {
                    if (piece.player == 1) { imageView.setImageResource(R.drawable.blue_commander) }
                    else { imageView.setImageResource(R.drawable.red_commander) }
                }
            }
        }
    }

    fun resetAvailableMovesRender() {
        for (imageView in imageViews) {
            val background = imageView.background
            if (background is ColorDrawable && background.color == Color.GREEN) {
                imageView.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    fun setAvailableMovesRender(cells: ArrayList<Pair<Int, Int>>) {
        for (cell in cells) {
            val (r, c) = cell
            val i = r * 7 + c
            val iV = imageViews[i]

            iV.setBackgroundColor(Color.GREEN)
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

    fun getActionPoints(): Int {
        return actionPoints
    }
    fun useActionPoints(cost: Int) {
        actionPoints -= cost
    }
    fun resetActionPoints() {
        actionPoints = 4
    }
}