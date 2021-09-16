package duke;

import java.util.ArrayList;

public class TaskHandler {

    private String EMPTY_DESCRIPTION_MSG = "My liege, there is no description!";

    protected ArrayList<Task> tasks;

    public TaskHandler() {
        this.tasks = new ArrayList<Task>();
    }

    public String handleTasks(String line) throws IllegalArgumentException, DukeException {
            String lc = line.toLowerCase();
            if (inputIsList(lc)) {
                return listTasks();
            } else if (inputIsDone(lc)) {
                return doTask(lc);
            } else if (inputIsDelete(lc)) {
                return deleteTask(lc);
            } else if (inputIsTodo(lc)) {
                return addTodo(line);
            } else if (inputIsDeadline(lc)) {
                if (!deadlineContainsBy(lc)) {
                    return returnDeadlineNoBy();
                } else {
                    return addDeadline(line);
                }
            } else if (inputIsEvent(lc)) {
                if (!eventContainsAt(lc)) {
                    return returnEventNoAt();
                } else {
                    return addEvent(line);
                }
            } else {
                throw new DukeException(returnInputInvalid());
            }
    }

    public boolean inputIsTodo(String lc) {
        //lc: lowercase line
        return lc.startsWith("todo");
    }

    public boolean inputIsDelete(String lc) {
        return lc.startsWith("delete");
    }

    public boolean inputIsDeadline(String lc) {
        return lc.startsWith("deadline");
    }

    public boolean deadlineContainsBy(String lc) {
        return lc.contains("/by");
    }

    public String returnDeadlineNoBy() {
        //TODO: make random phrases
        return "By when, my liege?";
    }

    public boolean inputIsEvent(String lc) {
        return lc.startsWith("event");
    }

    public boolean eventContainsAt(String lc) {
        return lc.contains("/at");
    }

    public String returnEventNoAt() {
        return "Where or when is this event, my liege?";
    }

    public boolean inputIsDone(String lc) {
        return lc.startsWith("done");
    }

    public boolean inputIsBye(String lc) {
        return lc.equals("bye");
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
        return returnTaskDeleted() + System.lineSeparator()
                + Formatter.returnOutputStart() + deletedTask.toString();
    }

    public boolean inputIsList(String lc) {
        return lc.equals("list");
    }

    public String listTasks() {
        String out = "Your magnificent tasks:";
        for (int i = 0; i < tasks.size() && tasks.get(i) != null; i++) {
            //output will be doubly "indented"
            out += System.lineSeparator() + Formatter.returnOutputStart() + (i + 1)
                    + "." + tasks.get(i).toString();
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

