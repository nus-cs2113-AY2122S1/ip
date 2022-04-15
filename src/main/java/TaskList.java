import java.util.ArrayList;

/**
 * Taking over from Parser, this class contains the operations to modify tasks
 */
public class TaskList {
    public static ArrayList<Task> entries = new ArrayList<>();

    public static int entriesCount = 0;

    /**
     * Returns the message to be written to the file
     * Adds a ToDo Task into the entries ArrayList
     * If the user input is invalid, the DukeException is thrown
     *
     * @param userCommandDetails Details of the user's command
     * @param toBeWritten Message to be written to the file
     * @return toBeWritten Message to be written to the file
     * @throws DukeException If the userCommandDetails is empty or is invalid
     */
    public static String addToDo(String userCommandDetails, String toBeWritten) {
        try {
            if (userCommandDetails.isEmpty() || userCommandDetails.equals("invalid")) {
                throw new DukeException();
            }
            entries.add(entriesCount, new ToDo(userCommandDetails));
            toBeWritten = Ui.taskAdded(entries, entriesCount);
            entriesCount++;
            Ui.entriesCount(entriesCount);
        } catch (DukeException e) {
            Ui.missingDetails();
        }
        return toBeWritten;
    }

    /**
     * Returns the message to be written to the file
     * Adds a Deadline Task into the entries ArrayList
     * If the user input is invalid, the DukeException is thrown
     *
     * @param userCommandDetails Details of the user's command
     * @param toBeWritten Message to be written to the file
     * @return toBeWritten Message to be written to the file
     * @throws DukeException If the userCommandDetails is empty or is invalid
     */
    public static String addDeadline(String userCommandDetails, String toBeWritten) {
        try {
            int deadlineIndex = userCommandDetails.indexOf("/by");
            String taskDescription = userCommandDetails.substring(0, deadlineIndex - 1);
            String taskDeadline = userCommandDetails.substring(deadlineIndex);
            if (userCommandDetails.equals("invalid") || taskDescription.isEmpty() || taskDeadline.isEmpty()) {
                throw new DukeException();
            }
            entries.add(entriesCount, new Deadline(taskDescription, taskDeadline));
            toBeWritten = Ui.taskAdded(entries, entriesCount);
            entriesCount++;
            Ui.entriesCount(entriesCount);
        } catch (DukeException e) {
            Ui.missingDetails();
        }
        return toBeWritten;
    }

    /**
     * Returns the message to be written to the file
     * Adds an Event Task into the entries ArrayList
     * If the user input is invalid, the DukeException is thrown
     *
     * @param userCommandDetails Details of the user's command
     * @param toBeWritten Message to be written to the file
     * @return toBeWritten Message to be written to the file
     * @throws DukeException If the userCommandDetails is empty or is invalid
     */
    public static String addEvent(String userCommandDetails, String toBeWritten) {
        try {
            int eventDateTimeIndex = userCommandDetails.indexOf("/at");
            String eventDescription = userCommandDetails.substring(0, eventDateTimeIndex - 1);
            String eventDateTime = userCommandDetails.substring(eventDateTimeIndex);
            if (userCommandDetails.equals("invalid") || eventDescription.isEmpty() || eventDateTime.isEmpty()) {
                throw new DukeException();
            }
            entries.add(entriesCount, new Event(eventDescription, eventDateTime));
            toBeWritten = Ui.taskAdded(entries, entriesCount);
            entriesCount++;
            Ui.entriesCount(entriesCount);
        } catch (DukeException e) {
            Ui.missingDetails();
        }
        return toBeWritten;
    }

    /**
     * Prints every element in the entries ArrayList sequentially to give the user a list of all their tasks
     */
    public static void list() {
        Ui.printLongLine();
        System.out.println("Here are the tasks in your list:");
        int i = 0;
        for(Task entry : entries){
            System.out.println((i+1) + ". " + "[" + entry.getSymbol() + "] [" + entry.getStatusIcon() + "] " + entry.getDescription());
            i++;
        }
        Ui.printLongLine();
    }

    /**
     * Marks an X in the isDone checkbox of the completed task to show the user that the task is complete
     *
     * @param userIn User's input
     */
    public static void done(String userIn) {
        Ui.printLongLine();
        String stringTaskIndex = userIn.substring(userIn.indexOf(" ") + 1);
        int intTaskIndex = Integer.parseInt(stringTaskIndex) - 1;
        entries.get(intTaskIndex).isDone = true;
        System.out.println("Nice! I've marked this task as done:\n"
                + "   [" + entries.get(intTaskIndex).getSymbol()
                + "] [" + entries.get(intTaskIndex).getStatusIcon() + "] " + entries.get(intTaskIndex).description);
        Ui.printLongLine();
    }

    /**
     * Removes a particular task from the entries ArrayList
     *
     * @param userIn User's input
     */
    public static void delete(String userIn) {
        Ui.printLongLine();
        String stringTaskIndex = userIn.substring(userIn.indexOf(" ") + 1);
        int intTaskIndex = Integer.parseInt(stringTaskIndex) - 1;
        System.out.println("Noted. I've removed this task:\n"
                + "   [" + entries.get(intTaskIndex).getSymbol()
                + "] [" + entries.get(intTaskIndex).getStatusIcon() + "] " + entries.get(intTaskIndex).description);
        System.out.println("Now you have " + (entriesCount - 1) + " tasks in the list.");
        for (int i = intTaskIndex; i < entriesCount - 1; i++) {
            entries.set(i, entries.get(i + 1));
        }
        entries.remove(entriesCount - 1);
        entriesCount -= 1;
        Ui.printLongLine();
    }

    /**
     * Parses through every element of the entries ArrayList to find a match with the user's input
     *
     * @param userCommandDetails Details of the user's command
     */
    public static void find(String userCommandDetails) {
        Ui.printLongLine();
        int i = 0;
        for(Task entry : entries){
            if (entry.getDescription().contains(userCommandDetails)) {
                if (i == 0) {
                    System.out.println("Here are the matching tasks in your list:\n");
                }
                System.out.println((i+1) + ". " + "[" + entry.getSymbol() + "] [" + entry.getStatusIcon() + "] " + entry.getDescription());
                i++;
            }
        }
        if (i == 0) {
            System.out.println("Sorry, keyword couldn't be found...");
        }
        Ui.printLongLine();
    }
}