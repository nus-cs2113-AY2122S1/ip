import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greetUser();
        executeResponse();
    }

    /**
     * Prints a message that greets the user.
     */
    public static void greetUser() {
        Picture.drawRimuruLogo();
        System.out.println("     Welcome to Jura Tempest!\n"
                + "     I'm Rimuru Tempest, pleased to make your acquaintance.\n"
                + "     How can I help you today?");
        Picture.printLine();
    }

    /**
     * Prints an exit message.
     */
    public static void exitDuke() {
        Picture.printLine();
        System.out.println("     Sayonara. Come visit our country again soon!");
        Picture.printLine();
    }

    /**
     * Continually waits for input user commands,
     * and executes the corresponding actions,
     * until the exit command is typed.
     */
    public static void executeResponse() {
        String line;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        do {
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case "bye":
                exitDuke();
                isExit = true;
                break;
            case "list":
                TaskController.printList();
                break;
            case "done":
                TaskController.markAsCompleted(Integer.parseInt(words[1]));
                break;
            default:
                TaskController.addToList(line);
            }
        } while (!isExit);
    }

}
