package ru.job4j.bank;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class for store users and accounts.
 * @author atrifonov.
 * @since 13.08.2017.
 * @version 1.
 */
public class Bank {
    /**
     * Map for store users and their accounts.
     */
    private Map<User, List<Account>> mapUsers = new HashMap<>();

    /**
     * Adds user in mapUsers.
     * @param user user for adding.
     */
    public void addUser(User user) {
        if(user != null) {
            this.mapUsers.putIfAbsent(user, new LinkedList<>());
        }
    }

    /**
     * Deletes user from mapUsers.
     * @param user user for deleting.
     */
    public void deleteUser(User user) {
        if(user != null) {
            mapUsers.remove(user);
        }
    }

    /**
     * Adds account to user.
     * @param user user that adds account.
     * @param account account for adding.
     */
    public void addAccountToUser(User user, Account account) {
        if(mapUsers.containsKey(user) && account != null) {
            List<Account> accountList = mapUsers.get(user);
            if(!accountList.contains(account)) {
                accountList.add(account);
                mapUsers.replace(user, accountList);
            }

        }

    }

    /**
     * Delete account from user.
     * @param user user that deleting his account.
     * @param account account for deleting.
     */
    public void deleteAccountFromUser(User user, Account account) {
        if(mapUsers.containsKey(user) && account != null) {
            List<Account> accountList = mapUsers.get(user);
            accountList.remove(account);
            mapUsers.replace(user, accountList);
        }

    }

    /**
     * Gets all user accounts.
     * @param user user that has accounts.
     * @return list user accounts.
     */
    public List<Account> getUserAccounts(User user) {
        List<Account> listUserAccounts = null;
        if(user != null && mapUsers.containsKey(user)) {
            listUserAccounts = mapUsers.get(user);
        }
        return listUserAccounts;
    }

    /**
     * Transfer money from account to account.
     * @param srcUser user has account for transfer money.
     * @param srcAccount account for transfer.
     * @param dstUser user for getting money.
     * @param dstAccount account for getting money.
     * @param amount amount money for transfer.
     * @return true if transfer was successful, else false.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean transferSuccessfully = false;
        if(mapUsers.containsKey(srcUser) && mapUsers.containsKey(dstUser)) {
            List<Account> listSrcUserAcc = getUserAccounts(srcUser);
            List<Account> listDstUserAcc = getUserAccounts(dstUser);
            if(listSrcUserAcc.contains(srcAccount) && srcAccount.getValue() >= amount &&
                    listDstUserAcc.contains(dstAccount)) {
                listSrcUserAcc.remove(srcAccount);
                listDstUserAcc.remove(dstAccount);

                double newValueSrc = srcAccount.getValue() - amount;
                double newValueDst = dstAccount.getValue() + amount;

                srcAccount.setValue(newValueSrc);
                dstAccount.setValue(newValueDst);

                listSrcUserAcc.add(srcAccount);
                listDstUserAcc.add(dstAccount);

                mapUsers.replace(srcUser, listSrcUserAcc);
                mapUsers.replace(dstUser, listDstUserAcc);
                transferSuccessfully = true;
            }
        }
        return transferSuccessfully;
    }

    public Map<User, List<Account>> getMapUsers() {
        return mapUsers;
    }
}
