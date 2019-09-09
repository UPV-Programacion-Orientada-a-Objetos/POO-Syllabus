package com.spolancom.ch03.main.bubble.simple;

import java.util.Comparator;

public class StringComparator implements Comparator {

    @Override
    public int compare(Object first, Object second)
    {
        try
        {
            final String f = (String) first;
            final String s = (String) second;
            return f.compareTo(s);
        }
        catch (ClassCastException cce)
        {
            throw new NonStringElementInCollectionException("There are mixes in the collection.", cce);
        }
    }
}
