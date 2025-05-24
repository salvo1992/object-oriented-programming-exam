package controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import exceptions.BookException;
import observer.BookListObserver;

public class BookControllerTest {

    private BookController controller;
    private BookListObserver observerMock;

    @BeforeEach
    public void setUp() {
        controller = new BookController();
        observerMock = mock(BookListObserver.class);
        controller.addObserver(observerMock);
    }

    @Test
    public void testObserverNotifiedOnAddBook() throws Exception {
        controller.addBook("Mockito Test", "Author", 2024, "TestGenre");
        // Verifica che onBookListChanged sia stato chiamato una volta
        verify(observerMock, times(1)).onBookListChanged();
    }

    @Test
    public void testObserverNotifiedOnRemoveBook() throws Exception {
        controller.addBook("Remove Test", "Author", 2024, "TestGenre");
        controller.removeBook(controller.getBooks().get(0));
        // Deve essere stato chiamato due volte: aggiunta e rimozione
        verify(observerMock, times(2)).onBookListChanged();
    }

    @Test
public void testAddBookTitoloVuoto() {
    BookController controller = new BookController();
    assertThrows(BookException.class, () -> {
        controller.addBook("", "Autore", 2020, "Genere");
    });
}

@Test
public void testAddBookAutoreNumerico() {
    BookController controller = new BookController();
    assertThrows(BookException.class, () -> {
        controller.addBook("Titolo", "12345", 2020, "Genere");
    });
}

@Test
public void testAddBookGenereNumerico() {
    BookController controller = new BookController();
    assertThrows(BookException.class, () -> {
        controller.addBook("Titolo", "Autore", 2020, "1234");
    });
}

@Test
public void testAddBookAnnoFuturo() {
    BookController controller = new BookController();
    int nextYear = java.time.LocalDate.now().getYear() + 1;
    assertThrows(BookException.class, () -> {
        controller.addBook("Titolo", "Autore", nextYear, "Genere");
    });
}
}

