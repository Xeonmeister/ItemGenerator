package hu.xm.itemgenerator;

import hu.xm.itemgenerator.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NormalGenerator<T> extends ItemGenerator<T> {
    public NormalGenerator() {
        this.itemList = new ArrayList<>();
    }

    public NormalGenerator(int initSize) {
        this.itemList = new ArrayList<>(initSize);
    }

    public @NotNull List<ItemPair<T>> generate() {
        List<ItemPair<T>> result = new ArrayList<>(itemList.size());
        for(Item<T> item : itemList) {
            if(r.nextDouble(100) < item.getChance()) {
                result.add(new ItemPair<>(item.getItem(), item.getAmount()));
            }
        }
        return result;
    }

    public @NotNull List<ItemPair<T>> generate(double amountBonus, double chanceBonus) {
        List<ItemPair<T>> result = new ArrayList<>(itemList.size());
        for(Item<T> item : itemList) {
            if(r.nextDouble(100) < item.getChance(chanceBonus)) {
                result.add(new ItemPair<>(item.getItem(), r.nextInt(item.getAmount(), (int) (item.getAmount() + item.getAmountModifier()*amountBonus) + 1)));
            }
        }
        return result;
    }
}
