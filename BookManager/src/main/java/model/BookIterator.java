package model;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BookIterator implements Iterator<Book> {
    private List<Book> books;
    private int index = 0;

    public BookIterator(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return index < books.size();
    }

    @Override
    public Book next() {
        if (!hasNext()) throw new NoSuchElementException();
        return books.get(index++);
    }

    public void reset() {
        index = 0;
    }
}
