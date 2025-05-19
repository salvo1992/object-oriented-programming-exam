package controller;

import static org.mockito.Mockito.*;

import observer.BookListObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}

