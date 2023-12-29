1、动态代理
2、定义注解
3、动态获取注解内容
4、反射
5、运行时反射
6、编译时反射
7、编译时生成文件 APT

8、编译时插入代码，ASM

注解处理器 + 注解
反射 + 注解 + 注解处理器

注意：
使用注解在编译时生成辅助代码步骤
1、创建一个 java-library 用来定义注解相关内容
注解定义
2、创建一个 java-library 用来实现process
继承自AbstractProcessor，并实现process方法
使用@AutoService(Processor.class)标记，用于生产注册相关内容
在process方法中去生成对应的代码
3、创建一个 android-library 用来定义BindHelper类
使用类加载器来创建生产的类对象
4、使用
在子 android-library 中使用资源ID相关内容时会报错（Attribute value must be constant），
因为Android studio会自动生成R文件，该文件位于app包下，并且为非常量的
因此避免在子 android-library 中使用相关ID
butterknife 在编译时自动生成了一份R2文件，因此需要使用R2文件

auto-service：自动注册注解处理器
javapoet：用于生产代码

参考：https://www.cnblogs.com/wellcherish/p/17087711.html