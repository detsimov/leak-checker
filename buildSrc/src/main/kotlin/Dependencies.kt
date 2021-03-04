import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.4.20"

object App {
    const val versionName = "0.5"
    const val versionCode = 1
}

object AndroidSdk {
    const val min = 23
    const val compile = 29
    const val target = compile
}

object Plugin {

    object Versions {
        const val buildGradle = "7.0.0-alpha08"
        const val sqlDelightGradle = "1.4.4"
        const val firebaseCrashlyticsGradle = "2.5.0"
        const val gmsPlayServicesAdsGradle = "19.7.0"
        const val gmsPlayServicesGradle = "4.3.5"
    }


    private const val parcelizeGradle = "org.jetbrains.kotlin:kotlin-parcelize-runtime:$kotlinVersion"
    private const val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"
    private const val sqlDelightGradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelightGradle}"
    private const val serializationGradle = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
    private const val firebaseCrashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsGradle}"
    private const val gmsPlayServicesGradle = "com.google.gms:google-services:${Versions.gmsPlayServicesGradle}"
    private const val gmsPlayServicesAdsGradle = "com.google.android.gms:play-services-ads:${Versions.gmsPlayServicesAdsGradle}"

    val array = listOf(
        buildGradle,
        sqlDelightGradle,
        serializationGradle,
        parcelizeGradle,
        firebaseCrashlyticsGradle,
        gmsPlayServicesGradle,
        gmsPlayServicesAdsGradle
    )
}

object Libraries {

    object Versions {
        const val coroutines = "1.4.2"
        const val koin = "2.2.2"
        const val coreKtx = "1.2.0"
        const val appCompat = "1.2.0"
        const val material = "1.2.1"
        const val junit = "4.+"
        const val lifecycle = "2.2.0"
        const val viewBindingDelegate = "1.3.1"
        const val cicerone = "6.6"
        const val retrofit = "2.9.0"
        const val retrofitConverter = "0.8.0"
        const val serialization = "1.0.1"
        const val constraintLayout = "2.0.4"
        const val notificationDsl = "0.1.0"
        const val decoro = "1.5.0"
        const val swipeRefreshLayout = "1.0.0"
        const val fastAdapter = "5.3.2"
        const val admob = "19.7.0"
        const val workManager = "2.5.0"
        const val leakCanary = "2.6"
        const val firebaseBom = "26.5.0"
        const val compose = "1.0.0-alpha12"
        const val composeNavigation = "1.0.0-alpha07"
        const val activity = "1.3.0-alpha02"

    }

    /** Default implementation */
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    private const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private const val koinCore = "org.koin:koin-core:${Versions.koin}"
    private const val junit = "junit:junit:${Versions.junit}"

    /** Android */
    private const val koinAndroidScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    private const val koinAndroidViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    private const val koinAndroidFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
    private const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    private const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val appCompat = "androidx.appcompat:appcompat:{${Versions.appCompat}}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
    private const val notificationDslCore = "com.kirich1409.android-notification-dsl:core:${Versions.notificationDsl}"
    private const val notificationDslExt = "com.kirich1409.android-notification-dsl:extensions:${Versions.notificationDsl}"
    private const val decoro = "ru.tinkoff.decoro:decoro:${Versions.decoro}"
    private const val viewBindingDelegate = "com.kirich1409.viewbindingpropertydelegate:vbpd-noreflection:${Versions.viewBindingDelegate}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    private const val fastAdapter = "com.mikepenz:fastadapter:${Versions.fastAdapter}"
    private const val fastAdapterBinding = "com.mikepenz:fastadapter-extensions-binding:${Versions.fastAdapter}"
    private const val fastAdapterDiff = "com.mikepenz:fastadapter-extensions-diff:${Versions.fastAdapter}"
    private const val admob = "com.google.android.gms:play-services-ads:${Versions.admob}"
    private const val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    private const val workManagerMultiProcess = "androidx.work:work-multiprocess:${Versions.workManager}"
    private const val workManagerKoin = "org.koin:koin-androidx-workmanager:${Versions.koin}"
    private const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    private const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    private const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    private const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    private const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    private const val composeUiTool = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    private const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    private const val composeIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
    private const val composeIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    private const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.compose}"
    private const val composeActivity = "androidx.activity:activity-compose:${Versions.activity}"
    private const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

    /** Data */
    private const val sqlDelightAndroidDriver = "com.squareup.sqldelight:android-driver:${Plugin.Versions.sqlDelightGradle}"
    private const val sqlDelightCoroutines = "com.squareup.sqldelight:coroutines-extensions-jvm:${Plugin.Versions.sqlDelightGradle}"
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val retrofitConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitConverter}"
    private const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"


    fun DependencyHandler.addCommonDependencies() {
        implementation(coroutines)
        implementation(koinCore)
    }

    fun DependencyHandler.addFirebase() {
        implementation(platform(firebaseBom))
        api(firebaseAnalytics)
        api(firebaseCrashlytics)
    }


    fun DependencyHandler.addAndroidDebugDependencies() {
        debugImplementation(leakCanary)
    }

    val androidDependencies = listOf(
        coreKtx, coroutinesAndroid, appCompat, material, liveDataKtx, viewBindingDelegate,
        koinAndroidFragment, koinAndroidViewModel, koinAndroidScope, cicerone, constraintLayout,
        decoro, swipeRefreshLayout, fastAdapter, fastAdapterBinding, fastAdapterDiff, admob,
        notificationDslCore, notificationDslExt, workManager, workManagerMultiProcess, workManagerKoin
    )

    val androidComposeDependencies = listOf(
        appCompat, composeUi, composeUiTool, composeFoundation, composeIconsCore, composeIconsExtended,
        composeMaterial, coreKtx, admob, workManager, workManagerMultiProcess, notificationDslCore,
        notificationDslExt, composeActivity, composeNavigation, composeCompiler
    )

    val androidDebugDependencies = listOf(leakCanary)

    private val dataDependencies = listOf(
        sqlDelightAndroidDriver, sqlDelightCoroutines, retrofit, retrofitConverter, serialization, decoro
    )

    fun DependencyHandler.addDataDependencies() {
        dataDependencies.forEach { api(it) }
    }


    private fun DependencyHandler.api(depName: String) {
        add("api", depName)
    }

    private fun DependencyHandler.implementation(depName: Any) {
        add("implementation", depName)
    }

    private fun DependencyHandler.debugImplementation(depName: Any) {
        add("debugImplementation", depName)
    }

    private fun DependencyHandler.kapt(depName: Any) {
        add("kapt", depName)
    }
}