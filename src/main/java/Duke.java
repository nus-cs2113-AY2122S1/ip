import java.util.Scanner;

class Task {
    private boolean isDone;
    private String taskName;

    public Task(boolean isDone, String taskName) {
        this.isDone = isDone;
        this.taskName = taskName;
    }

    public Task() {
        this(false, "Nothing");
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}

public class Duke {
    public static void main(String[] args) {
        // list to store all tasks
        Task[] tasks = new Task[100];
        int tasksCounter = 0;
        String friday = "  __      _     _             \n"
                + " / _|    (_)   | |            \n"
                + "| |_ _ __ _  __| | __ _ _   _ \n"
                + "|  _| '__| |/ _` |/ _` | | | |\n"
                + "| | | |  | | (_| | (_| | |_| |\n"
                + "|_| |_|  |_|\\__,_|\\__,_|\\__, |\n"
                + "                         __/ |\n"
                + "                        |___/ \n";
        String dashes = "____________________________________________________________\n";

        // greet user
        System.out.print(dashes);
        System.out.println(friday);
        System.out.println("Initiating FRIDAY");
        System.out.println("Hello Mr Stark, how may I be of assistance to you today");
        System.out.print(dashes);

        // Scan in user input of tasks
        String userInput = "";
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            // if user lists
            if (userInput.equals("list")) {
                System.out.print(dashes);
                System.out.println("Listing all tasks for today.");
                for (Task task : tasks) {
                    if (task == null) {
                        break;
                    }
                    System.out.print(">");
                    String checkbox = "[ ]";
                    if (task.isDone()) {
                        checkbox = "[X]";
                    }
                    System.out.println(checkbox + task.getTaskName());
                }
                System.out.print(dashes);
                continue;
            }
            // if user wants to mark as done
            if (userInput.startsWith("done")) {
                // get index of task to change
                int taskIndex = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)) - 1;
                // if task out of bounds
                if (taskIndex < 0 || taskIndex > 99) {
                    System.out.println(dashes);
                    System.out.println("Apologies sir, there is no such task in your list.");
                    System.out.println(dashes);
                    continue;
                }
                // change task to done
                Task currTask = tasks[taskIndex];
                if (currTask == null) {
                    System.out.println(dashes);
                    System.out.println("Apologies sir, there are currently no tasks in your list");
                    System.out.println(dashes);
                    continue;
                }
                currTask.setDone(true);
                System.out.println(dashes);
                System.out.println("Your task \"" + currTask.getTaskName() + "\" is indicated as complete.");
                System.out.println("[X]" + currTask.getTaskName());
                System.out.println(dashes);
                continue;
            }
            // add to list
            Task newTask = new Task(false, userInput);
            tasks[tasksCounter++] = newTask;
            System.out.print(dashes);
            System.out.println("Very well, adding task \"" + userInput + "\"");
            System.out.print(dashes);
        }

        String exit = "____________________________________________________________\n"
                + "Powering Off now. Good Bye Mr Stark.\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }
}
