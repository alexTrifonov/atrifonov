package ru.job4j.carstore;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for ModelStore.
 */
public class ModelStoreTest {
    private final ModelStore modelStore = ModelStore.INSTANCE;
    private final MakeStore makeStore = MakeStore.INSTANCE;
    /**
     * Test getModels.
     */
    @Test
    public void TestGetModels() {
        MakeCar makeCar = new MakeCar();
        makeCar.setMake("make");
        makeCar = makeStore.add(makeCar);

        AutoModel modelOne = new AutoModel("modelOne", makeCar);
        AutoModel modelTwo = new AutoModel("modelTwo", makeCar);
        modelStore.add(modelOne);
        modelStore.add(modelTwo);

        List<AutoModel> list = modelStore.getModels(makeCar);
        List<AutoModel> autoModels = new LinkedList<>();
        autoModels.add(modelOne);
        autoModels.add(modelTwo);
        assertThat(list, is(autoModels));
    }
}
