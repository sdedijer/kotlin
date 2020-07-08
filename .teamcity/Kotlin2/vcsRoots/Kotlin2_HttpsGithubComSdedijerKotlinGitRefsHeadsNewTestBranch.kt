package Kotlin2.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object Kotlin2_HttpsGithubComSdedijerKotlinGitRefsHeadsNewTestBranch : GitVcsRoot({
    uuid = "e6254231-5f64-4594-9781-cc7627505e72"
    name = "https://github.com/sdedijer/kotlin.git#refs/heads/new_test_branch"
    url = "https://github.com/sdedijer/kotlin.git"
    branch = "refs/heads/new_test_branch"
    branchSpec = "+:refs/heads/second*"
    authMethod = password {
        userName = "sdedijer"
        password = "credentialsJSON:d5f8e4d4-ab79-400f-b9bc-ad262dd5918e"
    }
})
