package ru.job4j.loop;

/** Class Class for create pyramid from space and char "^".
* @author atrifonov
* @since 07.07.2017
* @version 1
*/
public class Paint {
	/**
	* Create pyramid from space and char "^".
	* @param h height of pyramid.
	* @return Array strings separated separator system.
	*/
	public String pyramid(int h) {
		int w = h * 2 - 1;
        StringBuilder sb = new StringBuilder();
        String separator = System.getProperty("line.separator");
        for (int i = h; i > 0; i--) {
            for (int j = 0; j < w; j++) {
                char x = (j < i - 1) || (j > w - i) ? ' ' : '^';
                sb.append(x);
            }
            if (i != 1) {
				sb.append(separator);
			}
        }
        String str = sb.toString();
        return str;
	}
}