package org.plecak;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemTest {

    @Test
    void atLeastOneItemFits_returnsNonEmptyResult() {
        int capacity = 8;
        Problem p = new Problem(6, 42);
        assertTrue(p.items().stream().anyMatch(it -> it.weight() <= capacity),
                "Premisa testu nie spełniona – żaden przedmiot nie mieści się");

        ProblemResult r = p.solve(capacity);
        int qty = r.getCounts().values().stream().mapToInt(Integer::intValue).sum();
        assertTrue(qty > 0, "Algorytm powinien zwrócić ≥ 1 przedmiot");
    }

    @Test
    void noItemFits_returnsEmptyResult() {
        int lower = 5, upper = 10;
        Problem p = new Problem(4, 123, lower, upper);
        int capacity = 4;

        ProblemResult r = p.solve(capacity);
        assertTrue(r.getCounts().isEmpty(), "Oczekiwano pustego rozwiązania");
        assertEquals(0, r.getTotalWeight());
        assertEquals(0, r.getTotalValue());
    }

    @Test
    void generatedItemsWithinBounds() {
        int lower = 1, upper = 10;
        Problem p = new Problem(50, 7, lower, upper);

        p.items().forEach(it -> {
            assertTrue(it.weight() >= lower && it.weight() <= upper,
                    "Waga poza zakresem");
            assertTrue(it.value() >= lower && it.value() <= upper,
                    "Wartość poza zakresem");
        });
    }

    @Test
    void solve_returnsOptimalForSmallInstance() {
        Problem p = new Problem(3, 1, 1, 10);
        int capacity = 12;

        ProblemResult greedy = p.solve(capacity);

        int bestValue = 0;
        Item a = p.items().get(0), b = p.items().get(1), c = p.items().get(2);
        for (int x = 0; x * a.weight() <= capacity; x++)
            for (int y = 0; y * b.weight() + x * a.weight() <= capacity; y++)
                for (int z = 0; z * c.weight() + y * b.weight() + x * a.weight() <= capacity; z++) {
                    int w = x * a.weight() + y * b.weight() + z * c.weight();
                    int v = x * a.value()  + y * b.value()  + z * c.value();
                    if (v > bestValue) bestValue = v;
                }

        assertEquals(bestValue, greedy.getTotalValue(),
                "Algorytm nie daje optymalnej wartości dla prostego przypadku");
        assertTrue(greedy.getTotalWeight() <= capacity);
    }
}
