import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();

    private static Task parseTask(String[] args) {
        String description;
        Task newTask = new Task();
        switch (args[0]) {
            case "deadline":
                int byPosition = Arrays.asList(args).indexOf("/by");
                // TODO if not found
                description = String.join(" ", Arrays.copyOfRange(args, 1, byPosition));
                String by = String.join(" ", Arrays.copyOfRange(args, byPosition + 1, args.length));
                newTask = new Deadline(description, by);
                break;
            case "event":
                int atPosition = Arrays.asList(args).indexOf("/at");
                description = String.join(" ", Arrays.copyOfRange(args, 1, atPosition));
                String at = String.join(" ", Arrays.copyOfRange(args, atPosition + 1, args.length));
                newTask = new Event(description, at);
                break;
            case "todo":
                description = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                newTask = new Todo(description);
                break;
        }
        return newTask;
    }

    private static InputHandleStatus handleOneInputLine(String line) {
        String[] splitted = line.split("\\s+");
        switch (splitted[0]) {
            case "bye":
                return InputHandleStatus.END;
            case "list":
                for (int i = 1; i <= taskList.size(); i += 1) {
                    System.out.println(i + ": " + taskList.get(i - 1));
                }
                return InputHandleStatus.OK;
            case "done":
                Task target = taskList.get(Integer.parseInt(splitted[1]) - 1);
                target.setDoneStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(target);
                return InputHandleStatus.OK;
            default:
                Task newTask = parseTask(splitted);
                taskList.add(newTask);
                System.out.println("added: " + newTask);
                return InputHandleStatus.OK;
        }
    }

    private static void initialise() {
        String greeting = "Hello! I'm Duke";
        String assist = "What can I do for you?";
        System.out.println(greeting);
        System.out.println(assist);
    }

    private enum InputHandleStatus {
        OK, END, ERROR
    }

    static class Task {

        private String title = "";
        private boolean doneStatus = false;

        protected String getType() {
            return "task";
        }

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
            return "[" + Character.toUpperCase(this.getType().charAt(0)) + ']' + '[' + this.getStatusIcon() + ']' + ' ' + this.title;
        }
    }

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }
    }

    public static class Deadline extends Task {
        String by;

        public Deadline(String description, String by) {
            super(description);
            setBy(by);
        }

        public String getBy() {
            return by;
        }

        public void setBy(String by) {
            this.by = by;
        }

        @Override
        public String toString() {
            return super.toString() + " (by: " + getBy() + ")";
        }

        @Override
        protected String getType() {
            return "deadline";
        }
    }

    public static class Event extends Task {
        String to;

        public Event(String description, String to) {
            super(description);
            setTo(to);
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        @Override
        public String toString() {
            return super.toString() + " (at: " + getTo() + ")";
        }

        @Override
        protected String getType() {
            return "event";
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
