package strategy;

import java.util.List;

import model.Book;

public class SortByYear implements SortStrategy {
    @Override
    public void sort(List<Book> books) {
        books.sort((b1, b2) -> Integer.compare(b1.getYear(), b2.getYear()));
    }
}
