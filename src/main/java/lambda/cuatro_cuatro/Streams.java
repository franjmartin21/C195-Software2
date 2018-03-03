package lambda.cuatro_cuatro;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        //*** CREATION STREAM SOURCES ***
        //Creating stream sources
        Stream<String> empty = Stream.empty();           // count = 0
        Stream<Integer> singleElement = Stream.of(1);    // count = 1
        Stream<Integer> fromArray = Stream.of(1, 2, 3);
        //Creating infinite streams
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Double> randoms2 = Stream.generate(()-> Math.random());
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

        //***TERMINAL FUNCTIONS ON STREAMS***
        //COUNT
        System.out.println("*** COUNT");
        System.out.println(empty.count());
        System.out.println(fromArray.count());
        //MIN AND MAX
        System.out.println("*** MIN AND MAX");
        Stream<String> s_min = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s_min.min((s1, s2) -> s1.length()-s2.length());
        min.ifPresent(System.out::println); // ape
        Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
        System.out.println(minEmpty.isPresent());

        //FINDANY AND FINDFIRST (Works on infinite Stream)
        System.out.println("*** FINDANY AND FINDFIST");
        Stream<String> s2 = Stream.of("monkey", "ape", "bonobo");
        s2.findAny().ifPresent(System.out::println);        // monkey
        randoms.findAny().ifPresent(System.out::println);

        //ALLMATCH, ANYMATCH, NONEMATCH
        System.out.println("*** ALLMATCH, ANYMATCH AND NONEMATCH");
        List<String> list = Arrays.asList("monkey", "2", "chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
        System.out.println(list.stream().anyMatch(pred)); // true
        System.out.println(list.stream().allMatch(pred)); // false
        System.out.println(list.stream().noneMatch(pred));// false
        System.out.println(infinite.anyMatch(pred));      // true

        //FOREACH
        System.out.println("*** FOREACH");
        Stream<String> s3 = Stream.of("Monkey", "Gorilla", "Bonobo");
        s3.forEach(System.out::print);    // MonkeyGorillaBonobo

        //REDUCE
        System.out.println("*** REDUCE");
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", (x, y) -> x + y);
        System.out.println(word);
        //anotherway...
        stream = Stream.of("w", "o", "l", "f");
        word = stream.reduce("", String::concat);
        System.out.println(word);
        //with numbers
        Stream<Integer> stream2 = Stream.of(3, 5, 6);
        System.out.println(stream2.reduce(1, (a, b) -> a*b));
        //When identity(the first param of reduce) is not passed, Optional gets returned
        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty_red = Stream.empty();
        Stream<Integer> oneElement_red = Stream.of(3);
        Stream<Integer> threeElements_red = Stream.of(3, 5, 6);

        empty_red.reduce(op).ifPresent(System.out::println);          // no output
        oneElement_red.reduce(op).ifPresent(System.out::println);     // 3
        threeElements_red.reduce(op).ifPresent(System.out::println);  // 90
        //COLLECT
        System.out.println("*** COLLECT");
        Stream<String> stream_collect = Stream.of("w", "o", "l", "f");
        StringBuilder word_collect = stream_collect.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(word_collect.toString());
        //Using a tree
        Stream<String> stream_collect2 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set_collect2 = stream_collect2.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println(set_collect2); // [f, l, o, w]
        //The easy way
        Stream<String> stream_collect3 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set = stream_collect3.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set); // [f, l, o, w]
        //For set or list, even easier
        Stream<String> stream_collect4 = Stream.of("w", "o", "l", "f");
        Set<String> set_collect4 = stream_collect4.collect(Collectors.toSet());
        System.out.println(set_collect4); // [f, w, l, o]
        stream_collect4 = Stream.of("w", "o", "l", "f");
        List<String> list_collect4 = stream_collect4.collect(Collectors.toList());
        System.out.println(list_collect4);

        //*** INTERMEDIATE OPERATIONS ***
        //FILTER
        System.out.println("*** FILTER");
        Stream<String> s_filter = Stream.of("monkey", "gorilla", "bonobo");
        s_filter.filter(x -> x.startsWith("m")).forEach(System.out::println);
        //DISTINCT
        System.out.println("*** DISTINCT");
        Stream<String> s_distinct = Stream.of("duck", "duck", "duck", "goose");
        s_distinct.distinct().forEach(System.out::println); // duckgoose
        //LIMIT AND SKIP (used for infinite streams
        System.out.println("*** LIMIT AND SKIP");
        Stream<Integer> s_limit = Stream.iterate(1, n -> n + 1);
        s_limit.skip(5).limit(2).forEach(System.out::println);
        //MAP
        System.out.println("*** MAP");
        Stream<String> s_map = Stream.of("monkey", "gorilla", "bonobo");
        //s_map.map(String::length).forEach(System.out::print);
        s_map.map(x -> x.length()).forEach(System.out::println);
        //FLATMAP
        System.out.println("*** FLATMAP");
        List<String> zero = Arrays.asList();
        List<String> one = Arrays.asList("Bonobo");
        List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);
        animals.flatMap(l -> l.stream()).forEach(System.out::println);
        //SORTED
        System.out.println("*** SORTED");
        Stream<String> s_sorted = Stream.of("brown-", "bear-");
        s_sorted.sorted().forEach(System.out::println);
        //to reverse order
        s_sorted = Stream.of("brown bear-", "grizzly-");
        s_sorted.sorted(Comparator.reverseOrder()).forEach(System.out::println);
        //PEEK
        System.out.println("*** PEEK");
        Stream<String> stream_peek = Stream.of("black bear", "brown bear", "grizzly", "gusano");
        long count = stream_peek.filter(s -> s.startsWith("g")).peek(System.out::println).count();    // grizzly
        System.out.println(count);

        //PUTTING TOGETHER THE PIPELINE
        //Without pipe
        System.out.println("*** PUTTING TOGETHER THE PIPELINE");
        List<String> list_together = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
        List<String> filtered = new ArrayList<>();
        for (String name: list_together) {
            if (name.length() == 4) filtered.add(name);
        }
        Collections.sort(filtered);
        Iterator<String> iter = filtered.iterator();
        if (iter.hasNext()) System.out.println(iter.next());
        if (iter.hasNext()) System.out.println(iter.next());
        //With pipe
        List<String> list_together2 = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
        list_together2.stream().filter(n -> n.length() == 4).sorted().limit(2).forEach(System.out::println);
    }
}
