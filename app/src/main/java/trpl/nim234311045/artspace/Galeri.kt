package trpl.nim234311045.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trpl.nim234311045.artspace.ui.theme.ArtSpaceTheme

class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryScreen(onBackClick = { finish() })
                }
            }
        }
    }
}

@Composable
fun ArtGalleryScreen(onBackClick: () -> Unit) {
    val images = listOf(
        R.drawable.gambar1,
        R.drawable.gambar2,
        R.drawable.gambar3,
        R.drawable.gambar4
    )
    val titles = listOf(
        "Jogja dengan ceritanya",
        "Ya..",
        "Saat itu",
        "Sore Jogja"
    )
    val artists = listOf(
        "Jalan Malioboro (2024)",
        "Tugu jam Malioboro (2024)",
        "Jl.Ahmad Dahlan (2024)",
        "Trotoar Malioboro (2024)"
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    fun nextImage() {
        if (currentIndex < images.size - 1) {
            currentIndex++
        }
    }

    fun previousImage() {
        if (currentIndex > 0) {
            currentIndex--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // tombol balek panah
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali ke Beranda"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp)
//                .border(4.dp, Color.Gray, RoundedCornerShape(8.dp))

                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = titles[currentIndex],
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = artists[currentIndex],
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // kanan kiri
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { previousImage() }, enabled = currentIndex > 0) {
                Text("Previous")
            }
            Button(onClick = { nextImage() }, enabled = currentIndex < images.size - 1) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryScreenPreview() {
    ArtSpaceTheme {
        ArtGalleryScreen(onBackClick = {})
    }
}
