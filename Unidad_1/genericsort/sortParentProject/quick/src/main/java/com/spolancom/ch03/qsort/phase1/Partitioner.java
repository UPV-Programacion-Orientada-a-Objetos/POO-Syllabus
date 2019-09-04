package com.spolancom.ch03.qsort.phase1;

import com.spolancom.ch03.Swapper;
import com.spolancom.ch03.generic.Sortable;

import java.util.Comparator;

public class Partitioner<E> {

    private final Comparator<E> comparator;
    private final Swapper swapper;

    public Partitioner(Comparator<E> comparator, Swapper swapper)
    {
        this.comparator = comparator;
        this.swapper = swapper;
    }

    public int partition(Sortable<E> sortable, int start, int end, E pivot)
    {
        return 0;
    }

}
