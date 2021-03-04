import Libraries.addCommonDependencies

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")

}

dependencies {
    addCommonDependencies()
    api(project(":core-domain"))
}