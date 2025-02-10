

package com.example.propolandladybug.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.propolandladybug.data.DiscoverScreenState

import com.example.propolandladybug.ui.components.section.ResidencePermitSection
import com.example.propolandladybug.ui.components.section.ChatBotSection
import com.example.propolandladybug.ui.components.section.HorizontalSection


@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    screenState: DiscoverScreenState,
    onNavigateToActivity: (Class<*>) -> Unit // Добавляем параметр для навигации
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        ChatBotSection(data = screenState.chatBot)

        // Передаем onNavigateToActivity в компоненты
        ResidencePermitSection(
            data = screenState.residencePermit,
            modifier = Modifier.padding(horizontal = 18.dp),
            onNavigateToActivity = onNavigateToActivity // Передача навигации
        )

        // Добавляем секцию "Our services"
        HorizontalSection(
            data = screenState.ourServices,
            name = "Our services",
            onNavigateToActivity = onNavigateToActivity // Передаем параметр навигации
        )

        // Другие секции
        /*HorizontalSection(
            data = screenState.polishLanguageTraining,
            name = "Polish language training",
            onNavigateToActivity = onNavigateToActivity // Передаем навигацию*/

    }
}
