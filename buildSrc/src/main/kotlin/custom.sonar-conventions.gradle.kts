plugins {
    id("org.sonarqube")
}

sonar {
    properties {
        property("sonar.organization", "grizztest")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}