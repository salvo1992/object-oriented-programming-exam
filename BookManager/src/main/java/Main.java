import javax.swing.SwingUtilities;

import controller.BookController;
import view.BookManagerGUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookController controller = new BookController();
            BookManagerGUI gui = new BookManagerGUI(controller);
            gui.setVisible(true);
        });
    }
}
