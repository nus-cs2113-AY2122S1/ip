import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Duke {
    /* Task Counter */
    public static int numberOfTasks = 0;

    /* This is an array of task & Total task quantity should not exceed 100! */
    public static Task[] tasks = new Task[100];

    /**
     * Prints a "Hi" message when the user first initialize the chat bot.
     */
    public static void printHelloText() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");

        printParrotText();

        System.out.println(" Hello! I'm Parrot Boi\n" +
                " What can I not do for you (I'm Lazy)? c(＞ω＜)ゞ");
        System.out.println(" I'm currently in parrot mode! ");
    }

    /**
     * Prints a "bye" message when the user input "bye" into the chat bot.
     */
    public static void printByeText() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to not see you again soon!ヾ(´￢｀)ﾉ");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads a .txt file in ASCII format and then print every single character (converted
     * ASCII index to characters) inside that .txt file onto the terminal. It also looks for
     * exception and print it out if any.
     *
     * @throws IOException Trying to read the .txt file which user do not have permission or a
     *                     .txt file that does not exist.
     */
    public static void printParrotText() {
        try {
            /**
             * Note that the text file must be placed at the base java directory,
             * and the name of the text file is placed here!
             */
            FileReader readParrot = new FileReader("text-art/ParrotText.txt");

            int singleCharacters;

            while ((singleCharacters = readParrot.read()) != -1) {
                // Print each character individually.
                System.out.print((char) singleCharacters);
            }
            readParrot.close();
        } catch (IOException except) {
            except.printStackTrace();
        }

        System.out.println("");
    }

    /**
     * Adds a new task with the task name provided.
     */
    public static void printTaskList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print("     ");
            System.out.print((i + 1) + ".");
            tasks[i].printStatus();
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Adds a new task with the task name provided.
     *
     * @param taskName Name of the new task.
     */
    public static void addToDo(String taskName) {
        tasks[numberOfTasks] = new ToDo(taskName);
        numberOfTasks += 1;
    }

    public static void addDeadline(String taskName, String by) {
        tasks[numberOfTasks] = new Deadline(taskName, by);
        numberOfTasks += 1;
    }

    public static void addEvent(String taskName, String by) {
        tasks[numberOfTasks] = new Event(taskName, by);
        numberOfTasks += 1;
    }

    /**
     * Marks a certain task based on task number as finished.
     *
     * @param taskNumber X coordinate of position..
     */
    public static void finishTask(int taskNumber) {
        if (taskNumber <= numberOfTasks) {
            tasks[taskNumber - 1].markAsDone();
        } else {
            System.out.println("    Please Enter the Legit Task Number... (ง'̀-'́)ง");
        }
    }

    //public static void finishTask(int taskNumber) {

    /**
     * Main method of the chat bot app.
     */
    public static void main(String[] args) {
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        printHelloText();

        while (true) {
            userInputString = userInput.nextLine();

            if (userInputString.equals("bye")) {
                break;
            } else if (userInputString.equals("list")) {
                printTaskList();
                continue;
            } else if (userInputString.split(" ")[0].equals("done")) {
                finishTask(Integer.parseInt(userInputString.split(" ")[1]));
            } else if (userInputString.split(" ")[0].equals("todo")) {
                addToDo(userInputString.split(" ")[1]);
                tasks[numberOfTasks - 1].printAddingStatus(numberOfTasks - 1);
            } else if (userInputString.split(" ")[0].equals("deadline")) {
                addDeadline(userInputString.substring(9).split("/")[0], userInputString.substring(9).split("/")[1]);
                tasks[numberOfTasks - 1].printAddingStatus(numberOfTasks - 1);
            } else if (userInputString.split(" ")[0].equals("event")) {
                addEvent(userInputString.substring(7).split("/")[0], userInputString.substring(7).split("/")[1]);
                tasks[numberOfTasks - 1].printAddingStatus(numberOfTasks - 1);
            } else {
                System.out.println("    Enter something legit please!");
            }
        }

        printByeText();
    }
}
