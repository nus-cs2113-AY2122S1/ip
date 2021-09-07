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
        default:
            return Command.NULL;
        }
    }

    public static String processToDo(String userInput) {
        String description = null;
        try {
            String[] words = userInput.split(" ", 2);
            description = words[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Description of todo task cannot be empty!");
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
            int byIndex = words[1].indexOf("/by");
            information[0] = words[1].substring(0, byIndex).trim();
            information[1] = words[1].substring(byIndex + 3).trim();
            if (information[0].equals("") || information[1].equals("")) {
                System.out.println("Please enter description / deadline by");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Description and dateline by of deadline task cannot be empty!");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please enter description / deadline by");
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
            int byIndex = words[1].indexOf("/at");
            information[0] = words[1].substring(0, byIndex).trim();
            information[1] = words[1].substring(byIndex + 3).trim();
            if (information[0].equals("") || information[1].equals("")) {
                System.out.println("Please enter description / event time");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Description and event time by of event task cannot be empty!");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please enter description / event time");
        }
        return information;
    }
}
