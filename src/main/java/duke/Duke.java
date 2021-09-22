package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static ArrayList<Task> userInputs = new ArrayList<>();
    private static int userInputsCount = 0;

    private Ui ui;

    public Duke() {
        this.ui = new Ui();
    }

    public void initiateDuke() {
        ui.printWelcome();

        try {
            loadSave();
        } catch (FileNotFoundException e) {
            ui.printFileNotFound(e.toString());
        }

        promptInput();
    }

    public void loadSave() throws FileNotFoundException {
        final String LOAD_DELIMITER = "--";
        final int TASK_INDEX = 0;
        final int DONE_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int BY_AT_INDEX = 3;
        final String TODO_CODE = "T";
        final String DEADLINE_CODE = "D";
        final String EVENT_CODE = "E";
        String line;
        File dir = new File("data");

        if (!dir.exists()) {
            dir.mkdir();
            throw new FileNotFoundException();
        }

        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            line = s.nextLine();
            String[] lineData = line.split(LOAD_DELIMITER);

            if (lineData[TASK_INDEX].equals(TODO_CODE)) {
                userInputs.add(new Todo(lineData[DESCRIPTION_INDEX]));
            } else if (lineData[TASK_INDEX].equals(DEADLINE_CODE)) {
                userInputs.add(new Deadline(lineData[DESCRIPTION_INDEX], lineData[BY_AT_INDEX]));
            } else if (lineData[TASK_INDEX].equals(EVENT_CODE)) {
                userInputs.add(new Event(lineData[DESCRIPTION_INDEX], lineData[BY_AT_INDEX]));
            }

            if (lineData[DONE_INDEX].equals("1")) {
                userInputs.get(userInputsCount).markAsDone();
            }
            userInputsCount++;
        }
    }

    public void promptInput() {
        String line;
        line = ui.getUserInput();

        while (!line.equals("bye")) {
            processInput(line);
            line = ui.getUserInput();
        }

        try {
            exit();
        } catch (IOException e) {
            ui.printError(e.toString());
        }
    }

    public void processInput(String input) {
        input = input.trim();

        try {
            if (input.startsWith("list")) {
                listTasks(input);
            } else if (input.startsWith("done")) {
                markDone(input);
            } else if (input.startsWith("todo")) {
                addTodo(input);
            } else if (input.startsWith("deadline")) {
                addDeadline(input);
            } else if (input.startsWith("event")) {
                addEvent(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                handleInvalid();
            }
        } catch (DukeException e) {
            ui.printDukeException(e.toString());
        }
    }

    public void exit() throws IOException{
        final String DELIMITER = "--";
        FileWriter beginWrite = new FileWriter("data/duke.txt");

        beginWrite.write("");
        beginWrite.close();

        FileWriter fw = new FileWriter("data/duke.txt", true);

        for (int i = 0; i < userInputsCount; i++) {
            if (userInputs.get(i).getCode().equals("T")) {
                String lineToAppend = userInputs.get(i).getCode() + DELIMITER + userInputs.get(i).getDoneValue() +
                        DELIMITER + userInputs.get(i).getDescription();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            } else if (userInputs.get(i).getCode().equals("D")) {
                String lineToAppend = userInputs.get(i).getCode() + DELIMITER + userInputs.get(i).getDoneValue() +
                        DELIMITER + userInputs.get(i).getDescription() + DELIMITER + userInputs.get(i).getBy();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            } else if (userInputs.get(i).getCode().equals("E")) {
                String lineToAppend = userInputs.get(i).getCode() + DELIMITER + userInputs.get(i).getDoneValue() +
                        DELIMITER + userInputs.get(i).getDescription() + DELIMITER + userInputs.get(i).getAt();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            }
        }
        fw.close();

        ui.printBye();
        System.exit(0);
    }

    public void listTasks(String line) throws DukeException {
        if (!line.equals("list")) {
            throw new DukeException("command list does not take additional parameters");
        }

        ui.printListTask();

        for (int i = 1; i <= userInputsCount; i++) {
            ui.printToUser("    ", Integer.toString(i), ".", userInputs.get(i - 1).toString());
        }
    }

    public void markDone(String line) throws DukeException {
        String[] taskInputs = line.split(" ");

        if (taskInputs.length != 2) {
            throw new DukeException("incorrect number of parameters for command done");
        }

        final int INDEX_DONE = 1;
        int taskIndexDone = Integer.parseInt(taskInputs[INDEX_DONE]) - 1;

        userInputs.get(taskIndexDone).markAsDone();

        ui.printFinishedTask();
        ui.printToUser("        ", userInputs.get(taskIndexDone).toString());
    }

    public void addTodo(String line) throws DukeException {
        final int START_INDEX = 5;
        String[] todoInputs = line.split(" ");

        if (todoInputs.length == 1) {
            throw new DukeException("command todo description missing");
        }

        userInputs.add(new Todo(line.substring(START_INDEX)));

        ui.printToUser("    ", "added: ", userInputs.get(userInputsCount).toString());
        userInputsCount++;
    }

    public void addDeadline(String line) throws DukeException {
        if (!line.contains("/by")) {
            throw new DukeException("wrong input format for command deadline");
        }

        final int DESCRIPTION_INDEX = 0;
        final int BY_INDEX = 1;
        final int START_INDEX = 9;

        String[] deadlineInputs = line.substring(START_INDEX).split("/by");
        userInputs.add(new Deadline(deadlineInputs[DESCRIPTION_INDEX].trim(),
                deadlineInputs[BY_INDEX].trim()));

        ui.printToUser("    ", "added: ", userInputs.get(userInputsCount).toString());
        userInputsCount++;
    }

    public void addEvent(String line) throws DukeException {
        if (!line.contains("/at")) {
            throw new DukeException("wrong input format for command event");
        }

        final int DESCRIPTION_INDEX = 0;
        final int AT_INDEX = 1;
        final int START_INDEX = 6;

        String[] eventInputs = line.substring(START_INDEX).split("/at");
        userInputs.add(new Event(eventInputs[DESCRIPTION_INDEX].trim(),
                eventInputs[AT_INDEX].trim()));

        ui.printToUser("    ", "added: ", userInputs.get(userInputsCount).toString());
        userInputsCount++;
    }

    public void deleteTask(String line) throws DukeException {
        String[] inputs = line.split(" ");

        if (inputs.length != 2) {
            throw new DukeException("incorrect number of parameters for command delete");
        }

        final int INDEX_DELETE = 1;
        int taskIndexDelete = Integer.parseInt(inputs[INDEX_DELETE]) - 1;

        ui.printDeletedTask();
        ui.printToUser("    ", "deleted: ", userInputs.get(taskIndexDelete).toString());
        userInputs.remove(taskIndexDelete);
        userInputsCount--;
    }

    public void handleInvalid() throws DukeException {
        throw new DukeException("invalid command");
    }

    public static void main(String[] args) {
        new Duke().initiateDuke();
    }
}
