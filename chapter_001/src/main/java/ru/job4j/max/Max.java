package ru.job4j.max;

/**
* Class Класс для нахождения максимума из двух целых чисел.
* @author atrifonov
* @since 04.07.2017
* @version 1
*/
public class Max {

	/**
	* Нахождение максимума из двух целых чисел.
	* @param first Первый аргумент.
	* @param second Второй аргумент.
	* @return Наибольшее из двух целых чисел.
	*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}
}