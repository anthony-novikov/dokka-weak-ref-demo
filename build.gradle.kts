buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.gradlePlugin.kotlin)
        classpath(libs.gradlePlugin.android)
        classpath(libs.gradlePlugin.dokka)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

ext {
    set("mavenUsername", System.getenv("USERNAME_PUBLISHER"))
    set("mavenPassword", System.getenv("PASSWORD_PUBLISHER"))
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
