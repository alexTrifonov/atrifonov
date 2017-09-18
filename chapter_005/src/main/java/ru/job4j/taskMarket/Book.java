package ru.job4j.taskMarket;

import java.util.*;

class Book {
    private Map<Integer, Order> mapBuy;
    private Map<Integer, Order> mapSell;

    private Map<Double, Order> bid;
    private Map<Double, Order> ask;

    Book() {
        mapBuy = new TreeMap<>();
        mapSell = new TreeMap<>();

        bid = new TreeMap<>(Comparator.<Double>reverseOrder());
        ask = new TreeMap<>();
    }

    void fillBuyAndSell(Order order, String operation, Integer id) {
        if(operation.equals("BUY")) {
            mapBuy.put(id, order);
        } else if(operation.equals("SELL")) {
            mapSell.put(id, order);
        }
    }

    void removeBuyAndSell(Integer id) {
        if(mapBuy.containsKey(id)) {
            mapBuy.remove(id);
        } else if (mapSell.containsKey(id)) {
            mapSell.remove(id);
        }
    }

    void fillAskBid() {
        fillAskOrBid(mapSell, ask);
        fillAskOrBid(mapBuy, bid);
    }


    private void fillAskOrBid(Map<Integer, Order> source, Map<Double, Order> purpose) {
        Set<Map.Entry<Integer, Order>> entries = source.entrySet();
        for (Map.Entry<Integer, Order> x:
                entries) {
            Order order = x.getValue();
            Double price = order.getPrice();
            Integer volumeSell = order.getVolume();
            if(purpose.containsKey(price)){
                Order orderOld = purpose.get(price);
                Integer volume = orderOld.getVolume();
                orderOld.setVolume(volume + volumeSell);
            } else {
                purpose.put(order.getPrice(), order);
            }
        }
    }

    void selling() {
        Set<Map.Entry<Double, Order>> entries = bid.entrySet();
        Iterator<Map.Entry<Double, Order>> itBid = entries.iterator();
        while (itBid.hasNext()) {
            Map.Entry<Double, Order> entry = itBid.next();
            Order orderBuy = entry.getValue();
            Double priceBuy = entry.getKey();

            Integer volumeBuy = orderBuy.getVolume();
            List<Double> listPriceAsk = new LinkedList<>();
            for (Double priceAsk: ask.keySet()) {
                if(Double.compare(priceBuy, priceAsk) >= 0) {
                    listPriceAsk.add(priceAsk);
                } else {
                    break;
                }
            }
            for(Double priceAsk : listPriceAsk) {
                Order orderAsk = ask.get(priceAsk);
                Integer volumeAsk = orderAsk.getVolume();
                Integer difVolume = volumeAsk - volumeBuy;
                if(difVolume < 0) {
                    ask.remove(priceAsk);
                    volumeBuy = volumeBuy - volumeAsk;
                } else if(difVolume == 0) {
                    ask.remove(priceAsk);
                    itBid.remove();
                    break;
                } else {
                    orderAsk.setVolume(difVolume);
                    itBid.remove();
                    break;
                }
            }
        }
    }

    Map<Double, Order> getBid() {
        return bid;
    }

    Map<Double, Order> getAsk() {
        return ask;
    }
}