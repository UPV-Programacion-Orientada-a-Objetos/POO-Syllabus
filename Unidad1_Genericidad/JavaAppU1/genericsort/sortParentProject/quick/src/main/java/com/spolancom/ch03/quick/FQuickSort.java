package com.spolancom.ch03.quick;

import com.spolancom.ch03.generic.AbstractSort;
import com.spolancom.ch03.generic.Sortable;
import com.spolancom.ch03.qsort.FJQuickSort;

public class FQuickSort<E> extends AbstractSort<E> {

    public void sort(Sortable<E> sortable)
    {
        int n = sortable.size();
        FJQuickSort<E> qsort = new FJQuickSort<>(comparator, swapper);
        qsort.qsort(sortable, 0, n -1);
    }
}
