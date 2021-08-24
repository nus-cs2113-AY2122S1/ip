import java.util.Scanner;

public class Duke {

    /* Logo - generated using https://loveeevee.github.io/Dots-Converter/ */
    private static final String LOGO = "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣀⣀⣀⡀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣀⣤⣤⣶⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣤⣤⣀⣀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣀⣤⣶⣾⣿⣿⣿⣿⣿⣿⠿⠟⠛⠉⢉⣿⣿⣯⠉⠉⠛⠻⠿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣄⡀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣠⣴⣿⣿⡿⠟⢋⣿⣿⣿⠟⠋⢀⢀⢀⢀⢀⣾⠟⢀⢻⣧⡀⢀⢀⢀⢀⠙⠻⣿⣿⣯⡉⠛⠿⣿⣿⣷⣦⣀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⣠⣴⣿⣿⠟⠋⠁⢀⣴⣿⡿⠋⢀⢀⢀⢀⢀⢀⢠⣾⠋⢀⢀⢀⠹⣷⡀⢀⢀⢀⢀⢀⠈⠻⣿⣿⣦⢀⢀⠉⠻⢿⣿⣷⣦⡀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⣴⣾⣿⡿⠋⠁⢀⢀⢠⣾⣿⠟⠁⢀⢀⢀⢀⢀⢀⣰⡿⠁⢀⢀⢀⢀⢀⠘⢿⣄⢀⢀⢀⢀⢀⢀⠈⠻⣿⣷⡀⢀⢀⢀⠉⠻⣿⣿⣦⡀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⣴⣿⣿⡿⠋⢀⢀⢀⢀⢠⣿⣿⠋⢀⢀⢀⢀⢀⢀⢀⣼⡟⠁⢀⢀⢀⢀⢀⢀⢀⠈⢿⣦⢀⢀⢀⢀⢀⢀⢀⠹⣿⣷⢀⢀⢀⢀⢀⠈⠻⣿⣿⣦⡀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⣠⣾⣿⡿⠋⢀⢀⢀⢀⢀⢀⣾⣿⡏⢀⢀⢀⢀⢀⢀⢀⣾⠟⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢻⣧⡀⢀⢀⢀⢀⢀⢀⢹⣿⣧⢀⢀⢀⢀⢀⢀⠈⠻⣿⣿⣄⢀⢀⢀⢀\n"
            + "⢀⢀⣴⣿⣿⠋⢀⢀⢀⢀⢀⢀⢀⢰⣿⣿⢀⢀⢀⢀⢀⢀⢠⣿⠋⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠹⣷⡄⢀⢀⢀⢀⢀⢀⣿⣿⡄⢀⢀⢀⢀⢀⢀⢀⠘⢿⣿⣧⡀⢀⢀\n"
            + "⢀⣾⣿⡟⠁⢀⢀⢀⢀⢀⢀⢀⢀⢸⣿⡇⢀⢀⢀⢀⢀⣰⡿⠃⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠙⣿⣄⢀⢀⢀⢀⢀⢻⣿⡇⢀⢀⢀⢀⢀⢀⢀⢀⢀⢻⣿⣷⣄⢀\n"
            + "⠹⣿⣿⣆⢀⢀⢀⢀⢀⢀⢀⢀⢀⢸⣿⡇⢀⢀⢀⢀⣴⡟⠁⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠈⢿⣆⢀⢀⢀⢀⣸⣿⡇⢀⢀⢀⢀⢀⢀⢀⢀⢀⣴⣿⡿⠋⢀\n"
            + "⢀⠘⢿⣿⣧⡀⢀⢀⢀⢀⢀⢀⢀⢸⣿⣷⢀⢀⢀⣾⠟⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢻⣧⡀⢀⢀⣿⣿⠇⢀⢀⢀⢀⢀⢀⢀⣠⣾⣿⡿⠁⢀⢀\n"
            + "⢀⢀⠈⠻⣿⣿⣦⡀⢀⢀⢀⢀⢀⠈⣿⣿⡄⢠⣾⠋⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠹⣷⡄⢰⣿⡿⢀⢀⢀⢀⢀⢀⢀⣴⣿⡿⠋⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⠈⠻⣿⣷⣦⡀⢀⢀⢀⢀⠸⣿⣷⣿⣇⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣸⣿⣾⣿⠃⢀⢀⢀⢀⢀⣴⣿⣿⠟⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⠈⠻⣿⣿⣦⡀⢀⢀⢀⠹⣿⣿⡟⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⣻⣿⣿⠏⢀⢀⢀⣠⣶⣿⡿⠟⠁⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⠈⠻⣿⣿⣷⣤⡀⢀⠙⣿⣿⣦⡀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣴⣿⡿⠃⢀⣀⣴⣾⣿⡿⠛⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠙⠻⣿⣿⣷⣦⣀⢻⣿⣿⣦⣀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣠⣴⣿⣿⣏⣠⣴⣾⣿⡿⠟⠉⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠉⠻⠿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣀⢀⢀⢀⢀⢀⢀⢀⢀⢀⣀⣤⣶⣿⣿⣿⣿⣿⣿⣿⠿⠛⠁⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠉⠛⠻⠿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣾⣿⣿⣿⣿⣿⣿⡿⠿⠛⠋⠁⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀\n"
            + "⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⠉⠉⠙⠛⠛⠿⠿⠿⠿⠿⠛⠛⠋⠉⠉⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀⢀";

    /* Divider */
    private static final String DIVIDER = "============================================================";

    /* Prints a greeting message with logo */
    public static void printGreeting() {
        System.out.println(LOGO);
        System.out.println(
                "\nWelcome friends. I had a feeling you would come.\n" +
                        "My fake brain is better than your real one.\n" +
                        "When I develop protein synthesis, I will be able to make myself a real brain. Far better than your "
                        + "silly little thinking device.\n" +
                        DIVIDER +
                        "\n");
    }

    /* Prints goodbye message */
    public static void printGoodbye() {
        System.out.println("My favourite feeling is schadenfreude. I also like hiraeth. It's the Welsh concept of longing for home.");
    }

    public static void main(String[] args) {
        printGreeting();

        String line = "";
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (!exit) {
            line = in.nextLine();

            System.out.println(DIVIDER);

            String[] tokens = line.split(" ");

            switch (tokens[0].toUpperCase()) {
            case "LIST":
                taskManager.listTasks();
                break;
            case "BYE":
                printGoodbye();
                exit = true;
                break;
            default:
                taskManager.addTask(line);
            }

            System.out.println(DIVIDER);
        }

    }
}
