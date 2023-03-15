package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String exp) {
        exp.trim();
        //쓸데없는 바깥쪽 괄호 제거
        exp = stripOuterBrackets(exp);
        //단일 항인 경우 리턴
        if (!exp.contains(" ")) return Integer.parseInt(exp);
        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+") || exp.contains(" - ");
        boolean neadToSplit = exp.contains("(") || exp.contains(")");
        //복합인지 체크
        boolean needToCompund = needToMulti && needToPlus;

        if(neadToSplit){
            int bracketsCount = 0;
            int splitPointIndex = -1;

            for(int idx = 0; idx <exp.length();idx++){
                if(exp.charAt(idx) == '('){
                    bracketsCount++;
                }else if(exp.charAt(idx) == ')')
                    bracketsCount--;


                if(bracketsCount == 0){
                    splitPointIndex = idx;
                    break;
                }
            }

            String firstExp = exp.substring(0, splitPointIndex + 1);
            String secondExp = exp.substring(splitPointIndex+4);

            char oper = exp.charAt(splitPointIndex + 2);
            exp = Calc.run(firstExp) + " " +oper + " " + Calc.run(secondExp);
            return Calc.run(exp);
        }




        if (needToCompund) {
            exp = exp.replaceAll("- ","+ -");
            String bits[] = exp.split(" \\+ ");

            //얘가 Stream형식 중요
            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newExp);
        }
        if (needToPlus) {
            exp = exp.replaceAll("- ", "+ -");
            String bits[] = exp.split(" \\+ ");
            int sum = 0;
            for (int idx = 0; idx < bits.length; idx++) {
                sum += Integer.parseInt(bits[idx]);
            }
            return sum;
        } else if (needToMulti) {
            String bits[] = exp.split(" \\* ");
            int sum = 1;
            for (int idx = 0; idx < bits.length; idx++) {
                sum *= Integer.parseInt(bits[idx]);
            }
            return sum;
        }
        throw new RuntimeException("올바른 계산식이 아닙니다.");
    }

    private static String stripOuterBrackets(String exp) {
        int outbracket = 0;
        while(exp.charAt(outbracket) == '(' && exp.charAt(exp.length()-1 -outbracket) == ')'){
            outbracket+=1;
        }
        if(outbracket ==0)
            return exp;

        return exp.substring(outbracket,exp.length() - outbracket);
    }


}
