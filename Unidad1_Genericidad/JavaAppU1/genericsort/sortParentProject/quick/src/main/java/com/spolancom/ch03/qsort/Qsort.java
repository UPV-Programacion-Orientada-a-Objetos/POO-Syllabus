package com.spolancom.ch03.qsort;

import com.spolancom.ch03.generic.Sortable;
import com.spolancom.ch03.Swapper;
import java.util.Comparator;

public class Qsort<E> {

    final private Comparator<E> comparator;
    final private Swapper swapper;

    public Qsort(Comparator<E> comparator, Swapper swapper)
    {
        this.comparator = comparator;
        this.swapper = swapper;
    }

    public void qsort(Sortable<E> sortable, int start, int end)
    {
        if (start < end)
        {
            final var pivot = sortable.get(start);
            final var partitioner = new Partitioner<E>(comparator, swapper);
            var cutIndex = partitioner.partition(sortable, start, end, pivot);
            if (cutIndex == start)
            {
                cutIndex++;
            }
            qsort(sortable, start, cutIndex - 1);
            qsort(sortable, cutIndex, end);
        }
    }
}
