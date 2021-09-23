package duke.tasklist;

import duke.ui.Ui;
import duke.exception.InvalidDoOrUndoException;
import duke.exception.InvalidTaskDescriptionException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {

    public static void addTodo(String input, ArrayList<Task> tasks) throws InvalidTaskDescriptionException {
        if (!Parser.isValidTaskDescription(input)) {
            throw new InvalidTaskDescriptionException("Task description is invalid!");
        }
        String description = Parser.getTodoDescription(input);
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        Ui.printAddedTaskMessage(newTodo, tasks);
    }

    public static void addDeadlineOrEvent(String input, ArrayList<Task> tasks) throws InvalidTaskDescriptionException {
        String taskType = Parser.getTaskType(input);
        String description = Parser.getDeadlineOrEventDescription(input);
        String time = Parser.getDeadlineOrEventTime(input);

        if (!Parser.isValidDeadlineOrEventDescription(input, description, time)) {
            throw new InvalidTaskDescriptionException("Invalid or missing task detail!");
        }

        if (taskType.equalsIgnoreCase("deadline")) {
            Deadline newDeadline = new Deadline(description, time);
            tasks.add(newDeadline);
            Ui.printAddedTaskMessage(newDeadline, tasks);
        } else if (taskType.equalsIgnoreCase("event")) {
            Event newEvent = new Event(description, time);
            tasks.add(newEvent);
            Ui.printAddedTaskMessage(newEvent, tasks);
        }
    }

    public static void inputDone(String input, ArrayList<Task> tasks) throws InvalidDoOrUndoException {
        int taskNumber = Parser.getTaskNumber(input);
        Task t = tasks.get(taskNumber);
        if (t.isDone()) {
            Ui.printHorizontalLine();
            throw new InvalidDoOrUndoException("This task has already been done, complete something else!");
        }
        t.markAsDone();
        Ui.printDoneTask(t);
    }

    public static void undoDone(String input, ArrayList<Task> tasks) throws InvalidDoOrUndoException {
        int taskNumber = Parser.getTaskNumber(input);
        Task t = tasks.get(taskNumber);
        if (!t.isDone()) {
            Ui.printHorizontalLine();
            throw new InvalidDoOrUndoException("This task has not been done yet!");
        }
        t.markAsNotDone();
        Ui.printUndoneTask(t);
    }

    public static void changeDoneStatus(String input, ArrayList<Task> tasks) {
        String[] splitInput = input.split(" ");
        try {
            if (splitInput[0].equalsIgnoreCase("done")) {
                inputDone(input, tasks);
            } else if (splitInput[0].equalsIgnoreCase("undo")) {
                undoDone(input, tasks);
            }
        } catch (NumberFormatException invalidTaskNumber) {
            System.out.println("Indicate the task number you'd like to do or undo!");
        } catch (InvalidDoOrUndoException e) {
            System.out.println(e.getMessage());
            Ui.printHorizontalLine();
        } catch (NullPointerException e) {
            Ui.printHorizontalLine();
            System.out.println("No such task number exists!");
            Ui.printHorizontalLine();
        }
    }

    public static void deleteTask(String input, ArrayList<Task> tasks) {
        int taskNumber = Parser.getTaskNumber(input);
        Ui.printDeleteMessage(tasks.get(taskNumber), tasks);
        tasks.remove(taskNumber);
    }

    public static void requestList(ArrayList<Task> tasks) {
        Ui.printHorizontalLine();
        if (tasks.size() == 0) {
            System.out.println("There are no tasks!");
            Ui.printHorizontalLine();
            return;
        }
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        Ui.printHorizontalLine();
    }
}
