package ru.job4j.vacancyparser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Class for map with commands.
 */
class MapConnDB {
    /**
     * Map for store commands and their names.
     */
    private static HashMap<String, String> mapCommand = new HashMap<>();
    /**
     * Create map with command for PreparedStatement and name of command.
     */
    void fillMapCommand() {
        try {
            InputStream is = getClass().getResourceAsStream("connect_db.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("-");
                mapCommand.put(strings[0], strings[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    /**
     * Get map with commands.
     * @return map.
     */
    static HashMap<String, String> getMapCommand() {
        return mapCommand;
    }
}
