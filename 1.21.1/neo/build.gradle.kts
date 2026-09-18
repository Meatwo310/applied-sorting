import net.meatwo310.mdk.build.req
import net.meatwo310.mdk.build.platformArtifacts

plugins {
    id("neoforge-mod-conventions")
    id("neoforge-config-conventions")
}

platformArtifacts {
    publishingDependencies {
        required {
            curseForge = "applied-energistics-2"
            modrinth = "ae2"
        }
    }
}

// Mod Dependencies
val ae2Version = project.property("ae2Version").toString()
val guidemeVersion = project.property("guidemeVersion").toString()

dependencies {
    implementation(libs.guideme, req(guidemeVersion))
    ciRuntimeMods(libs.guideme, req(guidemeVersion))
    implementation(libs.ae2, req(ae2Version))
    ciRuntimeMods(libs.ae2, req(ae2Version))
}
