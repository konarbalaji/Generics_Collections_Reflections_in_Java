package _17_StreamApi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOnBookApp {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on The Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

        List<String> filteredBooks = books.stream()
                .filter(x -> x.getType().equals(Type.NOVEL))
                .sorted(Comparator.comparing(Book::getPages))
                .map(Book::getTitle)
                .collect(Collectors.toList());

        //filteredBooks.stream().forEach(System.out::println);
        //System.out.println(Arrays.toString(filteredBooks.toArray()));

        //grouping by Type
        Map<Type, List<Book>> booksByType = books.stream()
                .collect(Collectors.groupingBy(Book::getType));

        /*booksByType.entrySet().stream()
                .forEach(System.out::println);*/

        //finding 2 longest book in terms of number of pages
        List<String> longBooks = books.stream()
                                    .filter(x -> x.getPages()>500)
                                    .map(Book::getTitle)
                                    .limit(2)
                                    .collect(Collectors.toList());

//        longBooks.stream().forEach(System.out::println);

        //Finding 2 longest books(with more than 500 pages)
        //Short circuiting & loop fusion. Filter & Map are different operations , but they are merged into same pass(loop fusion)
        //Short-circuiting : some operations don't need to process the whole stream to produce a result
        //Here, we are looking for just 2 items - so the algorithm terminates after finding 2 items !!!
        books.stream()
                .filter(x -> {
                    System.out.println("Filtering " + x.getTitle() + " with pages " + x.getPages());
                    return x.getPages()>600;
                }).map(x -> {
                    System.out.println(">> SUCCCESS Mapping " + x.getTitle() + " with pages " + x.getPages());
                    return x.getTitle();
                }).limit(2)
                .forEach(System.out::println);



    }
}