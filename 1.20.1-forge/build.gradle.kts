import org.gradle.api.provider.Provider

plugins {
    id("legacyforge-mod-conventions")
    id("legacyforge-config-conventions")
}

val ae2Version: String by project
val guidemeVersion: String by project
val refinedStorageVersion: String by project
val glodiumVersion: String by project
val appliedFluxVersion: String by project
val mekanismVersion: String by project
val appliedMekanisticsVersion: String by project
val catalogueVersion: String by project
val configuredVersion: String by project

// Mod Dependencies
dependencies {
    fun implRuntime(dependencyNotation: Provider<*>, requiredVersion: String) {
        modImplementation(dependencyNotation) { version { require(requiredVersion) } }
        runtimeMods(dependencyNotation) { version { require(requiredVersion) } }
    }
    fun modRuntime(dependencyNotation: Provider<*>, requiredVersion: String) {
        modRuntimeOnly(dependencyNotation) { version { require(requiredVersion) } }
    }

    implRuntime(libs.guideme, guidemeVersion)
    implRuntime(libs.ae2, ae2Version)

    modRuntime(libs.refinedstorage, refinedStorageVersion)

    modRuntime(libs.glodium, glodiumVersion)
    modRuntime(libs.appliedflux, appliedFluxVersion)

    modRuntime(libs.mekanism, mekanismVersion)
    modRuntime(libs.appliedmekanistics, appliedMekanisticsVersion)

    modRuntime(libs.catalogue, catalogueVersion)
    modRuntime(libs.configured, configuredVersion)
}
