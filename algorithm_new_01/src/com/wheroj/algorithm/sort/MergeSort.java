package com.wheroj.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序：递归实现，非递归实现
 */
public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        if (l >= r || mid + 1 > r) {
            return ;
        }

        int[] heap = new int[r - l + 1];

        int index = -1;
        int LStart = l;
        int RStart = mid + 1;
        while (LStart <= mid && RStart <= r) {
            if (arr[LStart] < arr[RStart]) {
                heap[++index] = arr[LStart++];
            } else {
                heap[++index] = arr[RStart++];
            }
        }

        while (LStart <= mid) {
            heap[++index] = arr[LStart++];
        }

        while (RStart <= r) {
            heap[++index] = arr[RStart++];
        }

        index = -1;
        for (int i = l; i <= r; i++) {
            arr[i] = heap[++index];
        }
    }

    /**
     * 非递归实现归并排序
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int step = 1;
        int N = arr.length;
        while (step < N) {
            int L = 0;
            int R = Math.min(L + step - 1, N - 1);
            int M = L + ((R - L)>>1);
            while (M < N - 1) {
                System.out.print("Merge Before:");
                print(arr);
                merge(arr, L, M, R);
                System.out.print("Merge After [" + L + "->" + M + "->" + R + "] : ");
                print(arr);
                L = R + 1;
                R = Math.min(L + step - 1, N - 1);
                M = L + ((R - L)>>1);
            }

            if (step > N/2) {
                break;
            }
            step<<=1;
            System.out.println(step);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] randomArray(int maxSize, int maxVal) {
        int[] arr = new int[(int) (Math.random() * maxSize)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxVal);
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    private static boolean arrayEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTimes = 2, maxSize = 10, maxVal = 100;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxSize, maxVal);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            mergeSort(arr1);
            mergeSort2(arr2);
            Arrays.sort(arr);
            if (!arrayEquals(arr1, arr) || !arrayEquals(arr, arr2)) {
                print(arr);
                print(arr1);
                print(arr2);
                System.out.println("Oops! 出错拉");
            }
        }
        System.out.println("没有问题！");

        int[] arr = new int[] {1,3,5,7,9,2,4,6,8,10};
        mergeSort2(arr);
//        merge(arr, 0, 4, 9);
        print(arr);
    }
}
