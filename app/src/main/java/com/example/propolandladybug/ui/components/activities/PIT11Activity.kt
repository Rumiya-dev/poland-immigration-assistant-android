package com.example.propolandladybug.ui.components.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.propolandladybug.R

class PIT11Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PIT11Page()
        }
    }
}

@Composable
fun PIT11Page() {
    val scrollState = rememberScrollState() // Для скролинга текста
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ksiengowosc), // Замените на ваше изображение
                contentDescription = "PIT11 Image",
                modifier = Modifier.fillMaxWidth().height(250.dp)
            )

            IconButton(
                onClick = {
                    (context as? PIT11Activity)?.onBackPressed() // Обработка кнопки назад
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(16.dp)) {

            Text(
                text = stringResource(id = R.string.pit11_description),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // WhatsApp Button
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/1234567890"))
                context.startActivity(intent)
            }) {
                Text(stringResource(id = R.string.whatsapp_button))
            }

            // Facebook Button
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/yourpageid"))
                context.startActivity(intent)
            }) {
                Text(stringResource(id = R.string.facebook_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPIT11Page() {
    PIT11Page()
}
