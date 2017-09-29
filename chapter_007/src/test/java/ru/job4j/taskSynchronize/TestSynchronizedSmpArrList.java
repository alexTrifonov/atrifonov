package ru.job4j.taskSynchronize;

import org.junit.Test;
import ru.job4j.taskList.SimpleList;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestSynchronizedSmpArrList {
    /**
     * Test add. SynchronizedSmpArrList.
     */
    @Test
    public void whenSomeThreadAddItemInListThenAllItemWillAdd(){
        SynchronizedSmpArrList<Integer> list = new SynchronizedSmpArrList<>(100);
        addList(list);

        ArrayList<Integer> listTest = new ArrayList<>(300);
        for(int i = 0; i < 300; i++) {
            listTest.add(i);
        }
        ArrayList<Integer> listArr = listFill(list);
        assertThat(listTest.containsAll(listArr), is(true));
    }

    /**
     * Test iterator.remove. SynchronizedSmpArrList.
     */
    @Test
    public void whenRemoveSomeItemsThenWillRemoveAllTheseItems() {
        SynchronizedSmpArrList<Integer> list = new SynchronizedSmpArrList<>(300);
        addList(list);
        removeAndAddThread(list);

        assertThat(list.getLastIndex(), is(280));
    }

    @Test
    public void whenAddItemThenAllItemWillAdd() {
        SynchronizedSmpLinkList<Integer> linkList = new SynchronizedSmpLinkList<>();
        addList(linkList);

        ArrayList<Integer> listTest = new ArrayList<>(300);
        for(int i = 0; i < 300; i++) {
            listTest.add(i);
        }
        ArrayList<Integer> listArr = listFill(linkList);
        assertThat(listTest.containsAll(listArr), is(true));
    }

    @Test
    public void whenRemoveItemInThreadThenRemoveAllItems(){
        SynchronizedSmpLinkList<Integer> list = new SynchronizedSmpLinkList<>();
        addList(list);
        removeAndAddThread(list);

        Iterator<Integer> iterator = list.iterator();
        int count = 0;
        while(iterator.hasNext()) {
            iterator.next();
            count++;
        }

        assertThat(count, is(280));
    }

    private ArrayList<Integer> listFill(SimpleList<Integer> list) {
        ArrayList<Integer> listArr = new ArrayList<>(300);
        for (Integer x:
                list) {
            listArr.add(x);
        }


        return listArr;
    }

    private void removeAndAddThread(SimpleList<Integer> list) {
        Iterator<Integer> itTwo = list.iterator();

        Runnable a = () -> {
            for(int i = 300; i < 380; i++) {
                list.add(i);
            }
        };

        Runnable b = () -> {
            for(int i = 0; i < 100; i++) {

                if(itTwo.hasNext()) {
                    itTwo.next();
                    itTwo.remove();
                }

            }
        };


        Thread one = new Thread(a);
        Thread two = new Thread(b);
        one.start();
        two.start();

        try {
            one.join();
            two.join();

        } catch (InterruptedException e) {
            one.interrupt();
            two.interrupt();
        }
    }

    private void addList(SimpleList<Integer> list) {

        Runnable a = () -> {
            for(int i = 0; i < 100; i ++) {
                list.add(i);
            }
        };
        Runnable b = () -> {
            for(int i = 100; i < 200; i ++) {
                list.add(i);
            }
        };
        Runnable c = () -> {
            for(int i = 200; i < 300; i ++) {
                list.add(i);
            }
        };

        Thread threadOne = new Thread(a);
        Thread threadTwo = new Thread(b);
        Thread threadThree = new Thread(c);

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        try {
            threadOne.join();
            threadTwo.join();
            threadThree.join();
        } catch (InterruptedException e) {
            threadOne.interrupt();
            threadTwo.interrupt();
            threadThree.interrupt();
        }

    }
}
