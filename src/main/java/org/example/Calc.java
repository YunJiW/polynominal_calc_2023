package org.example;

public class Calc {


    public static int run(String exp) {
        boolean needToMulti = exp.contains("*");
        boolean needToPlus = !needToMulti;
        //"- "를 "+ -"로 바꿔준다. 모든연산을 더하기로 바꾸자라는 생각


        String[] bits = null;
        if(needToPlus){
            exp = exp.replaceAll("\\- ","\\+ \\-");
            bits = exp.split(" \\+ ");
        }else if(needToMulti){
            bits = exp.split(" \\* ");
        }


        int sum = 0;

        if(needToPlus) {
            for (int idx = 0; idx < bits.length; idx++) {
                sum += Integer.parseInt(bits[idx]);
            }
        }else if(needToMulti){
            sum = 1;
            for (int idx = 0; idx < bits.length; idx++) {
                sum *= Integer.parseInt(bits[idx]);
            }
        }

        return sum;
    }
}
