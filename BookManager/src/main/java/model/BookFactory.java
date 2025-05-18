package model;

public class BookFactory {

    public static Book createBook(String title, String author, int year, String genre) {
        return new Book(title, author, year, genre);
    }
}

