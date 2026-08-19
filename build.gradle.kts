plugins {
    java
}

group = "com.kartibrown"
version = "0.5.0-alpha"

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

    maven {
        name = "glaremasters repo"
        url = uri("https://repo.glaremasters.me/repository/towny/")
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

    compileOnly("com.palmergames.bukkit.towny:towny:0.103.2.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}