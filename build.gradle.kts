 import org.springframework.boot.gradle.tasks.run.BootRun
 
plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.java"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Database driver
    implementation("org.postgresql:postgresql:42.3.1")

	// service dependencies
	implementation("org.projectlombok:lombok:latest.release")
	annotationProcessor("org.projectlombok:lombok:latest.release")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.liquibase:liquibase-core")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// testing only
	testCompileOnly("org.projectlombok:lombok:latest.release")
	testAnnotationProcessor("org.projectlombok:lombok:latest.release")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<BootRun> {
    args = listOf("--spring.profiles.active=local")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
