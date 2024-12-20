pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PMDM7N"
include(":helloapp")
include(":addapp")
include(":weekdaysapp")
include(":kotlin")
include(":calculadora")
include(":statechange")
include(":permissiondemo")
include(":implicitintent")
include(":sumarapp")
include(":spinnerdemo")
include(":mysensors")
include(":recyclerviewdemo")
include(":futboldemo")
include(":bmi")
