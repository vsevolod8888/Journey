plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.sever.journey.internetconnection"
    compileSdk = 37
    defaultConfig { minSdk = 24 }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.test)
}
