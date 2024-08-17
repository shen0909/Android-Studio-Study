plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.8.22" // 这里添加Kotlin插件
}

android {
    namespace = "com.example.androidstudiostudy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidstudiostudy"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // 1.添加依赖
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    // 1.添加 gson 依赖
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // 添加 Kotlin 标准库依赖
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")


    // 添加okhttp的依赖
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    // 添加volley依赖
    implementation ("com.android.volley:volley:1.2.1")
}