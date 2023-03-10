package org.example;

public class Calc {


    public static int run(String exp) {
        //"- "를 "+ -"로 바꿔준다. 모든연산을 더하기로 바꾸자라는 생각
        exp = exp.replaceAll("\\- ","\\+ \\-");
        String[] bits = exp.split(" \\+ ");


        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[1]);
        int c = 0;

        if(bits.length > 2){
            c = Integer.parseInt(bits[2]);
        }

        return a+b+c;
    }
}
