package ru.job4j.sorting;

import java.util.*;

/**
 * Class for sorting user.
 * @author atrifonov.
 * @since 07.08.2017.
 * @version 1.
 */
public class SortUser {
    /**
     * Create TreeSet from List.
     * @param list list for sorting.
     * @return the TreeSet of users.
     */
    public Set<User> sort (List<User> list) {
        Set<User> userSet = new TreeSet<>();
        for (User user:
             list) {
            if(user != null) {
                userSet.add(user);
            }

        }
        return userSet;
    }




}
