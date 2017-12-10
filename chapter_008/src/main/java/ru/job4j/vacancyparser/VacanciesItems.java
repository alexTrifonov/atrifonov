package ru.job4j.vacancyparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;

public class VacanciesItems implements Callable<Elements>{

    /**
     * Logger for logging to console with VacanciesItems.class.
     */
    private static final Logger VACANCIES_LOGGER = LogManager.getLogger(VacanciesItems.class);
    /**
     * Date for start search.
     */
    private static LocalDateTime lastDateTime = LocalDateTime.of(LocalDate.now().getYear() - 1, 12, 31, 23, 59);
    /**
     * Instance of VacanciesItems.
     */
    private static VacanciesItems items;


    private VacanciesItems() {

    }

    public static VacanciesItems instance() {
        return items == null ? items = new VacanciesItems() : items;
    }

    @Override
    public Elements call() throws Exception {
        VACANCIES_LOGGER.info(String.format("start call() %n"));
        TreeMap<Integer, String> pagesMap = new TreeMap<>();
        pagesMap.put(1, "http://www.sql.ru/forum/job-offers/1");
        pagesMap.putAll(mapPages("http://www.sql.ru/forum/job-offers"));

        boolean foundNearlyLastPg = false;
        while (!foundNearlyLastPg) {
            String linkLastPg = pagesMap.lastEntry().getValue();



            ArrayList<LocalDateTime> list = firsLastDates(linkLastPg);
            LocalDateTime pageFirstDate = list.get(0);
            LocalDateTime pageLastDate = list.get(1);

            if(lastDateTime.compareTo(pageFirstDate) > 0) {
                pagesMap.remove(pagesMap.lastKey());
            } else if(lastDateTime.compareTo(pageFirstDate) == 0) {
                pagesMap.remove(pagesMap.lastKey());
                foundNearlyLastPg = true;
            } else if(lastDateTime.compareTo(pageLastDate) < 0) {
                pagesMap.putAll(mapPages(linkLastPg));
            } else if(lastDateTime.compareTo(pageLastDate) >= 0) {
                foundNearlyLastPg = true;
            }
        }

        Document doc;
        Elements rows = new Elements();
        for(Map.Entry<Integer, String> entry : pagesMap.entrySet()) {
            doc = Jsoup.connect(entry.getValue()).get();
            Elements elementsRef = doc.getElementsByAttributeValueMatching("href", "java(?!script)");
            Elements elements = new Elements();
            for(Element x : elementsRef) {
                if(!(x.text().matches("\\d*") || x.text().matches("все"))) {
                    elements.add(x);
                }
            }
            VACANCIES_LOGGER.info(String.format("page number = %d, found vacancies %d", entry.getKey(), elements.size()));
            if(entry.equals(pagesMap.lastEntry())) {
                for(int i = 0; i < elements.size(); i++) {
                    Element row = elements.get(i).parent().parent();
                    LocalDateTime dateTime = dateFromStr(row.getElementsByTag("td").last().text());
                    if(dateTime.compareTo(lastDateTime) >= 0) {
                        rows.add(row);
                    }

                }
            } else {
                for(Element x : elements) {
                    rows.add(x.parent().parent());
                }
            }
        }
        lastDateTime = dateFromStr(rows.first().getElementsByTag("td").last().text());

        VACANCIES_LOGGER.info(String.format("lastDateTime after found all vacancies = %s%n",
                lastDateTime.format(DateTimeFormatter.ofPattern("dd MMM yy, HH:mm", new Locale("ru")))));
        return rows;
    }

    private TreeMap<Integer, String> mapPages(String pageLink) throws IOException {
        Document doc = Jsoup.connect(pageLink).get();
        Element cellPages = doc.selectFirst(":containsOwn(Страницы)");
        Elements linksPages = cellPages.getElementsByAttribute("href");
        linksPages.remove(linksPages.last());
        TreeMap<Integer, String> map = new TreeMap<>();
        for(Element x : linksPages) {
            map.put(Integer.parseInt(x.ownText()), x.attr("href"));
        }
        return map;
    }

    private ArrayList<LocalDateTime> firsLastDates(String pageLink) throws IOException{
        LocalDateTime firstDateTime;
        LocalDateTime lastDateTime;
        Document doc = Jsoup.connect(pageLink).get();
        Element forumTable = doc.selectFirst(".forumTable");
        Elements rowsForumTbl = forumTable.getElementsByTag("tr");
        Element firstRow = rowsForumTbl.get(4);
        Element lastRow = rowsForumTbl.last();
        firstDateTime = dateFromStr(firstRow.getElementsByTag("td").last().text());
        lastDateTime = dateFromStr(lastRow.getElementsByTag("td").last().text());
        ArrayList<LocalDateTime> list = new ArrayList<>(2);
        list.add(firstDateTime);
        list.add(lastDateTime);
        return list;
    }


    static LocalDateTime dateFromStr(String strDateTime) throws IOException{
        LocalDateTime dateTime;
        DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd MMM yy", new Locale("ru"));
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm", new Locale("ru"));
        if(strDateTime.contains("сегодня")) {
            strDateTime = strDateTime.replace("сегодня", LocalDate.now().format(dateFormat));
        } else if(strDateTime.contains("вчера")) {
            strDateTime = strDateTime.replace("вчера", LocalDate.now().minusDays(1).format(dateFormat));
        } else if(strDateTime.contains("май")) {
            strDateTime = strDateTime.replace("май", "мая");
        }
        if(strDateTime.matches("\\d\\d.*")) {
            dateTime = LocalDateTime.parse(strDateTime, dateTimeFormat);
        } else {
            strDateTime = String.format("0%s", strDateTime);
            dateTime = LocalDateTime.parse(strDateTime, dateTimeFormat);
        }
        return dateTime;
    }
}
