buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
    }
}

// REMINDER THAT THIS IS PUBLIC
plugins {
    `java-library`
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

group = "com.github.polygon-io"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.20")

    val ktorVersion = "2.1.2"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // Annotation processor that generates Java builders for data classes
    val ktBuilderVersion = "1.2.1"
    implementation("com.thinkinglogic.builder:kotlin-builder-annotation:$ktBuilderVersion")

    testImplementation("junit:junit:4.12")
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}


tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    val sourcesJar by creating(Jar::class) {
        dependsOn(JavaPlugin.CLASSES_TASK_NAME)
        classifier = "sources"
        from(sourceSets["main"].allSource)
    }

    artifacts {
        add("archives", sourcesJar)
        add("archives", jar)
    }
}
