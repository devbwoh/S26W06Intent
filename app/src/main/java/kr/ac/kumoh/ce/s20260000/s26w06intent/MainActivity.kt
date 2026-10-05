package kr.ac.kumoh.ce.s20260000.s26w06intent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
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
    val intentItems: List<Pair<String, String>> = listOf(
        "유튜브" to "https://www.youtube.com/results?search_query=android+developers",
        "인스타그램" to "https://www.instagram.com/blackpinkofficial/",
        "페이스북" to "https://www.facebook.com/bangtan.official",
        "우리집 좌표" to "geo:36.145014,128.393047?z=17",
        "우리집 주소" to "geo:0,0?q=국립금오공과대학교",
        "전화" to "tel:054-478-7114",
        "문자" to "sms:054-478-7114",
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                CameraButton()
            }

            items(intentItems) { item ->
                IntentButton(item = item)
            }
        }
    }
}

@Composable
fun IntentButton(
    modifier: Modifier = Modifier,
    item: Pair<String, String>,
) {
    val context = LocalContext.current
    val (text, link) = item

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

@Composable
fun CameraButton() {
    var imageBitmap by rememberSaveable { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            imageBitmap = bitmap
        }
    )

    // 권한 요청 Launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                cameraLauncher.launch(null)
            } else {
                Toast.makeText(
                    context,
                    "카메라 권한 거부됨",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    )

    val launchCamera = {
        val permissionCheckResult = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        )

        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(null)
        } else { // 권한이 없다면 권한 요청 런처 실행
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = launchCamera,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("카메라")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // imageBitmap이 null이 아니면 실행
        imageBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "카메라 촬영 이미지",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray),
                //contentScale = ContentScale.Crop
            )
        }
    }
}