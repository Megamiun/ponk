group = "br.com.gabryel"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.31"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.processing:core:3.3.7")
}