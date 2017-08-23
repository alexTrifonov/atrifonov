package ru.job4j.taskGeneric;

/**
 * Class store for User.
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public class UserStore implements Store<User> {

    private SimpleArray<User> simpleArray = new SimpleArray<>(5);
    @Override
    public void add(User user) {
        if (user != null && indexRole(user) == -1) {
            simpleArray.add(user);
        }
    }

    @Override
    public void update(User newUser) {
        int index = -1;
        if (newUser != null) {
            index = indexRole(newUser);
        }
        if (index != -1) {
            simpleArray.update(newUser, index);
        }

    }

    @Override
    public User delete(User deletedUser) {
        User user = null;
        int index = -1;
        if (deletedUser != null) {
            index = indexRole(deletedUser);
        }
        if(index != -1) {
            user = simpleArray.get(index);
            simpleArray.delete(index);
        }
        return user;
    }

    private int indexRole(User user) {
        User finedUser;
        int index = -1;
        for (int i = 0; i < simpleArray.size(); i++) {
            finedUser = simpleArray.get(i);
            if (finedUser.getId().equals(user.getId())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
