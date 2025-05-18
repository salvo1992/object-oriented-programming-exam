package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Book;
import model.BookFactory;
import model.BookIterator;
import utils.FileManager;

public class BookController {
    private List<Book> books;
    private final Logger logger = Logger.getLogger(BookController.class.getName());

    public BookController() {
        books = new ArrayList<>();
    }

    public void addBook(String title, String author, int year, String genre) {
        Book book = BookFactory.createBook(title, author, year, genre);
        books.add(book);
        logger.info("Libro aggiunto: " + book);
    }

    public void removeBook(Book book) {
        books.remove(book);
        logger.info("Libro rimosso: " + book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public BookIterator getIterator() {
        return new BookIterator(books);
    }

    public void saveBooks(String filename) {
        FileManager.saveToFile(books, filename);
        logger.info("Libri salvati su file.");
    }

    public void loadBooks(String filename) {
        List<Book> loaded = FileManager.loadFromFile(filename);
        if (loaded != null) {
            books = loaded;
            logger.info("Libri caricati da file.");
        } else {
            logger.warning("Caricamento fallito.");
        }
    }
}
