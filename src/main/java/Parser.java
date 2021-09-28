import java.util.Scanner;

public class Parser {
    /** User inputs parsed, formatted properly and stored to cmd in class methods. */
    protected static String cmd;

    /**
     * Parses user input for mode selection.
     *
     * @return int value 1 (ECHO MODE) or 2 (TASK MODE).
     * @throws DukeException If input string is not '1' or '2'.
     */
    public static int readModeSelection() throws DukeException {
        Scanner inp = new Scanner(System.in);
        System.out.print("Mode> ");
        cmd = inp.nextLine();
        if (cmd.equals("1") | cmd.equals("2")) {
            return Integer.parseInt(cmd);
        }
        throw new DukeException("\tERROR. PLEASE RUN AGAIN AND SELECT RIGHT MODE.");
    }

    /**
     * Parses user input to echo.
     *
     * @return User input string if valid (not empty and not blank).
     * @throws DukeException If input string is empty or blank.
     */
    public static String parseEchoCommand() throws DukeException {
        Scanner inp = new Scanner(System.in);
        System.out.print("Listening> ");
        cmd = inp.nextLine();
        if (cmd.equals("") | cmd.equals(" ")) {
            throw new DukeException("\tPlease enter a valid command!");
        }
        return cmd;
    }

    /**
     * Parses input command string to determine appropriate action in Task mode.
     *
     * @return Command object representing the different functions possible in Task mode.
     */
    public static Command parseTaskCommand() {
        Scanner inp = new Scanner(System.in);
        System.out.print("TellMe> ");
        cmd = inp.nextLine();

        if (cmd.startsWith("done ")) {
            return new DoneCommand(cmd);
        } else if (cmd.startsWith("delete ") | cmd.startsWith("remove ")) {
            return new DeleteCommand(cmd);
        } else if (cmd.startsWith("list")) {
            return new ListCommand();
        } else if (cmd.equalsIgnoreCase("switch")) {
            return new SwitchCommand();
        } else if (cmd.equalsIgnoreCase("bye") | cmd.equalsIgnoreCase("exit")){
            return new ExitCommand();
        } else {
            return new AddCommand(cmd);
        }
    }

    /**
     * Parses input string to confirm if user really wants to exit program.
     *
     * @return Input string of user.
     */
    public static String parseExitPref() {
        System.out.print("\tDo you really want to exit chatbot (type y or n)? ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Parses lines in the local file where items in list are saved, and
     * constructs a user-style CLI command to be input into createTask()
     * method to create the task in current session.
     *
     * @param lineFragments A line of the saved local file.
     * @return toReturn is the CLI command that a user will input to create Task.
     */
    public static String createCommand(String[] lineFragments) {
        if (lineFragments.length == 3) {
            cmd = "todo " + lineFragments[2];
        } else if (lineFragments[0].equals("D")) {
            cmd = "deadline " + lineFragments[2] + " /by " + lineFragments[3];
        } else if (lineFragments[0].equals("E")) {
            cmd = "event " + lineFragments[2] + " /at " + lineFragments[3];
        }
        return cmd;
    }
}
