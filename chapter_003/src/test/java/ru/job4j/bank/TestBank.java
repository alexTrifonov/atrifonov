package ru.job4j.bank;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 12.08.2017.
 * @version 1.
 */
public class TestBank {
    /**
     * Test addUser.
     */
    @Test
    public void whenUsersNullAndTwoSameNotNullThenOnlyUserNotNullAdded() {
        Bank bank = new Bank();
        User ben = new User("Ben", 123456);
        bank.addUser(ben);
        bank.addUser(ben);
        bank.addUser(null);
        Map<User, List<Account>> expectedMap = new HashMap<>();
        expectedMap.put(ben, new LinkedList<>());
        assertThat(bank.getMapUsers(), is(expectedMap));
    }

    /**
     * Test deleteUser.
     */
    @Test
    public void whenDeleteUserNullAndUserNotNullThenDeletedUserNotNull() {
        Bank bank = new Bank();
        User ben = new User("Ben", 123456);
        User dan = new User("Dan", 234567);
        bank.addUser(ben);
        bank.addUser(dan);
        bank.deleteUser(ben);
        bank.deleteUser(null);
        Map<User, List<Account>> expectedMap = new HashMap<>();
        expectedMap.put(dan, new LinkedList<>());
        assertThat(bank.getMapUsers(), is(expectedMap));
    }

    /**
     * Test addAccountToUser.
     */
    @Test
    public void whenAddNotNullAccountAndNullAccountToUserThenNotNullAccountAdded() {
        Bank bank = new Bank();
        User ben = new User("Ben", 123456);
        bank.addUser(ben);
        Account account = new Account(1500.23, 132);
        bank.addAccountToUser(ben, account);
        bank.addAccountToUser(ben, account);
        bank.addAccountToUser(ben, null);
        List<Account> expectedListAccount = new LinkedList<>();
        expectedListAccount.add(account);
        assertThat(bank.getUserAccounts(ben), is(expectedListAccount));
    }

    /**
     * Test deleteAccountFromUser.
     */
    @Test
    public void whenDeleteNotNullAccountAndNullAccountThenDeletedNotNullAccount() {
        Bank bank = new Bank();
        User ben = new User("Ben", 123456);
        bank.addUser(ben);
        Account account = new Account(1500.23, 132);
        Account account1 = new Account(16500.00, 159);
        bank.addAccountToUser(ben, account);
        bank.addAccountToUser(ben, account1);
        bank.deleteAccountFromUser(ben, account);
        bank.deleteAccountFromUser(ben, null);
        List<Account> expectedListAccount = new LinkedList<>();
        expectedListAccount.add(account1);
        assertThat(bank.getUserAccounts(ben), is(expectedListAccount));
    }

    /**
     * Test transferMoney.
     */
    @Test
    public void whenTransferMoneyFromUserToSameUserThanSuccess() {
        Bank bank = new Bank();
        User ben = new User("Ben", 123456);
        bank.addUser(ben);
        Account account = new Account(1500.00, 132);
        Account account1 = new Account(16500.00, 159);
        bank.addAccountToUser(ben, account);
        bank.addAccountToUser(ben, account1);

        boolean bankTransfer = bank.transferMoney(ben, account1, ben, account, 2500.00);
        List<Account> expectedListAccount = new LinkedList<>();
        double newValueAcc1 = account1.getValue() - 2500.00;
        account1.setValue(newValueAcc1);
        double newValueAcc = account.getValue() + 2500.00;
        account.setValue(newValueAcc);
        expectedListAccount.add(account1);
        expectedListAccount.add(account);

        boolean listEquals = true;
        Iterator<Account> it = expectedListAccount.iterator();
        for (Account x :
             bank.getUserAccounts(ben)) {
            if(x != it.next()) {
                listEquals = false;
            }
        }
        assertThat((bankTransfer && listEquals), is(true));
    }

    /**
     * Test transferMoney.
     */
    @Test
    public void whenTranferMoneyFromUserToAnotherUserThanSuccess() {
        Bank bank = new Bank();
        User ben = new User("Ben", 123456);
        bank.addUser(ben);
        Account account = new Account(1500.00, 132);
        Account account1 = new Account(16500.00, 159);
        bank.addAccountToUser(ben, account);
        bank.addAccountToUser(ben, account1);

        User dan = new User("Den", 234567);
        Account account2 = new Account(20000.00, 456);
        Account account3 = new Account(10000.00, 789);
        bank.addUser(dan);
        bank.addAccountToUser(dan, account2);
        bank.addAccountToUser(dan, account3);

        boolean bankTransfer = bank.transferMoney(ben, account1, dan, account2, 1500.00);
        List<Account> listBenExpected = new LinkedList<>();
        double newValueAcc1 = account1.getValue() - 1500.00;
        account1.setValue(newValueAcc1);
        listBenExpected.add(account);
        listBenExpected.add(account1);

        List<Account> listDanExpected = new LinkedList<>();
        double newValueAcc = account2.getValue() + 1500.00;
        account2.setValue(newValueAcc);
        listDanExpected.add(account3);
        listDanExpected.add(account2);

        boolean listDanEqual = bank.getUserAccounts(dan).equals(listDanExpected);
        boolean listBenEqual = bank.getUserAccounts(ben).equals(listBenExpected);

        assertThat(bankTransfer && listBenEqual && listDanEqual, is(true));
    }

    /**
     * Test transferMoney.
     */
    @Test
    public void whenTransferMoneyFromUserToSameUserAndAccountAbsentThanFail() {
        Bank bank = new Bank();
        User ben = new User("Ben", 123456);
        bank.addUser(ben);
        Account account = new Account(1500.00, 132);
        Account account1 = new Account(16500.00, 159);
        bank.addAccountToUser(ben, account);
        bank.addAccountToUser(ben, account1);

        Account account3 = new Account(10000.00, 456);

        boolean bankTransfer = bank.transferMoney(ben, account1, ben, account3, 2500.00);

        assertThat(bankTransfer, is(false));
    }

    /**
     * Test transferMoney.
     */
    @Test
    public void whenTransferMoneyFromUserToSameUserAndMoneyNotEnoughThanSuccess() {
        Bank bank = new Bank();
        User ben = new User("Ben", 123456);
        bank.addUser(ben);
        Account account = new Account(1500.00, 132);
        Account account1 = new Account(500.00, 159);
        bank.addAccountToUser(ben, account);
        bank.addAccountToUser(ben, account1);

        boolean bankTransfer = bank.transferMoney(ben, account1, ben, account, 2500.00);
        assertThat(bankTransfer, is(false));
    }
}
