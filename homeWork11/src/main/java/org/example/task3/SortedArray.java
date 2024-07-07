package org.example.task3;

import java.util.Arrays;

import java.util.stream.Collectors;

public class SortedArray {
    public static String sortArray(String[] numbers){
       return Arrays.stream(numbers)
               .flatMap(s -> Arrays.stream(s.split(", ")))
               .map(Integer::parseInt)
               .sorted()
               .map(String::valueOf)
               .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        String[] numbers = {"1, 2, 0", "4, 5"};
        System.out.println(sortArray(numbers));
    }
}
