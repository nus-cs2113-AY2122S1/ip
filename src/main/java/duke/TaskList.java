package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class that contains methods to handle respective user inputs.
 *
 * @author pragyan01
 */
public class TaskList {

    public static String line = "------------------------------------------------------------------------------------------\n";
    public static ArrayList<Task> t = new ArrayList<>();
    public static int taskCount = 0;
    public static int quitFlag = 0;

    /**
     * Method that updates quitFlag to 1 when user wants to terminate the program.
     */
    public static void updateQuitFlag() {
        quitFlag = 1;
    }

    /**
     * Terminates the program.
     *
     * @param input that user gives
     */
    public static void sayBye(String input) throws DukeException{
        if (input.length() != 3) {
            throw new DukeException("You didn't say bye properly, but i get it! Adios for now!\n");
        } else {
            System.out.println(line + "\n" + "Ciao! More tasks to do later!\n" + line);
            updateQuitFlag();
        }
        System.exit(0);
    }

    /**
     * Prints out all the tasks, their types and statuses stored by the bot.
     */
    public static void sayList() throws DukeException{
        if (taskCount == 0) {
            throw new DukeException("Hold your horses, you didn't even tell me about your wishes yet!");
        } else {
            System.out.println(line);
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + t.get(i).toString() + "\n");
            }
            System.out.println(line);
        }
    }

    /**
     * Marks a specific task stored as done.
     *
     * @param input that user gives containing the position of task
     */
    public static void sayDone(String input) throws DukeException {
        if (taskCount != 0) {
            try {
                String taskNumber = input.substring(input.lastIndexOf(" ") + 1);
                int finalTaskNumber = Integer.parseInt(taskNumber) - 1;
                t.get(finalTaskNumber).markAsDone();
                System.out.println(line + "\n" + "Kudos! One less thing to stress about!\n");
                System.out.println("  " + t.get(finalTaskNumber).toString() + "\n" + line);
            } catch (NullPointerException e) {
                System.out.println(line + "\nInvalid task number entered! Please try again!\n" + line);
            }
        } else {
            throw new DukeException("Calm down, we haven't even started listing out tasks yet!");
        }
    }

    /**
     * Adds a todo task.
     *
     * @param input that user gives
     */
    public static void sayTodo(String input) {
        if (input.length() == 4) {
            System.out.println(line + "\nDid you forget what you were gonna say?\n" + line);
        } else {
            int endIndex = input.length();
            String taskName = input.substring(5, endIndex);
            t.add(taskCount, new Todo(taskName));
            System.out.println(line + "\n");
            System.out.println("That's the spirit! I've added this task:\n");
            System.out.println(t.get(taskCount).toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }

    /**
     * Adds a deadline task.
     *
     * @param input that user gives
     */
    public static void sayDeadline(String input) {
        if (input.length() == 8) {
            System.out.println(line + "\nAre you kidding me? You didn't even tell me what you were supposed to do!\n");
            System.out.println(line);
        } else if (!input.contains("/")) {
            System.out.println(line + "\nAre you kidding me? You forgot to tell me your deadline again?\n" + line);
        } else {
            int endIndex = input.indexOf("/");
            String taskName = input.substring(9, endIndex);
            int endIndex2 = input.length();
            String by = input.substring(endIndex + 4, endIndex2);
            LocalDate dateTime = LocalDate.parse(by);
            t.add(taskCount, new Deadline(taskName, by));
            System.out.println(line + "\n");
            System.out.println("Deadline Entered: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n");
            System.out.println("Got it. I've added this task:\n");
            System.out.println(t.get(taskCount).toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }


    /**
     * Adds a event task.
     *
     * @param input that user gives
     */
    public static void sayEvent(String input) {
        if (input.length() == 5) {
            System.out.println(line + "\nAre you kidding me? You didn't even tell me what you were supposed to do!\n");
            System.out.println(line);
        } else if (!input.contains("/")) {
            System.out.println(line + "\nAre you kidding me? You forgot to tell me your event timing again?\n" + line);
        } else {
            int endIndex = input.lastIndexOf("/");
            String taskName = input.substring(6, endIndex);
            int endIndex2 = input.length();
            String at = input.substring(endIndex + 4, endIndex2);
            t.add(taskCount, new Event(taskName, at));
            System.out.println(line + "\n");
            System.out.println("Got it. I've added this task:\n");
            System.out.println(t.get(taskCount).toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }

    /**
     * Deletes a specific task.
     *
     * @param input that user gives containing the position of task
     */
    public static void sayDelete(String input) throws DukeException {
        if (taskCount != 0) {
            try {
                String taskNumber = input.substring(input.lastIndexOf(" ") + 1);
                int finalTaskNumber = Integer.parseInt(taskNumber) - 1;
                Task taskRemoved = t.get(finalTaskNumber);
                t.remove(finalTaskNumber);
                taskCount--;
                System.out.println(line + "\n" + "One more thing outta your life as always...\n");
                System.out.println("  " + taskRemoved.toString() + "\n" + "\nYou now have " + taskCount + " tasks left.\n");
                System.out.println(line);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(line + "\nInvalid task number entered! Please try again!\n" + line);
            }
        } else {
            throw new DukeException("Calm down, we don't even have tasks yet!");
        }
    }

    /**
     * Finds and prints a specific task.
     *
     * @param input that user gives containing the position of task
     */
    public static void sayFind(String input){
        String[] arrayString = input.split(" ", 2);
        String keyWord = arrayString[1];
        int matchCount = 0;

        ArrayList<String> filteredList = new ArrayList<>();
        for (Task task : t) {
            if (task.toString().contains(keyWord)) {
                filteredList.add(task.toString());
                matchCount++;
            }
        }
        if (matchCount != 0) {
            System.out.println("\n" + "Lucky for you, i'm really good at digging through your mess:" + "\n" + line);
            for (int i = 0; i < filteredList.size(); i++) {
                System.out.println((i + 1) + ". " + filteredList.get(i) + "\n");
            }
        } else {
            System.out.println("Too bad i couldn't find anything similar. Let's not get too ambitious and just stick to the tasks on hand shall we?" + "\n");
        }
    }
}
