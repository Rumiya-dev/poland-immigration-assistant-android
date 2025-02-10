package com.example.propolandladybug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.propolandladybug.data.DiscoverScreenState
import com.example.propolandladybug.ui.screen.DiscoverScreen
import com.example.propolandladybug.ui.theme.MovieTheaterTheme
import com.example.propolandladybug.ui.utils.navigateToActivity // Импорт функции из NavigationUtils.kt
import com.example.propolandladybug.data.getChatBotData
import com.example.propolandladybug.data.getResidencePermitData
import com.example.propolandladybug.data.getOurServicesData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Включаем поддержку Edge-to-Edge

        setContent {
            MovieTheaterTheme { // Применяем тему
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    // Отображаем DiscoverScreen и передаём обработчик навигации
                    DiscoverScreen(
                        modifier = Modifier.padding(innerPadding),
                        screenState = DiscoverScreenState(
                            chatBot = getChatBotData(),
                            residencePermit = getResidencePermitData(),
                            ourServices = getOurServicesData()
                        ),
                        onNavigateToActivity = { targetActivity ->
                            // Используем функцию navigateToActivity для перехода
                            navigateToActivity(this, targetActivity)
                        }
                    )
                }
            }
        }
    }
}
