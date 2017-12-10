package ru.job4j.vacancyparser;

import java.sql.*;
import java.util.concurrent.*;

/**
 * Entry point of application.
 * @author atrifonov.
 * @version 1.
 * @since 27.11.2017.
 */
public class Main {

    /**
     * psvm.
     * @param args arguments.
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement statm = null;
        MapConnDB mapConnDB = new MapConnDB();
        mapConnDB.fillMapCommand();
        try {
            conn = ConnectionFactory.getDatabaseConnection();
            statm = conn.createStatement();
            statm.execute(MapConnDB.getMapCommand().get("create_table"));
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            if(statm != null) {
                try {
                    statm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e)  {
                    e.printStackTrace();
                }
            }
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        int period = Integer.parseInt(MapConnDB.getMapCommand().get("period"));
        TimeUnit timeUnit = TimeUnit.valueOf(MapConnDB.getMapCommand().get("TimeUnit"));
        executor.scheduleAtFixedRate(new One(), 0, period, timeUnit);
        try {
            timeUnit.sleep(period * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
