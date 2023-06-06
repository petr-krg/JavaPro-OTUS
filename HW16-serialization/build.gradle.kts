plugins {
    id("java")
}

group = "krg.petr.otusru"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("javax.json:javax.json-api:1.1.4")
    implementation ("com.google.guava:guava")
    implementation ("com.fasterxml.jackson.core:jackson-databind")
    implementation ("com.fasterxml.jackson.core:jackson-core")
    implementation ("javax.json:javax.json-api")
    implementation ("com.google.protobuf:protobuf-java-util")


    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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