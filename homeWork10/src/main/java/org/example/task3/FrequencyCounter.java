package org.example.task3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        String file = "words.txt";
        Map<String, Integer> wordCounter = countWordFrequency(file);

        for (Map.Entry<String, Integer> entry : wordCounter.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
