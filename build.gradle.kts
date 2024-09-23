plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.allopen") version "2.0.20"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.11"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.11")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

benchmark {
    targets {
        register("test")
    }
}