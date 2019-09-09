package com.spolancom.ch03.support;

import com.spolancom.ch03.generic.Sortable;

public class ArrayWrapper<E> implements Sortable<E> {

    private final E[] array;

    public ArrayWrapper(E[] array)
    {
        this.array = array;
    }

    public E[] getArray() {
        return array;
    }

    @Override
    public E get(int i) {
        return array[i];
    }

    @Override
    public int size() {
        return array.length;
    }
}
