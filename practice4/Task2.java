package practice4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringJoiner;

// Задание 2: авиабилеты, алгоритм Дейкстры с двоичной кучей
// Сложность: O((n + m) log n); память O(n + m)
public class Task2 {

    private static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) {
        // рейсы задаются тройками {откуда, куда, длительность}
        int[][] flights1 = {{0, 1, 100}, {0, 2, 20}, {2, 1, 30}, {1, 3, 10}, {2, 3, 100}};
        System.out.println(format(solve(4, 0, flights1)));

        // в город 2 рейсов нет
        int[][] flights2 = {{0, 1, 7}};
        System.out.println(format(solve(3, 0, flights2)));
    }

    // Возвращает кратчайшие времена от s; недостижимые города получают INF
    public static long[] solve(int n, int s, int[][] flights) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] f : flights) {
            adj.get(f[0]).add(new int[]{f[1], f[2]});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        // в куче пары {расстояние, вершина}
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        heap.add(new long[]{0, s});
        while (!heap.isEmpty()) {
            long[] top = heap.poll();
            long d = top[0];
            int v = (int) top[1];
            // устаревшая запись, вершина уже обработана с меньшим расстоянием
            if (d > dist[v]) continue;
            for (int[] edge : adj.get(v)) {
                long nd = d + edge[1];
                if (nd < dist[edge[0]]) {
                    dist[edge[0]] = nd;
                    heap.add(new long[]{nd, edge[0]});
                }
            }
        }
        return dist;
    }

    public static String format(long[] dist) {
        StringJoiner out = new StringJoiner(" ");
        for (long d : dist) out.add(d == INF ? "INF" : String.valueOf(d));
        return out.toString();
    }
}
