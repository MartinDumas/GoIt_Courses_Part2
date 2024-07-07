package org.example.task2;

import java.util.List;
import java.util.stream.Collectors;

public class UpperAndSortDescending {

    public static List<String> toUpperAndSortDescending(List<String> names){
        return names.stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> names = List.of("Ivan", "Peter", "Nick", "Maksym", "Danylo", "Artem", "Nazar", "Arsen");
        List<String> result = toUpperAndSortDescending(names);
        result.forEach(System.out::println);

    }
}
