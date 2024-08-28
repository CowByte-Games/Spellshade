package com.cowbytegames.spellshade.Activities

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.gridlayout.widget.GridLayout
import com.cowbytegames.spellshade.Game.Board
import com.cowbytegames.spellshade.Game.Pieces.Common.Piece
import com.cowbytegames.spellshade.R

class GameActivity : ComponentActivity() {
    private lateinit var coordinateTextView: TextView
    private lateinit var imageViews: Array<ImageView>
    private lateinit var gridLayout: GridLayout
    private lateinit var boardView: ImageView

    private lateinit var board: Board
    private var selectedPiece: Piece? = null

    private var isProcessingClick: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        coordinateTextView = findViewById(R.id.textView2)
        gridLayout = findViewById(R.id.board_grid)
        boardView = findViewById(R.id.board)
        imageViews = arrayOf(
            findViewById(R.id.square_00), findViewById(R.id.square_01), findViewById(R.id.square_02),
            findViewById(R.id.square_03), findViewById(R.id.square_04), findViewById(R.id.square_05),
            findViewById(R.id.square_06), findViewById(R.id.square_10), findViewById(R.id.square_11),
            findViewById(R.id.square_12), findViewById(R.id.square_13), findViewById(R.id.square_14),
            findViewById(R.id.square_15), findViewById(R.id.square_16), findViewById(R.id.square_20),
            findViewById(R.id.square_21), findViewById(R.id.square_22), findViewById(R.id.square_23),
            findViewById(R.id.square_24), findViewById(R.id.square_25), findViewById(R.id.square_26),
            findViewById(R.id.square_30), findViewById(R.id.square_31), findViewById(R.id.square_32),
            findViewById(R.id.square_33), findViewById(R.id.square_34), findViewById(R.id.square_35),
            findViewById(R.id.square_36), findViewById(R.id.square_40), findViewById(R.id.square_41),
            findViewById(R.id.square_42), findViewById(R.id.square_43), findViewById(R.id.square_44),
            findViewById(R.id.square_45), findViewById(R.id.square_46), findViewById(R.id.square_50),
            findViewById(R.id.square_51), findViewById(R.id.square_52), findViewById(R.id.square_53),
            findViewById(R.id.square_54), findViewById(R.id.square_55), findViewById(R.id.square_56),
            findViewById(R.id.square_60), findViewById(R.id.square_61), findViewById(R.id.square_62),
            findViewById(R.id.square_63), findViewById(R.id.square_64), findViewById(R.id.square_65),
            findViewById(R.id.square_66)
        )

        board = Board(imageViews)
        renderLayout()
        setPieceOnClick()
    }

    private fun renderLayout() {
        renderBoardBackground()
        board.fixCellImageSize(gridLayout, boardView)
        board.renderPieces()
    }

    private fun renderBoardBackground() {
        val rootView = findViewById<ViewGroup>(R.id.root_view)

        rootView.post {
            val marginInDp = 5
            val marginInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginInDp.toFloat(), resources.displayMetrics
            ).toInt()

            val width = rootView.width - 2 * marginInPx
            val height = rootView.height - 2 * marginInPx
            val size = minOf(width, height)

            val layoutParams = boardView.layoutParams
            layoutParams.width = size
            layoutParams.height = size
            boardView.layoutParams = layoutParams

            val constraintLayout = rootView as ConstraintLayout
            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)

            constraintSet.clear(boardView.id, ConstraintSet.TOP)
            constraintSet.clear(boardView.id, ConstraintSet.START)
            constraintSet.clear(boardView.id, ConstraintSet.END)
            constraintSet.clear(boardView.id, ConstraintSet.BOTTOM)

            constraintSet.connect(boardView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, marginInPx)
            constraintSet.connect(boardView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, marginInPx)
            constraintSet.connect(boardView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, marginInPx)

            constraintSet.applyTo(constraintLayout)
        }
    }

    private fun setPieceOnClick() {
        for ((index, imageView) in imageViews.withIndex()) {
            val row = index / 7
            val column = index % 7
            imageView.setOnClickListener {
                if (!isProcessingClick) {
                    isProcessingClick = true

                    handleClick(row, column)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isProcessingClick = false
                    }, 100)
                }
            }
        }
    }

    private fun handleClick(row: Int, column: Int) {
        val piece = board.get(row, column)

        if (selectedPiece != null) {
            val availableMoves = selectedPiece!!.availableMoves(board)

            if (availableMoves.contains(Pair(row, column))) {
                selectedPiece!!.move(Pair(row, column), board)
                board.resetCellBackgrounds()
                board.renderPieces()
            } else {
                board.resetCellBackgrounds()
            }
            selectedPiece = null
        }
        else if (piece != null){
            selectedPiece = piece
            val availableMoves = selectedPiece!!.availableMoves(board)

            board.setCellBackgrounds(availableMoves)
        }
        coordinateTextView.text = selectedPiece?.pieceName ?: "Row: $row, Column: $column"
    }
}