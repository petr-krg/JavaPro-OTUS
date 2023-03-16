import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow")
}

dependencies {
    implementation("com.google.guava:guava")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("gradleHelloOtus")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "krg.petr.otusru.HelloOtus"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}