plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.tapascodev.dragonball"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tapascodev.dragonball"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //paging
    implementation(libs.pagingCompose)
    implementation(libs.androidx.paging.runtime.ktx)

    //Dagger Hilt
    implementation(libs.dagger.hilt)
    implementation(libs.dagger.hilt.navigation)
    implementation(libs.dagger.hilt.lifecycle.compose)
    kapt(libs.dagger.hilt.compiler)

    //Retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.converter.gson)

    //Splash
    implementation(libs.splash.screen)

    //Serialization
    implementation(libs.kotlinx.serialization.json)

    //Compose
    implementation(libs.androidx.navigation.compose)
    implementation(libs.constraintlayout.compose)
    implementation(libs.live.data.compose)
    implementation(libs.coil.compose)

    // Room
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)


    // Mock
    testImplementation (libs.mockk)
    testImplementation (libs.jetbrains.kotlinx.coroutines.test)
    testImplementation (libs.androidx.core.testing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}