package ru.job4j.array;

/** Class Class for turn values in array.
* And the last will be the first.
* @author atrifonov
* @since 07.07.2017
* @version 1
*/
public class Turn {
	/**
	* Turn values array items.
	* @param array This is array that need turn.
	* @return Turned array.
	*/
	public int[] back(int[] array) {
		int temp;
		for (int i = 0; i != array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;
		}
		return array;
	}
}