plugins {
    id("neoforge-mod-conventions")
}

// Mod Dependencies
val ae2Version: String by project
val guidemeVersion: String by project

dependencies {
    implementation("maven.modrinth:guideme:$guidemeVersion")
    implementation("maven.modrinth:ae2:$ae2Version")
    add("runtimeMods", "maven.modrinth:guideme:$guidemeVersion")
    add("runtimeMods", "maven.modrinth:ae2:$ae2Version")
}
