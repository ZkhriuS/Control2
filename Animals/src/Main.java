import controller.AnimalController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        AnimalController controller = new AnimalController();
        ConsoleView view = new ConsoleView(controller);
        view.menu();
    }
}
