package hu.xm.itemgenerator.item;

import hu.xm.itemgenerator.ItemPair;

public class FixedItem<T> extends Item<T> {
    private final T item;
    protected int amount; // base amount of the item
    protected double amountModifier; // multiplier for the generator
    // the actual amount that gets generated is an integer between amount and (amount + amountModifier*amountBonus)

    public FixedItem(T item, int amount, double chance, double amountModifier, double chanceModifier) {
        this.item = item;
        this.amount = amount;
        this.chance = chance;
        this.amountModifier = amountModifier;
        this.chanceModifier = chanceModifier;
    }

    public T getItem() {
        return item;
    }

    public int getBaseAmount() {
        return amount;
    }

    public double getAmountModifier() {
        return amountModifier;
    }

    @Override
    public ItemPair<T> get() {
        return new ItemPair<>(item, amount);
    }

    @Override
    public ItemPair<T> get(double amountBonus) {
        return new ItemPair<>(item, r.nextInt(amount, (int) (amount + amountModifier*amountBonus) + 1));
    }
}
