import java.util.ArrayList;

public class Ui {

    private static final String LINE_SEPARATOR = ("_____________________________________________________");

    public static void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    public static void printMatchingList(ArrayList<Task> tasks, String query) {
        int count = 0;
        if (tasks.size() == 0) {
            System.out.println("I'm sorry, there is no task containing your word.");
        } else {
            System.out.println("Here is your list of tasks containing " + "' " + Parser.getQueryDescription(query) + "'" + ":");
            for (Task item : tasks) {
                if (tasks.get(count) != null) {
                    count++;
                    System.out.println(count + ". " + item);
                }
            }
        }
    }

    /**
     * Return void. Function is responsible for printing out the whole task list of the user.
     *
     * @param tasks list of tasks input by user.
     */
    public static void printList(ArrayList<Task> tasks) {
        int count = 0;
        if (tasks.size() == 0) {
            System.out.println("Well... your list is looking very scarce...");
        } else {
            System.out.println("Here is your list:");
            for (Task item : tasks) {
                if (tasks.get(count) != null) {
                    count++;
                    System.out.println(count + ". " + item);
                }
            }
        }
    }

    public static void printDone(Task task) {
        System.out.println("You have marked item " + task.description + " as done:");
        System.out.println(task.getStatusIcon() + " " + task.description);
    }

    public static void printDeletedMessage(Task task) {
        System.out.println("You have deleted the item: " + "\n" + task);
    }

    public static void printGreeting() {
        String FACE = "⣿⣿⡇⠄⣼⣿⣿⠿⣿⣿⣿⣦⠘⣿⣿⣿⣿⣿⠏⣰⣿⡿⠟⢻⣿⣿⣷⡀⠸⣿\n"
                + "⣿⣿⡇⠰⣿⣿⠁⠄⠄⠄⣿⣿⠆⢹⣿⣿⣿⣿⠄⣿⣿⠁⠄⠄⠄⣿⣿⡇⠄⣿\n"
                + "⣿⣿⡇⠄⢿⣿⣷⣤⣤⣼⣿⡟⢀⣿⣿⣿⣿⣿⡄⠻⣿⣷⣤⣤⣾⣿⡿⠁⠄⣿\n"
                + "⣿⣿⠃⢸⣦⡙⠛⠿⠟⠛⠉⣠⣾⣿⣿⣿⣿⣿⣿⣆⡈⠛⠻⠿⠛⢋⣴⡇⢸⣿\n"
                + "⣿⣿⡀⠈⢿⣿⣷⣶⣶⣶⣿⣿⣿⣿⠛⣿⡋⣿⣿⣿⣿⣷⣶⣶⣾⣿⡿⠄⢸⣿\n"
                + "⣿⣿⡇⠄⠈⢿⣿⣯⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⣽⣿⡟⠄⠄⣮⣿\n"
                + "⣿⣿⣷⠄⠄⠄⠹⣿⣷⣌⠙⢿⣿⣿⣿⣿⣿⣿⣿⡿⠟⢁⣾⣿⠋⠄⠄⠄⢹⣿\n"
                + "⣿⣿⣏⠄⠄⠄⠄⠘⢿⣿⣦⡀⠈⠛⢿⣿⡿⠟⠉⢀⣴⣿⠟⠁⠄⠄⠄⢠⢸⣿\n"
                + "⣿⣿⣿⠄⠄⠄⠄⠄⠄⠙⢿⣿⣦⡀⠄⠄⠄⢀⣴⣿⠟⠃⠄⠄⠄⠄⠄⠄⣸⣿\n"
                + "⣿⣿⣿⡄⠄⠄⠄⠄⠄⠄⢠⠉⠻⢿⣷⣶⣾⡿⠛⠁⡀⠄⠄⠄⠄⠄⠄⠄⣿⣿\n";

        System.out.println(FACE);
        System.out.println("Hello! I'm Duke the Dancing Dragon.");
        System.out.println("Anything I can help you with, young Padawan?");
        printSeparator();
    }

    public static void printGoodbyeMessage() {
        System.out.println("It's over Anakin... I can finally eat my lun-");
        System.out.println(LINE_SEPARATOR);
    }
}
