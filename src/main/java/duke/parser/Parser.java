package duke.parser;

import duke.DukeException;

public class Parser {
    public static Command processCommand(String command) {
        switch (command) {
        case "list":
            return Command.LIST;
        case "bye":
            return Command.BYE;
        case "done":
            return Command.DONE;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "delete":
            return Command.DELETE;
        default:
            return Command.NULL;
        }
    }

    /**
     * @param userInput
     * @return
     * description
     */
    public static String processToDo(String userInput) {
        String description = null;
        try {
            String[] words = userInput.split(" ", 2);
            if (words.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            description = words[1];
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return description;
    }

    /**
     * @param userInput
     * @return
     * description = information[0]
     * <p>
     * dateline by = information[1]
     */
    public static String[] processDeadline(String userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            String[] words = userInput.split(" ", 2);
            if (words.length == 1) {
                throw new DukeException("The description of a deadline task cannot be empty.");
            }

            int byIndex = words[1].indexOf("/by");
            if (byIndex == -1) {
                throw new DukeException("The deadline time of the deadline task cannot be empty.");
            }

            information[0] = words[1].substring(0, byIndex).trim();
            information[1] = words[1].substring(byIndex + 3).trim();
            if (information[0].equals("") || information[1].equals("")) {
                throw new DukeException("The description or dateline time cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return information;
    }

    /**
     * @param userInput
     * @return
     * description = information[0]
     * <p>
     * event time = information[1]
     */
    public static String[] processEvent(String userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            String[] words = userInput.split(" ", 2);
            if (words.length == 1) {
                throw new DukeException("The description of a event task cannot be empty.");
            }

            int atIndex = words[1].indexOf("/at");
            if (atIndex == -1) {
                throw new DukeException("The event time of the event task cannot be empty.");
            }

            information[0] = words[1].substring(0, atIndex).trim();
            information[1] = words[1].substring(atIndex + 3).trim();
            if (information[0].equals("") || information[1].equals("")) {
                throw new DukeException("The description or dateline time cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return information;
    }
}
