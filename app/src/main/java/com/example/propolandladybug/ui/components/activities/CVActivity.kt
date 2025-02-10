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

class CVActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CVPage()
        }
    }
}

@Composable
fun CVPage() {
    val scrollState = rememberScrollState() // Скроллинг текста
    val context = LocalContext.current // Контекст для Intent

    // Главный контейнер с фиксированными кнопками внизу
    Box(modifier = Modifier.fillMaxSize()) {
        // Прокручиваемая часть (текст и изображение)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 80.dp), // Учитываем место для кнопок
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Верхнее изображение
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cv), // Укажите реальное изображение
                    contentDescription = "CV Image",
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                )

                // Кнопка "Назад"
                IconButton(
                    onClick = { (context as? CVActivity)?.onBackPressed() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }

            // Описание сервиса
            Text(
                text = stringResource(id = R.string.cv_description),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
        }

        // Фиксированные кнопки внизу экрана
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/1234567890"))
                context.startActivity(intent)
            }) {
                Text(stringResource(id = R.string.whatsapp_button))  // Локализованная кнопка
            }

            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/yourpageid"))
                context.startActivity(intent)
            }) {
                Text(stringResource(id = R.string.facebook_button))  // Локализованная кнопка
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCVPage() {
    CVPage()
}
