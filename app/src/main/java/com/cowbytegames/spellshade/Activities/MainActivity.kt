package com.cowbytegames.spellshade.Activities

import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.cowbytegames.spellshade.R
import com.cowbytegames.spellshade.ui.theme.SpellshadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        renderLayout()
    }

    private fun renderLayout() {
        setContentView(R.layout.activity_main)
        renderBoard()
        renderPieces()
    }

    private fun renderBoard() {
        val boardView = findViewById<View>(R.id.board)
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

            // Reset any previous constraints
            constraintSet.clear(boardView.id, ConstraintSet.TOP)
            constraintSet.clear(boardView.id, ConstraintSet.START)
            constraintSet.clear(boardView.id, ConstraintSet.END)
            constraintSet.clear(boardView.id, ConstraintSet.BOTTOM)

            // Set constraints based on the orientation
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Portrait mode: Position at the top of the screen, centered horizontally
                constraintSet.connect(boardView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, marginInPx)
                constraintSet.connect(boardView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, marginInPx)
                constraintSet.connect(boardView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, marginInPx)
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Landscape mode: Position at the left of the screen, centered vertically
                constraintSet.connect(boardView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, marginInPx)
                constraintSet.connect(boardView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, marginInPx)
                constraintSet.connect(boardView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, marginInPx)
            }

            constraintSet.applyTo(constraintLayout)
        }
    }

    private fun renderPieces() {

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpellshadeTheme {
        Greeting("Android")
    }
}