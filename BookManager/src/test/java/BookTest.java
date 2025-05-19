

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookController;
import exceptions.BookException;
import model.Book;

class BookTest {
    private BookController controller;

    @BeforeEach
    void setup() {
        controller = new BookController();
    }

    @Test
    void testAddBook() throws BookException {
        controller.addBook("1984", "George Orwell", 1949, "Distopia");
        List<Book> books = controller.getBooks();
        assertEquals(1, books.size());
        assertEquals("1984", books.get(0).getTitle());
    }

    @Test
    void testRemoveBook() throws BookException {
        controller.addBook("Il Signore degli Anelli", "Tolkien", 1954, "Fantasy");
        Book book = controller.getBooks().get(0);
        controller.removeBook(book);
        assertTrue(controller.getBooks().isEmpty());
    }
}
