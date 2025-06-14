package org.plecak;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem {

    private final int itemTypesAmount;
    private final int lowerBound;
    private final int upperBound;
    private final List<Item> items;
    private final Random random;

    /**
     * @param itemTypesAmount itemsAmount
     * @param seed        random number generator seed
     * @param lowerBound  lower value and weight
     * @param upperBound  upper value and weight
     */
    public Problem(int itemTypesAmount, Integer seed, int lowerBound, int upperBound) {
        if (itemTypesAmount <= 0) throw new IllegalArgumentException("itemTypesAmount must be > 0");
        if (lowerBound < 1 || upperBound < lowerBound)
            throw new IllegalArgumentException("invalid bounds");

        this.itemTypesAmount = itemTypesAmount;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.random     = (seed == null) ? new Random() : new Random(seed);
        this.items      = generateItems();
    }

    public Problem(int itemTypesAmount, Integer seed) {
        this(itemTypesAmount, seed, 1, 10);
    }

    public int size() { return itemTypesAmount; }

    public List<Item> items() { return Collections.unmodifiableList(items); }

    private List<Item> generateItems() {
        List<Item> tmp = new ArrayList<Item>(itemTypesAmount);
        for (int i = 0; i < itemTypesAmount; i++) {
            int value  = drawInt();
            int weight = drawInt();
            tmp.add(new Item(value, weight));
        }
        return tmp;
    }

    private int drawInt() {
        return lowerBound + random.nextInt(upperBound - lowerBound + 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append("No: ").append(i)
                    .append(" ").append(items.get(i))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    public ProblemResult solve(int capacity) {
        if (capacity <= 0)
            return new ProblemResult(Map.of(), 0, 0);

        List<Integer> itemsOrderedByValue = IntStream.range(0, items.size())
                .boxed()
                .sorted((i, j) -> Double.compare(
                        (double) items.get(j).value()  / items.get(j).weight(),
                        (double) items.get(i).value()  / items.get(i).weight()))
                .toList();

        Map<Integer, Integer> chosen = new LinkedHashMap<>();
        int totalValue = 0, totalWeight = 0, free = capacity;

        for (int idx : itemsOrderedByValue) {
            Item item = items.get(idx);
            int quantity = free / item.weight();
            if (quantity > 0) {
                chosen.put(idx, quantity);
                int addWeight = quantity * item.weight();
                int addValue  = quantity * item.value();
                totalWeight += addWeight;
                totalValue  += addValue;
                free        -= addWeight;
            }
            if (free == 0) break;
        }
        return new ProblemResult(chosen, totalValue, totalWeight);
    }
}