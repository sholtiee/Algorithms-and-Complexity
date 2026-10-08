import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Задание 1: координаты пикселей в [0, 1), карманная сортировка
// Сложность: в среднем O(n), в худшем O(n^2), память O(n)
// Запуск: javac BucketSort.java && java BucketSort
public class BucketSort {

    public static void main(String[] args) {
        double[] arr1 = {0.25, 0.10, 0.80, 0.45};
        System.out.println(Arrays.toString(arr1));
        sort(arr1);
        System.out.println(Arrays.toString(arr1));

        double[] arr2 = {0.77, 0.07, 0.70};
        System.out.println(Arrays.toString(arr2));
        sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    public static void sort(double[] arr) {
        if (arr.length == 0) return;
        int s = arr.length;
        List<List<Double>> buckets = new ArrayList<>(s);

        for (int i = 0; i < s; i++) {
            buckets.add(new ArrayList<>());
        }

        for (double val : arr) {
            // значение вне [0, 1) не подходит, массив не трогаем
            if (!(val >= 0 && val < 1.0)) {
                return;
            }
            // 0..1 -> 0..s
            int index = Math.min(s - 1, (int) (val * s));
            buckets.get(index).add(val);
        }

        int index = 0;
        for (List<Double> bucket : buckets) {
            insertionSort(bucket);
            for (double val : bucket) {
                arr[index++] = val;
            }
        }
    }

    private static void insertionSort(List<Double> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            double key = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > key) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }
}
