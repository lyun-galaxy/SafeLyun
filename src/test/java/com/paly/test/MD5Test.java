package com.paly.test;

import java.security.MessageDigest;

import org.junit.Test;

public class MD5Test {
	@Test
	public void testMD() {  
        try {             
            String username = "Vicky";  
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.update(username.getBytes());  
            String usernameMD5 = messageDigest.digest().toString();  
            System.out.println(usernameMD5);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
}
