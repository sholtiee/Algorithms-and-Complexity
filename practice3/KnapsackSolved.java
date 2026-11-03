package practice3;

import java.util.ArrayList;
import java.util.List;

// Задача о рюкзаке 0/1, динамическое программирование
// Сложность: O(n * W) по времени и памяти
public class KnapsackSolved {

    public static KnapsackResult solve(List<Item> items, int capacity) {
        int n = items.size();

        // dp[i][w] = максимальная стоимость из первых i предметов при вместимости w
        // нулевая строка и нулевой столбец равны 0: нет предметов или нет места
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            Item item = items.get(i - 1);
            for (int w = 0; w <= capacity; w++) {
                // не берем предмет
                dp[i][w] = dp[i - 1][w];
                // берем предмет, если помещается: его цена + лучший результат на оставшемся месте
                if (item.weight() <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - item.weight()] + item.value());
                }
            }
        }

        // восстановление: идем из правого нижнего угла вверх
        List<Item> chosen = new ArrayList<>();
        int w = capacity;
        for (int i = n; i >= 1; i--) {
            // значение изменилось относительно строки выше, значит предмет взят
            if (dp[i][w] != dp[i - 1][w]) {
                Item item = items.get(i - 1);
                chosen.add(item);
                w -= item.weight();
            }
        }

        return new KnapsackResult(dp[n][capacity], chosen);
    }
}
