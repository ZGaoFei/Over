package com.example.proxylibrary.factory;

import android.util.Log;

import com.example.annotation_lib.factory.Factory;

@Factory(type = Shape.class, id = "Rectangle")
public class Rectangle implements Shape {
   @Override
   public void draw() {
      Log.e("shape", "======Rectangle======= draw rectangle");
   }
}
