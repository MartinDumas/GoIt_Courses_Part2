package org.example.task5;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Zipper {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        Stream.iterate(new Object[]{firstIterator, secondIterator, true}, arr -> new Object[]{arr[0], arr[1], !(boolean)arr[2]})
                                .takeWhile(arr -> ((Iterator<T>)arr[0]).hasNext() && ((Iterator<T>)arr[1]).hasNext())
                                .map(arr -> (boolean)arr[2] ? ((Iterator<T>)arr[0]).next() : ((Iterator<T>)arr[1]).next())
                                .iterator(), 0
                ), false
        );

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
