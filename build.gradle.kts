buildscript {
    repositories {
        google()
        mavenCentral()
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
    }
}

task(name = "clean", type = Delete::class) {
    delete(rootProject.buildDir)
}