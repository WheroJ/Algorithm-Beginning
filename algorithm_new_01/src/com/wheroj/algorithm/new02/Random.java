package com.wheroj.algorithm.new02;

public class Random {

    public static void main(String[] args) {
        double x = 0.67, total = 10000000;

        int count = 0;
        for (int i = 0; i < total; i++) {
            if (xOneSubPow2() < x) {
                count ++;
            }
        }
        System.out.println(count*1.0/total*1.0);
        System.out.println(1 - Math.pow(1 - x, 2));

        count = 0;
        for (int i = 0; i < 10000; i++) {
            int x1 = zeroOne(1, 5);
            if (x1 == 0) {
                count++;
            }
        }
        System.out.println(count*1.0/100);

        g();
    }

    /**
     * 题目：按照x平方分之一的概率返回[0,x)上的数
     * @return
     */
    private static double xPow2() {
        return Math.max(Math.random(), Math.random());
    }

    private static double xOneSubPow2() {
        return Math.min(Math.random(), Math.random());
    }

    /**
     * 等概率返回[x,y)的整数
     * @param x
     * @param y
     * @return
     */
    private static int randomNum(int x, int y) {
        return x + (int)(Math.random() * (y - x + 1));
    }

    /**
     * 等概率返回0和1
     * @param start
     * @param end
     * @return
     */
    private static int zeroOne(int start, int end) {
        int diff = end - start + 1;
        if (diff % 2 == 0) {
            return (randomNum(start, end) < (diff/2 + start)) ? 0 : 1;
        } else {
            int middle = start + (diff -1)/2;
            int randomNum = middle;
            while (randomNum == middle) {
                randomNum = randomNum(start, end);
            }
            return randomNum < middle? 0: 1;
        }
    }

    /**
     * 等概率返回0-5
     * @return
     */
    private static int f() {
        int i = 7;
        while (i==7) {
            i = (zeroOne(1, 5) << 2) + (zeroOne(1, 5) << 1) + zeroOne(1, 5);
        }
        return i;
    }

    /**
     * 题目：等概率返回1-7
     */
    private static void g() {
        int count = 0, total = 1000000;

        // 0 - 7上等概率
        for (int j = 0; j < total; j++) {
            if ((f() + 1) == 7) {
                count++;
            }
        }
        System.out.println(count*1.0/total);
    }

}
