package ru.job4j.loop;

/** Class Class for create chess board from space and char "x".
* @author atrifonov
* @since 07.07.2017
* @version 1
*/
public class Board {
	/**
	* Create chess board from space and char "x".
	* @param width width of board.
	* @param height height of board.
	* @return Array strings separate \r\n.
	*/
	public String paint(int width, int height) {
        String charX = "x";
        String charSpace = " ";
        String separator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        sb.append(charX);
                    } else {
                        sb.append(charSpace);
                    }
                } else {
                    if (j % 2 == 0) {
                        sb.append(charSpace);
                    } else {
                        sb.append(charX);
                    }
                }
            }
			sb.append(separator);
        }
        return sb.toString();
    }
}