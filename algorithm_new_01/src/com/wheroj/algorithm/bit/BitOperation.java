package com.wheroj.algorithm.bit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/divide-two-integers/submissions/
 */
public class BitOperation {

    private static int add(int a, int b) {
        int sum = a;
        while (b!=0) {
            // 异或等同于无符号相加
            sum = a^b;
            // 相加的进位信息
            b = (a&b)<<1;
            a= sum;
        }
        return sum;
    }

    private static int minus(int a, int b) {
        // a -b = a + (-b) = a + (~b + 1)
        return add(a, add(~b, 1));
    }

    private static int multi(int a, int b) {
        if (a == Integer.MIN_VALUE || b == Integer.MIN_VALUE) {
            return isNeg(a) ^ isNeg(b) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int ans = 0;
        for (int i = 0; i < 31 && y != 0; i++) {
            if ((y & (1<<i)) != 0) {
                ans = add(ans, x<<i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(ans) : ans;
    }

    /**
     * 老师的解法
     * @param a
     * @param b
     * @return
     */
    private static int multiByTeacher(int a, int b) {
        int ans = 0;
        while (b!=0) {
            if ((b&1) != 0) {
                add(ans, a);
            }
            b>>>=1;
            a<<=1;
        }
        return ans;
    }

    private static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;

        int ans = 0;
        for (int i = 30; i >= 0 ; i--) {
            if ((x>>i) >= y) {
               ans |= (1<<i);
               x = minus(x, y<<i);
            }
        }

        return isNeg(a) ^ isNeg(b) ? negNum(ans) : ans;
    }

    private static boolean isNeg(int num) {
        return num < 0;
    }

    private static int negNum(int num) {
        return add(~num, 1);
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }

        if (dividend == divisor) {
            return 1;
        }

        if (divisor == Integer.MIN_VALUE) {
            // 没有可以整除的整数(虽然是负数，但是数字却是最大的)
            return 0;
        } else if (dividend == Integer.MIN_VALUE) {
            if (divisor == negNum(1)) {
                return  Integer.MAX_VALUE;
            }
            // 除数为最大整型，求绝对值没有与之对应的整数，所以先+1再求绝对值
            // (dividend+1)/divisor
            int div = div(add(dividend, 1), divisor);
            // c = divisor * div
            // d = dividend - c
            // ans = div + d/divisor
            int multi = multi(divisor, div);
            int minus = minus(dividend, multi);
            int rem = div(minus, divisor);
            return add(div, rem);
        }
        return div(dividend, divisor);
    }

    private static void printBit(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num&(1<<i))==0?"0":"1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        System.out.println(add(-1, -2));
//        System.out.println(add(26,25));
//
//        System.out.println(minus(0,0));
//        System.out.println(minus(Integer.MIN_VALUE,71));
//        System.out.println(minus(71,13));
//
//        System.out.println(multi(0,0));
//        System.out.println(multi(-13,71));
//        System.out.println(multi(71,13));
//        System.out.println(multi(71,0));

//        printBit(-10);
//        printBit(-10>>>1);
//        System.out.println(-10>>>1);
//
//        System.out.println(multi(-1, -1));
//        System.out.println(multi(-1, 10));
        System.out.println(divide(7,-3));

        // AtomicInteger Test
        AtomicInteger atomicInteger = new AtomicInteger(1);

        CountDownLatch countDownLatch = new CountDownLatch(3);
        int testTimes = 10000;
        Runnable runnable = () -> {
            for (int i = 0; i < testTimes; i++) {
                atomicInteger.addAndGet(1);
            }
            countDownLatch.countDown();
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicInteger.get());
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    }

}
