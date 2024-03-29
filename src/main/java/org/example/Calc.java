package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String exp) {
        exp = exp.trim();
        //쓸데없는 바깥쪽 괄호 제거
        exp = stripOuterBrackets(exp);
        //단일 항인 경우 리턴
        if (!exp.contains(" ")) return Integer.parseInt(exp);
        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");
        boolean neadToSplit = exp.contains("(") || exp.contains(")");
        //복합인지 체크
        boolean needToCompund = needToMulti && needToPlus;

        if(neadToSplit){
            int splitPointIndex = findSplitPointIndex(exp);

            String firstExp = exp.substring(0,splitPointIndex);
            String secondExp = exp.substring(splitPointIndex+1);

            char oper = exp.charAt(splitPointIndex);

            exp  = Calc.run(firstExp) + " " + oper + " " + Calc.run(secondExp);

            return Calc.run(exp);
        }

        else if (needToCompund) {
            exp = exp.replaceAll("- ","+ -");
            String bits[] = exp.split(" \\+ ");

            //얘가 Stream형식 중요
            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newExp);
        }
        else if (needToPlus) {
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

    private static int findSplitPointIndex(String exp) {
        int index = findSplitPointIndexBy(exp,'+');

        if( index >= 0) return  index;

        return findSplitPointIndexBy(exp,'*');

    }

    private static int findSplitPointIndexBy(String exp, char findChar) {
        int bracketsCount = 0;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if ( c == '(' ) {
                bracketsCount++;
            }
            else if ( c == ')' ) {
                bracketsCount--;
            }
            else if ( c == findChar ) {
                if ( bracketsCount == 0 ) return i;
            }
        }

        return -1;

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
