package com.cowbytegames.spellshade

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cowbytegames.spellshade.ui.theme.SpellshadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boardView = findViewById<View>(R.id.board)
        val rootView = findViewById<ViewGroup>(R.id.root_view) // Use the ID of the parent layout

        rootView.post {
            val width = rootView.width
            val height = rootView.height
            val size = minOf(width, height)

            val layoutParams = boardView.layoutParams
            layoutParams.width = size
            layoutParams.height = size
            boardView.layoutParams = layoutParams
        }
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