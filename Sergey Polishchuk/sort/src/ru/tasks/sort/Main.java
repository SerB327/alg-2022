package ru.tasks.sort;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static final int ARRAY_SIZE = 50000;

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[ARRAY_SIZE];
        int[] checkArray = new int[ARRAY_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        System.arraycopy(array, 0, checkArray, 0, ARRAY_SIZE);
        System.out.println("Start user sort");
        long time = System.currentTimeMillis();
        sort(array);
        System.out.format("sorted: %.3f seconds\r\n", (System.currentTimeMillis() - time) / 1000d);
        System.out.println("Start java lib sort");
        time = System.currentTimeMillis();
        Arrays.sort(checkArray);
        System.out.format("sorted: %.3f seconds\r\n", (System.currentTimeMillis() - time) / 1000d);
        System.out.println("Check result");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != checkArray[i]) {
                throw new RuntimeException("Error in index: " + i);
            }
        }
        System.out.println("OK");
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            int swap = array[i];
            array[i] = array[min];
            array[min] = swap;
        }
    }
}