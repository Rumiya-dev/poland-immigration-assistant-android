package com.example.propolandladybug.ui.components.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.propolandladybug.R

import com.example.propolandladybug.data.ResidencePermitState

import com.example.propolandladybug.ui.components.ResidencePermit
import com.example.propolandladybug.ui.components.SectionHeader
import com.example.propolandladybug.ui.components.activities.BlueCardActivity
import com.example.propolandladybug.ui.components.activities.BusinessActivity
import com.example.propolandladybug.ui.components.activities.CitizenshipActivity
import com.example.propolandladybug.ui.components.activities.EUResidenceActivity
import com.example.propolandladybug.ui.components.activities.FamilyActivity
import com.example.propolandladybug.ui.components.activities.PeselActivity
import com.example.propolandladybug.ui.components.activities.UniversityActivity
import com.example.propolandladybug.ui.components.activities.WorkActivity

import com.example.propolandladybug.ui.utils.navigateToActivity



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ResidencePermitSection(
    modifier: Modifier = Modifier,
    data: List<ResidencePermitState>,
    onNavigateToActivity: (Class<*>) -> Unit
) {
    val buttonTexts = mapOf(
        0 to stringResource(id = R.string.work_title),
        1 to stringResource(id = R.string.university_title),
        2 to stringResource(id = R.string.business_title),
        3 to stringResource(id = R.string.family_title),
        4 to stringResource(id = R.string.eu_blue_card_title),
        5 to stringResource(id = R.string.pesel_title),
        6 to stringResource(id = R.string.citizenship_title),
        7 to stringResource(id = R.string.eu_residence_card_title)
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(
            text = stringResource(id = R.string.residence_permit_section_title)  // Локализованная строка
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            maxItemsInEachRow = 2
        ) {
            data.forEach { thumbnail ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    ResidencePermit(
                        img = thumbnail.img,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            when (thumbnail.id) {
                                0 -> onNavigateToActivity(WorkActivity::class.java)
                                1 -> onNavigateToActivity(UniversityActivity::class.java)
                                2 -> onNavigateToActivity(BusinessActivity::class.java)
                                3 -> onNavigateToActivity(FamilyActivity::class.java)
                                4 -> onNavigateToActivity(BlueCardActivity::class.java)
                                5 -> onNavigateToActivity(PeselActivity::class.java)
                                6 -> onNavigateToActivity(CitizenshipActivity::class.java)
                                7 -> onNavigateToActivity(EUResidenceActivity::class.java)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = buttonTexts[thumbnail.id] ?: stringResource(id = R.string.default_button_text))  // Локализованная строка для кнопки
                    }
                }
            }
        }
    }
}
