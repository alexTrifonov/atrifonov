package ru.job4j.taskBlockCondition;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestProducerConsumer {
    AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void whenSomeThreadAddAndPollThenAllDataNotLose() {

        ProducerConsumer<String> producerConsumer = new ProducerConsumer<>();
        List<String> listTest = new LinkedList<>();

        ArrayList<String> listString = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l"));

        ArrayList<Thread> listThread = new ArrayList<>();
        for(String x : listString) {
            RunAdd runAdd = new RunAdd(x, producerConsumer);
            listThread.add(new Thread(runAdd));
        }

        for(int i = 0; i < listString.size(); i++) {
            RunPoll runPoll = new RunPoll(producerConsumer, listTest);
            listThread.add(new Thread(runPoll));
        }

        listThread.forEach(value -> value.start());

        while (! (atomicInteger.get() == listString.size() * 2)) {
        }


        boolean contAll = listTest.containsAll(listString);
        boolean hasNotNull = listTest.contains(null);
        assertThat(contAll && !hasNotNull, is(true));

    }

    private class RunAdd implements Runnable {
        private String value;
        private ProducerConsumer<String> producerConsumer;
        public RunAdd(String value, ProducerConsumer<String> producerConsumer) {
            this.value = value;
            this.producerConsumer = producerConsumer;
        }
        @Override
        public void run() {

            producerConsumer.add(value);
            atomicInteger.incrementAndGet();
        }
    }

    private class RunPoll implements  Runnable {
        private ProducerConsumer<String> producerConsumer;
        private List<String> list;

        public RunPoll(ProducerConsumer<String> producerConsumer, List<String> list) {
            this.producerConsumer = producerConsumer;
            this.list = list;
        }

        @Override
        public void run() {

            String value = producerConsumer.poll();
            synchronized (list) {
                list.add(value);
            }
            atomicInteger.incrementAndGet();
        }
    }
}
