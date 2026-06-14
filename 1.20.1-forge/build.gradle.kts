import net.meatwo310.mdk.build.req

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
