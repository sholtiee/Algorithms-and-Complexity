package practice4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// Задание 1: роботы на поле, обход в ширину по клеткам сетки
// Сложность: O(h * w); память O(h * w)
public class Task1 {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) {
        String[] field1 = {
                "S...#.",
                "##..#.",
                "...##.",
                ".#....",
                "......"
        };
        System.out.println(solve(field1));

        // старт окружен стенами, достижима только сама клетка S
        String[] field2 = {
                ".#.",
                "#S#",
                ".#."
        };
        System.out.println(solve(field2));
    }

    // Возвращает число достижимых клеток и наибольшее расстояние от S
    public static String solve(String[] field) {
        int h = field.length;
        int w = field[0].length();

        // dist[r][c] = -1, пока клетка не посещена
        int[][] dist = new int[h][w];
        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (field[r].charAt(c) == 'S') {
                    dist[r][c] = 0;
                    queue.add(new int[]{r, c});
                }
            }
        }

        int reachable = 0;
        int maxDistance = 0;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
            reachable++;
            maxDistance = Math.max(maxDistance, dist[r][c]);
            for (int k = 0; k < 4; k++) {
                int nr = r + DR[k];
                int nc = c + DC[k];
                if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                if (field[nr].charAt(nc) == '#' || dist[nr][nc] != -1) continue;
                dist[nr][nc] = dist[r][c] + 1;
                queue.add(new int[]{nr, nc});
            }
        }

        return "reachable=" + reachable + " maxDistance=" + maxDistance;
    }
}
