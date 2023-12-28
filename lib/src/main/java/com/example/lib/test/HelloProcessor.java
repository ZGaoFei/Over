package com.example.lib.test;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)

class HelloProcessor extends AbstractProcessor {

   // 用于消息传递和元素的处理的工具实例
   private Messager messager;
   private Elements elementUtils;
   private Filer filer;

   @Override
   public synchronized void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);
      // 通过init()方法，完成初始化工作
      this.messager = processingEnv.getMessager();
      this.elementUtils = processingEnv.getElementUtils();
      this.filer = processingEnv.getFiler();
   }

   // 也可以使用@SupportedSourceVersion规定source version而无需重写方法
   @Override
   public SourceVersion getSupportedSourceVersion() {
      // 一般直接返回最近的source version即可
      // return SourceVersion.latestSupported();
      // 这个规定source version至少为8
      if (SourceVersion.latest().compareTo(SourceVersion.RELEASE_8) > 0) {
         return SourceVersion.latest();
      }
      return SourceVersion.RELEASE_8;
   }

   @Override
   public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      // 一条简单的信息打印，用于辅助process方法是否被执行
      messager.printMessage(Diagnostic.Kind.NOTE, "source version -- " + getSupportedSourceVersion());
      // 遍历注解处理器可以处理的注解，获得被注解说明的元素
      for (TypeElement annotation : annotations) {
         String annotationName = annotation.getSimpleName().toString();
         Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
         for (Element element : elements) {
            // 该注解处理器只处理@Hello，而且@Hello的Target为Type，进一步要求为CLASS
            if (element.getKind() != ElementKind.CLASS) {
               messager.printMessage(Diagnostic.Kind.ERROR, "@" + annotationName + "must be used for a class");
            }
            // 获取被说明类的包名和类名，为创建对应的Hello类做准备
            TypeElement classElement = (TypeElement) element;
            String simpleName = classElement.getSimpleName().toString();
            PackageElement packageElement = elementUtils.getPackageOf(classElement);
            String packageName = packageElement.getQualifiedName().toString();
            // 借助JavaPoet模板引擎，生成对应的Hello类
            TypeSpec typeSpec = generateClassFile(simpleName, packageName);

            // 生成对应的Java文件
            JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();
            try {
               javaFile.writeTo(filer);
            } catch (IOException e) {
               messager.printMessage(Diagnostic.Kind.ERROR, "Failed to generate Java file for class ", element);
            }
         }
      }
      return roundEnv.processingOver();
   }

   private TypeSpec generateClassFile(String simpleName, String packageName) {
      // 定义FieldSpec，存储类名
      FieldSpec nameField = FieldSpec.builder(String.class, "className", Modifier.PRIVATE).build();

      // 定义无参构造函数，自动完成类名的初始化
      MethodSpec constructor = MethodSpec.constructorBuilder()
              .addModifiers(Modifier.PUBLIC)
              .addStatement("this.$N = $S", nameField, simpleName)
              .build();

      // 定义sayHello方法
      String msg = "Hello, this is ";
      MethodSpec sayHello = MethodSpec.methodBuilder("sayHello")
              .addModifiers(Modifier.PUBLIC)
              .addStatement("System.out.println($S + className)", msg)
              .build();

      // 创建类
      ClassName helloClass = ClassName.get(packageName, simpleName + "Hello");
      return TypeSpec.classBuilder(helloClass)
              .addModifiers(Modifier.PUBLIC)
              .addField(nameField)
              .addMethod(constructor)
              .addMethod(sayHello)
              .build();
   }

}
