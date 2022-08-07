package hu.xm.itemgenerator;

import hu.xm.itemgenerator.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeightedGenerator<T> extends ItemGenerator<T> {
    private double weightSum = 0;
    private double bonusSum = 0;
    private final ArrayList<Item<T>> tempStorage;

    public WeightedGenerator() {
        this.itemList = new ArrayList<>();
        this.tempStorage = new ArrayList<>();
    }

    @SafeVarargs
    public WeightedGenerator(Item<T> ...items) {
        this(items.length);
        for(Item<T> item : items) {
            add(item);
        }
    }

    public WeightedGenerator(int initSize) {
        this.itemList = new ArrayList<>(initSize);
        this.tempStorage = new ArrayList<>(initSize);
    }

    @Override
    public void add(Item<T> item) {
        itemList.add(item);
        weightSum += item.getChance();
        bonusSum += item.getChanceModifier();
    }

    public @NotNull ItemPair<T> generate() {
        checkEmpty();
        double number = r.nextDouble(weightSum);
        for(Item<T> item : itemList) {
            number -= item.getChance();
            if(number <= 0.0) {
                return item.get();
            }
        }
        // in case of number not reaching 0.0 or lower due to floating point precision errors
        Item<T> item = itemList.get(itemList.size() - 1);
        return item.get();
    }

    public @NotNull ItemPair<T> generate(double amountBonus, double chanceBonus) {
        checkEmpty();
        double number = r.nextDouble(weightSum + bonusSum*chanceBonus);
        for(Item<T> item : itemList) {
            number -= item.getChance(chanceBonus);
            if(number <= 0.0) {
                return item.get(amountBonus);
            }
        }
        // in case of number not reaching 0.0 or lower due to floating point precision errors
        Item<T> item = itemList.get(itemList.size() - 1);
        return item.get(amountBonus);
    }

    public @NotNull List<ItemPair<T>> generate(int amount, double amountBonus, double chanceBonus) {
        checkEmpty();
        if(amount > itemList.size()) throw new ArrayIndexOutOfBoundsException("Can not generate " + amount + " items from generator because it only has " + itemList.size() + " items!");
        List<ItemPair<T>> result = new ArrayList<>(itemList.size());
        double tempWeightSum = weightSum;
        double tempBonusSum = bonusSum;
        for(int i = 0; i < amount; i++) {
            double number = r.nextDouble(tempWeightSum + tempBonusSum*chanceBonus);
            for(int index = 0; index < itemList.size(); index++) { // generating from the list
                Item<T> item = itemList.get(index);
                number -= item.getChance(chanceBonus);
                if(number <= 0.0) {
                    result.add(item.get(amountBonus));
                    tempStorage.add(item);
                    fastRemove(itemList, index);
                    tempWeightSum -= item.getChance();
                    tempBonusSum -= item.getChanceModifier();
                    break;
                }
            }
        }
        for(int j = tempStorage.size() - 1; j >= 0; j--) { // putting back removed items to main list
            itemList.add(tempStorage.remove(j)); // removing from end of the arraylist to prevent unnecessary element shifting
        }
        return result;
    }



    private void fastRemove(ArrayList<Item<T>> list, int index) {
        if(index == list.size() - 1) list.remove(index);
        else list.set(index, list.remove(list.size() - 1));  // removing from list without shifting the elements above it
    }
}
