package ru.job4j.taskMarket;



import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;


public class Handler {

    private Map<String, Book> bookMap = new TreeMap<>();

    public void createAndPrintBook() {
        fillMapBuyAndSell();
        createBook();
        printBook();
    }

    private void fillMapBuyAndSell(){
        File file = new File("E:\\Java\\PetrArsentev\\chapter_005\\orders.xml");
        try {
            FileInputStream in = new FileInputStream(file);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            while (parser.hasNext()) {
                int event = parser.next();
                if(event == XMLStreamConstants.START_ELEMENT && parser.getLocalName().equals("AddOrder")) {
                    String book = parser.getAttributeValue(0);
                    String operation = parser.getAttributeValue(1);
                    Double price = Double.parseDouble(parser.getAttributeValue(2));
                    Integer volume = Integer.parseInt(parser.getAttributeValue(3));
                    Integer id = Integer.parseInt(parser.getAttributeValue(4));
                    Order order = new Order(book, operation, price, volume);
                    addOrder(order, id);
                } else if(event == XMLStreamConstants.START_ELEMENT && parser.getLocalName().equals("DeleteOrder")) {
                    String bookName = parser.getAttributeValue(0);
                    Integer id = Integer.parseInt(parser.getAttributeValue(1));
                    removeOrder(bookName, id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addOrder(Order order, Integer id) {
        String nameBook = order.getBook();
        String operation = order.getOperation();

        if(bookMap.containsKey(nameBook)) {
            Book book = bookMap.get(nameBook);
            book.fillBuyAndSell(order, operation, id);
        } else {
            Book book = new Book();
            book.fillBuyAndSell(order, operation, id);
            bookMap.put(nameBook, book);

        }
    }

    private void removeOrder(String bookName, Integer id) {
        Book book = bookMap.get(bookName);
        book.removeBuyAndSell(id);
    }

    private void createBook() {
        Set<Map.Entry<String, Book>> entries = bookMap.entrySet();
        for (Map.Entry<String, Book> x : entries) {
            Book book = x.getValue();
            book.fillAskBid();
            book.selling();
        }
    }

    private void printBook() {
        Set<Map.Entry<String, Book>> entries = bookMap.entrySet();

        for (Map.Entry<String, Book> x : entries) {
            Book book = x.getValue();
            Set<Map.Entry<Double, Order>> ordersBid = book.getBid().entrySet();
            Set<Map.Entry<Double, Order>> ordersAsk = book.getAsk().entrySet();

            System.out.printf("Order book: $%s%n", x.getKey());
            System.out.printf("%-17s%s%n", "BID", "ASK");
            System.out.println("Volume@Price   - Volume@Price");
            int minSize = Math.min(ordersBid.size(), ordersAsk.size());
            Iterator<Map.Entry<Double, Order>> itBid = ordersBid.iterator();
            Iterator<Map.Entry<Double, Order>> itAsk = ordersAsk.iterator();
            for(int i = 0; i < minSize; i++) {
                Order bidOrd = itBid.next().getValue();
                String strBid = String.format("%d@%,.2f", bidOrd.getVolume(), bidOrd.getPrice());
                Order askOrd = itAsk.next().getValue();
                String strAsk = String.format("%d@%,.2f", askOrd.getVolume(), askOrd.getPrice());
                System.out.printf("%-15s- %s%n", strBid, strAsk);
            }

            if(Integer.compare(ordersBid.size(), ordersAsk.size()) > 0) {
                printMap(itBid, minSize, ordersBid.size(), false);
            } else if(Integer.compare(ordersBid.size(), ordersAsk.size()) < 0) {
                printMap(itAsk, minSize, ordersAsk.size(), true);
            }
            System.out.println();
        }
    }

    private void printMap(Iterator<Map.Entry<Double, Order>> it, int start, int finish, boolean left)  {
        String spc = "----------";
        for(int i = start; i < finish; i++) {
            Order order = it.next().getValue();
            String str = String.format("%d@%,.2f", order.getVolume(), order.getPrice());
            if(left) {
                System.out.printf("%-15s- %s%n", spc, str);
            } else {
                System.out.printf("%-15s- %s%n", str, spc);
            }

        }
    }



}
