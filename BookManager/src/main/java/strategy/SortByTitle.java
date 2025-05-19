package strategy;

import java.util.List;

import model.Book;

public class SortByTitle implements SortStrategy {
    @Override
    public void sort(List<Book> books) {
        books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
    }
}
