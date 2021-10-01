package duke;

import java.util.ArrayList;

public class TaskHandler {
    /* Error types */
    // enum?
    private String COMMAND_UNKNOWN;
    private String COMMAND_MISSING_DESCRIPTION;
    private String COMMAND_MISSING_BY;
    private String COMMAND_MISSING_AT;

    /* Error messages */
    private String COMMAND_UNKNOWN_ERROR_MESSAGE;
    private String COMMAND_MISSING_DESCRIPTION_ERROR_MESSAGE = "My liege, there is no description!";
    private String COMMAND_MISSING_BY_ERROR_MESSAGE = "By when, my liege?";
    private String COMMAND_MISSING_AT_ERROR_MESSAGE = "Where at, my liege?";

    private String EMPTY_DESCRIPTION_MSG = "My liege, there is no description!";

    private Parser parser;
    private Storage storage;
    protected ArrayList<Task> tasks;

    public TaskHandler(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
        this.parser = new Parser();
    }


    public String handleTasks(String line) throws IllegalArgumentException, DukeException {
        //what I want is a switch-case thing here
        Command command = parser.returnCommand(line);
        String result = "";

        switch (command) {
        case LIST:
            result = listTasks();
            break;
        case CLEAR:
            result = clearTasks();
            break;
        case ADD_TODO:
            result = addTodo(line);
            break;
        case ADD_DEADLINE:
            result = addDeadline(line);
            break;
        case ADD_EVENT:
            result = addEvent(line);
            break;
        case DO_TASK:
            result = doTask(line);
            break;
        case DELETE:
            result = deleteTask(line);
            break;
        default:
            result = "Invalid command";
        }


        return result;
/*
        if (parser.inputIsList(lc)) {
            return listTasks();
        } else if (parser.inputIsClear(lc)) {
            this.tasks.clear();
            return storage.clearFileData();
        } else if (parser.inputIsDone(lc)) {
            return doTask(lc);
        } else if (parser.inputIsDelete(lc)) {
            return deleteTask(lc);
        } else if (parser.inputIsTodo(lc)) {
            return addTodo(line);
        } else if (parser.inputIsDeadline(lc)) {
            if (!parser.deadlineContainsBy(lc)) {
                return returnDeadlineNoBy();
            } else {
                return addDeadline(line);
            }
        } else if (parser.inputIsEvent(lc)) {
            if (!parser.eventContainsAt(lc)) {
                return returnEventNoAt();
            } else {
                return addEvent(line);
            }
        } else if (parser.inputIsBye(lc)) {
            return "";
        } else {
                return returnInputInvalid();
        }
        */
    }

    /*
    public String returnErrorMessage
     */

    /*
    public String returnErrorMessage(String errorType) {
        String message = "";
        switch (type) {
        case (COMMAND_UNKNOWN):
            message = COMMAND_UNKNOWN_ERROR_MESSAGE;
            break;
        case (COMMAND_MISSING_DESCRIPTION):
            message = COMMAND_MISSING_DESCRIPTION_ERROR_MESSAGE;
            break;
        case (COMMAND_MISSING_AT):
            message = COMMAND_MISSING_AT_ERROR_MESSAGE;
            break;
        case (COMMAND_MISSING_BY):
            message = COMMAND_MISSING_BY_ERROR_MESSAGE;
            break;
        }
        return message;
    }
     */
    private String clearTasks() {
        this.tasks.clear();
        return storage.clearFileData();
    }

    public String returnDeadlineNoBy() {
        //TODO: make random phrases
        return "By when, my liege?";
    }

    public String returnEventNoAt() {
        return "Where or when is this event, my liege?";
    }

    public String returnAddTaskSuccess() {
        return "As you command. Added: ";
    }


    public String addTodo(String line) throws IllegalArgumentException {
        if (line.length() <= 5) {
            throw new IllegalArgumentException(EMPTY_DESCRIPTION_MSG);
        }
        String description = line.substring(5).trim();
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        storage.addTaskToFileData(newTodo.taskString());
        return returnAddTaskSuccess() + newTodo.toString();
    }

    public String addDeadline(String line) throws IllegalArgumentException {
        String lc = line.toLowerCase();
        int dividerPosition = lc.indexOf("/by");
        String description = line.substring(9, dividerPosition).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_DESCRIPTION_MSG);
        }
        String by = line.substring(dividerPosition + 3).trim();
        if (by.isEmpty()) {
            throw new IllegalArgumentException(returnDeadlineNoBy());
        }
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        storage.addTaskToFileData(newDeadline.taskString());
        return returnAddTaskSuccess() + newDeadline.toString();
    }

    public String addEvent(String line) throws IllegalArgumentException {
        String lc = line.toLowerCase();
        int dividerPosition = lc.indexOf("/at");
        String description = line.substring(6, dividerPosition).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_DESCRIPTION_MSG);
        }
        String at = line.substring(dividerPosition + 3).trim();
        if (at.isEmpty()) {
            throw new IllegalArgumentException(returnEventNoAt());
        }
        Event newEvent = new Event(description, at);
        tasks.add(newEvent);
        storage.addTaskToFileData(newEvent.taskString());
        return returnAddTaskSuccess() + newEvent.toString();
    }

    public String returnTaskCompleted() {
        return "Transcendent, my liege. You have completed: ";
    }

    public String returnDoTaskFail() {
        return "Have mercy, for that is beyond my knowledge.";
    }

    public String returnDoTaskNone() {
        return "You have no tasks, my liege.";
    }

    public String returnTaskDeleted() {
        return "I have removed this unworthy task from your magnificent sight, my liege: ";
    }

    public String doTask(String lc) {
        if (tasks.size() <= 0) {
            return returnDoTaskNone();
        }
        String inputNumStr = lc.replace("done", "").trim();
        int inputNum = Integer.parseInt(inputNumStr);
        if (!(inputNum > 0 && inputNum <= tasks.size())) {
            throw new IllegalArgumentException(returnDoTaskFail());
        }
        int id = inputNum - 1;
        tasks.get(id).setDone();
        // TODO setDone function in storage
        String newFileDataLine = storage.getLine(id);
        char[] newFileDataLineChars = newFileDataLine.toCharArray();
        newFileDataLineChars[3] = 'X';
        storage.replaceFileData(id, String.valueOf(newFileDataLineChars));
        return returnTaskCompleted() + System.lineSeparator()
                + Formatter.returnOutputStart() + tasks.get(id).toString();
    }

    public String deleteTask(String lc) {
        if (tasks.size() <= 0) {
            return returnDoTaskNone();
        }
        String inputNumStr = lc.replace("delete", "").trim();
        int inputNum = Integer.parseInt(inputNumStr);
        if (!(inputNum > 0 && inputNum <= tasks.size())) {
            throw new IllegalArgumentException(returnInputOutOfRange());
        }
        int id = inputNum - 1;
        Task deletedTask = tasks.remove(id);
        storage.deleteLineFromFileData(id);
        return returnTaskDeleted() + System.lineSeparator()
                + Formatter.returnOutputStart() + deletedTask.toString();
    }

    public String listTasks() {
        String out = "Your magnificent tasks:";
        for (int i = 1; i <= tasks.size(); i++) {
            out += System.lineSeparator() + i + "." + tasks.get(i - 1).toString();
        }
        return out;
    }

    public String returnInputInvalid() {
        return "I do not comprehend, my liege.";
    }

    public String returnInputOutOfRange() {
        return "Have mercy, for that is beyond my knowledge.";
    }

}
