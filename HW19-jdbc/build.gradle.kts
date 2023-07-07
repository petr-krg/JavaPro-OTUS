plugins {
    id("java");
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic")
    implementation("org.flywaydb:flyway-core:9.5.0")
    implementation("com.zaxxer:HikariCP")
    implementation("org.postgresql:postgresql")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}