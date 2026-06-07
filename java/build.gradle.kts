// 로컬 includeBuild 전용 — CI 배포는 pom.xml(Maven) 사용
plugins {
    kotlin("jvm") version "2.2.0"
}

group = "com.onet"
version = "unspecified"

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
