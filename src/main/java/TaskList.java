import java.util.ArrayList;
import java.io.IOException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * This class manages and modifies the task list in Duke.
 */
public class TaskList {
    public ArrayList<Task> schedule = new ArrayList<>();
    public int totalTasks;
    public Storage s = new Storage();

    public TaskList() {

    }

    /**
     * Prints out bye message to exit Duke.
     */
    public void printBye() {
        System.out.println("Bye! Cya next time!");
    }

    /**
     * Loads the task list saved in data/dukedata.txt
     */
    public void initSchedule() {
        schedule = s.checkFile(schedule);
        totalTasks = schedule.size();
    }

    /**
     * Prints out current task list.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list: ");

        for(int i = 0; i < this.totalTasks; i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(schedule.get(i));
        }

    }

    /**
     * Handles done command by obtaining the number contained in index 5 of the input string
     * That number is the index in the task list that will be marked 'X' to signify that it is done.
     * The input is first checked for validity.
     *
     * @param line input from user
     */
    public void handleDone(String line) {
        if (line.length() < 5) {
            System.out.println("Please reenter task number done");
            return;
        }
        int number = Character.getNumericValue(line.charAt(5));
        if (number > totalTasks) {
            System.out.println("You do not have so many tasks");
            return;
        } else if (number < 1) {
            System.out.println("Please enter a valid task number");
            return;
        }
        schedule.get(number - 1).markAsDone();
        s.saveFile(schedule, totalTasks);
    }

    /**
     * Adds the todo task into the task list, but checks its validity first
     *
     * @param content specifications of the todo task
     */
    public void addTodo(String content) {
        Check c = new Check();
        boolean b = c.handleTodoException(content);
        if (b) {
            Todo t = new Todo(content);
            schedule.add(totalTasks, t);
            ++this.totalTasks;
            this.gotItMessage(t);
        }
        s.saveFile(schedule, totalTasks);
    }

    public static Deadline initDeadline(String content) {
        int byIndex = content.indexOf("/by");
        String description = content.substring(0, byIndex - 1);
        String by = content.substring(byIndex + 4);
        Deadline d = new Deadline(description, by);
        return d;
    }

    /**
     * Adds the deadline task into the task list, but checks its validity first
     *
     * @param content specifications of the deadline task
     */
    public void addDeadline(String content) {
        Check c = new Check();
        boolean b = c.handleDeadlineException(content);
        if (b) {
            Deadline d = initDeadline(content);
            schedule.add(totalTasks, d);
            ++this.totalTasks;
            this.gotItMessage(d);
        }
        s.saveFile(schedule, totalTasks);
    }

    public static Event initEvent(String content) {
        int byIndex = content.indexOf("/at");
        String description = content.substring(0, byIndex - 1);
        String eventTime = content.substring(byIndex + 4);
        Event e = new Event(description, eventTime);
        return e;
    }

    /**
     * Adds the event task into the task list, but checks its validity first
     *
     * @param content specifications of the event task
     */
    public void addEvent(String content) {
        Check c = new Check();
        boolean b = c.handleEventException(content);
        if (b) {
            Event e = initEvent(content);
            schedule.add(totalTasks, e);
            ++this.totalTasks;
            this.gotItMessage(e);
        }
        s.saveFile(schedule, totalTasks);
    }

    public void gotItMessage(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + this.totalTasks + " tasks in the list.");
    }

    public void delete(String line) {
        int number = Character.getNumericValue(line.charAt(7));
        Task t = schedule.get(number - 1);
        schedule.remove(t);
        totalTasks--;

        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + this.totalTasks + " tasks in the list.");
    }


    public void finding(String word) {
        ArrayList<Task> found = new ArrayList<>();

        int totalFound = 0;

        for (int i = 0; i < totalTasks; i ++) {
            String description = schedule.get(i).getDescription();
            if (description.contains(word)) {
                found.add(totalFound, schedule.get(i));
                totalFound++;
            }
        }

        printFound(found, totalFound);
    }

    public void printFound(ArrayList<Task> found, int totalFound) {
        System.out.println("Here are matching tasks in your list: ");

        for(int i = 0; i < totalFound; ++i) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(found.get(i));
        }

    }
}
