plugins {
    id("neoforge-mod-conventions")
    id("neoforge-config-conventions")
}

// Mod Dependencies
val ae2Version: String by project
val guidemeVersion: String by project

dependencies {
    fun implRuntime(dependencyNotation: Any) {
        implementation(dependencyNotation)
        runtimeMods(dependencyNotation)
    }

    implRuntime("maven.modrinth:guideme:$guidemeVersion")
    implRuntime("maven.modrinth:ae2:$ae2Version")
}
