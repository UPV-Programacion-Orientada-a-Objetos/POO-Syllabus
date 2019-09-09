package com.spolancom.ch03.generic;

import com.spolancom.ch03.Swapper;
import java.util.Comparator;
public interface SortSupport<E> {

    void setSwapper(Swapper swap);
    void setComparator(Comparator<E> compare);
}
