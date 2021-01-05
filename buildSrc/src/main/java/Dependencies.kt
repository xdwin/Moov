import BuildPlugins.Version.kotlin

object BuildPlugins {

    object Version {
        const val buildTools = "3.5.0"
        const val kotlin = "1.3.50"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.buildTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object DefaultConfig {
    const val versionCode = 1
    const val versionName = "1.0"
    const val applicationId = "com.xdwin.moov"

    const val minSdkVersion = 21
    const val compileSdkVersion = 28
    const val targetSdkVersion = compileSdkVersion
    const val buildToolsVersion = "29.0.2"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Version {
    //common
    const val kotlin = "1.3.20"
    const val gradle = "3.3.1"
    const val material = "1.1.0-alpha05"

    //androidx
    const val constraintLayout = "1.1.3"
    const val appCompat = "1.0.0"
    const val ktx = "1.0.1"

    // lifecycle
    const val archLifecycle = "2.0.0"
    const val jetpack = "2.1.0"
    const val recyclerView = "1.2.0-alpha03"

    // network
    const val retrofit = "2.6.0"
    const val gson = "2.8.0"
    const val retrofit_gson = "2.6.0"
    const val stetho = "1.5.1"

    // coroutines
    const val coroutines = "1.3.3"
    const val coroutinesAdapter = "0.9.2"

    // media
    const val glide = "4.9.0"
    const val shimmer = "0.5.0"

    // design component (support)
    const val design = "28.0.0"

    // depedency injection
    const val dagger = "2.26"
    const val koin = "2.2.0"

    // flipper
    const val flipper = "0.51.0"
    const val soloader = "0.9.0"

    // local
    const val room = "2.2.0-rc01"

    // test
    const val jUnit = "4.12"
    const val testRunner = "1.1.1"
    const val espresso = "3.1.1"
    const val mockito = "3.0.0"
    const val mockTestRunner = "0.3.1"
    const val rules = "1.1.0"
    const val ext = "1.0.0"
    const val mockk = "1.10.0"
    const val roboelectric = "4.3"
}

object Core {
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    val gradle = "com.android.tools.build:gradle:${Version.gradle}"
}

object Android {
    val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    val ktx = "androidx.core:core-ktx:${Version.ktx}"
    val gson = "com.google.code.gson:gson:${Version.gson}"
}

object Design {
    val design = "com.google.android.material:material:${Version.material}"
    val cardView = "androidx.cardview:cardview:${Version.appCompat}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
}

object Jetpack {
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Version.jetpack}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.jetpack}"
    val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.jetpack}"
}

object Retrofit {
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    val scalarConverter = "com.squareup.retrofit2:converter-scalars:${Version.retrofit}"
    val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.coroutinesAdapter}"
    val mock = "com.squareup.retrofit2:retrofit-mock:${Version.retrofit}"
    //val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.rxretrofit}"
    //val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLogging}"
    //val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
}

object Coroutines {
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
}

object Glide {
    val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"
}

object Dagger {
    val dagger = "com.google.dagger:dagger:${Version.dagger}"
    val android = "com.google.dagger:dagger-android:${Version.dagger}"
    val androidSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
    val compiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    val processor = "com.google.dagger:dagger-android-processor:${Version.dagger}"
}

object Koin {
    val koinAndroid = "org.koin:koin-android:${Version.koin}"
    val koinScope = "org.koin:koin-androidx-scope:${Version.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Version.koin}"
    val koinFragment = "org.koin:koin-androidx-fragment:${Version.koin}"
}

object Room {
    val room = "androidx.room:room-runtime:${Version.room}"
    val compiler = "androidx.room:room-compiler:${room}"
}

object Log {
    val stetho = "com.facebook.stetho:stetho-okhttp3:${Version.stetho}"
}

object Flipper {
    val flipper = "com.facebook.flipper:flipper:${Version.flipper}"
    val flipperNoOp = "com.facebook.flipper:flipper-noop:${Version.flipper}"
    val flipperNetwork = "com.facebook.flipper:flipper-network-plugin:${Version.flipper}"
    val soloader = "com.facebook.soloader:soloader:${Version.soloader}"
}

object Testing {
    val jUnit = "junit:junit:${Version.jUnit}"
    val testRunner = "androidx.test:runner:${Version.testRunner}"
    val coreTesting = "androidx.arch.core:core-testing:${Version.archLifecycle}"
    val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Version.espresso}"
    val espressoIdleResources = "androidx.test.espresso:espresso-idling-resource:${Version.espresso}"
    val mockito = "org.mockito:mockito-inline:${Version.mockito}"
    val mockKtRunner = "de.jodamob.kotlin:kotlin-runner-junit4:${Version.mockTestRunner}"
    val mockk = "io.mockk:mockk:${Version.mockk}"
    val roboelectric = "org.robolectric:robolectric:${Version.roboelectric}"

    val androidX = "androidx.arch.core:core-testing:${Version.archLifecycle}"
    val rules = "androidx.test:rules:${Version.rules}"
    val core = "androidx.test:core:${Version.rules}"

    val extJunit = "androidx.test.ext:junit:${Version.ext}"
    val extTruth = "androidx.test.ext:truth:${Version.ext}"
}

object Misc {
    val shimmer = "com.facebook.shimmer:shimmer:${Version.shimmer}"
    //val deeplink = "com.airbnb:deeplinkdispatch:${Version.deeplink}"
    //val deeplinkProcessor = "com.airbnb:deeplinkdispatch-processor:${Version.deeplink}"
}