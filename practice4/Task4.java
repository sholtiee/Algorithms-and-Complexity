package practice4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringJoiner;

// Задание 4: учебный план, топологическая сортировка (алгоритм Кана)
// Сложность: O((n + m) log n) из-за кучи; память O(n + m)
public class Task4 {

    public static void main(String[] args) {
        // пары {prerequisite, course}
        int[][] pairs1 = {{0, 2}, {1, 2}, {2, 3}, {1, 4}};
        print(solve(5, pairs1));

        // курсы 0, 1, 2 зависят друг от друга по кругу
        int[][] pairs2 = {{0, 1}, {1, 2}, {2, 0}};
        print(solve(3, pairs2));
    }

    // Возвращает допустимый порядок курсов или null, если есть цикл
    public static int[] solve(int n, int[][] pairs) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[] inDegree = new int[n];
        for (int[] p : pairs) {
            adj.get(p[0]).add(p[1]);
            inDegree[p[1]]++;
        }

        // куча вместо очереди дает наименьший по номерам порядок
        PriorityQueue<Integer> ready = new PriorityQueue<>();
        for (int v = 0; v < n; v++) {
            if (inDegree[v] == 0) ready.add(v);
        }

        int[] order = new int[n];
        int size = 0;
        while (!ready.isEmpty()) {
            int v = ready.poll();
            order[size++] = v;
            for (int to : adj.get(v)) {
                if (--inDegree[to] == 0) ready.add(to);
            }
        }

        // не все курсы извлечены, значит остался цикл
        return size == n ? order : null;
    }

    private static void print(int[] order) {
        if (order == null) {
            System.out.println("CYCLE");
            return;
        }
        StringJoiner out = new StringJoiner(" ");
        for (int v : order) out.add(String.valueOf(v));
        System.out.println(out);
    }
}
