public class Parser {
    public static Command processCommand(String command) {
        boolean isList = command.equals("list");
        if(isList) return Command.LIST;

        boolean isBye = command.equals("bye");
        if(isBye) return Command.BYE;

        boolean isDone = command.equals("done");
        if(isDone) return Command.DONE;

        boolean isTodo = command.equals("todo");
        if(isTodo) return Command.TODO;

        boolean isDeadline = command.equals("deadline");
        if(isDeadline) return Command.DEADLINE;

        boolean isEvent = command.equals("event");
        if(isEvent) return Command.EVENT;

        return Command.NULL;
    }

    public static String processTodo(String userInput) {
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
