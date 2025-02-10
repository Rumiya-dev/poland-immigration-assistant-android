package com.example.propolandladybug.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.propolandladybug.R

// Данные для ChatBot
data class ChatBotState(
    val id: Int,
    @DrawableRes val img: Int,
    val title: String,
    val description: String,
    val timeSlots: List<String>
)

// Функция для получения данных ChatBot с локализацией
@Composable
fun getChatBotData(): List<ChatBotState> {
    return listOf(
        ChatBotState(
            id = 0,
            img = R.drawable.chat_bot,
            title = stringResource(id = R.string.chat_bot_title),  // Используем локализованную строку
            description = stringResource(id = R.string.chat_bot_title),  // Локализованная строка
            timeSlots = listOf("3:30 PM", "6:00 PM", "8:30 PM")
        )
    )
}

// Данные для ResidencePermit
data class ResidencePermitState(
    val id: Int,
    @DrawableRes val img: Int,
    val title: String
)

// Функция для получения данных ResidencePermit с локализацией
@Composable
fun getResidencePermitData(): List<ResidencePermitState> {
    return listOf(
        ResidencePermitState(
            id = 0,
            img = R.drawable.praca,
            title = stringResource(id = R.string.work_title)
        ),
        ResidencePermitState(
            id = 1,
            img = R.drawable.absolwenty,
            title = stringResource(id = R.string.university_title)
        ),
        ResidencePermitState(
            id = 2,
            img = R.drawable.dzialnosc_gospodarcza,
            title = stringResource(id = R.string.business_title)
        ),
        ResidencePermitState(
            id = 3,
            img = R.drawable.rodzina,
            title = stringResource(id = R.string.family_title)
        ),
        ResidencePermitState(
            id = 4,
            img = R.drawable.niebeska_karta,
            title = stringResource(id = R.string.eu_blue_card_title)
        ),
        ResidencePermitState(
            id = 5,
            img = R.drawable.pesel_ukr,
            title = stringResource(id = R.string.pesel_title)
        ),
        ResidencePermitState(
            id = 6,
            img = R.drawable.citizenship,
            title = stringResource(id = R.string.citizenship_title)
        ),
        ResidencePermitState(
            id = 7,
            img = R.drawable.karta_rezidentaeu,
            title = stringResource(id = R.string.eu_residence_card_title)
        )
    )
}

// Функция для получения данных услуг с локализацией
@Composable
fun getOurServicesData(): List<ResidencePermitState> {
    return listOf(
        ResidencePermitState(
            id = 0,
            img = R.drawable.cv,
            title = stringResource(id = R.string.cv_title)
        ),
        ResidencePermitState(
            id = 1,
            img = R.drawable.ksiengowosc,
            title = stringResource(id = R.string.accounting_title)
        ),
        ResidencePermitState(
            id = 2,
            img = R.drawable.job_offers,
            title = stringResource(id = R.string.job_offers_title)
        ),
        ResidencePermitState(
            id = 3,
            img = R.drawable.tlumaczenie,
            title = stringResource(id = R.string.polish_language_learning_title)
        ),
        ResidencePermitState(
            id = 4,
            img = R.drawable.uzupelnienie_dokumentow,
            title = stringResource(id = R.string.help_in_filling_out_documents_title)
        ),
        ResidencePermitState(
            id = 5,
            img = R.drawable.wynajem_mieszkania,
            title = stringResource(id = R.string.rental_housing_title)
        )
    )
}
