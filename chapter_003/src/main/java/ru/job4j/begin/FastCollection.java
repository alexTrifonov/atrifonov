package ru.job4j.begin;

import java.util.*;

/**
 * Class Class for remove duplicates.
 * @author atrifonov
 * @since 12.07.2017
 * @version 1
 */
public class FastCollection {
    /**
     * Add elements in collection.
     * @param collection Collection for addition elements.
     * @param amount Count of added elements.
     * @return Time addition.
     */
    public long add(Collection<String> collection, int amount) {
        Random randomString = new Random();
        long timeStart = System.currentTimeMillis();
        for(int i = 0; i < amount; i++) {
            collection.add(String.format("%d", randomString.nextInt()));
        }
        long timeEnd = System.currentTimeMillis();
        return timeEnd - timeStart;
    }

    /**
     * Delete firs n elements from collection.
     * @param collection Collection for delete elements.
     * @param amount Count deleted elements.
     * @return
     */
    public long delete(Collection<String> collection, int amount) {
        amount = amount <= collection.size() ? amount : collection.size();

        LinkedList<String> linkedList = new LinkedList<>();
        Iterator<String> iterator = collection.iterator();
        for (int i = 0; i < amount; i++) {
            linkedList.add(iterator.next());
        }

        long timeStart = System.currentTimeMillis();
        for (String x : linkedList) {
            collection.remove(x);
        }
        long timeEnd = System.currentTimeMillis();
        return timeEnd - timeStart;




    }


}
