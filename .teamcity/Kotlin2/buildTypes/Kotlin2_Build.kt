package Kotlin2.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Kotlin2_Build : BuildType({
    name = "Build"
    description = "Blablabl now push from tc to new branch only 123"

    params {
        password("variable123", "credentialsJSON:6adb0c60-5084-4ca3-9838-63062feafeae", display = ParameterDisplay.HIDDEN)
    }

    vcs {
        root(Kotlin2.vcsRoots.Kotlin2_HttpsGithubComSdedijerKotlinGitRefsHeadsNewTestBranch)
    }

    steps {
        script {
            name = "Set version using script"
            scriptContent = """
                #!/bin/bash
                HASH=%build.vcs.number%
                SHORT_HASH=${'$'}{HASH:0:7}
                BUILD_COUNTER=%build.counter%
                BUILD_NUMBER="1.0${'$'}BUILD_COUNTER.${'$'}SHORT_HASH"
                echo "##teamcity[buildNumber '${'$'}BUILD_NUMBER']"
            """.trimIndent()
        }
        script {
            name = "build"
            scriptContent = """
                mkdir bin
                echo "built artifact" > bin/compiled.txt
            """.trimIndent()
        }
    }

    triggers {
        vcs {
        }
    }
})
