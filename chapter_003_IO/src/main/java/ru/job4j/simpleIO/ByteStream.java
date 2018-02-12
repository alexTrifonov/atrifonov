package ru.job4j.simpleIO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Byte stream. Check byte stream has even number.
 * @author atrifonov.
 * @version 1.
 * @since 12.02.2018.
 */
public class ByteStream {
    /**
     * Check byte stream has even number.
     * @param in InputStream.
     * @return True if InputStream has even number.
     */
    boolean isNumber(InputStream in) {
        boolean isEvenNumber = false;

        try (InputStream testIn = in) {
            int oneByte = 0;
            while (oneByte != -1 && !isEvenNumber) {
                oneByte = testIn.read();
                if (oneByte % 2 == 0) {
                    isEvenNumber = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return isEvenNumber;
    }
}
