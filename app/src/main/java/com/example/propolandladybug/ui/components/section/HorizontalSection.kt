package com.example.propolandladybug.ui.components.section

//import TranslationAssistanceActivity
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.propolandladybug.data.ResidencePermitState
import com.example.propolandladybug.ui.components.ResidencePermit
import com.example.propolandladybug.ui.components.SectionHeader
import com.example.propolandladybug.ui.components.activities.*  // Импортируем все активности

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalSection(
    modifier: Modifier = Modifier,
    data: List<ResidencePermitState>,
    name: String,
    onNavigateToActivity: (Class<*>) -> Unit // Принимаем функцию для навигации
) {
    val pagerState = rememberPagerState(pageCount = { data.size })

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(
            text = name,
            modifier = Modifier.padding(horizontal = 18.dp)
        )

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 18.dp),
            pageSpacing = 18.dp,
            // Убираем SinglePageViewport и используем стандартную настройку
            modifier = Modifier.fillMaxWidth()
        ) { pageIndex ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Внутренние отступы
                horizontalAlignment = Alignment.CenterHorizontally // Центрирование содержимого
            ) {
                // Иконка
                ResidencePermit(
                    img = data[pageIndex].img,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                // Кнопка с названием услуги
                androidx.compose.material3.Button(
                    onClick = {
                        // Переход к соответствующей активности в зависимости от ID
                        when (data[pageIndex].id) {
                            0 -> onNavigateToActivity(CVActivity::class.java)
                            1 -> onNavigateToActivity(PIT11Activity::class.java)
                            2 -> onNavigateToActivity(JobOffersActivity::class.java)

                            4 -> onNavigateToActivity(HelpInFillingOutDocumentsActivity::class.java)
                            5 -> onNavigateToActivity(RentalHousingActivity::class.java)

                            3 -> onNavigateToActivity(TranslationAssistanceActivity::class.java)



                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    // Используем название секции услуги на кнопке
                    Text(text = data[pageIndex].title) // data[pageIndex].title содержит название услуги
                }
            }
        }
    }
}
