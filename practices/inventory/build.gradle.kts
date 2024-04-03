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
}
