import java.util.Scanner;

public class Duke {

    private static int byeFlag = 0;
    private static int positionCheck = 0;
    private static Task[] commands = new Task[100];

    // sendCommands() is a method used to allow the user to send his/her commands to C3PO
    private static void sendCommands() {
        String line;
        Scanner in = new Scanner(System.in);
        while (byeFlag != 1) {
            System.out.println("____________________________________________________________\n");
            System.out.print("Type something: ");
            line = in.nextLine();
            System.out.println("____________________________________________________________\n");
            checkCommands(line);
        }
    }

    // checkCommands() is a method that allows us to determine when the user says bye.
    private static void checkCommands(String line) {
        if (positionCheck >= 100) {
            System.out.println("Oh dear me! We have exceeded my system's maximum capacity!");
        } else if (line.equals("bye")) {
            byeFlag = 1;
        } else if (line.equals("list")) {
            if (positionCheck == 0) {
                System.out.println("There is no data to show master!");
            } else {
                System.out.println("Accessing archives...");
                for (int i = 0; i < positionCheck; i++) {
                    System.out.println((i + 1) + ".[" + commands[i].getStatusIcon() + "] " + commands[i].description);
                }
            }
        } else if (line.equals("done")) {
            System.out.println("Oh no master, I am not quite sure which task you would like me to mark as done");
        } else if (line.contains("done")) {
            String[] input = line.split(" ");
            int doneTaskNumber = (Integer.parseInt(input[1]) - 1);
            commands[doneTaskNumber].markAsDone();
            System.out.println("The following task has been marked as done Master!");
            System.out.println((doneTaskNumber + 1) + "." + "[X] " + commands[doneTaskNumber].description);
        } else {
            commands[positionCheck] = new Task(line);
            positionCheck += 1;
            System.out.println("Added to Galactic database: " + line);
        }
    }

    public static void sayBye() {
        System.out.println("Goodbye master! May the force be with you!\n");
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) {
        String logo = "       /~\\\n"
                + "      |oo )\n"
                + "      _\\=/_\n"
                + "     /     \\\n"
                + "    //|/.\\|\\\\\n"
                + "   ||  \\_/  ||\n"
                + "   || |\\ /| ||\n"
                + "    # \\_ _/  #\n"
                + "      | | |\n"
                + "      | | |\n"
                + "      []|[]\n"
                + "      | | |\n"
                + "     /_]_[_\\\n";
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello! I am C3P0! Human-cyborg relations! \n" + logo);
        System.out.println("What can I do for you my master?\n");
        sendCommands();
        sayBye();
    }
}
