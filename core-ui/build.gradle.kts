import Libraries.addCommonDependencies
import Libraries.androidDependencies

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}


android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    androidDependencies.forEach { api(it) }
    addCommonDependencies()
}