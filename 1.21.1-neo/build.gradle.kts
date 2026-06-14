import net.meatwo310.mdk.build.req

plugins {
    id("neoforge-mod-conventions")
    id("neoforge-config-conventions")
}

// Mod Dependencies
val ae2Version: String by project
val guidemeVersion: String by project

dependencies {
    implementation(libs.guideme, req(guidemeVersion))
    ciRuntimeMods(libs.guideme, req(guidemeVersion))
    implementation(libs.ae2, req(ae2Version))
    ciRuntimeMods(libs.ae2, req(ae2Version))
}
