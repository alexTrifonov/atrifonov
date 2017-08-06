package ru.job4j.begin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for convert two-dimensional array to List and inversely.
 * @author atrifonov
 * @version 1
 * @since 04.07.2017
 */
public class ConvertList {
    /**
     * Convert Integer List to two-dimensional array.
     * @param array Array for converting.
     * @return List of Integer.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> listArrays = new LinkedList<>();
        for(int[] x : array) {
            for(int y : x) {
                listArrays.add(y);
            }
        }
        return listArrays;
    }

    /**
     * Convert two-dimensional array to List with assigned row number.
     * @param list List for converting.
     * @param rows Number rows in two-dimensional array.
     * @return Two-dimensional array.
     */
    public int[][] toArray(List<Integer> list, int rows) {


        int column = list.size() / rows;
        if(list.size() % rows != 0 || list.size() == 0) {
            column++;
        }
        int[][] arrayFromList = new int[rows][column];


        int positionRow = 0;
        int positionColumn = 0;
        for (Integer itemList : list) {
            arrayFromList[positionRow][positionColumn++] = itemList;
            if (positionColumn == column) {
                positionRow++;
                positionColumn = 0;
            }
        }

        return arrayFromList;
    }
}
