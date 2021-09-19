package duke.command;

import Type.task.Deadline;
import Type.task.Event;
import Type.task.Mascot;
import Type.task.Task;
import Type.task.Todo;
import duke.exception.InputCheckAndPrint;
import duke.security.AccountDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String EVENT_DIVIDER = " /at ";
    public static final String DEADLINE_DIVIDER = " /by ";

    public static void printLine() {
        System.out.println("                 ...                 ");
    }

    public static void sayHi(String username) {
        printLine();
        System.out.println("Hello " + username + "!" + "\n" + "I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you  again soon!");
        printLine();
    }

    public static String readInputEchoCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        System.out.println("    " + command);
        return command;
    }

    public static String printTask(Task item) {
        if (item instanceof Deadline) {
            return " (by: " + ((Deadline) item).getByWhen() + ") ";
        } else if (item instanceof Event) {
            return " (at: " + ((Event) item).getAtWhen() + ") ";
        }
        return "";
    }

    public static void listTasks(ArrayList<Task> items) {
        int in = 1;
        System.out.println(" /          / ");
        for (Task item : items) {
            if (item != null) {
                String tick = (item.isDone()) ? "✓" : " ";
                System.out.println(in + ". " + "[" + item.getType() + "] " + "[" + tick + "]" + " " + item.getDescription() + printTask(item));
                in++;
            }
        }
        System.out.println(" /          / ");
    }
    
    public static void printDone(String task) {
        System.out.println(task + " completed");
    }

    public static void runDuke() throws IOException {
        Scanner in = new Scanner(System.in);
        StoreData saveTool = new StoreData();
        setupUsernamePassword(in);
        sayHi(AccountDetail.getUsername());
        String command;
        ArrayList<Task> taskList = StoreData.readList("data/list.txt");
        do {
            command = in.nextLine();
            switch (command.toLowerCase()) {
            case ("list"):
                listTasks(taskList);
                printDone("list");
                break;
            case ("add"):
                addTaskToList(in, taskList);
                printDone("add");
                break;
            case ("done"):
                markTasksAsDone(in, taskList);
                printDone("mark task as done");
                break;
            case ("clear"):
                clearTaskList(taskList);
                printDone("clear list");
                break;
            case ("mascot"):
                mascotSay(in);
                printDone("mascot say");
                break;
            case("echo"):
                readInputEchoCommand();
                printDone("echo");
                break;
            case("deadline"):
                amendTaskDeadline(in, taskList);
                printDone("amend deadline");
                break;
            case("delete"):
                deleteTasks(in, taskList);
                printDone("delete tasks");
                break;
            case ("bye"):
                break;
            default:
                System.out.println("Command not recognized. Please enter a command again!");
            }
        } while (!command.equals("bye"));
        sayGoodbye();
        StoreData.saveList(taskList);
    }

    private static void deleteTasks(Scanner in, ArrayList<Task> taskList) {
        //1. collect data
        List<Integer> toDeleteList = new ArrayList<Integer>();
        String input = in.nextLine();
        String[] inputData = input.split(" ");
        int sData = 0;
        try {
            for (String s : inputData) {
                sData = Integer.parseInt(s) - 1;
                toDeleteList.add(sData);
            }
        } catch (NumberFormatException e) {
            InputCheckAndPrint.printIntegerOnly();
            return;
        }
        //2. sort in decreasing order
        toDeleteList.sort(Collections.reverseOrder());
        //3. remove from list
        try {
            for (int i : toDeleteList) {
                System.out.println("remove " + (i + 1) + ": " + taskList.get(i).getDescription());
                taskList.remove(i);
            }
        } catch (IndexOutOfBoundsException e) {
            InputCheckAndPrint.printNotInRange(sData);
        }
    }

    private static void mascotSay(Scanner in) {
        Mascot jim = new Mascot();
        String text = in.nextLine();
        Mascot.penguinSay(text);
    }

    private static void setupUsernamePassword(Scanner in) {
        AccountDetail user = new AccountDetail();
        System.out.print("Username [dukeBot]: ");
        AccountDetail.setUsername(in.nextLine());
        System.out.print("Password [bukeDot]: ");
        AccountDetail.setPassword(in.nextLine());
    }

    private static void clearTaskList(ArrayList<Task> taskList) {
        taskList.clear();
    }

    protected static void markTasksAsDone(Scanner in, ArrayList<Task> taskList) {
        try {
            String userInputString = in.nextLine();
            userInputString = userInputString.trim();
            String[] numberList = userInputString.split(" ");
            System.out.print("done ");
            for (String i : numberList) {
                int index = Integer.parseInt(i) - 1;
                if (index >= taskList.size()) {
                    InputCheckAndPrint.printNotInRange(index);
                    break;
                }
                taskList.get(index).setDone(true);
                System.out.print(taskList.get(index).getDescription() + ", ");
            }
            System.out.println("\n / done tasks, good job! / ");
        } catch (NumberFormatException e) {
            InputCheckAndPrint.printIntegerOnly();
        } catch (NullPointerException e) {
            InputCheckAndPrint.printNoNull();
        }
    }

    protected static Task parseInputAsTask(String userInput) {
        if (inputIsEvent(userInput)) {
            Event eventToAdd = parseInputToEvent(userInput);
            return eventToAdd;
        } else if (inputIsDeadline(userInput)) {
            Deadline deadlineToAdd = parseInputToDeadline(userInput);
            return deadlineToAdd;
        } else {
            Todo todoToAdd = new Todo(userInput);
            return todoToAdd;
        }
    }

    protected static String[] splitInput(String userInput, String divider) {
            return userInput.split(divider);
    }

    protected static Event parseInputToEvent(String userInput) {
        String[] eventData = splitInput(userInput, EVENT_DIVIDER);
        return new Event(eventData[0], eventData[1]);
    }

    protected static Deadline parseInputToDeadline(String userInput) {
        String[] deadlineData = splitInput(userInput, DEADLINE_DIVIDER);
        return new Deadline(deadlineData[0], deadlineData[1]);
    }

    protected static boolean inputIsDeadline(String userInput) {
        return userInput.toLowerCase().contains(DEADLINE_DIVIDER);
    }

    protected static boolean inputIsEvent(String userInput) {
        return userInput.toLowerCase().contains(EVENT_DIVIDER);
    }

    protected static void addTaskToList(Scanner in, ArrayList<Task> taskList) {
        String userInput;
        do {
            userInput = in.nextLine();
        } while (!userStopAdd(taskList, userInput));
        System.out.println("Finished adding tasks!");
    }

    private static boolean userStopAdd(ArrayList<Task> taskList, String userInput) {
        if (!userInput.equals("stop")) {
            Task taskToAdd = parseInputAsTask(userInput);
            taskList.add(taskToAdd);
        } else {
            return true;
        }
        return false;
    }

    private static void amendTaskDeadline(Scanner in, ArrayList<Task> taskList) {

    }

    public static void main(String[] args) throws IOException {
        Duke bot = new Duke();
        runDuke();
    }

}
