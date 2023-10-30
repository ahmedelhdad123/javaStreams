package org.example;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Author> authors = Library.getAuthors();

        banner("Authors information");
        // TODO With functional interfaces declared
        Consumer<Author> printer = System.out::println;
        authors
                .stream()
                .forEach(printer);
        // TODO With functional interfaces used directly
        authors
                .stream()
                .forEach(System.out::println);

        banner("Active authors");
        // TODO With functional interfaces declared
        Predicate<Author> activeAuthors = author -> author.active;
        authors
                .stream()
                        .filter(activeAuthors)
                                .forEach(printer);
        // TODO With functional interfaces used directly
        authors
                .stream()
                        .filter(author -> author.active)
                                .forEach(System.out::println);
        banner("Active books for all authors");
        // TODO With functional interfaces declared
        Consumer<Book> activeBooks= System.out::println;
        Function<Author,Stream<Book>> streamFunction=author -> author.books.stream();
        Predicate<Book> bookPredicate= book -> book.published;
        authors
                .stream()
                        .flatMap(streamFunction)
                                .filter(bookPredicate)
                                        .forEach(activeBooks);

        // TODO With functional interfaces used directly
        authors
                .stream()
                        .flatMap(author -> author.books.stream())
                                .filter(book -> book.published)
                                        .forEach(System.out::println);
        banner("Average price for all books in the library");
        // TODO With functional interfaces declared
        ToIntFunction<Book> getPrice=(book) ->book.price;
        authors
                .stream()
                        .flatMap(streamFunction)
                                .mapToInt(getPrice)
                                        .average()
                                                .orElse(0);

        // TODO With functional interfaces used directly
        authors
                .stream()
                        .flatMap(author -> author.books.stream())
                                .mapToInt(book -> book.price)
                                        .average()
                                                .orElse(0);

        banner("Active authors that have at least one published book");
        // TODO With functional interfaces declared
        Predicate<Author> leastOnePublishedBook=author -> author.books.stream().anyMatch(bookPredicate);
        authors
                .stream()
                .filter(activeAuthors)
                .filter(leastOnePublishedBook)
                .forEach(printer);

        // TODO With functional interfaces used directly
        authors
                .stream()
                .filter(author -> author.active)
                .filter(author -> author.books.stream().anyMatch(bookPredicate))
                .forEach(System.out::println);
    }

    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }
}