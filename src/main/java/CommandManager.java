import java.util.Locale;

public class CommandManager {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DONE_COMMAND = "done";

    public static Command processCommand(String userInput) throws DukeException {
        if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
            return new ExitCommand();
        } else if (userInput.equalsIgnoreCase(LIST_COMMAND)) {
            return new ListCommand();
        } else if ((userInput.toLowerCase()).contains(DONE_COMMAND)) {
            String[] extractedCommand = userInput.split(" ");
            int taskIndex = -1;
            try {
                taskIndex = Integer.parseInt(extractedCommand[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid number!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Please specify index of the task!");
            }
            return new DoneCommand(taskIndex - 1);
        } else if ((userInput.toLowerCase()).contains(TODO_COMMAND)) {
            //4 is index after todo in input string
            String toDoDescription = userInput.substring(4);
            if (toDoDescription.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddTaskCommand(new ToDos(toDoDescription));
        } else if ((userInput.toLowerCase()).contains(EVENT_COMMAND)) {
            try {
                int endOfDescriptionIndex = userInput.indexOf("/");
                //5 is index after "event" in input string
                String eventDescription = userInput.substring(5, endOfDescriptionIndex);
                //3 is no of chars after "at"
                String at = userInput.substring(endOfDescriptionIndex + 3);
                if (eventDescription.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of an event task cannot be empty.");
                } else if (at.equals("")) {
                    throw new DukeException("☹ OOPS!!! Please indicate when the event happens after /at");
                }
                return new AddTaskCommand(new Events(eventDescription, at));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Please key in command in format: \"event taskDescription /at eventLocation\".");
            }
        } else if ((userInput.toLowerCase()).contains(DEADLINE_COMMAND)) {
            try {
                int endOfDescriptionIndex = userInput.indexOf("/");
                //8 is index after "deadline" in input string
                String deadlineDescription = userInput.substring(8, endOfDescriptionIndex);
                //3 is no of chars after 'by'
                String by = userInput.substring(endOfDescriptionIndex + 3);
                if (deadlineDescription.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline task cannot be empty.");
                } else if (by.equals("")) {
                    throw new DukeException("☹ OOPS!!! Please indicate the deadline after /by");
                }
                return new AddTaskCommand(new Deadline(deadlineDescription, by));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Please key in command in format: \"deadline taskDescription /by deadline\".");
            }
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
