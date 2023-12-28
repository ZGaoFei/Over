package com.example.lib.annotation.factory;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

class FactoryAnnotatedClass {

   private TypeElement mAnnotatedClassElement;
   private String mQualifiedSuperClassName;
   private String mSimpleTypeName;
   private String mId;

   public FactoryAnnotatedClass(TypeElement classElement) {
      this.mAnnotatedClassElement = classElement;
      Factory annotation = classElement.getAnnotation(Factory.class);
      mId = annotation.id();
      if (mId.length() == 0) {
         throw new IllegalArgumentException(
                 String.format("id() in @%s for class %s is null or empty! that's not allowed",
                         Factory.class.getSimpleName(), classElement.getQualifiedName().toString()));
      }

      // Get the full QualifiedTypeName
      try {  // 该类已经被编译
         Class<?> clazz = annotation.type();
         mQualifiedSuperClassName = clazz.getCanonicalName();
         mSimpleTypeName = clazz.getSimpleName();
      } catch (MirroredTypeException mte) {// 该类未被编译
         DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
         TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
         mQualifiedSuperClassName = classTypeElement.getQualifiedName().toString();
         mSimpleTypeName = classTypeElement.getSimpleName().toString();
      }
   }

}
