package com.example.propolandladybug.data

import androidx.annotation.DrawableRes
import com.example.propolandladybug.R

data class ChatBotState1 (
    val id: Int,
    @DrawableRes val img: Int,
    val title: String = "Chat bot",  // Используйте локализованную строку
    val description: String = "Get instant answers about residence permit requirements, application steps, and necessary documents",  // Локализованная строка
    val timeSlots: List<String>
)

val ChatBotData = listOf(
    ChatBotState(
        id = 0,
        img = R.drawable.chat_bot,
        title = "Chat bot",  // Используем локализованную строку
        description = "Get instant answers about residence permit requirements, application steps, and necessary documents",  // Локализованная строка
        timeSlots = listOf("3:30 PM", "6:00 PM", "8:30 PM")
    ),
)
