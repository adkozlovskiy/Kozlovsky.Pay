buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        gradleGeneralClasspath()
        hiltPluginClasspath()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

task(name = "clean", type = Delete::class) {
    delete(rootProject.buildDir)
}