package ru.job4j.array;

/**
* Class Class make sort array with manner "bubble".
* @author atrifonov
* @since 10.07.2017
* @version 1
*/
public class BubbleSort {
	/**
	* Sort array with manner "manner".
	* @param array Array that need to sort.
	* @return Sorted array.
	*/
	public int[] sort(int[] array) {
		boolean isSorted = false;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					isSorted = false;
				}
			}
		}
		return array;
	}
}