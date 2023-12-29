package com.example.lib.test;

import com.example.annotation_lib.test.TestOne;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.util.HashSet;
import java.util.List;
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
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class TestOneProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Messager messager;
    private Filer filer;
    private Types typeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        elementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
        typeUtils = processingEnvironment.getTypeUtils();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(TestOne.class.getCanonicalName());
        return set;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateProcess(set, roundEnvironment);
        return false;
    }

    private void generateProcess(Set<? extends TypeElement> set, RoundEnvironment environment) {
        Set<? extends Element> elements = environment.getElementsAnnotatedWith(TestOne.class);
        for (Element element : elements) {
            if (element.getKind() != ElementKind.FIELD) {
                break;
            }

            TypeElement typeElement = (TypeElement) element.getEnclosingElement();
            Name simpleName = typeElement.getSimpleName();

            ElementKind kind = typeElement.getKind();
            TestOne annotation = typeElement.getAnnotation(TestOne.class);
            List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
            Element enclosingElement = typeElement.getEnclosingElement();
            TypeMirror typeMirror = typeElement.asType();

            PackageElement packageOf = elementUtils.getPackageOf(typeElement);
            String packageName = packageOf.getQualifiedName().toString();

            TypeSpec typeSpec = generateClass(simpleName.toString(), packageName);
            generateJavaFile(typeSpec, packageName);
        }
    }

    private TypeSpec generateClass(String simpleName, String packageName) {
        // 变量
        FieldSpec field = FieldSpec
                .builder(String.class, "className", Modifier.PRIVATE)
                .build();

        // 构造方法
        MethodSpec constructor = MethodSpec
                .constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addStatement("this.$N = $S", field, simpleName)
                .build();

        // 方法
        String msg = "this is ";
        MethodSpec methodSpec = MethodSpec
                .methodBuilder("sayHello")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("System.out.println($S + className)", msg)
                .build();

        // 类
        ClassName className = ClassName.get(packageName, simpleName + "_TestOne");
        TypeSpec typeSpec = TypeSpec
                .classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addField(field)
                .addMethod(constructor)
                .addMethod(methodSpec)
                .build();
        return typeSpec;
    }

    private void generateJavaFile(TypeSpec typeSpec, String packageName) {
        try {
            JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
        } catch (Exception e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Failed to generate Java file for class ");
        }
    }
}
