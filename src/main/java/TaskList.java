import todo.Deadline;
import todo.Event;
import todo.Task;
import todo.ToDo;


/**
 * This class includes the following functions: List, Remove, Add
 */
public class TaskList {
    protected static void removeTask(int index) {
        try {
            Duke.tasks.remove(index - 1);
            Task t = Duke.tasks.get(index - 1);
            Duke.printMessage("Noted. I've removed this task:\n" + t);
            getTasksLeft();
        } catch (IndexOutOfBoundsException e) {
            Duke.printMessage("Something went wrong! Please try again!");
        }
    }

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

    private static void addEvent(String date1, String taskDescription) {
        String date = date1;
        Duke.tasks.add(new Event(taskDescription, date));
    }

    private static void addDeadline(String date1, String taskDescription) {
        String date = date1;
        Duke.tasks.add(new Deadline(taskDescription, date));
    }

    private static void addTodo(String taskDescription) {
        Duke.tasks.add(new ToDo(taskDescription));
    }

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

    protected static void addSuccess() {
        String taskDescription = Duke.tasks.get(Duke.tasks.size() - 1).toString();
        Duke.printMessage("Got it. I've added this task:\n" + taskDescription);
        getTasksLeft();
    }

    protected static Boolean checkEmptyDate(String[] input) {
        if(input.length == 1 || input[1].trim().length() == 0) {
            return true;
        }
        return false;
    }
}
