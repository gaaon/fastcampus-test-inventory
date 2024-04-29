plugins {
    id("org.sonarqube")
}

sonar {
    properties {
        property("sonar.organization", "gaaon")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}