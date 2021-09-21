package kitty;

import kitty.io.IO;
import kitty.task.Task;
import kitty.userinterface.Ui;
import kitty.userinterface.UiHandler;
import java.util.ArrayList;

/**
 * Represents the main class in which the initialisation and running process resides.
 */
public class Kitty {
    public static final String DATA_PATH = "data/data.txt";

    public static ArrayList<Task> tasks = new ArrayList<>();

    public Kitty(String filePath) {
        // Initialise Data
        try {
            new IO(filePath);
        } catch (KittyException e) {
            Ui.printErrorMessage();
            Ui.exit();
        }
    }

    /**
     * Greets the user with Initialisation message followed by running the application.
     */
    private void runApp() {
        Ui.greet();
        UiHandler.beginUi();
    }

    public static void main(String[] args) {
        new Kitty(DATA_PATH).runApp();
    }
}
