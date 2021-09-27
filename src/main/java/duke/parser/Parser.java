package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.HalUi;
import duke.util.HalException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static int DEADLINE_INDEX = 9;
    private static int EVENT_INDEX = 5;
    private static int TODO_INDEX = 4;
    private static int FIND_INDEX = 4;
    private static int TASK_STRING_OFFSET = 3;

    String description;
    String timing;

    static HalUi ui = new HalUi();

    public List<String> parseTextInput(Task task, String input) throws HalException {
        if (task instanceof Deadline && !input.contains("/by")) {
            ui.printErrorMessage();
            throw new HalException("Wrong Deadline task format");
        } else if (task instanceof Event && !input.contains("/at")) {
            ui.printErrorMessage();
            throw new HalException("Wrong Event task format");
        }


        try {
            if (task instanceof ToDo) {
                description = input.substring(TODO_INDEX).trim();
            } else if (task instanceof Event) {
                description = input.substring(EVENT_INDEX, input.indexOf('/')).trim();
                timing = input.substring(input.indexOf("/at") + TASK_STRING_OFFSET).trim();
            } else if (task instanceof Deadline) {
                description = input.substring(DEADLINE_INDEX, input.indexOf('/')).trim();
                timing = input.substring(input.indexOf("/by") + TASK_STRING_OFFSET).trim();
            }
        } catch (StringIndexOutOfBoundsException e) {
            ui.printErrorMessage();
            throw new HalException("Wrong task format");
        }

        //Error handling
        if (description.equals("")) {
            ui.printEmptyDescriptionMessage();
            throw new HalException("Empty description");
        } else if ((task instanceof Deadline || task instanceof Event) && timing.equals("")) {
            ui.printEmptyDateMessage();
            throw new HalException("Empty date");
        }

        List<String> returnVal = new ArrayList<>();
        returnVal.add(description);
        returnVal.add(timing);
        return returnVal;
    }

    public String parseTextInput(String str) throws HalException {
        description = str.substring(FIND_INDEX).trim();
        if (description.equals("")) {
            ui.printEmptyDescriptionMessage();
            throw new HalException("Empty search term");
        }
        return description;
    }

    public int parseInt(String task) {
        try {
            int taskNum = Integer.parseInt(task.substring(task.indexOf(' ') + 1));
            return taskNum;
        } catch (NumberFormatException error) {
            ui.printInvalidNumberMessage();
            ui.printEnterCommandMessage();
        }
        return -1;
    }


    //function takes in a string and converts to a local date. Defaults to current date if invalid format added
    public static LocalDate parseLocalDate(String str) {
        try {
            LocalDate date = LocalDate.parse(str);
            return date;
        } catch (DateTimeParseException e) {
            ui.printInvalidDateMessage();
        }

        return LocalDate.now();
    }

    public static String formatLocalDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("EEEE, MMM d yyyy"));
    }

}
