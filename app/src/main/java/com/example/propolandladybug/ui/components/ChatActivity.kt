package com.example.propolandladybug.ui.components

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import okhttp3.Interceptor
import okhttp3.Response as OkHttpResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.propolandladybug.R
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

// 1. Retrofit service interface
interface ChatGptApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    fun sendMessage(@Body message: RequestBody): Call<ChatGptResponse>
}

// 2. Модель ответа от ChatGPT (data classes)
data class ChatGptResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)

data class Message(
    val role: String,
    val content: String
)

// 3. Main Activity
class ChatActivity : AppCompatActivity() {

    private lateinit var apiService: ChatGptApiService
    private lateinit var editTextUserInput: EditText
    private lateinit var textViewChat: TextView
    private lateinit var buttonSend: Button

    // Добавляем переменную для кнопки "Назад"
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(R.layout.activity_chat)

        // Находим все View по их ID
        editTextUserInput = findViewById(R.id.editText)
        textViewChat = findViewById(R.id.textView)
        buttonSend = findViewById(R.id.button)
        buttonBack = findViewById(R.id.buttonBack)

        // Код сохранения/восстановления текста
        if (savedInstanceState != null) {
            val editTextContent = savedInstanceState.getString("EditTextContent")
            editTextUserInput.setText(editTextContent)
        }

        // Инициализируем ключ (если нужно)
        initializeApiKey()

        // Настраиваем Retrofit и т.д.
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(this))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ChatGptApiService::class.java)

        // Обработчик нажатия кнопки "Submit"
        buttonSend.setOnClickListener {
            sendMessageToGpt()
        }

        // Обработчик нажатия кнопки "Назад"
        buttonBack.setOnClickListener {
            finish()
        }

        // Translate UI text
        val helpText = resources.getString(R.string.how_can_i_help)
        val submitButtonText = resources.getString(R.string.submit_button)
        val backButtonText = resources.getString(R.string.back_button)

        // Set translated texts for buttons
        buttonSend.text = submitButtonText
        buttonBack.text = backButtonText

        // Set translated text for instruction
        textViewChat.text = helpText
    }

    // Вынесли код отправки сообщения в отдельный метод
    private fun sendMessageToGpt() {
        val userInput = editTextUserInput.text.toString()
        Log.d("MyAppError", "User input: $userInput")

        val json = """
            {
                "model": "gpt-3.5-turbo",
                "messages": [{"role": "user", "content": "$userInput"}],
                "temperature": 0.7
            }
        """.trimIndent()

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(mediaType)

        val call = apiService.sendMessage(requestBody)
        call.enqueue(object : Callback<ChatGptResponse> {
            override fun onResponse(
                call: Call<ChatGptResponse>,
                response: Response<ChatGptResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.choices.isNotEmpty()) {
                        val assistantResponse = responseBody.choices.first {
                            it.message.role == "assistant"
                        }.message.content
                        textViewChat.text = assistantResponse
                    } else {
                        Log.d("MyAppError", "Response body is null or choices is empty")
                        Toast.makeText(
                            this@ChatActivity,
                            "Received empty response",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("MyAppError", "Response error code: ${response.code()}")
                    Log.e("MyAppError", "Response error body: $errorBody")

                    Toast.makeText(
                        this@ChatActivity,
                        "Response error",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ChatGptResponse>, t: Throwable) {
                Log.e("MyAppError", "Call failed with error: ${t.message}")
                Toast.makeText(
                    this@ChatActivity,
                    "Failed to send message: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        // Очищаем поле ввода
        editTextUserInput.text.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("EditTextContent", editTextUserInput.text.toString())
    }

    private fun initializeApiKey() {
        val sharedPreferences = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        if (sharedPreferences.getString("apiKey", null) == null) {
            sharedPreferences.edit().apply {
                putString("apiKey", "YOUR_OPENAI_API_KEY_HERE\n" +
                        "\n") // Use your actual API key
                apply()
            }
        }
    }
}

class AuthInterceptor(context: Context) : Interceptor {
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun intercept(chain: Interceptor.Chain): OkHttpResponse {
        val apiKey = sharedPreferences.getString("apiKey", null) ?: ""
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        return chain.proceed(newRequest)
    }
}
