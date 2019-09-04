package com.spolancom.ch03.qsort;

import com.spolancom.ch03.support.ArraySwapper;
import com.spolancom.ch03.support.ArrayWrapper;
import org.junit.Assert;
import org.junit.Test;

public class PartitionerTest {

    @Test
    public void partitionsInArray()
    {
        final var partitionThis = new Integer[]{0, 7, 6, 2};
        final var swapper = new ArraySwapper<>(partitionThis);
        final var partitioner = new Partitioner<Integer>(
                (a, b) -> a < b ? -1 : a > b ? +1 : 0, swapper
        );
        final var pivot = 6;
        final var cutIndex = partitioner.partition(
                new ArrayWrapper<>(partitionThis),
                0,
                partitionThis.length - 1,
                pivot
        );
        Assert.assertEquals(2, cutIndex);
        final var expected = new Integer[]{0, 2, 6, 7};
        Assert.assertArrayEquals(expected, partitionThis);
    }
}
