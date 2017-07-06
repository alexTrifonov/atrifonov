package ru.job4j.loop;

/**
* Class class for sum even number.
* @author atrifonov.
* @since 06.07.2017
* @version 1
*/
public class Counter {
	/**
	* Find sum even number.
	* @param start Start number.
	* @param finish Finish number.
	* @return Sum even number.
	*/
	public int add(int start, int finish) {
		int sum = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
}