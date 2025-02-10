package com.example.propolandladybug.ui.components.section

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.propolandladybug.R

import com.example.propolandladybug.data.ChatBotState
import com.example.propolandladybug.ui.components.ChatActivity
import com.example.propolandladybug.ui.components.SectionHeader

@Composable
fun ChatBotSection(
    modifier: Modifier = Modifier,
    data: List<ChatBotState>
) {
    Column(
        modifier = modifier.padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SectionHeader(
            text = stringResource(id = R.string.chat_bot_title)  // Используем локализованную строку
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = data,
                key = { it.id }
            ) { chatBot ->
                ChatBot(
                    item = chatBot
                )
            }
        }
    }
}

@Composable
private fun ChatBot(
    modifier: Modifier = Modifier,
    item: ChatBotState
) {
    val context = LocalContext.current // Получаем контекст, это допустимо только внутри Composable функции

    Column(
        modifier = modifier.width(224.dp)
    ) {
        // Иконка
        Image(
            painter = painterResource(item.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(324.dp)
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        // Название
        Text(
            text = item.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        // Описание
        Text(
            text = item.description,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(
            modifier = Modifier.height(18.dp)
        )
        // Кнопка вместо временных слотов
        androidx.compose.material3.Button(
            onClick = {
                // Логика для кнопки
                val intent = Intent(context, ChatActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(stringResource(id = R.string.default_button_text)) // Используем локализованную строку
        }
    }
}
