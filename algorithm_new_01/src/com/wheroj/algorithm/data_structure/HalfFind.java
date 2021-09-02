package com.wheroj.algorithm.data_structure;

public class HalfFind {

    private static int sortFind(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int half = R/2;
        while (half >= L && half <= R) {
            if (arr[half] == num) {
                return half;
            }
            if (arr[half] > num) {
                R = half - 1;
            } else {
                L = half + 1;
            }
            half = (R + L) / 2;
        }
        return -1;
    }

    private static int findLENumLastLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int half = R/2;
        int mostLeft = -1;
        while (half >= L && half <= R) {
            if (arr[half] >= num) {
                mostLeft = half;
                R = half - 1;
            } else {
                L = half + 1;
            }
            half = (R + L) / 2;
        }
        return mostLeft;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,5,7,9,10};
        System.out.println(sortFind(arr, 1));

        int[] arr2 = new int[]{1,4,5,7,9,10,11,13,19};
        System.out.println(sortFind(arr, 13));
        System.out.println(sortFind(arr2, 13));
        System.out.println(sortFind(arr2, 11));
        System.out.println(findLENumLastLeft(arr2, 2));
    }
}
