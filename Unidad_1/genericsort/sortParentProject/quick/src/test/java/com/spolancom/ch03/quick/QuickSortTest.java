package com.spolancom.ch03.quick;

import org.junit.Test;
import org.junit.Assert;

import com.spolancom.ch03.support.ArraySwapper;
import com.spolancom.ch03.support.ArrayWrapper;

public class QuickSortTest {

    @Test
    public void canSortString()
    {
        final var actualNames = new String[] {
                "Johnson", "Wilson",
                "Wilkinson", "Abraham", "Dagobert"
        };
        final var expected = new String[]{
                "Abraham","Dagobert", "Johnson", "Wilkinson", "Wilson"
        };
        var sort = new QuickSort<String>();
        sort.setComparator(String::compareTo);
        sort.setSwapper(new ArraySwapper<>(actualNames));
        sort.sort(new ArrayWrapper<>(actualNames));
        Assert.assertArrayEquals(expected, actualNames);
    }
}
