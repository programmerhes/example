package com.es.example.utils;

public class Print {

    public static void consolePrint(String str,Object... args) {
        System.out.println(String.format(str, args));
    }

    public static void consolePrint(String str) {
        System.out.println(str);
    }
}
