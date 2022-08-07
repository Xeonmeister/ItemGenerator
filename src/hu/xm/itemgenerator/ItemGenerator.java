package hu.xm.itemgenerator;

import hu.xm.itemgenerator.item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public abstract class ItemGenerator<T> {

    protected ArrayList<Item<T>> itemList;
    protected static final SplittableRandom r = new SplittableRandom();

    public void add(Item<T> item) {
        itemList.add(item);
    }

    public List<Item<T>> getItems() {
        return new ArrayList<>(itemList);
    }

    public boolean remove(Item<T> item) {
        return itemList.remove(item);
    }

    public Item<T> remove(int index) {
        return itemList.remove(index);
    }

    protected void checkEmpty() {
        if(itemList.size() == 0) throw new IllegalStateException("Generator is empty.");
    }
}
