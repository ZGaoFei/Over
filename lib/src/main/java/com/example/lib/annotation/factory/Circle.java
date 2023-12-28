package com.example.lib.annotation.factory;

@Factory(type = Circle.class, id = "Circle")
public class Circle implements IShape {
   @Override
   public void draw() {
      System.out.println("Circle draw");
   }
}
