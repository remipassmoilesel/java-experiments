package org.j8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by remipassmoilesel on 01/03/17.
 */
public class StreamExperiments {

    // sources: http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
    // http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html

    public static void main(String[] args) {
        // stream example
        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        // here nothing is done, we should add a terminal operation
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

        // like here
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));


        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });


        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        // streams cannot be reused
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);    // ok
        //stream.noneMatch(s -> true);   // exception


        // parallel processing
        List<Transaction> transactions = Arrays.asList(
                new Transaction(Transaction.GROCERY, 1, 5),
                new Transaction(Transaction.NOT_GROCERY, 2, 15),
                new Transaction(Transaction.GROCERY, 3, 25),
                new Transaction(Transaction.NOT_GROCERY, 4, 35)
                );
        List<Integer> transactionsIds =
                transactions.parallelStream()
                        .filter(t -> t.getType() == Transaction.GROCERY)
                        .sorted(Comparator.comparing(Transaction::getValue).reversed())
                        .map(Transaction::getId)
                        .collect(toList());

        System.out.println(transactionsIds);
    }


}
