package com.wheroj.algorithm.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 递归实现
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        int[] range = f(arr, L, R);
        process(arr, L, range[0]);
        process(arr, range[1], R);
    }

    private static int[] f(int[] arr, int L, int R) {
        int index = 0, lessLeft = -1, gtRight = R;
        while (index < gtRight) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++lessLeft);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --gtRight);
            } else {
                index++;
            }
        }

        swap(arr, gtRight, R);
        return new int[] {lessLeft, gtRight + 1};
    }

    static class Job {
        int left;
        int right;

        public Job(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 非递归实现
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Job job = stack.pop();
            int[] range = f(arr, job.left, job.right);
            if (job.left < range[0]) {
                stack.push(new Job(job.left, range[0]));
            }

            if (job.right > range[1]) {
                stack.push(new Job(range[1], job.right));
            }
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
        int testTimes = 10000, maxSize = 100, maxVal = 100;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxSize, maxVal);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            quickSort(arr1);
            quickSort2(arr2);
            Arrays.sort(arr);
            if (!arrayEquals(arr1, arr) || !arrayEquals(arr2, arr)) {
                print(arr);
                print(arr1);
                print(arr2);
                System.out.println("Oops! 出错拉");
            }
        }
        System.out.println("没有问题！");

        int[] arr = new int[] {7,4,3,4,2,5,2,7,6,5,8,9,9,5};
        quickSort(arr);
        print(arr);
    }

}
