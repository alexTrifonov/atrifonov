package ru.job4j.simpleIO;

import java.io.*;

/**
 * Class for drop abuse words from Reader.
 * @author atrifonov.
 * @version 1.
 * @since 12.02.2018.
 */
public class DropAbuse {

    /**
     * Drop abuse words from Reader and write text without abuse words in Writer.
     * @param reader Reader.
     * @param writer Writer.
     * @param abuse Array of abuse words.
     */
    public void dropAbuse(Reader reader, Writer writer, String[] abuse) {
        try (BufferedReader br = new BufferedReader(reader);
             BufferedWriter bwr = new BufferedWriter(writer)) {

            String line;
            while ((line = br.readLine()) != null) {

                for (String abuseWord :
                        abuse) {

                    line = line.replaceAll("(?iu)(" + abuseWord +")", "");
                }
                bwr.write(line);
                bwr.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
