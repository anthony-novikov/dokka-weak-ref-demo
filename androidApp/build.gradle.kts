plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = libs.versions.compileAndroidSdk.get().toInt()
    defaultConfig {
        applicationId = "com.example.dokka.weakref.android"
        minSdk = libs.versions.minAndroidSdk.get().toInt()
        targetSdk = libs.versions.targetAndroidSdk.get().toInt()
        versionCode = 1
        versionName = libs.versions.appVersion.get()
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.google.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
}
