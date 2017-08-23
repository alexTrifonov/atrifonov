package ru.job4j.taskGeneric;

import org.junit.Test;

import java.util.Random;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public class TestRoleStore {
    /**
     * Test add and delete.
     */
    @Test
    public void whenAddTwoRoleAndDeleteSecondItemThenWillGetSecondItem() {
        RoleStore roleStore = new RoleStore();
        Role roleOne = new Role(1);
        Random r = new Random(1000);
        roleOne.setId(System.currentTimeMillis() + r.nextInt() + "");

        Role roleTwo = new Role(2);
        String timeRoleTwo = System.currentTimeMillis() + r.nextInt() + "";
        roleTwo.setId(timeRoleTwo);

        roleStore.add(roleOne);
        roleStore.add(roleTwo);

        Role deletedRole = roleStore.delete(roleTwo);
        boolean equalRT = deletedRole.getId().equals(timeRoleTwo) &&
                deletedRole.getTypeModel() == 2;

        assertThat(equalRT, is(true));
    }

    /**
     * Test add, update and delete.
     */
    @Test
    public void whenAddThreeRoleAndUpdateSecondItemAndDeleteSecondItemThenWillGetSecondItem() {
        RoleStore roleStore = new RoleStore();
        Role roleOne = new Role(1);
        Random r = new Random(1000);
        roleOne.setId(System.currentTimeMillis() + r.nextInt() + "");

        Role roleTwo = new Role(2);
        String timeRoleTwo = System.currentTimeMillis() + r.nextInt() + "";
        roleTwo.setId(timeRoleTwo);

        Role roleThree = new Role(3);
        roleThree.setId(System.currentTimeMillis() + r.nextInt() + "");

        roleStore.add(roleOne);
        roleStore.add(roleTwo);
        roleStore.add(roleThree);

        Role roleFour = new Role(4);
        roleFour.setId(timeRoleTwo);

        roleStore.update(roleFour);

        Role deletedRole = roleStore.delete(roleFour);
        boolean equalRT = deletedRole.getId().equals(timeRoleTwo) &&
                deletedRole.getTypeModel() == 4;

        assertThat(equalRT, is(true));
    }
}
