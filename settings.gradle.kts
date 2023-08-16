rootProject.name = "JavaPro-Otus"
include("HW01-gradle")
include("HW04-generics")
include("HW06-annotations")
include("HW08-gc")
include("HW10-byteCode")
include("HW12-solid")
include("HW15-structuralPatterns")
include("HW16-serialization")
include("HW19-jdbc")

include("HW21-jpql")

include("HW25-ContainerIoC")

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings
    val protobufVer: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
        id("com.google.protobuf") version protobufVer
    }
}

