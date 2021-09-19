package duke.task;

import duke.exceptions.EmptyField;
import duke.exceptions.IllegalOperation;
import duke.list.TaskList;
import duke.ui.MessageBubble;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public boolean isExit;

    public Parser() {
        isExit = false;
    }

    /**
     * Parse the given raw command and execute the corresponding methods to the specified TaskList
     *
     * @param fullCommand raw command
     * @param targetTaskList TaskList to be manipulated
     */
    public void parse(String fullCommand, TaskList targetTaskList) {
        if (fullCommand.startsWith("event")) {
            try {
                targetTaskList.addItem(Parser.parseEvent(extractPartialCommand(fullCommand)));
            } catch (EmptyField e) {
                MessageBubble.printMessageBubble("Oops! Use \"event (name) /at (start time) /to (end time)\" to create event.");
            } catch (IllegalOperation illegalOperation) {
                MessageBubble.printMessageBubble("Oops! Too many items already, I cannot record any more.");
            }
        } else if (fullCommand.startsWith("deadline")) {
            try {
                targetTaskList.addItem(Parser.parseDeadline(extractPartialCommand(fullCommand)));
            } catch (EmptyField e) {
                MessageBubble.printMessageBubble("Oops! Use \"deadline (name) /by (time)\" to create deadline.");
            } catch (IllegalOperation illegalOperation) {
                MessageBubble.printMessageBubble("Oops! Too many items already, I cannot record any more.");
            }
        } else if (fullCommand.startsWith("todo")) {
            try {
                targetTaskList.addItem(Parser.parseTodo(extractPartialCommand(fullCommand)));
            } catch (EmptyField e) {
                MessageBubble.printMessageBubble("Oops! Use \"todo (name)\" to create todo.");
            } catch (IllegalOperation illegalOperation) {
                MessageBubble.printMessageBubble("Oops! Too many items already, I cannot record any more.");
            }
        } else if (fullCommand.startsWith("done")) {
            try {
                targetTaskList.doneItem(Integer.parseInt(extractPartialCommand(fullCommand)));
            } catch (EmptyField e) {
                MessageBubble.printMessageBubble("Oops! No index found.");
            } catch (NumberFormatException e) {
                MessageBubble.printMessageBubble("Oops! Use \"done (integer index of item)\" to mark item as done.");
            }
        } else if (fullCommand.startsWith("undone")) {
            try {
                targetTaskList.undoneItem(Integer.parseInt(extractPartialCommand(fullCommand)));
            } catch (EmptyField e) {
                MessageBubble.printMessageBubble("Oops! No index found.");
            } catch (NumberFormatException e) {
                MessageBubble.printMessageBubble("Oops! Use \"undone (integer index of item)\" to mark item as not done.");
            }
        } else if (fullCommand.startsWith("delete")) {
            try {
                targetTaskList.removeItem(Integer.parseInt(extractPartialCommand(fullCommand)));
            } catch (EmptyField e) {
                MessageBubble.printMessageBubble("Oops! No index found.");
            } catch (NumberFormatException e) {
                MessageBubble.printMessageBubble("Oops! Wrong index format.");
            }
        } else if (fullCommand.startsWith("find")) {
            try {
                targetTaskList.searchItem(extractPartialCommand(fullCommand));
            } catch (EmptyField e) {
                MessageBubble.printMessageBubble("Oops! Use \"find (keywords)\" to search in your task list.");
            }
        } else if (fullCommand.equals("bye")) {
            isExit = true;
        } else if (fullCommand.equals("list")) {
            targetTaskList.printList();
        } else {
            MessageBubble.printMessageBubble("Oops! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the raw information (including the description and latest time to complete) of the Deadline to a Deadline object
     *
     * @param full_description raw information
     * @return parsed Deadline object
     * @throws EmptyField if any necessary information is missing
     */
    public static Deadline parseDeadline(String full_description) throws EmptyField {
        String CMD_TIME = " /by ";

        if (!full_description.contains(CMD_TIME)) {
            throw new EmptyField();
        }

        try {
            String description = full_description.split(CMD_TIME)[0];
            String time = full_description.split(CMD_TIME)[1];

            if (description.isBlank() || time.isBlank()) {
                throw new EmptyField();
            }

            return new Deadline(description, time);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyField();
        }
    }

    /**
     * Parses the raw information (including the description and start time) of the Event to a Event object
     *
     * @param full_description raw information
     * @return parsed Event object
     * @throws EmptyField if any necessary information is missing
     */
    public static Event parseEvent(String full_description) throws EmptyField {
        String CMD_TIME = " /at ";
        String CMD_ENDTIME = " /to ";

        if (!full_description.contains(CMD_TIME)) {
            throw new EmptyField();
        }

        try {
            String description = full_description.split(CMD_TIME)[0];
            String rawTime = full_description.split(CMD_TIME)[1];
            String startTime = rawTime.split(CMD_ENDTIME)[0];
            String endTime = rawTime.split(CMD_ENDTIME)[1];

            if (description.isBlank() || startTime.isBlank() || endTime.isBlank()) {
                throw new EmptyField();
            }

            return new Event(description, startTime, endTime);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyField();
        }
    }

    /**
     * Parses the raw description of the Todo to a Todo object
     *
     * @param full_description description of Todo
     * @return parsed Todo object
     * @throws EmptyField if any necessary information is missing
     */
    public static Todo parseTodo(String full_description) throws EmptyField {
        if (full_description.isBlank()) {
            throw new EmptyField();
        }

        return new Todo(full_description);
    }

    /**
     * Returns the command without the first word
     *
     * @param fullCommand raw command
     * @return command without the first word
     * @throws EmptyField if any necessary information is missing
     */
    private static String extractPartialCommand(String fullCommand) throws EmptyField {
        if (!fullCommand.contains(" ")) {
            throw new EmptyField();
        }
        return fullCommand.substring(fullCommand.indexOf(" ") + 1);
    }
}
