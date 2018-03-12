package ru.job4j.carstore;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for MakeStore.
 */
public class MakeStoreTest {
    private final MakeStore makeStore = MakeStore.INSTANCE;
    /**
     * Test getMakeCar.
     */
    @Test
    public void TestGetMakeCar() {
        MakeCar makeCar = new MakeCar();
        String make = "someMake";
        makeCar.setMake(make);
        makeCar = makeStore.add(makeCar);
        MakeCar makeCarFromBase = makeStore.getMakeCar(make);
        assertThat(makeCar, is(makeCarFromBase));
    }

    /**
     * Test getMakes.
     */
    @Test
    public void TestGetMakes() {
        MakeCar makeCarOne = new MakeCar();
        makeCarOne.setMake("aMake");
        MakeCar makeCarTwo = new MakeCar();
        makeCarTwo.setMake("newMake");
        makeStore.add(makeCarOne);
        makeStore.add(makeCarTwo);

        List<MakeCar> makeCarList = makeStore.getMakes();
        List<MakeCar> list = new LinkedList<>();
        list.add(makeCarOne);
        list.add(makeCarTwo);
        assertThat(list, is(makeCarList));
    }
}
