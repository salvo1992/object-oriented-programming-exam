package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.BookController;
import exceptions.BookException;
import model.Book;
import model.BookIterator;
import observer.BookListObserver;
import strategy.SortByAuthor;
import strategy.SortByTitle;
import strategy.SortByYear;
import utils.FileManager;
import utils.LoggerUtil;



public class BookManagerGUI extends JFrame implements BookListObserver {
    private final BookController controller;
    private DefaultListModel<Book> listModel;
    private JList<Book> bookList;

    private JTextField titleField, authorField, yearField, genreField;

    // Logger Singleton
    private static final Logger logger = LoggerUtil.getInstance();

    public BookManagerGUI(BookController controller) {
        this.controller = controller;
        setTitle("Book Manager");
        setSize(700, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents(); 
        controller.addObserver(this);
    }

    private void initComponents() {
        // Campi di input
        titleField = new JTextField(10);
        authorField = new JTextField(10);
        yearField = new JTextField(5);
        genreField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Titolo:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Autore:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Anno:"));
        inputPanel.add(yearField);
        inputPanel.add(new JLabel("Genere:"));
        inputPanel.add(genreField);

        // ComboBox per ordinamento (Strategy)
        String[] opzioniOrdinamento = {"Titolo", "Autore", "Anno"};
        JComboBox<String> sortBox = new JComboBox<>(opzioniOrdinamento);
        sortBox.addActionListener(e -> {
            switch ((String) sortBox.getSelectedItem()) {
                case "Titolo"-> controller.setSortStrategy(new SortByTitle());
                   
                case "Autore"->
                    controller.setSortStrategy(new SortByAuthor());
                  
                case "Anno"->
                    controller.setSortStrategy(new SortByYear());
                
                default -> throw new IllegalStateException("Unexpected value: " + sortBox.getSelectedItem());
            }
            controller.sortBooks();
            refreshList();
            logger.log(Level.INFO, "Ordinamento cambiato: {0}", sortBox.getSelectedItem());

        });
        inputPanel.add(new JLabel("Ordina per:"));
        inputPanel.add(sortBox);

        // Pulsanti
        JButton addBtn = new JButton("Aggiungi");
        JButton removeBtn = new JButton("Rimuovi");
        JButton saveBtn = new JButton("Salva");
        JButton loadBtn = new JButton("Carica");
        JButton exportBtn = new JButton("Esporta CSV");

        addBtn.addActionListener(this::addBook);
        removeBtn.addActionListener(this::removeBook);

        saveBtn.addActionListener(e -> {
            try {
                controller.saveBooks("books_data.txt");
                logger.log(Level.INFO, "Libri salvati su file.");

                JOptionPane.showMessageDialog(this, "Libri salvati!", "Salva", JOptionPane.INFORMATION_MESSAGE);
            } catch (BookException ex) {
                logger.severe("Errore salvataggio libri: " + ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Caricamento asincrono con refresh automatico
        loadBtn.addActionListener(e -> controller.loadBooksAsync("books_data.txt", this::refreshList));

        exportBtn.addActionListener(e -> {
            FileManager.exportToCSV(controller.getBooks(), "books_export.csv");
            logger.log(Level.INFO, "Libri esportati in books_export.csv.");

            JOptionPane.showMessageDialog(this, "Libri esportati in books_export.csv!", "Export", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(loadBtn);
        buttonPanel.add(exportBtn);

        // Lista dei libri
        listModel = new DefaultListModel<>();
        bookList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(bookList);

        // Layout generale
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ordinamento di default e refresh lista
        controller.setSortStrategy(new SortByTitle());
        controller.sortBooks();
        refreshList();
    }

    private void addBook(ActionEvent e) {
    try {
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());
        String genre = genreField.getText();

        controller.addBook(title, author, year, genre);
        logger.log(Level.INFO, "Libro aggiunto: {0} - {1}", new Object[]{title, author});

        // Pulisci i campi dopo inserimento
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
        genreField.setText("");
    } catch (BookException ex) {
        logger.severe("Errore aggiunta libro: " + ex.getMessage());
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException ex) {
        logger.severe("Errore inserimento anno: " + ex.getMessage());
        JOptionPane.showMessageDialog(this, "Anno non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
    }
}

    private void removeBook(ActionEvent e) {
        Book selected = bookList.getSelectedValue();
        if (selected != null) {
            controller.removeBook(selected);
           logger.log(Level.INFO, "Libro rimosso: {0}", selected.getTitle());

           
        }
    }

    private void refreshList() {
        listModel.clear();
        // Usa BookIterator
        BookIterator iterator = new BookIterator(controller.getBooks());
        while (iterator.hasNext()) {
            listModel.addElement(iterator.next());
        }
    }
@Override
public void onBookListChanged() {
    refreshList();
}


}

