package ru.job4j.begin;

import java.util.HashMap;
import java.util.List;

/**
 * Class for convert User's list to HashMap with user id and user.
 * @author atrifonov
 * @version 1
 * @since 04.07.2017
 */
public class UserConvert {

    /**
     * Convert User's list to HashMap with user id and user.
     * @param list List for convert.
     * @return HashMap with user id and user.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> hashMap = new HashMap<>();
        for(User x : list) {
            hashMap.put(x.getId(), x);
        }
        return hashMap;
    }
}
