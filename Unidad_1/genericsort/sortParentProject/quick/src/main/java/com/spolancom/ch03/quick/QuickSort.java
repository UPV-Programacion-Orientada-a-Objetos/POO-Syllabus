package com.spolancom.ch03.quick;

import com.spolancom.ch03.generic.AbstractSort;
import com.spolancom.ch03.generic.Sortable;
import com.spolancom.ch03.qsort.Qsort;

public class QuickSort<E> extends AbstractSort<E> {

    public void sort(Sortable<E> sortable)
    {
        final var n = sortable.size();
        final var qsort = new Qsort<E>(comparator, swapper);
        qsort.qsort(sortable, 0, n -1);
    }
}
