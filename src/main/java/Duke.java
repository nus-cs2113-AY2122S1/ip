import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();

    private static inputHandleStatus handleOneInputLine(String line) {
        switch (line) {
            case "bye":
                return inputHandleStatus.END;
            case "list":
                for (int i = 1; i <= taskList.size(); i += 1) {
                    System.out.println(i + ": " + taskList.get(i - 1));
                }
                return inputHandleStatus.OK;
            default:
                taskList.add(new Task(line));
                System.out.println("added: " + line);
                return inputHandleStatus.OK;
        }
    }

    private static void initialise() {
        String greeting = "Hello! I'm Duke";
        String assist = "What can I do for you?";
        System.out.println(greeting);
        System.out.println(assist);
    }

    private enum inputHandleStatus {
        OK, END, ERROR
    }

    static private class Task {

        private String title = "";

        private Task() {
            // prevent uninitialised task
        }

        public Task(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String toString() {
            return this.title;
        }
    }

    private static void finalise() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    public static void main(String[] args) {
        initialise();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
	        switch (handleOneInputLine(sc.nextLine())) {
                case END:
                    finalise();
                    return;
            }
	    }
	    finalise();
    }
}
