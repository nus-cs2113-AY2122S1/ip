import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String taskDoneChecker = "0";

    public static void main(String[] args) {
        StorageUI.storageWelcomeMessage();

        Ui.welcomeMessage();

        Parser.commandChecker();
    }
}

