package duke;

import java.util.ArrayList;

public class TaskList {

    // getting task names, deadline, duration etc should be done in Parser

    public static void addTodo(String input, ArrayList<Task> tasks) {
        String description = Parser.getTodoDescription(input);
        tasks.add(new Todo(description));
    }

    public static void addDeadlineOrEvent(String input, ArrayList<Task> tasks) {
        String taskType = Parser.getTaskType(input);
        String description = Parser.getDeadlineOrEventDescription(input);
        String time = Parser.getDeadlineOrEventTime(input);

        if (taskType.equalsIgnoreCase("deadline")) {
            tasks.add(new Deadline(description, time));
        } else if (taskType.equalsIgnoreCase("event")) {
            tasks.add(new Event(description, time));
        }
    }

    public static void inputDone(String input, ArrayList<Task> tasks) throws InvalidDoOrUndoException {
        int taskNumber = Parser.getTaskNumber(input);
        Task t = tasks.get(taskNumber);
        if (t.isDone) {
            Ui.printHorizontalLine();
            throw new InvalidDoOrUndoException("This task has already been done, complete something else!");
        }
        t.markAsDone();
        Ui.printDoneTask(t);
    }

    public static void undoDone(String input, ArrayList<Task> tasks) throws InvalidDoOrUndoException {
        int taskNumber = Parser.getTaskNumber(input)
        Task t = tasks.get(taskNumber);
        if (!t.isDone) {
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
}
