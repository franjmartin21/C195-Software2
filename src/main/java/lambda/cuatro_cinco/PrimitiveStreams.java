package lambda.cuatro_cinco;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreams {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(1, 2, 3);
        OptionalDouble avg = intStream.average();
        System.out.println(avg.getAsDouble());

        //*** Creating Primitive Streams
        System.out.println("*** Creating Primitive Streams");
        DoubleStream oneValue = DoubleStream.of(3.14);
        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        oneValue.forEach(System.out::println);
        System.out.println();
        varargs.forEach(System.out::println);
        //Infinite streams
        DoubleStream random_infinite = DoubleStream.generate(Math::random);
        DoubleStream fractions_infinite = DoubleStream.iterate(.5, d -> d / 2);
        random_infinite.limit(3).forEach(System.out::println);
        System.out.println();
        fractions_infinite.limit(3).forEach(System.out::println);

        //*** IntStream
        System.out.println("*** IntStream");
        IntStream count_intStream = IntStream.iterate(1, n -> n+1).limit(5);
        count_intStream.forEach(System.out::println);
        //more simple
        IntStream range_intStream = IntStream.range(1, 6);
        range_intStream.forEach(System.out::println);
        //including all in the range
        IntStream rangeClosed_intStream = IntStream.rangeClosed(1, 6);
        rangeClosed_intStream.forEach(System.out::println);

        //*** Convesions from Stream
        System.out.println("*** Convesions from Stream");
        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream intStream1 = objStream.mapToInt(s -> s.length());
        intStream1.forEach(System.out::println);

        //*** Using Optional with Primitive Streams
        System.out.println("*** Using Optional with Primitive Streams");
        IntStream stream_optional = IntStream.rangeClosed(1,10);
        OptionalDouble optional = stream_optional.average();
        optional.ifPresent(System.out::println);
        System.out.println(optional.getAsDouble());
        System.out.println(optional.orElseGet(() -> Double.NaN));
        //Another example
        LongStream longs = LongStream.of(5, 10);
        long sum = longs.sum();
        System.out.println(sum);            // 15
        DoubleStream doubles = DoubleStream.generate(() -> Math.PI);
        OptionalDouble min = doubles.limit(5).min(); // runs infinitely (not adding limit)
        System.out.println(min.getAsDouble());

        //**** SUMARIZING STATISTICS
        System.out.println("*** Sumarizing Statistics");

    }

}
