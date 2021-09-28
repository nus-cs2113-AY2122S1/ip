import java.util.Scanner;

public class Parser {
    protected static String cmd;

    public static int readModeSelection() throws DukeException {
        Scanner inp = new Scanner(System.in);
        System.out.print("Mode> ");
        cmd = inp.nextLine();
        if (cmd.equals("1") | cmd.equals("2")) {
            return Integer.parseInt(cmd);
        }
        throw new DukeException("\tERROR. PLEASE RUN AGAIN AND SELECT RIGHT MODE.");
    }

    public static String parseEchoCommand() throws DukeException {
        Scanner inp = new Scanner(System.in);
        System.out.print("Listening> ");
        cmd = inp.nextLine();
        if (cmd.equals("") | cmd.equals(" ")) {
            throw new DukeException("\tPlease enter a valid command!");
        }
        return cmd;
    }

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
        } else if (cmd.startsWith("find ")) {
            return new FindCommand(cmd);
        } else {
            return new AddCommand(cmd);
        }
    }

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
        String toReturn = null;
        if (lineFragments.length == 3) {
            toReturn = "todo " + lineFragments[2];
        } else if (lineFragments[0].equals("D")) {
            toReturn = "deadline " + lineFragments[2] + " /by " + lineFragments[3];
        } else if (lineFragments[0].equals("E")) {
            toReturn = "event " + lineFragments[2] + " /at " + lineFragments[3];
        }
        return toReturn;
    }
}
