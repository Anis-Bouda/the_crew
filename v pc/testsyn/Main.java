import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NON model = new NON("NOT1", 10, 10);
            NonGateView view = new NonGateView();
            new NonGateController(model, view);
            view.setVisible(true);
        });
    }
}