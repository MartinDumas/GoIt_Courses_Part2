package org.example.task4;

import java.util.stream.Stream;

public class Iterator {
    public static Stream<Long> streamIterator(long seed, long a, long c, long m){
        return
                Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static void main(String[] args) {
        long a = 25214903917l;
        long c = 11;
        long m = (long) Math.pow(2, 48);
        long seed = 0l;
        Stream<Long> iterate = streamIterator(seed, a, c, m);
        iterate.limit(10).forEach(System.out::println);


    }
}
