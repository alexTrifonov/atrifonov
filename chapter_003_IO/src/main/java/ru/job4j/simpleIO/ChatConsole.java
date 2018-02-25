package ru.job4j.simpleIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple random chat with console.
 * @author atrifonov.
 * @version 1.
 * @since 21.02.2018.
 */
public class ChatConsole {
    /**
     * Logger this chat.
     */
    private static final Logger CHAT_CONSOLE = LogManager.getLogger(ChatConsole.class);
    private final String STOP = "стоп";
    private final String CONTINUE = "продолжить";
    private final String FINISH = "закончить";

    /**
     * Make chat with console.
     * @throws IOException IOException.
     */
    public void chat() {
        File fileAnsw = new File(".\\Answer.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileAnsw), "UTF-8"))) {
            ArrayList<String> listAnsw = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                listAnsw.add(line);
            }

            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            CHAT_CONSOLE.info("Начало абсурда - разговоры с консолью");
            String message = consoleIn.readLine();
            CHAT_CONSOLE.info(String.format("user : %s", message));
            int size = listAnsw.size();
            int numb;
            boolean stop = false;
            while (!FINISH.equals(message)) {
                if (CONTINUE.equals(message)) {
                    stop = false;
                } else if (STOP.equals(message)) {
                    stop = true;
                } else if (!stop) {
                    numb = new Random().nextInt(size);
                    System.out.println(listAnsw.get(numb));
                    CHAT_CONSOLE.info(String.format("console : %s", listAnsw.get(numb)));
                }
                message = consoleIn.readLine();
                CHAT_CONSOLE.info(String.format("user : %s", message));
            }
            CHAT_CONSOLE.info("Вот и пообщались.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
