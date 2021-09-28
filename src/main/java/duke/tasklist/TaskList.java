package duke.tasklist;

import duke.exception.TaskNotFoundException;
import duke.ui.Ui;
import duke.exception.InvalidDoOrUndoException;
import duke.exception.InvalidTaskDescriptionException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    /**
     * Adds a Todo task to the ArrayList of tasks.
     *
     * @param input string containing description of Todo task
     * @param tasks ArrayList of tasks
     * @throws InvalidTaskDescriptionException if no description is provided by the user
     */
    public static void addTodo(String input, ArrayList<Task> tasks) throws InvalidTaskDescriptionException {
        if (!Parser.isValidTaskDescription(input)) {
            throw new InvalidTaskDescriptionException("Task description is invalid!");
        }
        String description = Parser.getTodoDescription(input);
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        Ui.printAddedTaskMessage(newTodo, tasks);
    }

    /**
     * Adds a Deadline or Event task to the ArrayList of tasks. As the format of Deadline and Event
     * tasks are very similar, adding these two types of tasks are condensed into one method.
     *
     * @param input string containing description and date and/or time of Deadline or Event task
     * @param tasks ArrayList of tasks
     * @throws InvalidTaskDescriptionException if no description is provided by the user, or the input by
     * the user does not contain "/by" for a Deadline task or "/at" for an Event task.
     * @see Deadline
     * @see Event
     */
    public static void addDeadlineOrEvent(String input, ArrayList<Task> tasks) throws InvalidTaskDescriptionException {
        String taskType = Parser.getCommand(input);
        String description = Parser.getDeadlineOrEventDescription(input);
        String timeAsString = Parser.getDateAndTimeSubstring(input);
        LocalDateTime time = Parser.convertSubStringToDateAndTime(timeAsString.trim());

        // This will allow for the DateTimeParseException message to be printed from convertSubStringToDateAndTime,
        // otherwise the program will attempt to add a new task with a null LocalDateTime object.
        if (time == null) {
            return;
        }

        if (!Parser.isValidDeadlineOrEventDescription(input, description)) {
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

    /**
     * Marks an undone task as done.
     *
     * @param input string containing "done" and the task number to be marked as done
     * @param tasks ArrayList of tasks
     * @throws InvalidDoOrUndoException if the user tries to mark an already completed task as done
     */
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

    /**
     * Reverts a completed task to undone state.
     *
     * @param input string containing "undo" and the task number to be marked as undone
     * @param tasks ArrayList of tasks
     * @throws InvalidDoOrUndoException if the user tries to revert an undone task to undone state
     */
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

    /**
     * Processes inputs which marks tasks as done or undone.
     * Prints an error message if the input does not contain a number, attempting to mark a done task as done, attempting to
     * undo an undone task, or if the task number is out of the range of the current number of tasks.
     *
     * @param input string containing either "done" or "undo" and the task number to have its status changed.
     * @param tasks ArrayList of tasks
     */
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
        } catch (IndexOutOfBoundsException e) {
            Ui.printHorizontalLine();
            System.out.println("No such task number exists!");
            Ui.printHorizontalLine();
        }
    }

    /**
     * Deletes a task given a task number.
     * Prints an error message if the task number to delete is out of range of the current number of tasks.
     *
     * @param input string containing which task to delete.
     * @param tasks ArrayList of tasks
     */
    public static void deleteTask(String input, ArrayList<Task> tasks) {
        try {
            if (Parser.isDeleteAll(input)) {
                tasks.clear();
                Ui.printDeleteAllMessage();
                return;
            }
            int taskNumber = Parser.getTaskNumber(input);
            Ui.printDeleteMessage(tasks.get(taskNumber), tasks);
            tasks.remove(taskNumber);
        } catch (NullPointerException e) {
            Ui.printHorizontalLine();
            System.out.println("No such task number exists!");
            Ui.printHorizontalLine();
        }
    }

    /**
     * Displays the current list of tasks. Each line will display the type of task, its done state, and
     * its description.
     * Prints a message to inform the user if the task list is empty.
     *
     * @param tasks ArrayList of tasks
     */
    public static void requestList(ArrayList<Task> tasks) {
        Ui.printHorizontalLine();
        if (tasks.size() == 0) {
            System.out.println("There are no tasks!");
            Ui.printHorizontalLine();
            return;
        }
        System.out.println("Here are the tasks in your list: ");
        Ui.printList(tasks);
        Ui.printHorizontalLine();
    }

    /**
     * Finds and returns all tasks containing a given keyword provided by the user.
     *
     * @param input string containing keyword of desired tasks to find
     * @param tasks ArrayList of tasks
     * @throws TaskNotFoundException if no tasks in the list of tasks contains the provided keyword
     */
    public static void findTasks(String input, ArrayList<Task> tasks) throws TaskNotFoundException {
        ArrayList<Task> filteredList = filterTasksByString(input, tasks);
        if (filteredList.isEmpty()) {
            throw new TaskNotFoundException("I can't find any matching tasks! Try again!");
        }
        Ui.printFilteredTaskList(filteredList);
    }

    /**
     * Filters the entire list of tasks and returns a new list of tasks that contains the
     * given keyword.
     *
     * @param input string containing keyword of desired tasks to find
     * @param tasks ArrayList of tasks
     * @return ArrayList of tasks containing the provided keyword.
     */
    public static ArrayList<Task> filterTasksByString(String input, ArrayList<Task> tasks) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((task) -> task.getDescription().toLowerCase().contains(input))
                .collect(Collectors.toList());
        return filteredList;
    }
}
