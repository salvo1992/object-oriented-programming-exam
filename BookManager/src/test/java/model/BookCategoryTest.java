package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import exceptions.BookException;

public class BookCategoryTest {

    @Test
    public void testBookCategoryComposite() throws BookException {
        // Creo categoria radice
        BookCategory root = new BookCategory("Root");

        // Creo sotto-categorie
        BookCategory classics = new BookCategory("Classici");
        BookCategory dystopics = new BookCategory("Distopici");

        // Aggiungo sottocategorie a root
        root.addSubcategory(classics);
        root.addSubcategory(dystopics);

        // Creo due libri tramite Factory
        Book b1 = BookFactory.createBook("Il nome della rosa", "Eco", 1980, "Giallo");
        Book b2 = BookFactory.createBook("1984", "Orwell", 1949, "Distopico");

        // Aggiungo i libri alle rispettive categorie
        classics.addBook(b1);
        dystopics.addBook(b2);

        // Stampo la gerarchia (puoi vedere l’output nel log di test)
        root.printCategory("");

        // Verifico che root abbia due sottocategorie
        assertEquals(2, root.getSubcategories().size());
        // Verifico che in classics ci sia b1
        assertTrue(classics.getBooks().contains(b1));
        // Verifico che in dystopics ci sia b2
        assertTrue(dystopics.getBooks().contains(b2));
    }
}

