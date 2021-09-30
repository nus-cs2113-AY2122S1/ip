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
            Task t = Duke.tasks.get(index - 1);
            Duke.tasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + t);
            getTasksLeft();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("This task index does not exist");
        }
    }

    /**
     * Prints all tasks that are currently stored in the list and number of tasks not done.
     * If there are no tasks, the user will be informed.
     */
    protected static void listAllTask() {
        if(Duke.tasks.size() == 0) {
            System.out.println("You have no tasks at the moment!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Duke.tasks.size(); i++) {
                System.out.println((i + 1) + ". " + Duke.tasks.get(i).toString());
            }
        }
        Duke.printDivider();
    }

    /**
     * This function adds in tasks after it reads from a .txt file.
     * It takes in a string array, formats it, and calls the function
     * that adds the tasks in depending on the type of task.
     *
     * @param splitInput string array containing the different parameters to input to a task
     */
    protected static void addTaskFromFile(String[] splitInput) {
        String type = splitInput[0];
        Boolean status = Boolean.parseBoolean(splitInput[1]);
        String taskDescription = splitInput[2];
        if(type.equals("t")) {
            addToDo(taskDescription);
        } else if(type.equals("d")) {
            String date = splitInput[3];
            addDeadline(taskDescription, date);
        } else if(type.equals("e")) {
            String date = splitInput[3];
            addEvent(taskDescription, date);
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
        System.out.println(undoneTasks + " tasks are undone.");
        Duke.printDivider();
    }

    /**
     * Obtains the latest task added and feedbacks to user
     * that the task has been successfully added
     */
    protected static void addSuccessMessage() {
        String taskDescription = Duke.tasks.get(Duke.tasks.size() - 1).toString();
        System.out.println("Got it. I've added this task:\n" + taskDescription);
        Duke.printDivider();
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

    protected static void addEvent(String taskDescription, String inputDate) {
        Duke.tasks.add(new Event(taskDescription, inputDate));
        TaskList.addSuccessMessage();
    }

    protected static void addDeadline(String taskDescription, String inputDate) {
        Duke.tasks.add(new Deadline(taskDescription, inputDate));
        TaskList.addSuccessMessage();
    }

    protected static void addToDo(String taskDescription) {
        Duke.tasks.add(new ToDo(taskDescription));
        TaskList.addSuccessMessage();
    }

    protected static void setTaskDone(int currentIndex) {
        Duke.tasks.get(currentIndex - 1).setDone(true);
        completeSuccess(currentIndex);
    }

    protected static void completeSuccess(int index) {
        System.out.println("Got it. I've marked this task as complete:");
        System.out.println(Duke.tasks.get(index - 1).toString());
        Duke.printDivider();
        TaskList.getTasksLeft();
    }
}
