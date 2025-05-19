package strategy;

import java.util.List;

import model.Book;
public class SortByAuthor  implements SortStrategy {
    @Override
    public void sort(List<Book> books) {
        books.sort((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()));
    }
    
}
