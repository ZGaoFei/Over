plugins {
    id("java-library")
//    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":annotation-lib"))

    // 方便注解处理器生成对应的文件
    annotationProcessor("com.google.auto.service:auto-service:1.0.1")
    // 方便我们在项目源码中使用 auto-service 对应的注解
    compileOnly("com.google.auto.service:auto-service-annotations:1.0.1")
    implementation("com.squareup:javapoet:1.13.0")
}