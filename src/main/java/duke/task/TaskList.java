package duke.task;

import duke.exception.InvalidInputException;

import java.io.IOException;
import java.util.ArrayList;

import duke.Storage;

public class TaskList {
    private static ArrayList<Todo> tasks = new ArrayList<Todo>();

    /**
     * Adds a Todo object to the ArrayList, tasks.
     * If userInput has less than 5 characters, an InvalidInputException is thrown.
     * If InvalidInputException is caught, print error message and return.
     *
     * @param userInput String which user inputted to add a Todo task, should contain information about the task.
     */
    public static void addTodo(String userInput) {
        try {
            if (userInput.length() < 5) {
                throw new InvalidInputException("OOPS!!! Description of todo cannot be empty :(");
            }
            Todo newTask = new Todo(userInput.substring(5));
            tasks.add(newTask);
            printTaskAddedConfirmation(newTask);
            updateFile();
        } catch (InvalidInputException e) {
            System.out.println(e.toString().substring(38));
        }
    }

    /**
     * Adds a Deadline object to the ArrayList, tasks.
     * If userInput has less than 9 characters, an InvalidInputException is thrown.
     * If userInput has no substring "/by", an InvalidInputException is thrown.
     * If InvalidInputException is caught, print error message and return.
     *
     * @param userInput String which user inputted to add a Deadline task, should contain information about the task.
     */
    public static void addDeadline(String userInput) {
        try {
            if (userInput.length() < 9) {
                throw new InvalidInputException("OOPS!!! Description of deadline cannot be empty :(");
            }
            if (!userInput.contains("/by ")) {
                throw new InvalidInputException("OOPS!! Please input date of deadline");
            }
            int startIndexOfTask = userInput.indexOf(' ') + 1;
            int endIndexOfTask = userInput.indexOf('/') - 1;

            int startIndexOfDeadline = userInput.indexOf('/') + 4;
            String taskName = userInput.substring(startIndexOfTask, endIndexOfTask);
            String deadline = userInput.substring(startIndexOfDeadline);
            Deadline newTask = new Deadline(taskName, deadline);
            tasks.add(newTask);
            printTaskAddedConfirmation(newTask);
            updateFile();
        } catch (InvalidInputException e) {
            System.out.println(e.toString().substring(38));
        }
    }

    /**
     * Adds an Event object to the ArrayList, tasks.
     * If userInput has less than 6 characters, an InvalidInputException is thrown.
     * If userInput has no substring "/at", an InvalidInputException is thrown.
     * If InvalidInputException is caught, print error message and return.
     *
     * @param userInput String which user inputted to add an Event task, should contain information about the task.
     */
    public static void addEvent(String userInput) {
        try {
            if (userInput.length() < 6) {
                throw new InvalidInputException("OOPS!!! Description of event cannot be empty :(");
            }
            if (!userInput.contains("/at ")) {
                throw new InvalidInputException("OOPS!! Please input time of event");
            }
            int startIndexOfTask = userInput.indexOf(' ') + 1;
            int endIndexOfTask = userInput.indexOf('/') - 1;
            int startIndexOfDate = userInput.indexOf('/') + 4;
            String taskName = userInput.substring(startIndexOfTask, endIndexOfTask);
            String deadline = userInput.substring(startIndexOfDate);
            Event newTask = new Event(taskName, deadline);
            tasks.add(newTask);
            printTaskAddedConfirmation(newTask);
            updateFile();
        } catch (InvalidInputException e) {
            System.out.println(e.toString().substring(38));
        }

    }

