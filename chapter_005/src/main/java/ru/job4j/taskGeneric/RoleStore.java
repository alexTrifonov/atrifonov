package ru.job4j.taskGeneric;

/**
 * Class store for Role.
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public class RoleStore implements Store<Role> {
    private SimpleArray<Role> simpleArray = new SimpleArray<>(5);

    @Override
    public void add(Role role) {
        if (role != null && indexRole(role) == -1) {
            simpleArray.add(role);
        }
    }

    @Override
    public void update(Role newRole) {
        int index = -1;
        if (newRole != null) {
            index = indexRole(newRole);
        }
        if (index != -1) {
            simpleArray.update(newRole, index);
        }
    }

    @Override
    public Role delete(Role deletedRole) {
        Role role = null;
        int index = -1;
        if(deletedRole != null) {
            index = indexRole(deletedRole);
        }
        if (index != -1) {
            role = simpleArray.get(index);
            simpleArray.delete(index);
        }
        return role;
    }

    private int indexRole(Role role) {
        Role finedRole;
        int index = -1;
        for (int i = 0; i < simpleArray.size(); i++) {
            finedRole = simpleArray.get(i);
            if (finedRole.getId().equals(role.getId())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
