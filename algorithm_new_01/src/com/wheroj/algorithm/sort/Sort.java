package com.wheroj.algorithm.sort;

public class Sort {

    private static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2){
            return arr;
        }

        int N = arr.length;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
        return arr;
    }

    private static int[] selectSort(int[] arr) {
        if (arr == null || arr.length < 2){
            return arr;
        }

        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minNumIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[minNumIndex] > arr[j]) {
                    minNumIndex = j;
                }
            }
            swap(arr, i, minNumIndex);
        }
        return arr;
    }

    private static int[] insertSort(int[] arr) {
        if (arr == null || arr.length < 2){
            return arr;
        }

        int N = arr.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j >= 1 ; j--) {
                if (arr[j] >= arr[j - 1]) {
                    break;
                }
                swap(arr, j, j-1);
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] arr = new int[] {6, 3, 1, 6, 5, -2, 8, 4, 7};
        printArray(arr);
        printArray(selectSort(arr));
        printArray(bubbleSort(arr));
        printArray(insertSort(arr));
    }
}
