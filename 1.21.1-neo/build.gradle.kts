plugins {
    id("neoforge-mod-conventions")
}

// Mod Dependencies
val ae2Version: String by project

dependencies {
    implementation("maven.modrinth:ae2:$ae2Version")
}
