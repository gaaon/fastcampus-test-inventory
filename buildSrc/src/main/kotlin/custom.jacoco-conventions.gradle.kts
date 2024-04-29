plugins {
    java
    idea
    jacoco
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    reports {
        html.required.set(EnvUtils.isLocal())
        xml.required.set(true)
    }
}