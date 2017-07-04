package ru.job4j.condition;

/**
* Class Класс для определения находится ли точка на прямой.
* @author atrifonov
* @since 04.07.2017
* @version 1
*/
public class Point {
	/**
	* Координата x.
	*/
	private int x;
	/**
	* Координата y.
	*/
	private int y;

	/**
	* Конструктор - создание новой точки с указанными координатами.
	* @param x Координата x.
	* @param y Координата y.
	*/
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	* Возвращает значение x.
	* @return координата x.
	*/
	public int getX() {
		return this.x;
	}

	/**
	* Возвращает значение y.
	* @return координата y.
	*/
	public int getY() {
		return this.y;
	}

	/**
	* Возвращает факт нахождения точки на прямой с указанными параметрами.
	* @param a значение параметра a для прямой.
	* @param b значение параметра b для прямой.
	* @return находится ли точка на прямой.
	*/
	public boolean is(int a, int b) {
		return this.y == a * this.x + b;
	}
}