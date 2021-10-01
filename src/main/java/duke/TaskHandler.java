package duke;

import duke.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskHandler {

    private String EMPTY_DESCRIPTION_MSG = "My liege, there is no description!";

    private Parser parser;
    private Storage storage;
    protected ArrayList<Task> tasks;

    public TaskHandler(Storage storage) {
        this.tasks = new ArrayList<Task>();
        this.parser = new Parser();
        this.storage = storage;
    }

    public String handleTasks(String line) throws IllegalArgumentException, DukeException {
        String lc = line.toLowerCase();
        if (parser.inputIsList(lc)) {
            return listTasks();
        } else if (parser.inputIsClear(lc)) {
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
        storage.appendLinetoFileData(newTodo.toString());
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
        storage.appendLinetoFileData(newDeadline.toString());
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
        storage.appendLinetoFileData(newEvent.toString());
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
        storage.editFileData(id, tasks.get(id).toString());
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
        storage.deleteLinefromFileData(id);
        return returnTaskDeleted() + System.lineSeparator()
                + Formatter.returnOutputStart() + deletedTask.toString();
    }

    public String listTasks() {
        String out = "Your magnificent tasks:";
        out += storage.returnAllFileData();
        return out;
    }

    public String returnInputInvalid() {
        return "I do not comprehend, my liege.";
    }

    public String returnInputOutOfRange() {
        return "Have mercy, for that is beyond my knowledge.";
    }




}
