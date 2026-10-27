package practice2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// Задание 1: топологическая сортировка орграфа (алгоритм Кана)
// Сложность: O(V + E); память O(V + E)
public class Task1 {

    public static void main(String[] args) {
        // ребра задаются парами {откуда, куда}
        int[][] edges1 = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        System.out.println(Arrays.toString(sort(6, edges1)));

        // вершины 0, 1, 2 образуют цикл, порядка не существует
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println(Arrays.toString(sort(3, edges2)));

        // граф без ребер
        System.out.println(Arrays.toString(sort(3, new int[0][])));
    }

    // Возвращает вершины в топологическом порядке или null, если в графе есть цикл
    public static int[] sort(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        // в очередь идут вершины, у которых не осталось входящих ребер
        Queue<Integer> queue = new ArrayDeque<>();
        for (int v = 0; v < n; v++) {
            if (inDegree[v] == 0) queue.add(v);
        }

        int[] order = new int[n];
        int size = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            order[size++] = v;
            for (int to : adj.get(v)) {
                if (--inDegree[to] == 0) queue.add(to);
            }
        }

        // не все вершины извлечены, значит остался цикл
        return size == n ? order : null;
    }
}
