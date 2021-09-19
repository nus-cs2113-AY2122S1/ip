package duke.command;

import duke.exception.InputCheckAndPrint;
import duke.security.AccountDetail;
import duke.task.Mascot;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final int STOP_ADD = -1;
    public static final int ADD_SUCCESS = 1;
    private static boolean isFail = false;
    public static int taskCount = 0;
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
        if (item.isToDo()) {
            return " (by: " + item.getDeadline() + ") ";
        } else if (item.isEvent()) {
            return " (at: " + item.getEventDescription() + ") ";
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

    public static boolean getIsFail() {
        return isFail;
    }

    public static void setIsFail() {
        isFail = Boolean.logicalXor(isFail, true);
    }

    public static void printDone(String task) {
        System.out.println(task + " completed");
    }

    public static void runDuke() throws IOException {
        Scanner in = new Scanner(System.in);
        if (isDukeFail()) {
            return;
        }
        StoreData saveTool = new StoreData();
        setupUsernamePassword(in);
        sayHi(AccountDetail.getUsername());
        String command;
        ArrayList<Task> taskList = StoreData.readList("data/list.txt");
        do {
            command = in.nextLine();
            switch (command) {
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
            case("todo"):
                addTodoToList(in, taskList);
                printDone("add todo");
                break;
            case("event"):
                addEventToList(in, taskList);
                printDone("add event");
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
        InputCheckAndPrint doneCheck = new InputCheckAndPrint("doneCheck");
        //1. collect data
        List<Integer> toDeleteList = new ArrayList<Integer>();
        String input = in.nextLine();
        String[] inputData = input.split(" ");
        for (String s : inputData) {
            if (doneCheck.startsWithSpace(s)) {
                doneCheck.inputFailMessage();
                doneCheck.printDoneFormat();
                return;
            }
            else if (doneCheck.isEmpty(s)) {
                doneCheck.inputFailMessage();
                doneCheck.printNoNull();
                return;
            }
            else if (!doneCheck.isIntegerInput(s)) {
                doneCheck.printIntegerOnly();
                return;
            }
            int sData = Integer.parseInt(s) - 1;
            toDeleteList.add(sData);
        }
        //2. sort in decreasing order
        Collections.sort(toDeleteList, Collections.reverseOrder());
        //3. remove from list
        for (int i : toDeleteList) {
            System.out.println("remove " + (i + 1) + ": " + taskList.get(i).getDescription());
            taskList.remove(i);
            taskCount--;
        }
    }

    private static boolean isDukeFail() {
        if (Duke.getIsFail()) {
            System.out.println("        bot failed, sometimes we all need a break       ");
            return true;
        }
        return false;
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
        taskCount = 0;
    }

    private static void markTasksAsDone(Scanner in, ArrayList<Task> taskList) {
        InputCheckAndPrint doneCheck = new InputCheckAndPrint("doneCheck");
        try {
            String userInputString = null;
            do {
                userInputString = in.nextLine();
                if (doneCheck.startsWithSpace(userInputString)) {
                    doneCheck.inputFailMessage();
                    doneCheck.printDoneFormat();
                } else if (doneCheck.isEmpty(userInputString)) {
                    doneCheck.inputFailMessage();
                    doneCheck.printNoNull();
                } else if (!doneCheck.isIntegerInput(userInputString)) {
                    doneCheck.printIntegerOnly();
                }
            } while (doneCheck.startsWithSpace(userInputString)
                    || doneCheck.isEmpty(userInputString)
                    || !doneCheck.isIntegerInput(userInputString)
                    );
            userInputString = userInputString.trim();
            String[] numberList = userInputString.split(" ");
            System.out.print("done ");
            for (String i : numberList) {
                int index = Integer.parseInt(i) - 1;
                if (index >= taskList.size()) {
                    doneCheck.printNotInRange(index);
                    break;
                }
                taskList.get(index).setDone(true);
                System.out.print(taskList.get(index).getDescription() + ", ");
            }
            System.out.println("\n / done tasks, good job! / ");
        } catch (NumberFormatException e) {
            doneCheck.printIntegerOnly();
        } catch (NullPointerException e) {
            doneCheck.printNoNull();
        }
    }

    private static int addTaskToList(Scanner in, ArrayList<Task> taskList) {
        String taskName;
        do {
            taskName = in.nextLine();
            if (taskName.equals("stop")) {
                return STOP_ADD;
            }
            taskList.add(new Task(taskName));
            taskCount++;
        } while (!taskName.equals("stop"));
        System.out.println("Finished adding tasks!");
        return ADD_SUCCESS;
    }

    private static int addTodoToList(Scanner in, ArrayList<Task> taskList) {
        String todoName;
        do {
            todoName = in.nextLine();
            if (todoName.equals("stop")) {
                return STOP_ADD;
            }
            taskList.add(new Task(todoName, "todo"));
            taskCount++;
        } while (!todoName.equals("stop"));
        System.out.println("Finished adding todos!");
        return ADD_SUCCESS;
    }

    private static int addEventToList(Scanner in, ArrayList<Task> taskList) {
        String eventName;
        do {
            eventName = in.nextLine();
            if (eventName.equals("stop")) {
                return STOP_ADD;
            }
            taskList.add(new Task(eventName, "event"));
            taskCount++;
        } while (!eventName.equals("stop"));
        System.out.println("Finished adding todos!");
        return ADD_SUCCESS;
    }

    private static void amendTaskDeadline(Scanner in, ArrayList<Task> taskList) {
        InputCheckAndPrint deadlineCheck = new InputCheckAndPrint("deadlineCheck");
        try {
            String input;
            do {
                input = getUserInputTrim(in);
                if (isIncorrectFormat(deadlineCheck, input)) {
                    return;
                } else if (isCorrectFormat(taskList, deadlineCheck, input)) {
                    return;
                }
            } while (!input.equals("stop"));
        } catch (NumberFormatException e) {
            InputCheckAndPrint.printDeadlineFormatIssue();
        } catch (NullPointerException e) {
            System.out.println("Not in range, try again!");
        }
    }

    private static boolean isCorrectFormat(ArrayList<Task> taskList, InputCheckAndPrint deadlineCheck, String input) {
        String[] separateInput = input.split("/", 2);
        if (separateInput.length == 2) {
            //checks for singular, digit input
            if (!deadlineCheck.isIntegerInput(separateInput[0])) {
                deadlineCheck.printIntegerOnly();
                deadlineCheck.printDeadlineFormatIssue();
                return true;
            }
            int index = Integer.parseInt(separateInput[0].trim()) - 1;
            if (!deadlineCheck.inListRange(index, taskCount)) {
                deadlineCheck.printNotInRange(index);
                return true;
            }
            Task toChange = taskList.get(index);
            toChange.setDeadline(separateInput[1]);
            toChange.setToDo(true);
        }
        return false;
    }

    private static boolean isIncorrectFormat(InputCheckAndPrint deadlineCheck, String input) {
        if (input.startsWith("stop")) {
            return true;
        }
        if (!input.contains("/")) {
            deadlineCheck.printDeadlineFormatIssue();
            return true;
        }
        return false;
    }

    private static String getUserInputTrim(Scanner in) {
        String input;
        input = in.nextLine();
        input = input.trim();
        return input;
    }

    public static void main(String[] args) throws IOException {
        Duke bot = new Duke();
        runDuke();
    }

}
