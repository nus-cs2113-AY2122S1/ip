package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class TaskHandler {

    private int MAX_TASKS = 100;
    private String EMPTY_DESCRIPTION_MSG = "My liege, there is no description!";

    protected Task[] tasks;
    private Path dataPath;

    public TaskHandler() {
        this.tasks = new Task[MAX_TASKS];
        openFile();
    }

    private void openFile() {
        try {
            String home = System.getProperty("user.dir");
            Path dataDirPath = Paths.get(home, "/data/");
            Files.createDirectories(dataDirPath);
            Path dataPath = Paths.get(home, "/data/duke.txt");
            if (!Files.exists(dataPath)) {
                Files.createFile(dataPath);
            }
            this.dataPath = dataPath;
        } catch (IOException e) {
            System.err.println("Open failure: " + e.getMessage());
        }
    }

    public String handleTasks(String line) throws IllegalArgumentException, DukeException {
        String lc = line.toLowerCase();
        if (inputIsList(lc)) {
            return listTasks();
        } else if (inputIsDone(lc)) {
            return doTask(lc);
        } else if (inputIsClear(lc)) {
            return clearFileData();
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

    public boolean inputIsClear(String lc) {
        return lc.startsWith("clear");
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
        tasks[Task.getNumOfTasks() - 1] = newTodo;
        appendLinetoFileData(newTodo.toString());
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
        tasks[Task.getNumOfTasks() - 1] = newDeadline;
        appendLinetoFileData(newDeadline.toString());
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
        tasks[Task.getNumOfTasks() - 1] = newEvent;
        appendLinetoFileData(newEvent.toString());
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

    public String doTask(String lc) {
        if (Task.getNumOfTasks() > 0) {
            String inputNumStr = lc.replace("done", "").trim();
            int inputNum = Integer.parseInt(inputNumStr);
            if (inputNum > 0 && inputNum <= Task.getNumOfTasks()) {
                String result;
                int id = inputNum - 1;
                tasks[id].setDone();
                editFileData(id, tasks[id].toString());
                return returnTaskCompleted() + System.lineSeparator()
                        + Formatter.returnOutputStart() + tasks[id].toString();
            } else {
                return returnDoTaskFail();
            }
        } else {
            return returnDoTaskNone();
        }
    }

    public boolean inputIsList(String lc) {
        return lc.equals("list");
    }

    public String listTasks() {
        String out = "Your magnificent tasks:";
        try {
            List<String> lines = Files.readAllLines(dataPath);
            for (String line : lines) {
                out += System.lineSeparator() + Formatter.returnOutputStart()
                        + line;
            }
        } catch (IOException e) {
            System.err.println("Read failure: " + e.getMessage());
        }
        return out;
    }

    public String returnInputInvalid() {
        return "I do not comprehend, my liege.";
    }

    private void appendLinetoFileData(String line) {
        try {
            List<String> lines = Files.readAllLines(dataPath);
            lines.add(line);
            //Path tempFile = Files.createTempFile("tmp", ".txt");
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    private void editFileData(int index, String line) {
        try {
            List<String> lines = Files.readAllLines(dataPath);
            lines.set(index, line);
            //Path tempFile = Files.createTempFile("tmp", ".txt");
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    private String clearFileData() {
        try {
            Files.write(dataPath, Collections.EMPTY_LIST);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
        return "A clean slate, my liege!";
    }
}
