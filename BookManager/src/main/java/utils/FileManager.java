package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Book;

public class FileManager {
    private static final Logger logger = Logger.getLogger(FileManager.class.getName());

    public static void saveToFile(List<Book> books, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
        } catch (IOException e) {
            logger.severe("Errore nel salvataggio dei libri: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Errore nel caricamento dei libri: " + e.getMessage());
            return null;
        }
    }
}
