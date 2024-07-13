package org.example.task3;

import java.io.*;
import java.util.*;

public class FrequencyCounter {
    public static Map<String, Integer> countWordFrequency(String file){
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        try(FileInputStream fIs = new FileInputStream(file);
        Scanner src = new Scanner(fIs)) {

            src.useDelimiter("[\\s\\n]+");

            while (src.hasNext()){
                String word = src.next();
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0)+1);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return wordFrequencyMap;
    }
    public static void main(String[] args) {
        String file = "homeWork10//words.txt";
        Map<String, Integer> wordFrequencyMap = countWordFrequency(file);

        List<Map.Entry<String, Integer>> list
                = new ArrayList<>(wordFrequencyMap.entrySet())
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())).toList();
        

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
