package hu.xm.itemgenerator.item;

import java.util.SplittableRandom;

public abstract class Item<T> {
    protected static final SplittableRandom r = new SplittableRandom();
    protected int amount; // amount of the item
    protected double chance; // chance of getting the item - either pct (0-100) or weight
    protected double amountModifier; // multiplier for the generator
    // the actual amount that gets generated is an integer between amount and (amount + amountModifier*amountBonus)
    protected double chanceModifier; // chance multiplier, the actual chance is chance + chanceModifier*chaneBonus
    public abstract T getItem();

    public int getAmount() {
        return amount;
    }

    public int getAmount(double amountBonus) {
        return r.nextInt(amount, (int) (amount + amountModifier*amountBonus) + 1);
    }

    public double getChance() {
        return chance;
    }

    public double getChance(double chanceBonus) {
        return chance + chanceModifier*chanceBonus;
    }

    public double getAmountModifier() {
        return amountModifier;
    }

    public double getChanceModifier() {
        return chanceModifier;
    }
}
