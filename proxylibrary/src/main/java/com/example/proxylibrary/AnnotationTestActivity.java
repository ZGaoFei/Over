package com.example.proxylibrary;

import android.util.Log;
import android.view.View;

import com.example.base.base.BaseActivity;
import com.example.proxylibrary.annotation.User;
import com.example.proxylibrary.annotation.UserAgeFiled;
import com.example.proxylibrary.annotation.UserAnnotation;
import com.example.proxylibrary.annotation.UserMethodAnnotation;
import com.example.proxylibrary.annotation.UserNameFiled;
import com.example.proxylibrary.databinding.ActivityAnnotationTestBinding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationTestActivity extends BaseActivity {

    private ActivityAnnotationTestBinding binding;

    @Override
    public void bindingView() {
        super.bindingView();

        binding = ActivityAnnotationTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void initView() {
        super.initView();

        binding.btAnnoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        binding.btAnnoUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserData();
            }
        });
        binding.btAnnoUserMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMethodData();
            }
        });
    }

    private void createUser() {
        Class<User> userClass = User.class;
        // 需要设置为运行时，不然获取不到
        if (userClass.isAnnotationPresent(UserAnnotation.class)) {
            UserAnnotation userAnnotation = userClass.getAnnotation(UserAnnotation.class);
            binding.tvAnnoUser.setText("age: " + userAnnotation.age() + ", name: " + userAnnotation.name());
        }
    }

    private void getUserData() {
        Class<User> userClass = User.class;
        try {
            Field age = userClass.getDeclaredField("age");
            Field name = userClass.getDeclaredField("name");
            String content = "";
            if (age.isAnnotationPresent(UserAgeFiled.class)) {
                UserAgeFiled ageFiled = age.getDeclaredAnnotation(UserAgeFiled.class);
                assert ageFiled != null;
                content = "age: " + ageFiled.value();
            }
            if (name.isAnnotationPresent(UserNameFiled.class)) {
                UserNameFiled nameFiled = name.getDeclaredAnnotation(UserNameFiled.class);
                content += ", name: " + nameFiled.value();
            }
            binding.tvAnnoUserData.setText(content);
        } catch (Exception e) {

        }
    }

    private void getMethodData() {
        Class<User> userClass = User.class;
        try {
            Method getUser = userClass.getDeclaredMethod("getUser");
            if (getUser.isAnnotationPresent(UserMethodAnnotation.class)) {
                UserMethodAnnotation annotation = getUser.getDeclaredAnnotation(UserMethodAnnotation.class);
                binding.tvAnnoUserMethod.setText(annotation.value());
            }
        } catch (Exception e) {

        }
    }
}