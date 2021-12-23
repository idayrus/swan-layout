plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("java")
    id("maven-publish")
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            groupId = "com.idayrus.layout.swan"
            artifactId = "SwanLayout"
            version = "1.0.00"
            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.code.gson:gson:2.8.9")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}