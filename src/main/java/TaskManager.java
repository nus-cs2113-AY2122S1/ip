import java.util.ArrayList;

public class TaskManager extends UserInterface{

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void printTasks() {
        if (tasks.isEmpty()) {
            echo("  List is empty!");
        } else {
            System.out.println(HORIZONTAL_BAR);
            // Traverse down the ArrayList and prints out each task
            for (int i = 0; i < tasks.size(); i++) {
                int currentIndexInOnesIndexing = i + 1;
                System.out.println("  " + currentIndexInOnesIndexing + ". "
                        + tasks.get(i).getTaskDescriptionWithStatus());
            }
            System.out.println(HORIZONTAL_BAR);
        }
    }

    /**
     * Marks task in array as done and prints echo message depending on validity of task no.
     */
    public void markTaskAsDone(String input) {
        // Extracting task number as an integer from input
        int taskNumber = Integer.parseInt(input.trim(), 10);
        boolean taskNumberInRange = (taskNumber <= tasks.size()) && (taskNumber >= 1);
        if (taskNumberInRange) {
            tasks.get(taskNumber - 1).setDone();
            echo("Task " + taskNumber + ": " + tasks.get(taskNumber - 1).taskDescription
                    + System.lineSeparator() + "  Marked as done!");
        } else {
            echo("  Invalid Task number!");
        }
    }

    public void addToDo (String description) {
        tasks.add(new ToDo(description));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public void addEvent (String description, String at) {
        tasks.add(new Event(description, at));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public void addDeadline (String description, String by) {
        tasks.add(new Deadline(description, by));
        echo("  You have successfully added the task:" + System.lineSeparator()
                + "    " + tasks.get(tasks.size() - 1).getTaskDescriptionWithStatus() + System.lineSeparator()
                + "  You now have " + tasks.size() + " tasks in your list!");
    }

    public void checkInputThenAddEvent(String[] eventInput) {
        if (eventInput.length > 1) {
            // eventInput[0] equals description, eventInput[1] equals "at"
            addEvent(eventInput[0].trim(), eventInput[1].trim());
        } else {
            // adds the description, no user input for "at"
            addEvent(eventInput[0].trim(), "");
        }
    }

    public void checkInputThenAddDeadline(String[] deadlineInput) {
        if (deadlineInput.length > 1) {
            // deadlineInput[0] equals description, deadlineInput[1] equals "by"
            addDeadline(deadlineInput[0].trim(), deadlineInput[1].trim());
        } else {
            // adds the description, no user input for "by"
            addDeadline(deadlineInput[0].trim(), "");
        }
    }

    public void printMessageForInvalidInput () {
        echo("  Invalid Input..." + System.lineSeparator()
                + "  Please enter a valid input!");
    }
}
