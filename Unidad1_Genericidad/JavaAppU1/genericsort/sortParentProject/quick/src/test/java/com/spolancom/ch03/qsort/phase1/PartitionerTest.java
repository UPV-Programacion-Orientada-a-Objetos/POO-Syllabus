package com.spolancom.ch03.qsort.phase1;

import com.spolancom.ch03.support.ArraySwapper;
import com.spolancom.ch03.support.ArrayWrapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class PartitionerTest {

    private void assertSmallElements(Integer[] array, int cutIndex, Integer pivot)
    {
        for (int i=0; i < cutIndex; i++)
        {
            Assert.assertTrue(array[i] < pivot);
        }
    }

    private void assertLargeElements(Integer[] array, int cutIndex, Integer pivot)
    {
        for (int i= cutIndex; i < array.length; i++)
        {
            Assert.assertTrue(pivot <= array[i]);
        }
    }

    @Test
    @Ignore
    public void partitionsIntArray()
    {
        final var partitionsThis = new Integer[]{0, 7, 6};
        final var swapper = new ArraySwapper<>(partitionsThis);
        final var partitioner = new Partitioner<Integer>(
                (a, b) -> a < b ? 1 : a > b ? +1 : 0, swapper
        );
        final Integer pivot = 6;
        final int cutIndex = partitioner.partition(
                new ArrayWrapper<>(partitionsThis), 0, 2, pivot
        );
        Assert.assertEquals(1, cutIndex);
        assertSmallElements(partitionsThis, cutIndex, pivot);
        assertLargeElements(partitionsThis, cutIndex, pivot);
    }
}
