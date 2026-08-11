plugins {
    java
}

group = "com.kartibrown"
version = "0.4.0-alpha"

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        name = "enginehub"
        url = uri("https://maven.enginehub.org/repo/")
    }

    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.+")

    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.18") {
        exclude(group = "com.google.guava", module = "guava")
        exclude(group = "com.google.code.gson", module = "gson")
    }

    compileOnly("com.github.Zrips:Residence:6.0.2.3"){
        isTransitive = false
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}