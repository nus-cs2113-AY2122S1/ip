package duke;
import java.io.IOException;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        showWelcomeMessage();
        TaskManager tasksList = new TaskManager();
        while (true) {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            userInput = checkIfIsEmpty(in, userInput);
            lineSeparator();
            if (userInput.equals("bye")) {
                Init.bye();
                break;
            } else if (userInput.equals("list")) {
                tasksList.listTasks();
            } else if (userInput.startsWith("done")) {
                tasksList.tasksDone(userInput);
            }
            //it is a task then
            else {
                Task task = convertInputToTask(userInput);
                // if user inputs a task that is added before
                if (tasksList.searchTask(task)) {
                    System.out.println("Sorry, you have already added this task, please change one!");
                    continue;
                }
                tasksList.addTask(task);
            }
            lineSeparator();
        }
    }


    /**
     * Wait for the user to input again if he inputs nothing
     */
    private static String checkIfIsEmpty(Scanner in, String userInput) {
        // if user inputs nothing
        if (userInput.isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }


    /**
     * Show the "Duke" logo and some greeting-message
     */
    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Init.greet();
        //echo & exit
    }

    private static void lineSeparator() {
        System.out.println("-*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*--*-*-*-*-");
    }

    /**
     * Convert the string that user inputs to a specific task object
     * Categorize them into todos, deadlines, and events
     */
    public static Task convertInputToTask(String userInput) {
        try {
            if (userInput.startsWith("deadline")) {
                try{
                    if (userInput.contains("/by")){
                        return new DeadLine(userInput);
                    } else {
                        throw new InvalidDeadlineTimeException();
                    }
                } catch (InvalidDeadlineTimeException e) {
                    e.getMessage();
                }
            }
            if (userInput.startsWith("event")) {
                try {
                    if (userInput.contains("/at")){
                        return new Event(userInput);
                    } else {
                        throw new InvalidEventTimeException();
                    }
                } catch (InvalidEventTimeException e) {
                    e.getMessage();
                }
            }
            if (userInput.startsWith("todo")) {
                return new ToDo(userInput);
            } else {
                throw new IrregularInputException();
            }
        } catch (IrregularInputException e) {
            e.getMessage();
            return new Task(userInput);
        }
    }
}