package org.example;

public class Calc {


    public static int run(String exp) {
        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+");

        //복합인지 체크
        boolean needToCompund = needToMulti && needToPlus;

        if(needToCompund){
            String[] bits = exp.split(" \\+ ");

            return Integer.parseInt(bits[0]) + run(bits[1]);
        }


        int sum = 0;

        if(needToPlus) {
            String bits[] = exp.split(" \\+ ");
            for (int idx = 0; idx < bits.length; idx++) {
                sum += Integer.parseInt(bits[idx]);
            }
        }else if(needToMulti){
            String bits[] = exp.split(" \\* ");
            sum = 1;
            for (int idx = 0; idx < bits.length; idx++) {
                sum *= Integer.parseInt(bits[idx]);
            }
        }

        return sum;
    }
}
