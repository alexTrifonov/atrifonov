package ru.job4j.vacancyparser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.*;


class One implements Runnable {
    @Override
    public void run() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Elements> result = service.submit(VacanciesItems.instance());
        Elements rows = null;
        try {
            rows = result.get();

        } catch (ExecutionException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (rows != null) {
            for(Element x : rows) {
                service.submit(new RecordBase(x));
            }
        }

        service.shutdown();
    }
}
