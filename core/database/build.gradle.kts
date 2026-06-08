plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.sever.journey.data.room"
    compileSdk = 37
    defaultConfig { minSdk = 24 }
}

dependencies {
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.room.ktx)
    ksp(libs.androidx.room.compiler)
}
