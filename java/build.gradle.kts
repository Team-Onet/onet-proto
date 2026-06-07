plugins {
    kotlin("jvm") version "2.2.0"
    `maven-publish`
}

group = "com.onet"
version = project.findProperty("version")?.toString() ?: "unspecified"

java {
    toolchain { languageVersion = JavaLanguageVersion.of(21) }
}

kotlin {
    sourceSets["main"].kotlin.srcDirs("src/main/kotlin", "src/main/java")
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.google.protobuf:protobuf-java:4.28.3")
    api("com.google.protobuf:protobuf-kotlin:4.28.3")
    api("io.grpc:grpc-stub:1.75.0")
    api("io.grpc:grpc-protobuf:1.75.0")
    api("io.grpc:grpc-kotlin-stub:1.4.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
}

publishing {
    publications {
        create<MavenPublication>("gpr") {
            groupId = "com.onet"
            artifactId = "onet-proto"
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${System.getenv("GITHUB_REPOSITORY_OWNER") ?: "Team-Onet"}/onet-proto")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: ""
                password = System.getenv("GITHUB_TOKEN") ?: ""
            }
        }
    }
}
