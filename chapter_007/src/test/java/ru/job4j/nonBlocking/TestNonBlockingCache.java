package ru.job4j.nonBlocking;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 * @author atrifonov.
 * @version 1.
 * @since 11.10.2017.
 */
public class TestNonBlockingCache {
    @Test
    public void whentUpdateEqualVersionThenSuccess() {
        NonBlockingCache cache = new NonBlockingCache();
        for(int i = 0; i < 10; i++) {
            cache.add(new Model("#" + i, i, 0));
        }

        Model model = new Model("#a", 3, 0);
        cache.update(model);

        Model dModel = cache.delete(model);
        boolean success = dModel.getName().equals("#a") && dModel.getVersion() == 1;
        assertThat(success, is(true));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateNonEqualVersionThenException() {
        NonBlockingCache cache = new NonBlockingCache();
        for(int i = 0; i < 10; i++) {
            cache.add(new Model("#" + i, i, 0));
        }

        Model model = new Model("#a", 3, 1);
        cache.update(model);
    }
}
