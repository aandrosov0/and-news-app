plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(project(":dvxnews-api"))
    implementation(libs.datastore.preferences)

    implementation(libs.kotlinx.serialization.json)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
}