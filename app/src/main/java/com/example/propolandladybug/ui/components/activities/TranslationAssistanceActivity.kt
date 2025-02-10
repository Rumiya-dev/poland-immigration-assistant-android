
package com.example.propolandladybug.ui.components.activities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource
import com.example.propolandladybug.ui.theme.MovieTheaterTheme

class TranslationAssistanceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TranslationAssistancePage(onBackClick = { finish() })
        }
    }
}

@Composable
fun TranslationAssistancePage(onBackClick: () -> Unit) {
    // Состояние для уровня языка
    var languageLevel by remember { mutableStateOf("") }
    // Состояние для текущего вопроса
    var currentQuestionIndex by remember { mutableStateOf(0) }
    // Состояние для ответа
    val answerState = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
////
    // Вопросы для каждого уровня
    val questionsByLevel = mapOf(
        "A1" to listOf("__________ mam na imię Jan.", "__________ uczysz się języka polskiego?", "Ona __________ w Warszawie."),
        "A2" to listOf("__________ mam 20 lat.", "My __________ w szkole.", "__________ idziesz do sklepu?"),
        "B1" to listOf("On __________ książkę codziennie.", "__________ wczoraj do pracy?", "Chciałbym, __________ do kina z moimi przyjaciółmi."),
        "B2" to listOf("Gdybyś __________ więcej czasu, pojechałbyś na wakacje.", "__________ on się spóźnił, poszliśmy do kina bez niego.", "Po całym dniu pracy __________, że zasługuję na odpoczynek."),
        "C1" to listOf("Byłoby lepiej, gdybyśmy __________ wcześniej o tym porozmawiali.", "Poza tym, nie jestem pewien, czy __________ wasze decyzje.", "__________ nie byłoby, gdybyście nie pomogli.")
    )

    // Текущий вопрос в зависимости от выбранного уровня
    val currentQuestion = questionsByLevel[languageLevel]?.getOrNull(currentQuestionIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Кнопка "Назад"
        TopBarForTranslation(onBackClick)

        if (languageLevel.isEmpty()) {
            // Вопрос о уровне языка
            Text("Na jakim poziomie znasz język polski?")
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Button(onClick = { languageLevel = "A1" }) {
                    Text("A1")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { languageLevel = "A2" }) {
                    Text("A2")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { languageLevel = "B1" }) {
                    Text("B1")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { languageLevel = "B2" }) {
                    Text("B2")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { languageLevel = "C1" }) {
                    Text("C1")
                }
            }
        } else if (currentQuestion != null) {
            // Отображаем вопрос для выбранного уровня
            Text(text = "Pytanie: $currentQuestion")
            TextField(
                value = answerState.value,
                onValueChange = { answerState.value = it },
                label = { Text("Odpowiedź") },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )

            Row(modifier = Modifier.padding(top = 16.dp)) {
                Button(onClick = {
                    // Проверка ответа пользователя
                    checkAnswer(answerState.value, currentQuestion, languageLevel, context)
                    // Переход к следующему вопросу
                    if (currentQuestionIndex < (questionsByLevel[languageLevel]?.size ?: 0) - 1) {
                        currentQuestionIndex++
                    } else {
                        showToast("Gratulacje! Zakończyłeś test.", context)
                    }
                }) {
                    Text("Wyślij odpowiedź")
                }
            }
        } else {
            Text("Brak pytań do wyświetlenia.")
        }
    }
}

// Функция для проверки ответа
fun checkAnswer(answer: String, question: String, level: String, context: android.content.Context) {
    val correctAnswers = mapOf(
        "A1" to mapOf(
            "__________ mam na imię Jan." to "Ja",
            "__________ uczysz się języka polskiego?" to "Jak",
            "Ona __________ w Warszawie." to "mieszka"
        ),
        "A2" to mapOf(
            "__________ mam 20 lat." to "Ja",
            "My __________ w szkole." to "pracujemy",
            "__________ idziesz do sklepu?" to "Dlaczego"
        ),
        "B1" to mapOf(
            "On __________ książkę codziennie." to "czyta",
            "__________ wczoraj do pracy?" to "Byłeś",
            "Chciałbym, __________ do kina z moimi przyjaciółmi." to "Iść"
        ),
        "B2" to mapOf(
            "Gdybyś __________ więcej czasu, pojechałbyś na wakacje." to "miał",
            "__________ on się spóźnił, poszliśmy do kina bez niego." to "Dlatego",
            "Po całym dniu pracy __________, że zasługuję na odpoczynek." to "czuję się"
        ),
        "C1" to mapOf(
            "JByłoby lepiej, gdybyśmy __________ wcześniej o tym porozmawiali." to "rozmawiali",
            "Poza tym, nie jestem pewien, czy __________ wasze decyzje." to "popieram",
            "__________ nie byłoby, gdybyście nie pomogli." to "Nic"
        )
    )

    val correctAnswer = correctAnswers[level]?.get(question)
    if (answer.equals(correctAnswer, ignoreCase = true)) {
        showToast("Dobrze! Odpowiedź poprawna.", context)
    } else {
        showToast("Odpowiedź niepoprawna. Spróbuj ponownie.", context)
    }
}

// Функция для отображения Toast-сообщений
fun showToast(message: String, context: android.content.Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

// Верхний бар с кнопкой "Назад" для страницы перевода
@Composable
fun TopBarForTranslation(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(onClick = onBackClick) {
            Icon(painter = painterResource(id = com.example.propolandladybug.R.drawable.back_icon), contentDescription = "Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTranslationAssistancePage() {
    TranslationAssistancePage(onBackClick = { })
}
