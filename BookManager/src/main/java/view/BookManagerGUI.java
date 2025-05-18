package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.BookController;
import model.Book;

public class BookManagerGUI extends JFrame {
    private final BookController controller;
    private DefaultListModel<Book> listModel;
    private JList<Book> bookList;

    private JTextField titleField, authorField, yearField, genreField;

    public BookManagerGUI(BookController controller) {
        this.controller = controller;
        setTitle("Book Manager");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
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

        // Pulsanti
        JButton addBtn = new JButton("Aggiungi");
        JButton removeBtn = new JButton("Rimuovi");
        JButton saveBtn = new JButton("Salva");
        JButton loadBtn = new JButton("Carica");

        addBtn.addActionListener(this::addBook);
        removeBtn.addActionListener(this::removeBook);
        saveBtn.addActionListener(e -> controller.saveBooks("books_data.txt"));
        loadBtn.addActionListener(e -> {
            controller.loadBooks("books_data.txt");
            refreshList();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(loadBtn);

        // Lista dei libri
        listModel = new DefaultListModel<>();
        bookList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(bookList);

        // Layout generale
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addBook(ActionEvent e) {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            String genre = genreField.getText();

            controller.addBook(title, author, year, genre);
            refreshList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Anno non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeBook(ActionEvent e) {
        Book selected = bookList.getSelectedValue();
        if (selected != null) {
            controller.removeBook(selected);
            refreshList();
        }
    }

    private void refreshList() {
        listModel.clear();
        for (Book b : controller.getBooks()) {
            listModel.addElement(b);
        }
    }
}
