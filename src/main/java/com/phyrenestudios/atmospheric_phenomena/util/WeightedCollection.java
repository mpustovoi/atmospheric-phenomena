package com.phyrenestudios.atmospheric_phenomena.util;

import java.util.*;

public class WeightedCollection<I> {
    private final NavigableMap<Float, I> map = new TreeMap<>();
    private final Random random;
    private float total = 0;

    public WeightedCollection() {
        this(new Random());
    }

    public WeightedCollection(Random random) {
        this.random = random;
    }

    public WeightedCollection(Random random, List<Float> weights, List<I> items, List<Integer> totals) {
        this(random);
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < totals.get(i); j++) {
                this.add(weights.get(i),items.get(i));
            }
        }
    }

    public WeightedCollection<I> add(float weight, I result) {
        if (weight <= 0) {
            return this;
        }
        total += weight;
        map.put(total, result);
        return this;
    }

    public I getRandomElement() {
        if (map.size() == 1) {
            return map.firstEntry().getValue();
        }
        float value = random.nextFloat() * total;
        return map.higherEntry(value).getValue();
    }

    public I getRandomElementWithRemoval() {
        I output;

        if (map.size() == 1) {
            output = map.firstEntry().getValue();
            map.remove(map.firstEntry().getKey());
            return output;
        }

        float value = random.nextFloat() * total;
        output = map.higherEntry(value).getValue();
        map.remove(map.higherEntry(value).getKey());
        return output;
    }

    public NavigableMap<Float, I> getMap()
    {
        return map;
    }

    public Collection<I> getEntries()
    {
        return this.getMap().values();
    }

    public Set<Float> getWeights()
    {
        return map.keySet();
    }

    public List<Float> returnConvertedWeights()
    {
        List<Float> weights = new ArrayList<>(getWeights());
        float sum = 0;
        for (float i: weights) {
            sum += i;
        }
        List<Float> ret = new ArrayList<>();
        for (float i: weights)
        {
            ret.add((float) (Math.round(i/sum) * 100));
        }
        return ret;
    }
}
