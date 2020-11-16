package com.example.demo.test.BIgDecimal;

import java.math.BigDecimal;

public class BigDecoimal {
    public static void main(String[] args) {

        BigDecimal num1 = new BigDecimal(0.005);
        BigDecimal num2 = new BigDecimal(1000000);
        BigDecimal num3 = new BigDecimal(-1000000);
        //尽量用字符串的形式初始化
        BigDecimal num1s = new BigDecimal("0.005");
        BigDecimal num2s = new BigDecimal("1000000");
        BigDecimal num3s = new BigDecimal("-1000000");

        //加法
        BigDecimal result1 = num1.add(num2);
        BigDecimal result1s = num1s.add(num2s);
        //减法
        BigDecimal result2 = num1.subtract(num2);
        BigDecimal result2s = num1s.subtract(num2s);
        //乘法
        BigDecimal result3 = num1.multiply(num2);
        BigDecimal result3s = num1s.multiply(num2s);
        //绝对值
        BigDecimal result4 = num3.abs();
        BigDecimal result4s = num3s.abs();
        //除法
        BigDecimal result5 = num2.divide(num1, 20, BigDecimal.ROUND_HALF_UP);
        BigDecimal result5s = num2s.divide(num1s, 20, BigDecimal.ROUND_HALF_UP);

        System.out.println("加法用value结果：" + result1);
        System.out.println("加法用string结果：" + result1s +"\r\n");

        System.out.println("减法value结果：" + result2);
        System.out.println("减法用string结果：" + result2s+"\r\n");

        System.out.println("乘法用value结果：" + result3);
        System.out.println("乘法用string结果：" + result3s+"\r\n");

        System.out.println("绝对值用value结果：" + result4);
        System.out.println("绝对值用string结果：" + result4s+"\r\n");

        System.out.println("除法用value结果：" + result5);
        System.out.println("除法用string结果：" + result5s+"\r\n");

        //除
        BigDecimal Dividend = new BigDecimal("1");
        BigDecimal divisor = new BigDecimal("3");

        BigDecimal res1 = Dividend.divide(divisor,3,BigDecimal.ROUND_UP);
        System.out.println("除法ROUND_UP："+res1);
        BigDecimal res2 = Dividend.divide(divisor,3,BigDecimal.ROUND_DOWN);
        System.out.println("除法ROUND_DOWN："+res2);
        BigDecimal res3 = Dividend.divide(divisor,3,BigDecimal.ROUND_CEILING);
        System.out.println("除法ROUND_CEILING："+res3);
        BigDecimal res4 = Dividend.divide(divisor,3,BigDecimal.ROUND_FLOOR);
        System.out.println("除法ROUND_FLOOR："+res4);
        BigDecimal res5 = Dividend.divide(divisor,3,BigDecimal.ROUND_HALF_UP);
        System.out.println("除法ROUND_HALF_UP："+res5);
        BigDecimal res6 = Dividend.divide(divisor,3,BigDecimal.ROUND_HALF_DOWN);
        System.out.println("除法ROUND_HALF_DOWN："+res6);
        BigDecimal res7 = Dividend.divide(divisor,3,BigDecimal.ROUND_HALF_EVEN);
        System.out.println("除法ROUND_HALF_EVEN："+res7);
//        BigDecimal res8 = Dividend.divide(divisor,3,BigDecimal.ROUND_UNNECESSARY);
//        System.out.println("除法ROUND_UNNECESSARY："+res8);

        BigDecimal a = new BigDecimal("100");
        BigDecimal b = new BigDecimal("12");
        //取余数
        BigDecimal[] res = a.divideAndRemainder(b);
        BigDecimal Quotient = res[0];//商
        System.out.println("Quotient = " + Quotient);
        BigDecimal remainderres = res[1];//余数
        System.out.println("remainderres = " + remainderres);

    }
}
