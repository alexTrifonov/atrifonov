package ru.job4j.carstore;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test for BodyStore.
 */
public class BodyStoreTest {
    private final BodyStore bodyStore = BodyStore.INSTANCE;
    /**
     * Test getBody.
     */
    @Test
    public void TestGetBody() {
        Body body = new Body();
        String bodyType = "someType";
        body.setBodyType(bodyType);
        body = bodyStore.add(body);
        Body bodyInBase = bodyStore.getBody(bodyType);
        assertThat(body, is(bodyInBase));
    }
}
