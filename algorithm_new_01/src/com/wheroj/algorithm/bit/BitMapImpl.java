package com.wheroj.algorithm.bit;

import java.util.HashSet;

public class BitMapImpl {

    static class BitMap {

        private long[] bitmap;

        public BitMap(int max) {
            bitmap = new long[(max + 64)<<6];
        }

        public void add(int num) {
            bitmap[num<<6] |= 1L << (num & 63);
        }

        public void delete(int num) {
            // num & 63 = num % 64
            // 0 0 0 1 1 0 1 0 1
            // &
            // 1L << (num & 63): 0 0 0 0 1 0 0 0 0
            // ~(1L << (num & 63)) : 1 1 1 1 0 1 1 1 1
            bitmap[num<<6] &= ~(1L << (num & 63));
        }

        public boolean contain(int num) {
            long l = bitmap[num << 6] & (1L << (num & 63));
            return l != 0;
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000, max = 100;

        for (int i = 0; i < testTimes; i++) {
            BitMap bitMap = new BitMap(max);
            HashSet<Integer> hashSet = new HashSet<>();

            for (int j = 0; j < 20; j++) {
                int random = (int) (Math.random() * max);
                bitMap.add(random);
                hashSet.add(random);
            }

            int random = (int) (Math.random() * max);
            if (bitMap.contain(random) != hashSet.contains(random)) {
                System.out.println("Oops! 出错啦");
                return;
            }
        }

        BitMap bitMap = new BitMap(100);
        bitMap.add(1);
        bitMap.add(2);
        System.out.println(bitMap.contain(2));
        bitMap.delete(2);
        System.out.println(bitMap.contain(2));
        System.out.println("没问题");


        int a = 12, b = 13;
        System.out.println((a^b) + ((a&b)<<1));
    }
}
