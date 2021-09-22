package duke;

import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public void initiateDuke() {
        ui.printWelcome();

        try {
            load();
        } catch (FileNotFoundException e) {
            ui.printFileNotFound(e.toString());
        }

        promptInput();
    }

    public void load() throws FileNotFoundException {
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
                taskList.addTodo(lineData[DESCRIPTION_INDEX]);
            } else if (lineData[TASK_INDEX].equals(DEADLINE_CODE)) {
                taskList.addDeadline(lineData[DESCRIPTION_INDEX], lineData[BY_AT_INDEX]);
            } else if (lineData[TASK_INDEX].equals(EVENT_CODE)) {
                taskList.addEvent(lineData[DESCRIPTION_INDEX], lineData[BY_AT_INDEX]);
            }

            if (lineData[DONE_INDEX].equals("1")) {
                taskList.markDone(taskList.getListSize() - 1);
            }
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
        int userInputsCount = taskList.getListSize();

        beginWrite.write("");
        beginWrite.close();

        FileWriter fw = new FileWriter("data/duke.txt", true);

        for (int i = 0; i < userInputsCount; i++) {
            if (taskList.getTask(i).getCode().equals("T")) {
                String lineToAppend = taskList.getTask(i).getCode() + DELIMITER + taskList.getTask(i).getDoneValue() +
                        DELIMITER + taskList.getTask(i).getDescription();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            } else if (taskList.getTask(i).getCode().equals("D")) {
                String lineToAppend = taskList.getTask(i).getCode() + DELIMITER + taskList.getTask(i).getDoneValue() +
                        DELIMITER + taskList.getTask(i).getDescription() + DELIMITER + taskList.getTask(i).getBy();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            } else if (taskList.getTask(i).getCode().equals("E")) {
                String lineToAppend = taskList.getTask(i).getCode() + DELIMITER + taskList.getTask(i).getDoneValue() +
                        DELIMITER + taskList.getTask(i).getDescription() + DELIMITER + taskList.getTask(i).getAt();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            }
        }
        fw.close();

        ui.printBye();
        System.exit(0);
    }

    public void listTasks(String line) throws DukeException {
        int userInputsCount = taskList.getListSize();

        if (!line.equals("list")) {
            throw new DukeException("command list does not take additional parameters");
        }

        ui.printListTask();

        for (int i = 1; i <= userInputsCount; i++) {
            ui.printToUser("    ", Integer.toString(i), ".", taskList.getTask(i - 1).toString());
        }
    }

    public void markDone(String line) throws DukeException {
        String[] taskInputs = line.split(" ");

        if (taskInputs.length != 2) {
            throw new DukeException("incorrect number of parameters for command done");
        }

        final int INDEX_DONE = 1;
        int taskIndexDone = Integer.parseInt(taskInputs[INDEX_DONE]) - 1;

        taskList.getTask(taskIndexDone).markAsDone();

        ui.printFinishedTask();
        ui.printToUser("        ", taskList.getTask(taskIndexDone).toString());
    }

    public void addTodo(String line) throws DukeException {
        final int START_INDEX = 5;
        int index = taskList.getListSize();
        String[] todoInputs = line.split(" ");

        if (todoInputs.length == 1) {
            throw new DukeException("command todo description missing");
        }

        taskList.addTodo(line.substring(START_INDEX));

        ui.printToUser("    ", "added: ", taskList.getTask(index).toString());
    }

    public void addDeadline(String line) throws DukeException {
        int index = taskList.getListSize();
        final int DESCRIPTION_INDEX = 0;
        final int BY_INDEX = 1;
        final int START_INDEX = 9;

        if (!line.contains("/by")) {
            throw new DukeException("wrong input format for command deadline");
        }

        String[] deadlineInputs = line.substring(START_INDEX).split("/by");
        taskList.addDeadline(deadlineInputs[DESCRIPTION_INDEX].trim(),
                deadlineInputs[BY_INDEX].trim());

        ui.printToUser("    ", "added: ", taskList.getTask(index).toString());
    }

    public void addEvent(String line) throws DukeException {
        int index = taskList.getListSize();
        final int DESCRIPTION_INDEX = 0;
        final int AT_INDEX = 1;
        final int START_INDEX = 6;

        if (!line.contains("/at")) {
            throw new DukeException("wrong input format for command event");
        }

        String[] eventInputs = line.substring(START_INDEX).split("/at");
        taskList.addEvent(eventInputs[DESCRIPTION_INDEX].trim(),
                eventInputs[AT_INDEX].trim());

        ui.printToUser("    ", "added: ", taskList.getTask(index).toString());
    }

    public void deleteTask(String line) throws DukeException {
        String[] inputs = line.split(" ");
        final int INDEX_DELETE = 1;
        int taskIndexDelete = Integer.parseInt(inputs[INDEX_DELETE]) - 1;

        if (inputs.length != 2) {
            throw new DukeException("incorrect number of parameters for command delete");
        }

        ui.printDeletedTask();
        ui.printToUser("        ", "deleted: ", taskList.getTask(taskIndexDelete).toString());
        taskList.deleteTask(taskIndexDelete);
    }

    public void handleInvalid() throws DukeException {
        throw new DukeException("invalid command");
    }

    public static void main(String[] args) {
        new Duke().initiateDuke();
    }
}
