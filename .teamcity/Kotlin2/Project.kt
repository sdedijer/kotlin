package Kotlin2

import Kotlin2.buildTypes.*
import Kotlin2.vcsRoots.*
import Kotlin2.vcsRoots.Kotlin2_HttpsGithubComSdedijerKotlinGitRefsHeadsNewTestBranch
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.versionedSettings

object Project : Project({
    id("Kotlin2")
    parentId("_Root")
    name = "Kotlin2"

    vcsRoot(Kotlin2_HttpsGithubComSdedijerKotlinGitRefsHeadsNewTestBranch)

    buildType(Kotlin2_Build)

    features {
        versionedSettings {
            id = "PROJECT_EXT_1"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = "${Kotlin2_HttpsGithubComSdedijerKotlinGitRefsHeadsNewTestBranch.id}"
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
})
