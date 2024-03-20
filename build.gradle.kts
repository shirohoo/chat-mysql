plugins {
    java
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.diffplug.spotless") version "6.11.0"
}

group = "io.shirohoo.example"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    java {
        palantirJavaFormat()
        indentWithSpaces()
        formatAnnotations()
        removeUnusedImports()
        trimTrailingWhitespace()
        importOrder("lombok", "org", "com", "java", "javax", "jakarta", "io.shirohoo.example")

        custom("noWildcardImports") {
            when {
                it.contains("*;\n") -> throw Error("No wildcard imports allowed")
                else -> it
            }
        }
        bumpThisNumberIfACustomStepChanges(1)
    }

    kotlinGradle {
        ktlint()
        indentWithSpaces()
        trimTrailingWhitespace()
    }
}
