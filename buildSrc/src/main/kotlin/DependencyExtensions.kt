

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.coroutines() {
    implementation(KotlinDependencies.COROUTINES)
}

fun DependencyHandler.hilt() {
    implementation(AndroidDependencies.HILT)
    kapt(AndroidDependencies.HILT_COMPILER)
}

fun DependencyHandler.navigation() {
    implementation(AndroidDependencies.NAV_FRAGMENT)
    implementation(AndroidDependencies.NAV_UI)
}

fun DependencyHandler.gradleGeneralClasspath() {
    classpath(GradlePlugins.BUILD_TOOLS)
    classpath(GradlePlugins.KGP)
}

fun DependencyHandler.hiltPluginClasspath() {
    classpath(GradlePlugins.BUILD_TOOLS)
    classpath(GradlePlugins.KGP)
    classpath(GradlePlugins.HILT)
}

fun DependencyHandler.androidDefaults() {
    implementation(AndroidDependencies.APPCOMPAT)
    implementation(AndroidDependencies.MATERIAL)
    implementation(AndroidDependencies.CORE_KTX)
    implementation(AndroidDependencies.FRAGMENT_KTX)
    implementation(AndroidDependencies.INPUT_MASK)
    implementation(AndroidDependencies.LOTTIE)
}

fun DependencyHandler.network() {
    implementation(AndroidDependencies.Network.RETROFIT)
    implementation(AndroidDependencies.Network.GSON_CONVERTER)
}

fun DependencyHandler.splashApi() {
    implementation(AndroidDependencies.SPLASH_API)
}

fun DependencyHandler.lifecycle() {
    implementation(AndroidDependencies.LIFECYCLE)
}

fun DependencyHandler.room() {
    implementation(AndroidDependencies.ROOM)
    kapt(AndroidDependencies.ROOM_COMPILER)
}

fun DependencyHandler.worker() {
    implementation(AndroidDependencies.WORKER)
    implementation(AndroidDependencies.HILT_WORK)
}

fun DependencyHandler.shimmer() {
    implementation(AndroidDependencies.SHIMMER)
}

fun DependencyHandler.ui() {
    implementation(AndroidDependencies.DOTS_INDOCATOR)
}

fun DependencyHandler.scanner() {
    implementation(AndroidDependencies.Scanner.VISION)
}

fun DependencyHandler.all() {
    coroutines()
    hilt()
    navigation()
    androidDefaults()
    network()
    splashApi()
    lifecycle()
    room()
    worker()
    shimmer()
    scanner()
    ui()
}

private fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

private fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

private fun DependencyHandler.classpath(dependency: String) {
    add("classpath", dependency)
}