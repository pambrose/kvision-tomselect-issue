import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
  val kotlinVersion: String by System.getProperties()
  val kvisionVersion: String by System.getProperties()
  val versionsVersion: String by System.getProperties()

  kotlin("plugin.serialization") version kotlinVersion
  kotlin("js") version kotlinVersion

  id("io.kvision") version kvisionVersion
  id("com.github.ben-manes.versions") version versionsVersion
}

version = "1.0.0-SNAPSHOT"
group = "com.example"

repositories {
  google()
  mavenCentral()
}

// Versions
val kotlinVersion: String by System.getProperties()
val kvisionVersion: String by System.getProperties()

val webDir = file("src/main/web")

kotlin {
  js {
    browser {
      commonWebpackConfig {
        outputFileName = "main.bundle.js"
      }
      runTask {
        sourceMaps = false
        devServer = KotlinWebpackConfig.DevServer(
          open = false,
          port = 3000,
          proxy = mutableMapOf(
            "/kv/*" to "http://localhost:8080",
            "/kvws/*" to mapOf("target" to "ws://localhost:8080", "ws" to true)
          ),
          static = mutableListOf("$buildDir/processedResources/js/main")
        )
      }
      testTask {
        useKarma {
          useChromeHeadless()
        }
      }
    }
    binaries.executable()
  }
  sourceSets["main"].dependencies {
    implementation("io.kvision:kvision:$kvisionVersion")
    implementation("io.kvision:kvision-bootstrap:$kvisionVersion")
    implementation("io.kvision:kvision-tabulator:$kvisionVersion")
    implementation("io.kvision:kvision-datetime:$kvisionVersion")
    implementation("io.kvision:kvision-tom-select:$kvisionVersion")
  }
  sourceSets["test"].dependencies {
    implementation(kotlin("test-js"))
    implementation("io.kvision:kvision-testutils:$kvisionVersion")
  }
  sourceSets["main"].resources.srcDir(webDir)
}
