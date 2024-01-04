package com.example.proxylibrary.factory;

import android.util.Log;

import com.example.annotation_lib.factory.Factory;

@Factory(type = Shape.class, id = "Circle")
public class Circle implements Shape {

   @Override
   public void draw() {
      Log.e("shape", "=====Circle==== draw circle");
   }
}
