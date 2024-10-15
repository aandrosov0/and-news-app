plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.datastore.preferences)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}