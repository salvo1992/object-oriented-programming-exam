package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookCategory implements Serializable {
    private String name;
    private List<BookCategory> subcategories = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public BookCategory(String name) {
        this.name = name;
    }

    public void addSubcategory(BookCategory category) {
        subcategories.add(category);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public String getName() {
        return name;
    }

    public List<BookCategory> getSubcategories() {
        return subcategories;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void printCategory(String indent) {
        System.out.println(indent + "📚 Categoria: " + name);
        for (Book b : books) {
            System.out.println(indent + "  - " + b);
        }
        for (BookCategory sub : subcategories) {
            sub.printCategory(indent + "  ");
        }
    }
}

