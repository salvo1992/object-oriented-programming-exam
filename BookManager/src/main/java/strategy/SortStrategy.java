package strategy;

import java.util.List;

import model.Book;

public interface SortStrategy {
    void sort(List<Book> books);
}

