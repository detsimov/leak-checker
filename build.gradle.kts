// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        Plugin.array.forEach { classpath(it) }

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts.kts.kts.kts.kts.kts files
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { setUrl("https://jitpack.io") }
    }
}


subprojects {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java)
        .configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    plugins.withType<com.android.build.gradle.internal.plugins.BasePlugin<*,*>> {
        configure<com.android.build.gradle.BaseExtension> {
            compileSdkVersion(AndroidSdk.compile)
            defaultConfig {
                minSdkVersion(AndroidSdk.min)
                targetSdkVersion(AndroidSdk.target)
                versionCode = App.versionCode
                versionName = App.versionName
                buildConfigField("int", "VERSION_CODE", App.versionCode.toString())
                buildConfigField("String", "VERSION_NAME", "\"${App.versionName}\"")
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                targetCompatibility = JavaVersion.VERSION_1_8
                sourceCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }
}