package algorithm.sortalgorithm;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-18
 */

public class QuickSort {
    // N*logN -> N*N
    public static void main(String[] args) {
        int[] array = new int[]{3, 88, 9, 4, 0, 749, 72, 892, 234, 22};

        int start = 0;
        int end = array.length - 1;
        doQuickSort(start, end, array);

        for (int element : array) {
            System.out.print(element + "\t");
        }

    }

    // N
    private static int doPartition(int start, int end, int[] array) {
        // 初始化
        int initPartition = array[start];
        int i = start;
        int j = end;

        while (i < j) {
            // 从后往前
            while (i < j && initPartition <= array[j]) {
                j--;
            }
            // 碰到 initPartition > array[j]，将array[j]放到前面
            if (i < j) {
                array[i] = array[j];
                i++;
            }

            // 从前往后
            while (i < j && initPartition >= array[i]) {
                i++;
            }
            // 碰到 initPartition < array[j]，将array[j]放到后面
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }

        // i == j时，将initPartition插入array[i]中，并返回i
        array[i] =  initPartition;
        return i;
    }

    // logN
    private static void doQuickSort(int start, int end, int[] array) {
        if (start < end) {
            // 先二分
            int partition = doPartition(start, end, array);
            // 左边递归
            doQuickSort(start, partition - 1, array);
            // 右边递归
            doQuickSort(partition + 1, end, array);
        }
    }
}
