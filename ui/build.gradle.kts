import Libraries.addAndroidDebugDependencies
import Libraries.addCommonDependencies
import Libraries.addFirebase

plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    addCommonDependencies()
    addFirebase()
    addAndroidDebugDependencies()
    implementation(project(":domain"))
    implementation(project(":data-local"))
    api(project(":core-ui"))
}