package ru.job4j.loop;

/**
* Class class for calculate positive number.
* @author atrifonov.
* @since 06.07.2017
* @version 1
*/
public class Factorial {
	/**
	* Calculate factorial number.
	* @param n positive number n;
	* @return Factorial number n for pozitive number, zero for negative number;
	*/
	public int calc(int n) {
		int factorial = 0;
		if (n == 0) {
			factorial = 1;
		} else if (n > 0) {
			factorial = 1;
			for (int i = 1; i <= n; i++) {
				factorial *= i;
			}
		}
		return factorial;
	}
}