import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();

    private static inputHandleStatus handleOneInputLine(String line) {
        String[] splitted = line.split("\\s+");
        switch (splitted[0]) {
            case "bye":
                return inputHandleStatus.END;
            case "list":
                for (int i = 1; i <= taskList.size(); i += 1) {
                    System.out.println(i + ": " + taskList.get(i - 1));
                }
                return inputHandleStatus.OK;
            case "done":
                Task target = taskList.get(Integer.parseInt(splitted[1]) - 1);
                target.setDoneStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(target);
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
        private boolean doneStatus = false;

        private Task() {
            // prevent uninitialised task
        }

        public Task(String title) {
            this.doneStatus = false;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDoneStatus(boolean status) {
            this.doneStatus = status;
        }

        public String getStatusIcon() {
            return this.doneStatus ? "x" : " ";
        }

        public String toString() {
            return '[' + this.getStatusIcon() + ']' + ' ' + this.title;
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
