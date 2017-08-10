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

    /**
     * Sort List of users by name length.
     * @param list list for sorting.
     * @return sorted list of users.
     */
    public List<User> sortNameLength(List<User> list) {
        List<User> sortUserList = new ArrayList<>(list);
        Collections.sort(sortUserList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final boolean bothUserNotNull = o1 != null && o2 != null;
                return bothUserNotNull ? o1.getName().length() - o2.getName().length() :
                        (o1 == null && o2 == null) ? 0 : ((o1 != null) ? -1 : 1);
            }
        });
        return sortUserList;
    }

    /**
     * Sort list of users by name length and age.
     * @param list list for sorting.
     * @return sorted list of users.
     */
    public List<User> sortByAllFields(List<User> list) {
        List<User> sortUserList = new ArrayList<>(list);
        Collections.sort(sortUserList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final boolean bothUserNotNull = o1 != null && o2 != null;
                return !bothUserNotNull ? (o1 == null && o2 == null) ? 0 : ((o1 != null) ? -1 : 1) :
                        (o1.getName().length() != o2.getName().length() ?
                                (o1.getName().length() - o2.getName().length()) : (o1.getAge() - o2.getAge()));
            }
        });
        return sortUserList;
    }


}
