import Libraries.addCommonDependencies

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}


android {
    defaultConfig {
        applicationId = "com.detsimov.leakchecker"
        versionCode = App.versionCode
        versionName = App.versionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    applicationVariants.all {
        outputs.forEach { output ->
            if (output is com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
                output.outputFileName = "${applicationId}.${versionName}-(${versionCode})-${buildType.name}.${output.outputFile.extension}"
            }
        }
    }

    buildToolsVersion = "30.0.3"
}

dependencies {
    addCommonDependencies()
    implementation(project(":data-local"))
    implementation(project(":domain"))
    implementation(project(":ui"))

}