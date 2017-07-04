package ru.job4j.calculator;

/**
* Class класс для выполнения операций с двумя аргументами.
* @author atrifonov
* @since 04.07.2017
* @version 1
*/
public class Calculator {
	/**
	* Результат вычисления.
	*/
    private double result;

	/**
	* Сумма аргументов.
	* @param first Первый аргумент.
	* @param second Второй аргумент.
	*/
    public void add(double first, double second) {
        this.result = first + second;
    }

	/**
	* Разница аргументов.
	* @param first Первый аргумент.
	* @param second Второй аргумент.
	*/
	public void subtruct(double first, double second) {
		this.result = first - second;
	}

	/**
	* Деление аргументов.
	* @param first Первый аргумент.
	* @param second Второй аргумент.
	*/
	public void div(double first, double second) {
		this.result = first / second;
	}

	/**
	* Умножение аргументов.
	* @param first Первый аргумент.
	* @param second Второй аргумент.
	*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}

	/**
    * Получить результат.
    * @return результат вычисления.
    */
    public double getResult() {
        return this.result;
    }

}