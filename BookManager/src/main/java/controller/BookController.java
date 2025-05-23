package controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import exceptions.BookException;
import model.Book;
import model.BookFactory;
import model.BookIterator;
import observer.BookListObserver;
import strategy.SortByTitle;
import strategy.SortStrategy;
import utils.FileManager;
import utils.LoggerUtil;

public class BookController {
    private SortStrategy sortStrategy = new SortByTitle(); // di default

    public void setSortStrategy(SortStrategy strategy) {
        this.sortStrategy = strategy;
    }

    public void sortBooks() {
        sortStrategy.sort(books);
        notifyObservers();
    }

    private List<Book> books;
    private final List<BookListObserver> observers = new ArrayList<>();

    private static final Logger logger = LoggerUtil.getInstance();

    public BookController() {
        books = new ArrayList<>();
    }

    // Metodi per observer
    public void addObserver(BookListObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(BookListObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        for (BookListObserver observer : observers) {
            observer.onBookListChanged();
        }
    }

   public void addBook(String title, String author, int year, String genre) throws BookException {
    if (title == null || title.trim().isEmpty())
        throw new BookException("Titolo non valido!");
    if (author == null || author.trim().isEmpty() || isNumeric(author))
        throw new BookException("Autore non valido!");
    if (year <= 0 || year > LocalDate.now().getYear())
        throw new BookException("Anno non valido!");
    if (genre == null || genre.trim().isEmpty() || isNumeric(genre))
        throw new BookException("Genere non valido!");

    Book book = BookFactory.createBook(title, author, year, genre);
    books.add(book);
    logger.log(Level.INFO, "Libro aggiunto: {0}", book);
    notifyObservers();
}

// Metodo di utilità
private boolean isNumeric(String str) {
    if (str == null) return false;
    return str.chars().allMatch(Character::isDigit);
}

    public void removeBook(Book book) {
        books.remove(book);
       logger.log(Level.INFO, "Libro rimosso: {0}", book);

        notifyObservers();
    }
   // Conta i libri di un certo autore
    public long countBooksByAuthor(String author) {
    return books.stream()
        .filter(book -> book.getAuthor().equalsIgnoreCase(author))
        .count();
}

// Trova tutti i libri di un certo genere
public List<Book> filterByGenre(String genre) {
    return books.stream()
        .filter(book -> book.getGenre().equalsIgnoreCase(genre))
        .collect(Collectors.toList());
}


    public List<Book> getBooks() {
        return books;
    }

    public BookIterator getIterator() {
        return new BookIterator(books);
    }

    public void saveBooks(String filename) {
        FileManager.saveToFile(books);
        logger.log(Level.INFO, "Libri salvati su file.");

    }

   public void loadBooks(String filename) {
    List<Book> loaded = FileManager.loadFromFile();
    if (loaded != null) {
        books = loaded;
        logger.log(Level.INFO, "Libri caricati da file.");

    } else {
        logger.warning("Caricamento fallito.");
    }
    notifyObservers(); // NOTIFICA dopo aver effettivamente caricato (non prima)
}
    public void loadBooksAsync(String filename, Runnable onFinish) {
    new Thread(() -> {
        loadBooks(filename);
        if (onFinish != null) onFinish.run();
    }).start();
}
}
