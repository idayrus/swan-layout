plugins {
    id("java")
    id("maven-publish")
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("org.jetbrains.dokka") version "1.6.0"
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            groupId = "com.idayrus.layout.swan"
            artifactId = "SwanLayout"
            version = "1.01.002"
            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

sourceSets["main"].java {
    srcDir("src/main/kotlin")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.dokkaHtml.configure {
    outputDirectory.set(file("${project.rootDir}/docs"))
    moduleName.set("SwanLayout")
    dokkaSourceSets {
        configureEach {
            jdkVersion.set(8)
        }
    }
}
