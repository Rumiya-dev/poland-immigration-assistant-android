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

class PeselActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheaterTheme {
                PeselScreen(
                    onBackClick = { finish() } // Завершение активности для возврата назад
                )
            }
        }
    }
}

@Composable
fun PeselScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        topBar = { PeselTopBar(onBackClick) },
        bottomBar = { PeselStickyBottomButtons(context) }
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
                    text = stringResource(id = R.string.pesel_instruction_text),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeselTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.pesel_instruction_title), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon), // Иконка "Назад"
                    contentDescription = "Back"
                )
            }
        },
        modifier = Modifier.height(56.dp)
    )
}

@Composable
fun PeselStickyBottomButtons(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { openWhatsAppPesel(context) },
            modifier = Modifier.weight(1f).padding(end = 8.dp)
        ) {
            Text(stringResource(id = R.string.contact_button))
        }
        Button(
            onClick = { downloadPDFPesel(context) },  // Скачивание PDF
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
        ) {
            Text(stringResource(id = R.string.download_pdf_button))
        }
    }
}

// Открытие WhatsApp для PESEL
fun openWhatsAppPesel(context: Context) {
    val phoneNumber = "+1234567890" // Номер телефона, на который будет отправлено сообщение
    val message = "Здравствуйте, мне нужно оформить PESEL."
    val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)  // Используем контекст для старта активности
}

// Скачивание PDF для PESEL
fun downloadPDFPesel(context: Context) {
    val pdfUrl = "https://example.com/pesel_instructions.pdf" // Ссылка на файл PDF
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pdfUrl))
    context.startActivity(intent) // Используем контекст для старта активности
}
