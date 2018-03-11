package mhy.utils;

import org.junit.Test;

public class StringThreadTest {

    private static final int THREAD_COUNT = 5;
    
    public static void main(String[] args) {
    	  Thread[] threads = new Thread[THREAD_COUNT];
          for (int i = 0; i < THREAD_COUNT; i++) {
              threads[i] = new Thread(new StringThread("192.168.1.1"));
          }
          
          for (int i = 0; i < THREAD_COUNT; i++) {
              threads[i].start();
          }
	}
}