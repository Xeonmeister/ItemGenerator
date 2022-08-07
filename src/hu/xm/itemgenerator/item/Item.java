package hu.xm.itemgenerator.item;

import hu.xm.itemgenerator.ItemPair;

import java.util.SplittableRandom;

public abstract class Item<T> {
    protected static final SplittableRandom r = new SplittableRandom();
    protected double chance; // chance of getting the item - either pct (0-100) or weight
    protected double chanceModifier; // chance multiplier, the actual chance is chance + chanceModifier*chaneBonus
    public abstract ItemPair<T> get();

    public abstract ItemPair<T> get(double amountBonus);

    public double getChance() {
        return chance;
    }

    public double getChance(double chanceBonus) {
        return chance + chanceModifier*chanceBonus;
    }

    public double getChanceModifier() {
        return chanceModifier;
    }
}
