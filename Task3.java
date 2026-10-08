import java.util.Arrays;

// Задание 3: уровни доступа 0..3, устойчивая сортировка подсчетом
// Сложность: O(n + 4), то есть O(n); память O(n)
// Запуск: javac CountingSort.java && java CountingSort
public class CountingSort {

    public static void main(String[] args) {
        int[] arr1 = {3, 0, 2, 3, 1};
        System.out.println(Arrays.toString(arr1));
        sort(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {2, 2, 0, 3};
        System.out.println(Arrays.toString(arr2));
        sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) return;

        int[] count = new int[4];
        for (int val : arr) {
            // уровень вне 0..3 не подходит, массив не трогаем
            if (val < 0 || val > 3) return;
            count[val]++;
        }

        // count[i] = где заканчивается группа уровня i
        for (int level = 1; level < 4; level++) {
            count[level] += count[level - 1];
        }

        // с конца, чтобы порядок внутри групп сохранился
        int[] result = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            result[--count[arr[i]]] = arr[i];
        }

        System.arraycopy(result, 0, arr, 0, result.length);
    }
}
