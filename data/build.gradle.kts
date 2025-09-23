plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(17)
}

android {
    compileSdk = 35
    namespace = "dvx.news.data"

    defaultConfig {
        minSdk = 26
    }
}

dependencies {
    implementation(project(":dvxnews-api"))
    implementation(libs.datastore.preferences)

    implementation(libs.kotlinx.serialization.json)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)

    implementation(libs.appwrite.android.sdk)

    implementation(libs.commonmark)
}