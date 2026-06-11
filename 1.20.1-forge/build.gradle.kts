plugins {
    id("legacyforge-mod-conventions")
    id("legacyforge-config-conventions")
}

val ae2Version: String by project
val guidemeVersion: String by project
val refinedStorageVersion: String by project

// Mod Dependencies
dependencies {
    fun implRuntime(dependencyNotation: Any) {
        modImplementation(dependencyNotation)
        runtimeMods(dependencyNotation)
    }

    implRuntime("maven.modrinth:guideme:$guidemeVersion")
    implRuntime("maven.modrinth:ae2:$ae2Version")

    modRuntimeOnly("curse.maven:refined-storage-243076:4844585")

    modRuntimeOnly("curse.maven:glodium-957920:5226922")
    modRuntimeOnly("curse.maven:applied-flux-965012:7651647")

    modRuntimeOnly("curse.maven:mekanism-268560:6552911")
    modRuntimeOnly("curse.maven:applied-mekanistics-574300:7244744")

    modRuntimeOnly("curse.maven:catalogue-459701:4766090")
    modRuntimeOnly("curse.maven:configured-457570:5180900")
}
