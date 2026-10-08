import java.util.Arrays;

// Задание 2: идентификаторы от 0 до 999 999 999, поразрядная сортировка (LSD)
// Сложность: O(d * (n + 10)), d не больше 9, то есть O(n); память O(n)
// Запуск: javac RadixSort.java && java RadixSort
public class RadixSort {

    public static void main(String[] args) {
        int[] arr1 = {100000001, 7, 900000000};
        System.out.println(Arrays.toString(arr1));
        sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {42, 420, 402};
        System.out.println(Arrays.toString(arr2));
        sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) return;

        int max = arr[0];
        for (int val : arr) {
            // идентификатор вне 0..999999999 не подходит, массив не трогаем
            if (val < 0 || val > 999999999) return;
            max = Math.max(val, max);
        }

        for (int place = 1; max / place > 0; place *= 10) {
            countingSortByDigit(arr, place);
        }
    }

    private static void countingSortByDigit(int[] arr, int place) {
        int[] count = new int[10];
        for (int val : arr) {
            count[(val / place) % 10]++;
        }

        for (int digit = 1; digit < 10; digit++) {
            count[digit] += count[digit - 1];
        }

        int[] result = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / place) % 10;
            result[--count[digit]] = arr[i];
        }

        System.arraycopy(result, 0, arr, 0, result.length);
    }
}
