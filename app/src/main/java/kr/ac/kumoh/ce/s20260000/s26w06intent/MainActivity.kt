package kr.ac.kumoh.ce.s20260000.s26w06intent

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import kr.ac.kumoh.ce.s20260000.s26w06intent.ui.theme.S26W06IntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S26W06IntentTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            IntentYouTube()
            IntentHomepage()
        }
    }
}

@Composable
fun IntentYouTube(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            val uri = "https://www.youtube.com/results?search_query=android+developers".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }
    ) {
        Text("유튜브")
    }
}

@Composable
fun IntentHomepage(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
//            val uri = "https://www.facebook.com/[본인 계정]".toUri()
            val uri = "https://www.facebook.com/bangtan.official".toUri()
//            val uri = "https://bwoh.github.io".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }
    ) {
        Text("홈 페이지")
    }
}