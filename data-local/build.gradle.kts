import Libraries.addCommonDependencies
import Libraries.addDataDependencies

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
}

dependencies {
    api(project(":core-data"))
    addCommonDependencies()
    implementation(project(":domain"))
    addDataDependencies()
}