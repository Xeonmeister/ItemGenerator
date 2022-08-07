package hu.xm.itemgenerator.item;

public class FixedItem<T> extends Item<T> {
    private final T item; // the item that get

    public FixedItem(T item, int amount, double chance, double amountModifier, double chanceModifier) {
        this.item = item;
        this.amount = amount;
        this.chance = chance;
        this.amountModifier = amountModifier;
        this.chanceModifier = chanceModifier;
    }

    @Override
    public T getItem() {
        return item;
    }
}
