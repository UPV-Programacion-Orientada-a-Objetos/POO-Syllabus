package com.spolancom.ch03.main.bubble;

import java.util.Comparator;

import com.spolancom.ch03.Sort;
import com.spolancom.ch03.SortSupport;
import com.spolancom.ch03.Sortable;
import com.spolancom.ch03.Swapper;

public class BubbleSort implements Sort, SortSupport {

    private Comparator comparator = null;
    private Swapper swapper = null;

    @Override
    public void sort(Sortable collection)
    {
        var n = collection.size();

        while (n > 1) {
            for (int j=0; j < n -1; j++) {
                if (comparator.compare(collection.get(j), collection.get(j + 1)) > 0)
                {
                    swapper.swap(j, j + 1);
                }
            }
            n--;
        }
    }

    @Override
    public void setComparator(Comparator comparator)
    {
        this.comparator = comparator;
    }

    @Override
    public void setSwapper(Swapper swapper)
    {
        this.swapper = swapper;
    }
}
