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

        Iterator<T> zippedIterator = new Iterator<T>() {
            private boolean isFirst = true;
            @Override
            public boolean hasNext() {
                return firstIterator.hasNext() && secondIterator.hasNext();
            }

            @Override
            public T next() {
                if (isFirst){
                    isFirst = false;
                    return firstIterator.next();
                } else {
                    isFirst =true;
                    return secondIterator.next();
                }
            }
        };
        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(zippedIterator,Spliterator.ORDERED);
        return StreamSupport.stream(spliterator, false);

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
