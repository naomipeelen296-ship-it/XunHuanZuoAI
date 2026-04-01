plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.xunhuan.zuoai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xunhuan.zuoai"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "CLAUDE_API_KEY", "\"sk-svqMZ9KDYnWsIRGvfWG6hhHgI6YFiuRJ2o5xM8kXqn11OWTs\"")
        buildConfigField("String", "CLAUDE_BASE_URL", "\"http://1.95.142.151:3000\"")
        buildConfigField("String", "CLAUDE_MODEL", "\"claude-sonnet-4-6\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

configurations.all {
    resolutionStrategy.eachDependency {
        when (requested.group) {
            "androidx.room"       -> useVersion("2.6.1")
            "androidx.sqlite"     -> useVersion("2.4.0")
            "androidx.fragment"   -> useVersion("1.6.2")
            "androidx.lifecycle"  -> useVersion("2.7.0")
            "androidx.navigation" -> useVersion("2.7.7")
            "com.google.dagger"   -> useVersion("2.51.1")
            "androidx.hilt"       -> useVersion("1.2.0")
            "androidx.datastore"  -> useVersion("1.1.1")
        }
    }
    resolutionStrategy.capabilitiesResolution.withCapability("com.google.collections:google-collections") {
        select(candidates.first())
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    debugImplementation("androidx.compose.ui:ui-tooling")
}
