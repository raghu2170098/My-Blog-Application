package com.myblog3;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

public class TestClass {
    public static void main(String[] args) {
////        TestClass testClass=new TestClass();
////        int val=testClass.test();
//////        System.out.println(val);
////        for(int i=0;i<5;i++)
////        {
////            System.out.println(i);
////        }
////        List<Integer> data= Arrays.asList(10,20,30,40,11,12,10,20,10,30,40,13,14,11,12);
////         List<Integer> val=data.stream().distinct().collect(Collectors.toList());
////        System.out.println(val);
////        System.out.println("Hello");
////        System.out.println("welcome");
//        System.out.println(Sort.Direction.ASC.name());
//
//    }
////    public int test()
////    {
////        int otp=SampleTest.test();
////        //  int x=100;
////        return otp;
////    }
        PasswordEncoder enocoder = new BCryptPasswordEncoder();
        String testing = enocoder.encode("testing");
        System.out.println(testing);
    }

}
