package duke;

import duke.task.*;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private static final ArrayList<Task> listInput = new ArrayList<>();
    private static int taskNumber = 0;

    public static void showTask() {
        if (taskNumber <= 0) {
            System.out.println("The list is empty! Add something to it:)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskNumber; i++) {
                System.out.println((i + 1) + "." + listInput.get(i).toString());
            }
        }
        UI.printBreaker();
    }

    public static void findTask(String[] arrayInput) {
        UI.printFindTask(listInput.stream().filter(t -> t.getName().contains(arrayInput[1])).collect(Collectors.toList()));
        UI.printBreaker();
    }

    public static void doneTask(int inputIndex) {
        try {
            listInput.get(inputIndex).markAsDone();
            UI.printDoneTask(listInput.get(inputIndex - 1).toString());
            Storage.saveToFile(listInput, taskNumber);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("OOPS!!! The index of the task that you entered does not exist:(");
            UI.printBreaker();
        }
    }

    public static void deleteTask(int inputIndex) {
        try {
            listInput.remove(inputIndex);
            UI.printDeleteTask(taskNumber, listInput.get(inputIndex - 1).toString());
            taskNumber--;
            Storage.saveToFile(listInput, taskNumber);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task that you entered does not exist:(");
            UI.printBreaker();
        }
    }

    public static void recordTask(String commandInput, String lineInput) throws DukeException {
        int breakIndex = lineInput.indexOf("/");
        try {
            switch (commandInput) {
            case "deadline":
                if (lineInput.length() < 12) {
                    throw new DukeException("The description of the deadline is too short! Please enter again.\n");
                }
                listInput.add(taskNumber, new Deadline(lineInput.substring(9, breakIndex), lineInput.substring(breakIndex + 3)));
                UI.printRecordTask(taskNumber, new Deadline(lineInput.substring(6, breakIndex), lineInput.substring(breakIndex + 3)));
                break;
            case "event":
                if (lineInput.length() < 9) {
                    throw new DukeException("The description of the event is too short! Please enter again.\n");
                }
                listInput.add(taskNumber, new Event(lineInput.substring(6, breakIndex), lineInput.substring(breakIndex + 3)));
                UI.printRecordTask(taskNumber, new Event(lineInput.substring(6, breakIndex), lineInput.substring(breakIndex + 3)));
                break;
            case "todo":
                listInput.add(taskNumber, new ToDo(lineInput.substring(5)));
                UI.printRecordTask(taskNumber, new ToDo(lineInput.substring(5)));
                break;
            }
            Storage.saveToFile(listInput, taskNumber);
        }catch (DukeException e) {
            System.out.println("OOPS!!! Sorry, but I do not understand:(");
            UI.printBreaker();
        }
    }
}