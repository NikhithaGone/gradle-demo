plugins {
    java
    application
    jacoco
    id("org.sonarqube") version "5.0.0.4638"
}

group = "com.bits"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    implementation("org.apache.logging.log4j:log4j-api:2.22.1")
    implementation("org.apache.logging.log4j:log4j-core:2.22.1")
}

application {
    mainClass.set("com.bits.mvn.App")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        html.required.set(true)
        xml.required.set(true)
        csv.required.set(false)
    }
}
sonarqube {
    properties {
        property("sonar.projectKey", "gradle-demo")
        property("sonar.projectName", "gradle-demo")
        property("sonar.host.url", "http://localhost:9000")

        property(
            "sonar.token",
            System.getenv("jenkins sonar token")
        )
    }
}
