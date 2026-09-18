import net.meatwo310.mdk.build.req
import net.meatwo310.mdk.build.platformArtifacts

plugins {
    id("lexforge-legacy-mod-conventions")
    id("lexforge-legacy-config-conventions")
}

platformArtifacts {
    publishingDependencies {
        required {
            curseForge = "applied-energistics-2"
            modrinth = "ae2"
        }
    }
}

val ae2Version = project.property("ae2Version").toString()
val guidemeVersion = project.property("guidemeVersion").toString()
val refinedStorageVersion = project.property("refinedStorageVersion").toString()
val glodiumVersion = project.property("glodiumVersion").toString()
val appliedFluxVersion = project.property("appliedFluxVersion").toString()
val mekanismVersion = project.property("mekanismVersion").toString()
val appliedMekanisticsVersion = project.property("appliedMekanisticsVersion").toString()
val catalogueVersion = project.property("catalogueVersion").toString()
val configuredVersion = project.property("configuredVersion").toString()

// Mod Dependencies
dependencies {
    modImplementation(libs.guideme, req(guidemeVersion))
    ciRuntimeMods(libs.guideme, req(guidemeVersion))
    modImplementation(libs.ae2, req(ae2Version))
    ciRuntimeMods(libs.ae2, req(ae2Version))

    modRuntimeOnly(libs.refinedstorage, req(refinedStorageVersion))

    modRuntimeOnly(libs.glodium, req(glodiumVersion))
    modRuntimeOnly(libs.appliedflux, req(appliedFluxVersion))

    modRuntimeOnly(libs.mekanism, req(mekanismVersion))
    modRuntimeOnly(libs.appliedmekanistics, req(appliedMekanisticsVersion))

    modRuntimeOnly(libs.catalogue, req(catalogueVersion))
    modRuntimeOnly(libs.configured, req(configuredVersion))
}
