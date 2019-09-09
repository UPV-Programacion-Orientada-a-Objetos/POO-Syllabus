package com.spolancom.ch03.main.bubble.generic;

import com.spolancom.ch03.Swapper;
import com.spolancom.ch03.generic.Sort;
import com.spolancom.ch03.generic.SortSupport;
import com.spolancom.ch03.generic.Sortable;

import java.util.Comparator;

public class BubbleSort<E> implements Sort<E>, SortSupport<E> {

    private Comparator<E> comparator = null;
    private Swapper swapper = null;

    @Override
    public void sort(Sortable<E> collection) {
        var n = collection.size();
        while (n > 1) {
            for (int j = 0; j < n -1; j++)
            {
                if (comparator.compare(collection.get(j), collection.get(j+1)) > 0)
                {
                    swapper.swap(j, j+1);
                }
            }
            n--;
        }
    }

    @Override
    public void setSwapper(Swapper swap) {
        this.swapper = swap;

    }

    @Override
    public void setComparator(Comparator<E> compare) {
        this.comparator = compare;
    }
}
