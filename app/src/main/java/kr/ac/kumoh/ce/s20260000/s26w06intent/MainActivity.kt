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
            IntentButton(
                text = "유튜브",
                link = "https://www.youtube.com/results?search_query=android+developers"
            )

            IntentButton(
                text = "인스타그램",
                link = "https://www.instagram.com/blackpinkofficial/"
            )

            IntentButton(
                text = "페이스북",
                link = "https://www.facebook.com/bangtan.official"
            )

            IntentButton(
                text = "우리집 좌표",
                link = "geo:36.145014,128.393047?z=17"
            )

            IntentButton(
                text = "우리집 주소",
                link = "geo:0,0?q=국립금오공과대학교"
            )

            IntentButton(
                text = "전화",
                link = "tel:054-478-7114"
            )

            IntentButton(
                text = "문자",
                link = "sms:054-478-7114"
            )
        }
    }
}

@Composable
fun IntentButton(
    modifier: Modifier = Modifier,
    text: String,
    link: String,
) {
    val context = LocalContext.current

    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            val uri = link.toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }
    ) {
        Text(text)
    }
}