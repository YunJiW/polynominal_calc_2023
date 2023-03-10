package org.example;

public class Calc {


    public static int run(String exp) {
        //"- "를 "+ -"로 바꿔준다. 모든연산을 더하기로 바꾸자라는 생각
        exp = exp.replaceAll("\\- ","\\+ \\-");
        String[] bits = exp.split(" \\+ ");

        int sum = 0;

        for(int idx = 0; idx < bits.length;idx++){
            sum += Integer.parseInt(bits[idx]);
        }

        return sum;
    }
}
