import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> entries = new ArrayList<>();

    public static int entriesCount = 0;

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