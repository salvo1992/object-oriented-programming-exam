package utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Book;
import model.BookFactory;

public class FileManager {
     private static final Logger logger = LoggerUtil.getInstance();
    private static final Path DATA_FILE = Paths.get("books_data.txt");

 public static void saveToFile(List<Book> books) {
        try (BufferedWriter writer = Files.newBufferedWriter(DATA_FILE)) {
            for (Book b : books) {
                String line = String.join("\n ", 
                   "il titolo è: " + b.getTitle(), 
                    "l'autore è: " + b.getAuthor(), 
                    "lanno è: " + String.valueOf(b.getYear()), 
                   "il genere è: "+ b.getGenre(),
                    "----------------------------------"
                );
                writer.write(line);
                writer.newLine();
            }
           logger.log(Level.INFO,"Libri salvati su file: {0}" , DATA_FILE);
        } catch (IOException e) {
            logger.severe("Errore nel salvataggio dei libri: " + e.getMessage());
        }
    }

  public static List<Book> loadFromFile() {
    List<Book> list = new ArrayList<>();
    if (!Files.exists(DATA_FILE)) {
        logger.warning("File non esiste, restituisco lista vuota: " + DATA_FILE);
        return list;
    }
    try (BufferedReader reader = Files.newBufferedReader(DATA_FILE)) {
        String title = null, author = null, genre = null, line;
        int year = 0;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("il titolo")) {
                title = line.substring(line.indexOf(":") + 1).trim();
                line = reader.readLine();
                author = line.substring(line.indexOf(":") + 1).trim();
                line = reader.readLine();
                year = Integer.parseInt(line.replaceAll("\\D+", "")); // Solo i numeri
                line = reader.readLine();
                genre = line.substring(line.indexOf(":") + 1).trim();
                // Skip separatore
                reader.readLine();
                list.add(BookFactory.createBook(title, author, year, genre));
            }
        }
        logger.log(Level.INFO, "Caricati {0} libri da {1}", new Object[]{list.size(), DATA_FILE});

    } catch (IOException | NumberFormatException e) {
        logger.severe("Errore nel caricamento dei libri: " + e.getMessage());
    }
    return list;
}
public static void exportToCSV(List<Book> books, String filename) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
        pw.println("Title,Author,Year,Genre");
        for (Book book : books) {
            pw.printf("\"%s\",\"%s\",%d,\"%s\"\n", book.getTitle(), book.getAuthor(), book.getYear(), book.getGenre());
        }
    } catch (IOException e) {
        LoggerUtil.getInstance().severe("Errore esportazione CSV: " + e.getMessage());
    }
}






}
