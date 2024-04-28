plugins {
    id("custom.java-conventions")
    id("custom.test-conventions")
    id("custom.spring-conventions")
}

dependencies {
    implementation(Spring.boot.actuator)
    implementation(Spring.boot.web.toString()) {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation(Spring.boot.data.jpa)
    implementation(Spring.boot.data.redis)

    implementation(Spring.cloud.stream.stream)
    implementation(Spring.cloud.stream.binderKafka)

    implementation("mysql:mysql-connector-java:_")

    // test
    testImplementation("com.h2database:h2")

    // testcontainers
    testImplementation(platform("org.testcontainers:testcontainers-bom:_"))
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.testcontainers:kafka")

    // archunit
    testImplementation("com.tngtech.archunit:archunit-junit5:_")

    // kafka
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.1")
    }
}