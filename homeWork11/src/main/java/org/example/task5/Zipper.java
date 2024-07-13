package org.example.task5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Zipper {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());

        int size = Math.min(firstList.size(), secondList.size());

        return IntStream.range(0, size)
                .mapToObj(i -> Stream.of(firstList.get(i), secondList.get(i)))
                .flatMap(s -> s);
    }

    public static void main(String[] args) {
        Stream<String> first = Stream.of("1", "3", "5", "7", "9");
        Stream<String> second = Stream.of("2", "4", "6", "8");
        Stream<String> result = zip(first, second);

        result.forEach(System.out::print);

//        Для виводу через кому
//        String stringresult = result.collect(Collectors.joining(", "));
//        System.out.println(stringresult);
    }
}
