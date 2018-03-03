package lambda.cuatro_seis;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedStreams {
    public static void main(String[] args) {
        //*** Linking streams to underlying data
        System.out.println("*** Linking streams to underlying data");
        List<String> cats = new ArrayList<>();
        cats.add("Annie");
        cats.add("Ripley");
        Stream<String> stream = cats.stream();
        cats.add("KC");
        System.out.println(stream.count());

        //*** Testing chaining optionals
        System.out.println("*** Testing chaining optionals");
        threeDigitOptional(Optional.of(10)); //Will not print
        threeDigitOptional(Optional.of(104)); //It will print

        //*** COLLECT USING BASIC COLLECTORS
        System.out.println("*** COLLECT USING BASIC COLLECTORS");
        Stream<String> ohMy_join = Stream.of("lions", "tigers", "bears");
        String result_join= ohMy_join.collect(Collectors.joining(", "));
        System.out.println(result_join); // lions, tigers, bears
        //for average
        Stream<String> ohMy_average = Stream.of("lions", "tigers", "bears");
        Double result_average = ohMy_average.collect(Collectors.averagingInt(String::length));
        System.out.println(result_average); // 5.333333333333333
        //converting to a collection
        Stream<String> ohMy_tree = Stream.of("lions", "tigers", "bears");
        TreeSet<String> result = ohMy_tree.filter(s -> s.startsWith("t")).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(result); // [tigers]
        //Collecting into Maps
        Stream<String> ohMy_map = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map = ohMy_map.collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map);

        //*** COLLECTING USING GROUPING, PARTITIONING AND MAPPING
        System.out.println("*** COLLECTING USING GROUPING, PARTITIONING AND MAPPING");
        Stream<String> ohMy_grouping = Stream.of("lions", "tigers", "bears", "bears");
        Map<Integer, List<String>> map_grouping = ohMy_grouping.collect(Collectors.groupingBy(String::length));
        System.out.println(map_grouping); // {5=[lions, bears], 6=[tigers]}
        //To use a set instead of a list
        Stream<String> ohMy_groupingset = Stream.of("lions", "tigers", "bears", "bears");
        Map<Integer, Set<String>> map_set = ohMy_groupingset.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(map_set);
        //Changing the default type of map(HashMap) for another map(TreeMap)
        Stream<String> ohMy_treeMap = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, Set<String>> map_treeMap = ohMy_treeMap.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        System.out.println(map_treeMap); // {5=[lions, bears], 6=[tigers]}

        //*** PARTITIONING is like splitting a list into two parts.
        System.out.println("*** PARTITIONING");
        Stream<String> ohMy_partitioning = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map_partitioning = ohMy_partitioning.collect(Collectors.partitioningBy(s -> s.length() <= 5));
        System.out.println(map_partitioning); // {false=[tigers], true=[lions, bears]}
        //To use set instead of list
        Stream<String> ohMy_partitioningset = Stream.of("lions", "tigers", "bears");
        Map<Boolean, Set<String>> map_partitioningset = ohMy_partitioningset.collect(Collectors.partitioningBy(s -> s.length() <= 7, Collectors.toSet()));
        System.out.println(map_partitioningset);
        //*** Another collector to count the number per group
        Stream<String> ohMy_mapcounting = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> map_mapcounting= ohMy_mapcounting.collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(map_mapcounting); // {5=2, 6=1}


/*
        Stream<String> ohMy_mostdifficult = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map_mostdifficult = ohMy_mostdifficult.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(s -> s.charAt(0),
                                Collectors.minBy(Comparator.naturalOrder()))));
        System.out.println(map_mostdifficult);

        Stream<String> ohMy_mostdifficult = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map_mostdifficult = ohMy_mostdifficult.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(s -> s.charAt(0),
                                Collectors.minBy(Comparator.naturalOrder()))));
        System.out.println(map_mostdifficult); // {5=Optional[b], 6=Optional[t]}
*/
    }

    //*** CHAINING OPTIONALS
    private static void threeDigit(Optional<Integer> optional) {
        if (optional.isPresent()) {        // outer if
            Integer num = optional.get();
            String string = "" + num;
            if (string.length() == 3)       // inner if
                System.out.println(string);
        }
    }

    private static void threeDigitOptional(Optional<Integer> optional) {
        optional.map(n -> "" + n)             // part 1
                .filter(s -> s.length() == 3)      // part 2
                .ifPresent(System.out::println);   // part 3
    }
}
