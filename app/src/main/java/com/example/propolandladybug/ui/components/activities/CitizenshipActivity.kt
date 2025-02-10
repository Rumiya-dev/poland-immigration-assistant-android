package com.example.propolandladybug.ui.components.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.propolandladybug.R
import com.example.propolandladybug.ui.theme.MovieTheaterTheme

class CitizenshipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheaterTheme {
                CitizenshipScreen(
                    onBackClick = { finish() } // Завершение активности для возврата назад
                )
            }
        }
    }
}

@Composable
fun CitizenshipScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current  // Получаем контекст, чтобы использовать его в функциях

    Scaffold(
        topBar = { CitizenshipTopBar(onBackClick) },
        bottomBar = { CitizenshipStickyBottomButtons(context) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Текст в центре
                Text(
                    text = stringResource(id = R.string.citizenship_instructions),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitizenshipTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.citizenship_instructions_title), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon), // Иконка "Назад"
                    contentDescription = "Назад"
                )
            }
        },
        modifier = Modifier.height(56.dp)
    )
}

@Composable
fun CitizenshipStickyBottomButtons(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Кнопка "Контакты"
        Button(
            onClick = { openWhatsAppCitizenship(context) },
            modifier = Modifier.weight(1f).padding(end = 8.dp)
        ) {
            Text(stringResource(id = R.string.contact_button))  // Локализованная кнопка
        }

        // Кнопка "Скачать PDF"
        Button(
            onClick = { downloadPDFCitizenship(context) },  // Скачивание PDF
            modifier = Modifier.weight(1f).padding(start = 8.dp)
        ) {
            Text(stringResource(id = R.string.download_pdf_button))  // Локализованная кнопка
        }
    }
}

// Открытие WhatsApp для гражданства
fun openWhatsAppCitizenship(context: Context) {
    val phoneNumber = "+1234567890" // Номер телефона, на который будет отправлено сообщение
    val message = "Здравствуйте, мне нужно оформить гражданство."
    val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)  // Используем контекст для старта активности
}

// Скачивание PDF для гражданства
fun downloadPDFCitizenship(context: Context) {
    val pdfUrl = "https://example.com/citizenship_instructions.pdf" // Ссылка на файл PDF
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pdfUrl))
    context.startActivity(intent) // Используем контекст для старта активности
}
