plugins {
    id("java")
}

group = "krg.petr.otusru"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")

    implementation("ch.qos.logback:logback-classic")
    implementation("org.hibernate.orm:hibernate-core")
    implementation("org.flywaydb:flyway-core")

    implementation("org.postgresql:postgresql")

    implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-test")

    implementation ("com.google.code.gson:gson")

    testImplementation (platform("org.junit:junit-bom:5.9.1"))
    testImplementation ("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}