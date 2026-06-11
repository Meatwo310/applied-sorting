import org.gradle.api.provider.Provider

plugins {
    id("neoforge-mod-conventions")
    id("neoforge-config-conventions")
}

// Mod Dependencies
val ae2Version: String by project
val guidemeVersion: String by project

dependencies {
    fun implRuntime(dependencyNotation: Provider<*>, requiredVersion: String) {
        implementation(dependencyNotation) { version { require(requiredVersion) } }
        runtimeMods(dependencyNotation) { version { require(requiredVersion) } }
    }

    implRuntime(libs.guideme, guidemeVersion)
    implRuntime(libs.ae2, ae2Version)
}
