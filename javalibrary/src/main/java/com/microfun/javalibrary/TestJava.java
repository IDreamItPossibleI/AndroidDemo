package com.microfun.javalibrary;

public class TestJava {

    private static int count = 0;

    public static void main(String[] args) {
        System.out.println("test java");
        char[] c = toString(5896);
        System.out.println(c);
    }

    //原理： ‘9’相当于ascii中的57，(char)(num + ‘0’)相当于9+48得数字57转为字符为’9’;
    public static char[] toString(int num) {
        char[] c = new char[4];
        c[0] = (char) (num/1000 + '0');
        c[1] = (char) ((num/100)%10 + '0');
        c[2] = (char) ((num/10)%10 + '0');
        c[3] = (char) (num%10 + '0');
        return c;
    }

    public static void testfunc() {
        for(int i=0; i<1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
        }
    }

}
