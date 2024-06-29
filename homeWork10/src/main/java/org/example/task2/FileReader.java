package org.example.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileReader {
    public static void main(String[] args) {
        File file = new File("file2.txt");
        List<User> userList = new ArrayList<>();
        try (FileInputStream fIs = new FileInputStream(file);
             Scanner fileScanner = new Scanner(fIs)) {
            if (fileScanner.hasNext()){
                fileScanner.nextLine();
            }
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    Integer age = Integer.parseInt(parts[1]);
                    userList.add(new User(name, age));

                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Gson gsonFile = new GsonBuilder().setPrettyPrinting().create();
        try(Writer writer = new FileWriter("user.json")) {
            gsonFile.toJson(userList, writer);

        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
