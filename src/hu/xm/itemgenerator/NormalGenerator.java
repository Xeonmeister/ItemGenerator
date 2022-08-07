package hu.xm.itemgenerator;

import hu.xm.itemgenerator.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NormalGenerator<T> extends ItemGenerator<T> {
    public NormalGenerator() {
        this.itemList = new ArrayList<>();
    }

    @SafeVarargs
    public NormalGenerator(Item<T> ...items) {
        this(items.length);
        for(Item<T> item : items) {
            add(item);
        }
    }

    public NormalGenerator(int initSize) {
        this.itemList = new ArrayList<>(initSize);
    }

    public @NotNull List<ItemPair<T>> generate() {
        checkEmpty();
        List<ItemPair<T>> result = new ArrayList<>(itemList.size());
        for(Item<T> item : itemList) {
            if(r.nextDouble(100) < item.getChance()) {
                result.add(item.get());
            }
        }
        return result;
    }

    public @NotNull List<ItemPair<T>> generate(double amountBonus, double chanceBonus) {
        checkEmpty();
        List<ItemPair<T>> result = new ArrayList<>(itemList.size());
        for(Item<T> item : itemList) {
            if(r.nextDouble(100) < item.getChance(chanceBonus)) {
                result.add(item.get(amountBonus));
            }
        }
        return result;
    }
}
