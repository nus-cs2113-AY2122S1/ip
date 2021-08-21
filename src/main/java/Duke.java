import java.util.Scanner;

public class Duke {
    public static void greeting() {
        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Duke the Dancing Dragon.");
        System.out.println("Anything I can help you with, young Padawan?");
        System.out.println("_____________________________________________________");
    }

    public static void goodbyeMessage() {
        System.out.println("It's over Anakin... I can finally eat my lun-");
        System.out.println("_____________________________________________________");
    }

    public static void echoMessage(String arg) {
        if (!arg.equals("bye")) {
            System.out.println("_____________________________________________________");
            System.out.println(arg);
            System.out.println("_____________________________________________________");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String face = "⣿⣿⡇⠄⣼⣿⣿⠿⣿⣿⣿⣦⠘⣿⣿⣿⣿⣿⠏⣰⣿⡿⠟⢻⣿⣿⣷⡀⠸⣿\n"
                    + "⣿⣿⡇⠰⣿⣿⠁⠄⠄⠄⣿⣿⠆⢹⣿⣿⣿⣿⠄⣿⣿⠁⠄⠄⠄⣿⣿⡇⠄⣿\n"
                    + "⣿⣿⡇⠄⢿⣿⣷⣤⣤⣼⣿⡟⢀⣿⣿⣿⣿⣿⡄⠻⣿⣷⣤⣤⣾⣿⡿⠁⠄⣿\n"
                    + "⣿⣿⠃⢸⣦⡙⠛⠿⠟⠛⠉⣠⣾⣿⣿⣿⣿⣿⣿⣆⡈⠛⠻⠿⠛⢋⣴⡇⢸⣿\n"
                    + "⣿⣿⡀⠈⢿⣿⣷⣶⣶⣶⣿⣿⣿⣿⠛⣿⡋⣿⣿⣿⣿⣷⣶⣶⣾⣿⡿⠄⢸⣿\n"
                    + "⣿⣿⡇⠄⠈⢿⣿⣯⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⣽⣿⡟⠄⠄⣮⣿\n"
                    + "⣿⣿⣷⠄⠄⠄⠹⣿⣷⣌⠙⢿⣿⣿⣿⣿⣿⣿⣿⡿⠟⢁⣾⣿⠋⠄⠄⠄⢹⣿\n"
                    + "⣿⣿⣏⠄⠄⠄⠄⠘⢿⣿⣦⡀⠈⠛⢿⣿⡿⠟⠉⢀⣴⣿⠟⠁⠄⠄⠄⢠⢸⣿\n"
                    + "⣿⣿⣿⠄⠄⠄⠄⠄⠄⠙⢿⣿⣦⡀⠄⠄⠄⢀⣴⣿⠟⠃⠄⠄⠄⠄⠄⠄⣸⣿\n"
                    + "⣿⣿⣿⡄⠄⠄⠄⠄⠄⠄⢠⠉⠻⢿⣷⣶⣾⡿⠛⠁⡀⠄⠄⠄⠄⠄⠄⠄⣿⣿\n";
        System.out.println(face);

        // print greeting message after logo
        greeting();

        String query = "";
        Scanner userInput = new Scanner(System.in);

        while (!query.equals("bye")) {
            System.out.print("=>");
            if (userInput.hasNextLine()) {
                query = userInput.nextLine();
            }
            // prints out the same message that was given by user
            echoMessage(query);
        }
        // print end message
        goodbyeMessage();
    }
}
