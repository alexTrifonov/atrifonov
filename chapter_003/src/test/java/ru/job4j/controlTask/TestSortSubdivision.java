package ru.job4j.controlTask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 12.08.2017.
 * @version 1.
 */
public class TestSortSubdivision {
    /**
     * Test sortAscendingCodes.
     */
    @Test
    public void whenListHaveAbsentCodesAndNotSortedThenAddAbsentCodesAndAscendingSort() {
        SortSubdivision sortSubdivision = new SortSubdivision();
        String[] strings = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] resultStrings = sortSubdivision.sortAscendingCodes(strings);
        String[] expectedStrings = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1",
                "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertThat(resultStrings, is(expectedStrings));
    }

    /**
     * Test sortDecreaseCodes.
     */
    @Test
    public void whenListHaveAbsentCodesAndNotSortedThenAddAbsentCodesAndDecreaseSort() {
        SortSubdivision sortSubdivision = new SortSubdivision();
        String[] strings = {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] resultStrings = sortSubdivision.sortDecreaseCodes(strings);
        String[] expectedStrings = {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1",
                "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        assertThat(resultStrings, is(expectedStrings));
    }
}
