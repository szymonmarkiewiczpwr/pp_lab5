package org.plecak;

public record Item(int value, int weight) {
    @Override
    public String toString() {
        return "v: %d w: %d".formatted(value, weight);
    }
}