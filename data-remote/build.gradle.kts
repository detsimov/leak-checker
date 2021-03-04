import Libraries.addCommonDependencies
import Libraries.addDataDependencies

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlinx-serialization")
}

dependencies {
    api(project(":core-data"))
    addCommonDependencies()
    implementation(project(":domain"))
    addDataDependencies()
}