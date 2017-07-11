package ru.job4j.array;

/**
* Class Class rotate bidimensional array.
* @author atrifonov
* @since 11.07.2017
* @version 1
*/
public class RotateArray {
	/**
	* This method rotate bidimensional array on 90 degree.
	* @param array bidimensional array for rotate.
	* @return Bidimensional array turned on 90 degree.
	*/
	public int[][] rotate(int[][] array) {
		int temp;
		int temp1;
		int row;
		int col;
		for (int j = 0; j < array.length / 2; j++) {
			row = j;
			col = j;
			int step = array.length - 1 - j * 2;
			for (int i = 0; i < step; i++) {
				temp = array[row + i][col + step - i];
				array[row + i][col + step - i] = array[row][col];

				temp1 = array[row + step][col + step - i * 2];
				array[row + step][col + step - i * 2] = temp;

				temp = array[row + step - i][col - i];
				array[row + step - i][col - i] = temp1;

				array[row][col] = temp;
				col++;
			}
		}
		return array;
	}
}