package algorithm.sortalgorithm;

import java.util.Arrays;

/**
 * @Description: 归并排序
 * @Author: mtdp
 * @Date: 2020-08-18
 */

public class MergeSort {
    public static void main(String[] args) {
        int[] array = new int[]{3, 88, 9, 4, 0, 749, 72, 892, 234, 22};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        int[] temp = new int[array.length];
        doSort(array, 0, array.length - 1, temp);
    }

    private static void doSort(int[] array, int start, int end, int[] temp) {
        if (start < end) {
            int middle = start + (end - start) / 2;
            doSort(array, start, middle, temp);
            doSort(array, middle + 1, end, temp);
            merge(array, start, middle, end, temp);
        }
    }

    private static void merge(int[] array, int start, int middle, int end, int[] temp) {
        int leftIndex = start;
        int rightIndex = middle + 1;
        int tempIndex = 0;

        while (leftIndex <= middle && rightIndex <= end) {
            if (array[leftIndex] < array[rightIndex]) {
                temp[tempIndex++] = array[leftIndex++];
            } else {
                temp[tempIndex++] = array[rightIndex++];
            }
        }

        while (leftIndex <= middle) {
            temp[tempIndex++] = array[leftIndex++];
        }

        while (rightIndex <= end) {
            temp[tempIndex++] = array[rightIndex++];
        }

        tempIndex = 0;

        while (start <= end) {
            array[start++] = temp[tempIndex++];
        }
    }
}
