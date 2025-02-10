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

class WorkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheaterTheme {
                WorkScreen(
                    onBackClick = { finish() } // Завершение активности для возврата
                )
            }
        }
    }
}

@Composable
fun WorkScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current // Get context for functions

    Scaffold(
        topBar = { TopBar(onBackClick) },
        bottomBar = { StickyBottomButtons(context) }
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
                // Main text in center
                Text(
                    text = stringResource(id = R.string.work_instructions), // Dynamically translated text
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.work_instructions), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back"
                )
            }
        },
        modifier = Modifier.height(56.dp)
    )
}

@Composable
fun StickyBottomButtons(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { openWhatsApp(context) },
            modifier = Modifier.weight(1f).padding(end = 8.dp)
        ) {
            Text(stringResource(id = R.string.contact_button)) // Dynamically translated text
        }

        Button(
            onClick = { downloadPDF(context) },
            modifier = Modifier.weight(1f).padding(start = 8.dp)
        ) {
            Text(stringResource(id = R.string.download_pdf_button)) // Dynamically translated text
        }
    }
}

// Open WhatsApp
fun openWhatsApp(context: Context) {
    val phoneNumber = "+1234567890" // Phone number to send the message
    val message = "Hello, I need assistance with work residence permit."
    val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)  // Use context to start the activity
}

// Download PDF
fun downloadPDF(context: Context) {
    val pdfUrl = "https://example.com/work_permit_instructions.pdf" // PDF link
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pdfUrl))
    context.startActivity(intent) // Use context to start the activity
}
