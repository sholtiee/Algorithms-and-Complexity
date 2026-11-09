package practice4;

import java.util.Arrays;

// Задание 3: сеть датчиков, алгоритм Краскала с системой непересекающихся множеств
// Сложность: O(m log m); память O(n + m)
public class Task3 {

    public static void main(String[] args) {
        // ребра задаются тройками {u, v, длина кабеля}, первое ребро петля
        int[][] edges1 = {{0, 0, 0}, {0, 1, 5}, {1, 2, 6}, {0, 2, 20}};
        System.out.println(solve(3, edges1));

        // несколько петель и кратное ребро
        int[][] edges2 = {{1, 1, 1}, {2, 2, 2}, {0, 1, 4}, {0, 1, 3}, {1, 2, 2}};
        System.out.println(solve(3, edges2));
    }

    // Возвращает стоимость остова и число выбранных ребер; петли не выбираются
    public static String solve(int n, int[][] edges) {
        int[][] sorted = edges.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[2], b[2]));

        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        long cost = 0;
        int chosen = 0;
        for (int[] e : sorted) {
            // у петли u u корни совпадают, она пропускается
            int ru = find(parent, e[0]);
            int rv = find(parent, e[1]);
            if (ru == rv) continue;
            if (rank[ru] < rank[rv]) {
                int t = ru;
                ru = rv;
                rv = t;
            }
            parent[rv] = ru;
            if (rank[ru] == rank[rv]) rank[ru]++;
            cost += e[2];
            chosen++;
            if (chosen == n - 1) break;
        }

        return "cost=" + cost + " edges=" + chosen;
    }

    // поиск корня со сжатием пути
    private static int find(int[] parent, int v) {
        while (parent[v] != v) {
            parent[v] = parent[parent[v]];
            v = parent[v];
        }
        return v;
    }
}
