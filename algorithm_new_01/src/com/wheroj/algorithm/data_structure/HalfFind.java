package com.wheroj.algorithm.data_structure;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 有序数组中找到>=num最左的位置（其实意思就是找到大于num最小的数）
     * @param arr
     * @param num
     * @return
     */
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

    /**
     * 有序数组中找到<=num最右的位置（其实意思就是找到<=num最大的数）
     * @param arr
     * @param num
     * @return
     */
    private static int findGENumLastRight(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int half = R/2;
        int mostLeft = -1;
        while (half >= L && half <= R) {
            if (arr[half] <= num) {
                mostLeft = half;
                L = half + 1;
            } else {
                R = half - 1;
            }
            half = (R + L) / 2;
        }
        return mostLeft;
    }

    private static int findPartMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr.length == 1) {
            return 0;
        }

        if (arr.length == 2) {
            return arr[0] > arr[1] ? 1 : 0;
        }

        if (arr[0] < arr[1]) {
            return 0;
        }

        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L <= mid && mid <= R) {
            mid = (R + L)/2;
            if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
                return mid;
            }
            if (arr[mid - 1] <= arr[mid]) {
                R = mid;
                continue;
            }

            if (arr[mid] >= arr[mid + 1]) {
                L = mid;
                continue;
            }
        }
        return -1;
    }

    private static int[] randomArr(int size, int maxVal) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            int random = (int) (Math.random() * maxVal);
            while (map.get(random) != null) {
                random = (int) (Math.random() * maxVal);
            }
            arr[i] = random;
            map.put(Integer.valueOf(random), 1);
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,5,7,9,10};
        System.out.println(sortFind(arr, 1));

        int[] arr2 = new int[]{1,4,5,7,9,10,11,13,19};
        System.out.println(sortFind(arr, 13));
        System.out.println(sortFind(arr2, 13));
        System.out.println(sortFind(arr2, 11));
        System.out.println(findLENumLastLeft(arr2, 2));
        System.out.println(findGENumLastRight(arr2, 2));

//        int[] randomArr = randomArr(10, 100);
//        printArray(randomArr);
//        System.out.println("part min value:" + findPartMin(randomArr));
        for (int i = 0; i < 100000; i++) {
            int[] randomArr = randomArr(20, 100);
            System.out.println(findPartMin(randomArr));
        }
    }
}
