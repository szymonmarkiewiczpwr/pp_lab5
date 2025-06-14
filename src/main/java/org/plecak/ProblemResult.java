package org.plecak;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProblemResult {

    private final Map<Integer, Integer> counts;
    private final int totalValue;
    private final int totalWeight;

    ProblemResult(Map<Integer, Integer> counts, int totalValue, int totalWeight) {
        this.counts = Collections.unmodifiableMap(new LinkedHashMap<>(counts));
        this.totalValue = totalValue;
        this.totalWeight = totalWeight;
    }

    public Map<Integer, Integer> getCounts() { return counts; }

    public int getTotalValue()  { return totalValue;  }
    public int getTotalWeight() { return totalWeight; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        counts.forEach((idx, qty) ->
                sb.append("No: ").append(idx).append(" × ").append(qty).append(System.lineSeparator()));
        sb.append("Weight: ").append(totalWeight).append(System.lineSeparator());
        sb.append("Value: ").append(totalValue);
        return sb.toString();
    }
}