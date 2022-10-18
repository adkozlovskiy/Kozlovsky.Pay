object KotlinDependencies {
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${KotlinLibsVersions.COROUTINES}"
}

object GradlePlugins {
    const val BUILD_TOOLS = "com.android.tools.build:gradle:${GradlePluginsVersions.BUILD_TOOLS}"
    const val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:${GradlePluginsVersions.KGP}"
    const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${GradlePluginsVersions.HILT}"
}

object AndroidDependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${KotlinLibsVersions.CORE_KTX}"
    const val HILT = "com.google.dagger:hilt-android:${AndroidLibsVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${AndroidLibsVersions.HILT}"

    const val NAV_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${AndroidLibsVersions.NAVIGATION}"
    const val NAV_UI = "androidx.navigation:navigation-ui-ktx:${AndroidLibsVersions.NAVIGATION}"

    const val APPCOMPAT = "androidx.appcompat:appcompat:${AndroidLibsVersions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${AndroidLibsVersions.MATERIAL}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${AndroidLibsVersions.FRAGMENT_KTX}"

    const val SPLASH_API = "androidx.core:core-splashscreen:${AndroidLibsVersions.SPLASH}"
    const val LIFECYCLE =
        "androidx.lifecycle:lifecycle-runtime-ktx:${AndroidLibsVersions.LIFECYCLE}"

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${AndroidLibsVersions.RETROFIT}"
        const val GSON_CONVERTER =
            "com.squareup.retrofit2:converter-gson:${AndroidLibsVersions.RETROFIT}"
    }

    const val ROOM = "androidx.room:room-ktx:${AndroidLibsVersions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${AndroidLibsVersions.ROOM}"

    const val WORKER = "androidx.work:work-runtime-ktx:${AndroidLibsVersions.WORKER}"
    const val HILT_WORK = "androidx.hilt:hilt-work:${AndroidLibsVersions.HILT_WORK}"
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${AndroidLibsVersions.COROUTINES}"

    const val SHIMMER = "com.facebook.shimmer:shimmer:${AndroidLibsVersions.SHIMMER}"
}