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
    namespace = "dvx.news.app"

    compileSdk = 34

    defaultConfig {
        applicationId = "dvx.news.app"

        minSdk = 24
        targetSdk = 35

        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt")
            )
        }
    }
}

dependencies {
    implementation(project(":data"))

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)

    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    implementation(libs.activity.ktx)
    implementation(libs.activity.compose)

    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)

    implementation(libs.datastore.preferences)

    implementation(libs.kotlinx.serialization.core)
}