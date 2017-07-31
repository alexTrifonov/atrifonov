package ru.job4j.array;

/**
 * Class Class for create sorted array from two sorted arrays.
 * @author atrifonov
 * @since 23.07.2017
 * @version 1
 */
public class ArrayFromTwoArrays {
    /**
     * Sort int array from items first array and items second array.
     * @param first Sorted int first array.
     * @param second Sorted int second array.
     * @return Sorted int array from items first arrays and items second arrays.
     */
    public int[] arrayFromTwoSortedArrays(int[] first, int[] second) {
        int j = 0;
        int k = 0;

        int firstLength = first.length;
        int secondLength = second.length;
        int[] resultArray = new int[firstLength + secondLength];

        boolean firstFinish = false;
        boolean secondFinish = false;
        for (int i = 0; i < firstLength + secondLength; i++) {
            if (firstFinish) {
                resultArray[i] = second[k];
                k++;
            } else if(secondFinish) {
                resultArray[i] = first[j];
                j++;
            } else if(first[j] <= second[k]) {
                resultArray[i] = first[j];
                if(j != firstLength - 1) {
                    j++;
                } else {
                    firstFinish = true;
                }
            } else {
                resultArray[i] = second[k];
                if(k != secondLength - 1) {
                    k++;
                } else {
                    secondFinish = true;
                }
            }
        }

        return resultArray;
    }
}