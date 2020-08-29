package algorithm.sortalgorithm;

/**
 * @Description: 堆排序
 * @Author: mtdp
 * @Date: 2020-08-20
 */

public class HeapSort {
    public static void main(String[] args) {
        int[] array = new int[]{3, 88, 9, 4, 0, 749, 72, 892, 234, 22};

        doHeapSort(array);

        for (int element : array) {
            System.out.print(element + "\t");
        }

    }

    // N * logN
    private static void doHeapSort(int[] array) {
        // 建立大根堆，从最后一个非叶子节点开始 N
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }

        // 排序，将堆顶放到最后，然后重新调整堆 N * logN
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, i, 0);
            adjustHeap(array, 0, i);
        }

    }

    // 从左往右，从上往下一点点调整
    private static void adjustHeap(int[] array, int i, int length) {
        // 想把当前元素取出来，这个元素可能会一直往下移动
        int temp = array[i];

        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 比较左右两个子数的大小，从左往右
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }

            if (temp < array[k]) {
                swap(array, i, k);
                // 从上到下
                i = k;
            } else {
                break;
            }
        }

    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