    /**
     * Prints the elements in the ArrayList, tasks.
     * If there are no elements in the ArrayList, prints "No tasks added".
     */
    public static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("\tNo tasks added");
            return;
        }
        int count = 1;
        System.out.println("\tHere are the tasks in your list");
        for (Todo task : tasks) {
            System.out.println("\t" + count + "." + task);
            count++;
        }
    }

    /**
     * Marks the task specified by userInput as done.
     * Casts the userInput as integer.
     * If the integer is larger than the size of the ArrayList, tasks, print "No such task".
     * First task corresponds to index 1.
     *
     * @param userInput Index of the task to be mark as done.
     */
    public static void markDone(String userInput) {
        int indexOfTaskNumber = userInput.indexOf(' ') + 1;
        int taskNumber = Integer.parseInt(userInput.substring(indexOfTaskNumber));
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            System.out.println("\tNo such task");
            return;
        }
        Todo task = tasks.get(taskNumber - 1);
        task.setIsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
        updateFile();
    }

    /**
     * Deletes the task specified by userInput.
     * Casts the userInput as integer.
     * If the integer is larger than the size of the ArrayList, tasks, print "No such task".
     * First task corresponds to index 1.
     *
     * @param userInput Index of the task to be deleted.
     */
    public static void deleteTask(String userInput) {
        int indexOfTaskNumber = userInput.indexOf(' ') + 1;
        int taskNumber = Integer.parseInt(userInput.substring(indexOfTaskNumber));
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            System.out.println("\tNo such task");
            return;
        }
        Todo task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
        updateFile();
    }


    public static void findTasksWithSubstring(String userInput) {
        int startIndexOfTargetSubstring = userInput.indexOf(' ') + 1;
        String targetSubString = userInput.substring(startIndexOfTargetSubstring);
        ArrayList<Todo> listOfTasksWithSubstring = new ArrayList<Todo>();
        for (Todo task : tasks) {
            if (task.getName().contains(targetSubString)) {
                listOfTasksWithSubstring.add(task);
            }
        }
        printTasksWithSubstring(listOfTasksWithSubstring);
    }

    private static void printTasksWithSubstring(ArrayList<Todo> listOfTasks) {
        if (listOfTasks.size() == 0) {
            System.out.println("\tNo tasks with target substring");
            return;
        }
        System.out.println("\tHere are the matching tasks in your list:");
        int count = 1;
        for (Todo task : listOfTasks) {
            System.out.println("\t" + count + "." + task);
            count++;
        }
    }


    /**
     * Prints the task that was added.
     * Helper method for addDeadline(), addEvent() and addTodo() methods.
     *
     * @param task Task that was added into the ArrayList, tasks.
     */
    private static void printTaskAddedConfirmation(Todo task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.format("\tNow you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Updates the text file to the current situation.
     * Helper method for addDeadline(), addEvent(), addTodo, deleteTask(), markDone() methods.
     * In short, helper method for any methods which modifies the ArrayList, tasks or the elements in it.
     */
    private static void updateFile() {
        try {
            Storage.write(tasks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /**
     * Converts the strings in the ArrayList to the corresponding objects.
     * The objects can be of type Todo, Deadline, or Event.
     * Adds the objects to the ArrayList, tasks.
     *
     * @param tasksString ArrayList which contains strings which are descriptions of tasks.
     */
    public static void convertTaskStringToTasks(ArrayList<String> tasksString) {
        if (tasksString == null) {
            return;
        }
        if (tasksString.isEmpty()) {
            return;
        }
        int startIndexOfTask;
        int endIndexOfTask;
        int startIndexOfDate;
        int endIndexOfDate;
        String date;
        String taskName;
        for (String taskString : tasksString) {
            char type = taskString.charAt(3);
            switch (type) {
            case 'T':
                startIndexOfTask = 9;
                taskName = taskString.substring(startIndexOfTask);
                Todo newTodo = new Todo(taskName);
                tasks.add(newTodo);
                if (taskString.charAt(6) == 'X') {
                    newTodo.setIsDone();
                }
                continue;
            case 'D':
                startIndexOfTask = 9;
                endIndexOfTask = taskString.indexOf('(') - 1;
                startIndexOfDate = endIndexOfTask + 6;
                endIndexOfDate = taskString.indexOf(')');
                taskName = taskString.substring(startIndexOfTask, endIndexOfTask);
                date = taskString.substring(startIndexOfDate, endIndexOfDate);
                Deadline newDeadline = new Deadline(taskName, date);
                tasks.add(newDeadline);
                if (taskString.charAt(6) == 'X') {
                    newDeadline.setIsDone();
                }
                continue;
            case 'E':
                startIndexOfTask = 9;
                endIndexOfTask = taskString.indexOf('(') - 1;
                startIndexOfDate = endIndexOfTask + 6;
                endIndexOfDate = taskString.indexOf(')');
                taskName = taskString.substring(startIndexOfTask, endIndexOfTask);
                date = taskString.substring(startIndexOfDate, endIndexOfDate);
                Event newEvent = new Event(taskName, date);
                tasks.add(newEvent);
                if (taskString.charAt(6) == 'X') {
                    newEvent.setIsDone();
                }
                continue;
            }

        }
    }
}
