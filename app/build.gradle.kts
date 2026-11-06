plugins {
    id("com.android.application")
}

// Every GitHub build gets a higher version number, so a new APK installs over the old one.
val runNumber = (System.getenv("GITHUB_RUN_NUMBER") ?: "1").toInt()

android {
    namespace = "com.doomscroller.theabandoned"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.doomscroller.theabandoned"
        minSdk = 24
        targetSdk = 34
        versionCode = runNumber
        versionName = "0.5.$runNumber"
    }

    // One fixed key for every build. Android only installs an update over the old app
    // when both are signed with the same key. Fine for sideloading; not for the Play Store.
    signingConfigs {
        create("shared") {
            storeFile = file("abandoned.keystore")
            storePassword = "abandoned"
            keyAlias = "abandoned"
            keyPassword = "abandoned"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("shared")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("shared")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}
