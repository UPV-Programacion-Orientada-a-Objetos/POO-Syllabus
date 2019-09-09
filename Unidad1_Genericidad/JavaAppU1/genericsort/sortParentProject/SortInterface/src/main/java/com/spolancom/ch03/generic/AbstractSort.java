package com.spolancom.ch03.generic;

import com.spolancom.ch03.Swapper;
import java.util.Comparator;

public abstract class AbstractSort<E> implements Sort<E>, SortSupport<E> {

    protected Comparator<E> comparator = null;
    protected Swapper swapper = null;

    @Override
    public void setSwapper(Swapper swap) {
        this.swapper = swap;
    }

    @Override
    public void setComparator(Comparator<E> compare) {
        this.comparator = compare;
    }
}
