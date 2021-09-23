import todo.Deadline;
import todo.Event;
import todo.Task;
import todo.ToDo;


/**
 * The Tasklist class implements different methods which allows for certain functions
 * such as add, remove and list.
 */
public class TaskList {

    /**
     * Removes task with index from list of current tasks.
     * If index is not present in the list, an error message will be displayed.
     * @param index index of task to remove
     */
    protected static void removeTask(int index) {
        try {
            Duke.tasks.remove(index - 1);
            Task t = Duke.tasks.get(index - 1);
            Duke.printMessage("Noted. I've removed this task:\n" + t);
            getTasksLeft();
        } catch (IndexOutOfBoundsException e) {
            Duke.printMessage("This task index does not exist");
        }
    }

    /**
     * Prints all tasks that are currently stored in the list and number of tasks not done.
     * If there are no tasks, the user will be informed.
     */
    protected static void listAllTask() {
        if(Duke.tasks.size() == 0) {
            Duke.printMessage("You have no tasks at the moment!");
        } else {
            Duke.printMessage("Here are the tasks in your list:");
            for (int i = 0; i < Duke.tasks.size(); i++) {
                System.out.println((i + 1) + ". " + Duke.tasks.get(i).toString());
            }
            getTasksLeft();
        }
    }

    /**
     * This function adds in tasks after it reads from a .txt file.
     * It takes in a string array, formats it, and calls the function
     * that adds the tasks in depending on the type of task.
     *
     * @param splitInput string array containing the different parameters to input to a task
     */
    static void addTask(String[] splitInput) {
        String type = splitInput[0];
        Boolean status = Boolean.parseBoolean(splitInput[1]);
        String taskDescription = splitInput[2];
        if(type.equals("t")) {
            addTodo(taskDescription);
        } else if(type.equals("d")) {
            addDeadline(splitInput[3], taskDescription);
        } else if(type.equals("e")) {
            addEvent(splitInput[3], taskDescription);
        }
        if(status) {
            Duke.tasks.get(Duke.tasks.size() - 1).setDone(true);
        }
    }

    /**
     * Gets number of undone tasks, prints the total number of tasks in the list
     * and prints the number of undone tasks.
     *
     */
    protected static void getTasksLeft() {
        int undoneTasks = 0;
        for(int i = 0; i < Duke.tasks.size(); i++) {
            if(!Duke.tasks.get(i).getStatus()) {
                undoneTasks++;
            }
        }
        System.out.println("You have " + Duke.tasks.size() + " tasks in the list.");
        Duke.printMessage(undoneTasks + " tasks are undone.");
    }

    /**
     * Obtains the latest task added and feedbacks to user
     * that the task has been successfully added
     */
    protected static void addSuccessMessage() {
        String taskDescription = Duke.tasks.get(Duke.tasks.size() - 1).toString();
        Duke.printMessage("Got it. I've added this task:\n" + taskDescription);
        getTasksLeft();
    }

    /**
     * Checks if a date is present. Returns true
     *
     * @param input string array of task split by " "
     * @return true if there is not date or date is whitespace
     */
    protected static Boolean checkEmptyDate(String[] input) {
        if(input.length == 1 || input[1].trim().length() == 0) {
            return true;
        }
        return false;
    }

    private static void addEvent(String inputDate, String taskDescription) {
        Duke.tasks.add(new Event(taskDescription, inputDate));
    }

    private static void addDeadline(String inputDate, String taskDescription) {
        Duke.tasks.add(new Deadline(taskDescription, inputDate));
    }

    private static void addTodo(String taskDescription) {
        Duke.tasks.add(new ToDo(taskDescription));
    }
}
