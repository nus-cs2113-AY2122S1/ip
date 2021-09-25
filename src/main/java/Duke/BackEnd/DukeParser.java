package Duke.BackEnd;

import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;

import static Duke.UI.DukeConstants.DEADLINE_KEYWORD;
import static Duke.UI.DukeConstants.EVENT_KEYWORD;

public class DukeParser {
    public static String getCommandType(String inWord) {
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0].trim().toLowerCase();
        return instructionType;
    }

    public static String[] parseDoneInstruction (String inWord) {
        String[] commands = inWord.split(" ");
        return commands;
    }

    public static String[] parseTodoInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    public static String[] parseEventInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    public static Event parseEventDescription (String[] commands) {
        String[] details = commands[1].split(EVENT_KEYWORD, 2);
        String description = details[0].trim();
        String at = details[1].trim();
        return new Event(description, at);
    }

    public static String[] parseDeadlineInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    public static Deadline parseDeadlineDescription (String[] commands) {
        String[] details = commands[1].split(DEADLINE_KEYWORD, 2);
        String description = details[0].trim();
        String at = details[1].trim();
        return new Deadline(description, at);
    }

    public static String[] parseDeleteInstruction (String inWord) {
        String[] commands = inWord.split(" ");
        return commands;
    }
}
