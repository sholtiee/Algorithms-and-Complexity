package practice3;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Item> items = List.of(
                new Item(3, 10),
                new Item(1, 3),
                new Item(4, 12),
                new Item(2, 5),
                new Item(2, 4)
        );
        KnapsackResult r = KnapsackSolved.solve(items, 7);
        System.out.println("Максимальная стоимость: " + r.maxValue());
        System.out.println("Выбрано: " + r.chosen());
    }
}
