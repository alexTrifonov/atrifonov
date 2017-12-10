package ru.job4j.vacancyparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class RecordBase implements Runnable{

    /**
     * Logger for logging to console with RecordBase.class.
     */
    private static final Logger RECORD_BASE_LOGGER = LogManager.getLogger(RecordBase.class);

    private Element row;
    public RecordBase(Element aRow) {
        this.row = aRow;
    }

    @Override
    public void run() {
        //RECORD_BASE_LOGGER.info("start run()");
        Element linkVacancy = row.selectFirst("[href]");
        //System.out.println(linkVacancy);
        String nameVacancy = linkVacancy.text();
        String author = row.selectFirst("[href*=memberinfo]").text();
        String dateStr = row.getElementsByTag("td").last().text();
        LocalDateTime dateTime;
        String link = linkVacancy.attr("href");
        String fullMsg;
        try {
            dateTime = VacanciesItems.dateFromStr(dateStr);
            Document docFullMsg = Jsoup.connect(link).get();
            Element msgTable = docFullMsg.selectFirst(".msgTable");
            fullMsg = msgTable.getElementsByClass("msgBody").last().text();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        Connection conn = null;
        PreparedStatement prepStm = null;
        ResultSet resultSet = null;
        try {
            conn = ConnectionFactory.getDatabaseConnection();
            prepStm = conn.prepareStatement(MapConnDB.getMapCommand().get("select_existing"));
            prepStm.setString(1, nameVacancy);
            prepStm.setString(2, author);
            resultSet = prepStm.executeQuery();
            if(!resultSet.next()) {
                prepStm = conn.prepareStatement(MapConnDB.getMapCommand().get("add_new_row"));
                prepStm.setString(1, nameVacancy);
                prepStm.setString(2, author);
                prepStm.setTimestamp(3, Timestamp.valueOf(dateTime));
                prepStm.setString(4, fullMsg);
                prepStm.executeUpdate();
                RECORD_BASE_LOGGER.info(String.format("Insert data: NAME_VACANCY = %s, AUTHOR = %s, DATE_TIME = %s",
                        nameVacancy, author, dateTime.format(DateTimeFormatter.ofPattern("dd MMM yy, HH:mm", new Locale("ru")))));
            } else {
                RECORD_BASE_LOGGER.info("Found same vacancy_message and same author");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    RECORD_BASE_LOGGER.error(e.getMessage());
                }
            }
            if(conn != null ) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    RECORD_BASE_LOGGER.error(ex.getMessage());
                }
            }
            if(prepStm != null) {
                try {
                    prepStm.close();
                } catch (SQLException ex) {
                    RECORD_BASE_LOGGER.error(ex.getMessage());
                }
            }
        }


    }


}
