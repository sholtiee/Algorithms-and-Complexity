package practice3;

import java.util.List;

// Результат: максимальная стоимость и выбранные предметы
public record KnapsackResult(int maxValue, List<Item> chosen) {
}
