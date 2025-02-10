package com.example.propolandladybug.data


import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.propolandladybug.R

data class ResidencePermitState1(
    val id: Int,
    @DrawableRes val img: Int,
    val title: String,
)

@Composable


fun getResidencePermitData1(): List<ResidencePermitState> {
    return listOf(
        ResidencePermitState(
            id = 0,
            img = R.drawable.praca,
            title = stringResource(id = R.string.work_title)  // Локализованная строка
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