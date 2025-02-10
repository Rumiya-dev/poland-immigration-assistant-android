plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.propolandladybug"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.propolandladybug"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //AndroidX
    implementation(libs.bundles.androidX)
    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    debugImplementation(libs.compose.tooling)
    implementation(libs.bundles.ui)


    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    val nav_version = "2.8.4"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    // OkHttp for network requests
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("org.json:json:20210307")
    implementation("androidx.compose.ui:ui:1.4.0") // Ensure Compose dependencies are up to date
    implementation("androidx.compose.material3:material3:1.0.0-alpha01")
    // Additional dependencies

    implementation ("com.google.android.material:material:1.6.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit library
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter for Retrofit
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0") // OkHttp Logging Interceptor
    implementation ("androidx.security:security-crypto:1.0.0")
}