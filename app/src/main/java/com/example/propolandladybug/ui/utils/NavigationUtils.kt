package com.example.propolandladybug.ui.utils



import android.content.Context
import android.content.Intent

/**
 * Navigates to the specified activity.
 * @param context The context from which the navigation is initiated.
 * @param activityClass The target activity class.
 */
fun navigateToActivity(context: Context, activityClass: Class<*>) {
    val intent = Intent(context, activityClass) // Создаем Intent для перехода
    context.startActivity(intent) // Запускаем новую активность
}
