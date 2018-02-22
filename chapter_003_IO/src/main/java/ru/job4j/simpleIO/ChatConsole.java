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

    /**
     * Make chat with console.
     * @throws IOException IOException.
     */
    public void chat() throws IOException {
        File fileAnsw = new File("chapter_003_IO\\src\\main\\resources\\ru\\job4j\\simpleIO\\Answer.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileAnsw), "UTF-8"));
        ArrayList<String> listAnsw = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            listAnsw.add(line);
        }

        br.close();
        BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
        CHAT_CONSOLE.info("Начало абсурда - разговоры с консолью");
        String message = consoleIn.readLine();
        CHAT_CONSOLE.info(String.format("user : %s", message));
        int size = listAnsw.size();
        int numb;
        boolean stop = false;
        while (!message.equals("закончить")) {
            if (message.equals("продолжить")) {
                stop = false;
            } else if (message.equals("стоп")) {
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
    }
}
