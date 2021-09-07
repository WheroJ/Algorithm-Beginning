package com.wheroj.algorithm.bit;

public class BitCalculate {

    private static void printBit(int num) {
        for (int i = 31; i >= 0; i--) {
            int temp = num & (1 << i);
            System.out.print(temp == 0? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printBit(Integer.MIN_VALUE);
        printBit(~Integer.MIN_VALUE + 1);
        System.out.println(Integer.MIN_VALUE);
        printBit(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.pow(2, 31) - 1);
        System.out.println(-Math.pow(2, 31));
    }
}
