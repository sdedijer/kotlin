import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.1"

project {

    buildType(Build)
}

object Build : BuildType({
    name = "Build"
    description = "abc 123 hello world"

    params {
        password("variable123",
                "credentialsJSON:6adb0c60-5084-4ca3-9838-63062feafeae",
                display = ParameterDisplay.HIDDEN)
    }

    id("Build")
    steps {
        script {
            name = "Set version using script"
            scriptContent = """
      #!/bin/bash
      HASH=%build.vcs.number%
      SHORT_HASH=${"$"}{HASH:0:7}
      BUILD_COUNTER=%build.counter%
      BUILD_NUMBER="1.0${"$"}BUILD_COUNTER.${"$"}SHORT_HASH"
      echo "##teamcity[buildNumber '${"$"}BUILD_NUMBER']"
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

    vcs {
        root(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }
})
