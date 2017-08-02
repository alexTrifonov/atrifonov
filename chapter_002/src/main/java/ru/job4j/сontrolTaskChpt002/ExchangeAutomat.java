package ru.job4j.сontrolTaskChpt002;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for task exchange automat.
 * @author atrifonov
 * @since 30.07.2017
 * @version 1
 */
public class ExchangeAutomat {
    /**
     * Const for coin 1 rouble.
     */
    private static int ONE_ROUBLE = 1;

    /**
     * Const for coin 51 roubles.
     */
    private static int FIVE_ROUBLES = 5;

    /**
     * Const for coin 10 roubles.
     */
    private static int TEN_ROUBLES = 10;

    /**
     * Method for create exchange list coins by five roubles.
     * @param money Money for exchange.
     * @return List coin by five roubles.
     */
    public List<Integer> exchangeOneManner(int money) {
        int countFiveRouble = money / FIVE_ROUBLES;
        ArrayList<Integer> listCoin = new ArrayList<>();
        for (int i = 0; i < countFiveRouble; i++) {
            listCoin.add(FIVE_ROUBLES);
        }
        return listCoin;
    }

    /**
     * Method for create all exchange lists coins by one, five, ten roubles.
     * @param money Money for exchange.
     * @return Lists with coin.
     */
    public List<List<Integer>> exchangeAllManners(int money) {
        List<List<Integer>> listsExchangeCoin = new ArrayList<>();
        int countTenRouble = money / TEN_ROUBLES;
        for(int i = 0; i < countTenRouble; i++) {
            List<List<Integer>> groupListsExchange = new ArrayList<>();
            int countLists;
            if (i == 0) {
                countLists = 2 * countTenRouble - 1;
            } else {
                countLists = (countTenRouble - i) * 2 + 1;
            }
            for(int j = 0; j < countLists; j++) {
                int baseCoin = TEN_ROUBLES;
                if (i == 0) {
                    baseCoin = FIVE_ROUBLES;
                }
                int countIteration = i + (countTenRouble - i) * 2 + j * 4;
                if(i == 0) {
                    countIteration += 4;
                }
                List<Integer> listExchange = new ArrayList<>();
                int conditionAddFive = i + (countTenRouble - i) * 2 - j;
                if (i == 0) {
                    conditionAddFive--;
                }
                for(int k = 0; k < countIteration; k++) {
                    if(i !=0 && k < i ) {
                        listExchange.add(baseCoin);
                        continue;
                    }
                    if (k < conditionAddFive) {
                        listExchange.add(FIVE_ROUBLES);
                    } else {
                        listExchange.add(ONE_ROUBLE);
                    }
                }
                groupListsExchange.add(listExchange);
            }
            listsExchangeCoin.addAll(groupListsExchange);

        }
        for (int i = 0; i < 3; i++) {
            List<Integer> listExchangeOneTypeCoin = new ArrayList<>();
            int countCoin = 0;
            int coin = 0;
            if(i == 0) {
                countCoin = money / TEN_ROUBLES;
                coin = TEN_ROUBLES;
            } else if (i == 1) {
                countCoin = money / FIVE_ROUBLES;
                coin = FIVE_ROUBLES;
            } else if (i == 2) {
                countCoin = money;
                coin = ONE_ROUBLE;
            }
            for (int j = 0; j < countCoin; j++) {
                listExchangeOneTypeCoin.add(coin);
            }

            listsExchangeCoin.add(listExchangeOneTypeCoin);
        }
        return listsExchangeCoin;
    }
}
