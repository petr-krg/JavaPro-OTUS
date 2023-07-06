plugins {
    id("java")
}

group = "krg.petr.otusru"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.google.guava:guava")
    implementation ("com.fasterxml.jackson.core:jackson-databind")
    implementation ("javax.json:javax.json-api")
    implementation ("com.google.protobuf:protobuf-java-util")

    testImplementation ("org.junit.jupiter:junit-jupiter-api")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine")
    testImplementation ("org.junit.jupiter:junit-jupiter-params")
    testImplementation ("org.assertj:assertj-core")
    testImplementation ("org.mockito:mockito-core")
    testImplementation ("org.mockito:mockito-junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}