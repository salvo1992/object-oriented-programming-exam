package model;
import java.io.Serializable;
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String author;
    private int year;
    private String genre;

    public Book(String title, String author, int year, String genre) {
        this.title  = title;
        this.author = author;
        this.year   = year;
        this.genre  = genre;
    }

    // Getter
    public String getTitle() { return   title; }
    public String getAuthor() { return author; }
    public int getYear() { return  year; }
    public String getGenre() { return  genre; }

    @Override
    public String toString() {
        return String.format("%s (%d) - %s | %s", title, year, author, genre);
    }
}
