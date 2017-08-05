package ru.job4j.begin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 05.08.2017.
 * @version 1.
 */
public class TestFastCollection {

    public static void main(String[] args) {
        FastCollection fastCollection = new FastCollection();
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        TreeSet<String> treeSet = new TreeSet<>();
        System.out.println(fastCollection.add(arrayList,  1000000));
        System.out.println(fastCollection.add(linkedList, 1000000));
        System.out.println(fastCollection.add(treeSet,    1000000));



        System.out.println(fastCollection.delete(arrayList,  50000));
        System.out.println(fastCollection.delete(linkedList, 50000));
        System.out.println(fastCollection.delete(treeSet,    50000));



    }
}
