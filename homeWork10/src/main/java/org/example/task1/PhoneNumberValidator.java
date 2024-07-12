package org.example.task1;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {
    public static void main(String[] args) {
        File file = new File("homeWork10//file1.txt");

        String pattern1 = "\\(\\d{3}\\) \\d{3}-\\d{4}";
        String pattern2 = "\\d{3}-\\d{3}-\\d{4}";

        Pattern p1 = Pattern.compile(pattern1);
        Pattern p2 = Pattern.compile(pattern2);

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                if (isValidNumber(line, p1, p2)){
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean isValidNumber(String line, Pattern p1, Pattern p2) {
        Matcher m1 = p1.matcher(line);
        Matcher m2 = p2.matcher(line);

        return m1.matches() || m2.matches();
    }
}
