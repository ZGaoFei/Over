pluginManagement {
    repositories {
//        maven { url 'https://maven.aliyun.com/repository/central' }
//        maven { url 'https://maven.aliyun.com/repository/public' }
//        maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
//        maven { url 'https://maven.aliyun.com/nexus/content/groups/public/' }
//        maven { url 'https://maven.aliyun.com/nexus/content/repositories/jcenter' }
//        maven { url 'https://maven.aliyun.com/nexus/content/repositories/google' }
//        maven { url 'https://maven.aliyun.com/nexus/content/repositories/gradle-plugin' }

        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        maven { url 'https://maven.aliyun.com/repository/central' }
//        maven { url 'https://maven.aliyun.com/repository/public' }
//        maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
//        maven { url 'https://maven.aliyun.com/nexus/content/groups/public/' }
//        maven { url 'https://maven.aliyun.com/nexus/content/repositories/jcenter' }
//        maven { url 'https://maven.aliyun.com/nexus/content/repositories/google' }
//        maven { url 'https://maven.aliyun.com/nexus/content/repositories/gradle-plugin' }

        google()
        mavenCentral()
    }
}

rootProject.name = "Overall"
include(":app")
include(":jetpack")
include(":base")
include(":otherlibrary")
