package algorithm;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-27
 */

public class QuickSelect {

    public static void main(String[] args) {
        int[] array = new int[]{3, 88, 9, 4, 0, 749, 72, 892, 234, 22, 99, 5};

        doQuickSelect(array, 0, array.length, array.length / 2);

        for (int element : array) {
            System.out.print(element + "\t");
        }
    }

    public static void doQuickSelect(int[] array, int start, int end, int k) {
        int partition = doPartition(array, start, end);
        if (partition - 1 > k) {
            doQuickSelect(array, start, partition - 1, k);
        } else if (partition <= k) {
            doQuickSelect(array, partition, end, k);
        }
    }

    public static int doPartition(int[] array, int start, int end) {
        int t = array[end - 1];
        int i = start;
        int j = start;

        while (j < end) {
            if (array[j] <= t) {
                swap(array, i++, j++);
            } else {
                j++;
            }
        }

        return i;
    }

    public static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
