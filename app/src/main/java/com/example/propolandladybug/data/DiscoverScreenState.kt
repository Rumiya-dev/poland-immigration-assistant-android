package com.example.propolandladybug.data

import com.example.propolandladybug.data.ChatBotState
import com.example.propolandladybug.data.ResidencePermitState


// Обычный data class, без @Composable вызовов
data class DiscoverScreenState(
    val chatBot: List<ChatBotState>,
    val residencePermit: List<ResidencePermitState>,
    val ourServices: List<ResidencePermitState>
)
