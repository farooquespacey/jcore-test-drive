package com.spacey.effective._07;

import java.util.WeakHashMap;

public class _3WeakMapDemo {

	public static void main(String[] args) throws InterruptedException {
		 WeakHashMap<Demo, String> m = new WeakHashMap<>(); 
	        Demo d = new Demo(); 
	          
	        // puts an entry into WeakHashMap 
	        m.put(d," Hi ");  
	        System.out.println(m); 
	          
	        d = null; 
	          
	        // garbage collector is called 
	        System.gc();  
	          
	        // thread sleeps for 4 sec 
	        Thread.sleep(4000);  
	          
	        System.out.println(m); 
	}

	
	static class Demo 
    { 
        public String toString() 
        { 
            return "demo"; 
        } 
          
        // finalize method 
        public void finalize() 
        { 
            System.out.println("finalize method is called"); 
        } 
    } 
}
