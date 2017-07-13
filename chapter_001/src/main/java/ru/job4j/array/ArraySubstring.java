package ru.job4j.array;

/**
* Class Класс для проверки наличия подстроки в строке.
* @author atrifonov
* @since 13.07.2017
* @version 1
*/
public class ArraySubstring {
	/**
	* Проверяет наличие подстроки в строке.
	* @param origin Строка, в которой может содержаться подстрока.
	* @param sub Подстрока, наличие которой проверяется.
	* @return Факт наличия подстроки в строке.
	*/
	public boolean contains(String origin, String sub) {
		char[] originChars = origin.toCharArray();
		char[] subChars = sub.toCharArray();
		boolean originHasSub = false;
		boolean originHasFirstCharSub = false;
		int indexFirstChar = 0;
		if (subChars.length > originChars.length) {
			originHasSub = false;
		} else if (origin.equals(sub)) {
			originHasSub = true;
		} else {
			for (int i = 0; i < originChars.length; i++) {
				if (originChars[i] == subChars[0]) {
					indexFirstChar = i;
					originHasFirstCharSub = true;
					break;
				}
			}
			if (originHasFirstCharSub) {
				for (int i = indexFirstChar; i < subChars.length + indexFirstChar; i++) {
					originHasSub = (originChars[i] == subChars[i - indexFirstChar]);
				}
			}
		}
		return originHasSub;
	}
}