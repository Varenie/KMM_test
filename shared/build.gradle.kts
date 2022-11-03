plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(SqlDelight.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(SqlDelight.android)
            }
        }
        val androidTest by getting
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(SqlDelight.native)
            }
        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.varenie.kmmtest"
    compileSdk = 32
    defaultConfig {
        minSdk = 29
        targetSdk = 32
    }
}
dependencies {
    implementation("androidx.core:core-ktx:+")
}

sqldelight {
    database("project_database") {
        packageName = "com.pole.multiplatform"
//        sourceFolders = listOf("sqldelight")
    }
}

object SqlDelight {
    private const val sqlDelightVersion = "1.5.3"
    val runtime = "com.squareup.sqldelight:runtime:$sqlDelightVersion"
    val android = "com.squareup.sqldelight:android-driver:$sqlDelightVersion"
    val native = "com.squareup.sqldelight:native-driver:$sqlDelightVersion"
}