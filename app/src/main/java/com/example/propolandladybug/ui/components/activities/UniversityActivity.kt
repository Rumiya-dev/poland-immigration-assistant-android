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

class UniversityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheaterTheme {
                UniversityScreen(
                    onBackClick = { finish() } // Finish activity to go back
                )
            }
        }
    }
}

@Composable
fun UniversityScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current  // Get context for functions

    Scaffold(
        topBar = { UniversityTopBar(onBackClick) },
        bottomBar = { UniversityStickyBottomButtons(context) }
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
                // Buttons below header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {


                }

                // Text in the center
                Text(
                    text = stringResource(id = R.string.university_instructions), // Load text based on language
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.university_instructions_title), fontSize = 18.sp) }, // Title text
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon), // Back icon
                    contentDescription = "Back"
                )
            }
        },
        modifier = Modifier.height(56.dp)
    )
}

@Composable
fun UniversityStickyBottomButtons(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { openWhatsAppUniversity(context) },
            modifier = Modifier.weight(1f).padding(end = 8.dp)
        ) {
            Text(stringResource(id = R.string.contacts)) // "Контакты" for Russian or "Contact Us" for English
        }

        Button(
            onClick = { downloadPDFUniversity(context) },  // Download PDF
            modifier = Modifier.weight(1f).padding(start = 8.dp)
        ) {
            Text(stringResource(id = R.string.download_pdf_university)) // "Скачать PDF" for Russian or "Download PDF" for English
        }
    }
}

// Open WhatsApp for university
fun openWhatsAppUniversity(context: Context) {
    val phoneNumber = "+1234567890" // Phone number to send the message
    val message = "Hello, I need to apply for university documents."
    val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)  // Use context to start the activity
}

// Open university instruction PDF


// Download PDF for university documents
fun downloadPDFUniversity(context: Context) {
    val pdfUrl = "https://example.com/university_documents.pdf" // PDF link
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pdfUrl))
    context.startActivity(intent) // Use context to start the activity
}
