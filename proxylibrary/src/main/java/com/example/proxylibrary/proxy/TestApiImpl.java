package com.example.proxylibrary.proxy;

import android.util.Log;

public class TestApiImpl implements TestApi {
   @Override
   public void test0() {
      Log.e("zgf", "=======test0=======");
   }

   @Override
   public void test1(int i) {
      Log.e("zgf", "=======test1=======" + i);
   }

   @Override
   public String test2(int i) {
      Log.e("zgf", "=======test2=======" + i);
      return "test2 return " + i;
   }
}
