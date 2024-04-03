plugins {
    id("custom.java-conventions")
    id("custom.test-conventions")
    id("custom.spring-conventions")
}

dependencies {
    implementation(Spring.boot.actuator)
    implementation(Spring.boot.web)
    implementation(Spring.boot.data.jpa)

    // test
    testImplementation("com.h2database:h2")
    testImplementation("mysql:mysql-connector-java:_")

    // testcontainers
    testImplementation(platform("org.testcontainers:testcontainers-bom:_"))
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
}
