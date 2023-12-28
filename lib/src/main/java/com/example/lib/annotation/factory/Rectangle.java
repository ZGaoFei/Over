package com.example.lib.annotation.factory;

@Factory(type = Rectangle.class, id = "Rectangle")
class Rectangle implements IShape {

   @Override
   public void draw() {
      System.out.println("Rectangle draw");
   }
}
