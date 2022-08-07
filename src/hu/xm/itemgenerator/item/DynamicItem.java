package hu.xm.itemgenerator.item;

import hu.xm.itemgenerator.ItemPair;
import hu.xm.itemgenerator.WeightedGenerator;

public class DynamicItem<T> extends Item<T> {
    private final WeightedGenerator<T> innerGenerator;

    public DynamicItem() {
        innerGenerator = new WeightedGenerator<>();
    }

    @SafeVarargs
    public DynamicItem(double chance, double chanceModifier, FixedItem<T> ...items) {
        this.chance = chance;
        this.chanceModifier = chanceModifier;
        innerGenerator = new WeightedGenerator<>(items.length);
        for(FixedItem<T> item : items) {
            innerGenerator.add(item);
        }
    }

    public DynamicItem(double chance, double chanceModifier, int initSize) {
        this.chance = chance;
        this.chanceModifier = chanceModifier;
        innerGenerator = new WeightedGenerator<>(initSize);
    }

    public void addItem(FixedItem<T> item) {
        innerGenerator.add(item);
    }

    @Override
    public ItemPair<T> get() {
        return innerGenerator.generate();
    }

    @Override
    public ItemPair<T> get(double amountBonus) {
        return innerGenerator.generate(amountBonus, 0.0);
    }
}
