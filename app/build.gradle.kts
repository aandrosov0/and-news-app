plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.dvx.news"

    compileSdk = 35

    defaultConfig {
        applicationId = "aandrosov.and.news"

        minSdk = 26
        targetSdk = 35

        versionCode = 2
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
        }
    }
}

dependencies {
    implementation(project(":data"))

    implementation(libs.androidx.core.splashcreen)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)

    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    implementation(libs.activity.compose)

    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)

    implementation(libs.datastore.preferences)

    implementation(libs.kotlinx.serialization.core)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}