package com.example.lib.annotation.factory;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

@AutoService(Processor.class)
class FactoryProcessor extends AbstractProcessor {
    private Types mTypeUtils;
    private Messager mMessager;
    private Filer mFiler;
    private Elements mElementUtils;

    private Map<String, FactoryGroupedClasses> factoryClasses = new LinkedHashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        mTypeUtils = processingEnvironment.getTypeUtils();
        mMessager = processingEnvironment.getMessager();
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new LinkedHashSet<>();
        set.add(Factory.class.getCanonicalName());
        return set;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Factory.class);
        for (Element element : elements) {
            if (element.getKind() != ElementKind.CLASS) {
//                throw new ProcessingException(annotatedElement, "Only classes can be annotated with @%s",
//                        Factory.class.getSimpleName());
            }
             // https://blog.csdn.net/qq_20521573/article/details/82321755?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-82321755-blog-131897774.235%5Ev39%5Epc_relevant_anti_vip_base&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-82321755-blog-131897774.235%5Ev39%5Epc_relevant_anti_vip_base&utm_relevant_index=2
            // https://www.cnblogs.com/wellcherish/p/17087711.html
            // https://blog.csdn.net/qq_34681580/article/details/121483450
        }
        return false;
    }

}
