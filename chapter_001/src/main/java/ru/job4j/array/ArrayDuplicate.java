package ru.job4j.array;

import java.util.Arrays;

/**
* Class Class for remove duplicates.
* @author atrifonov
* @since 12.07.2017
* @version 1
*/
public class ArrayDuplicate {
	/**
	* Remove duplicates.
	* @param array Array of strings has duplicates.
	* @return Array without duplicates.
	*/
	public String[] remove(String[] array) {
		String temp;
		int endIndex = array.length - 1;
		boolean hasDuplicate = false;
		while (!hasDuplicate) {
			hasDuplicate = true;
			for (int i = 0; i < endIndex + 1; i++) {
				String a = array[i];
				for (int j = i + 1; j < endIndex + 1; j++) {
					if (a.equals(array[j])) {
						hasDuplicate = false;
						temp = array[j];
						array[j] = array[endIndex];
						array[endIndex] = temp;
						endIndex--;
					}
				}
			}
		}
		return Arrays.copyOf(array, endIndex + 1);
	}
}