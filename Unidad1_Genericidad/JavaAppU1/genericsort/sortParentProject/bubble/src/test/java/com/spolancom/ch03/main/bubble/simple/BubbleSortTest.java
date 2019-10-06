package com.spolancom.ch03.main.bubble.simple;


import com.spolancom.ch03.Sortable;
import com.spolancom.ch03.Swapper;
import com.spolancom.ch03.main.bubble.BubbleSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BubbleSortTest {
    
    @Test
    public void canSortStrings()
    {
        var actualNames = new ArrayList(Arrays.asList(
                "Johnson", "Wilson", "Wilkinson", "Abraham", "Dagobert"
        ));

        var names = new Sortable() {

            @Override
            public Object get(int i) {
                return actualNames.get(i);
            }

            @Override
            public int size() {
                return actualNames.size();
            }
        };

        class SwapActualNamesArrayElements implements Swapper {

            @Override
            public void swap(int i, int j) {
                final Object tmp = actualNames.get(i);
                actualNames.set(i, actualNames.get(j));
                actualNames.set(j, tmp);
            }
        }

        Comparator stringCompare = new Comparator() {
            @Override
            public int compare(Object first, Object second) {
                final String f = (String) first;
                final String s = (String) second;
                return f.compareTo(s);
            }
        };

        var sort = new BubbleSort();
        sort.setComparator(stringCompare);
        sort.setSwapper(new SwapActualNamesArrayElements());
        sort.sort(names);

        Assert.assertEquals(List.of("Abraham", "Dagobert", "Johnson", "Wilkinson", "Wilson"), actualNames);

    }

    @Test
    public void canSortString2()
    {
        var actualNames = new ArrayList(List.of(
                "Johnson", "Wilson", "Wilkinson", "Abraham", "Dagobert"
        ));

        var expectedResult = List.of("Abraham", "Dagobert", "Johnson", "Wilkinson", "Wilson");

        var names = new ArrayListSortable(actualNames);
        var sort = new BubbleSort();
        sort.setComparator(new StringComparator());
        sort.setSwapper(new ArrayListSwapper(actualNames));
        sort.sort(names);
        Assert.assertEquals(expectedResult, actualNames);
    }

    @Test(expected = NonStringElementInCollectionException.class)
    public void canNotSortMixedElements()
    {
        var actualNames = new ArrayList(Arrays.asList(
                42, "Wilson", "Wilkinson", "Abraham", "Dagobert"
        ));

        var expectedResult = List.of("Abraham", "Dagobert", "Johnson", "Wilkinson", "Wilson");

        var names = new ArrayListSortable(actualNames);
        var sort = new BubbleSort();
        sort.setComparator(new StringComparator());
        sort.setSwapper(new ArrayListSwapper(actualNames));
        sort.sort(names);
        Assert.assertEquals(expectedResult, actualNames);
    }


}
