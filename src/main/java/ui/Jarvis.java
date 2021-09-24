package ui;

import exceptions.DeadlineException;
import exceptions.EventException;
import tasklist.Task;
import tasklist.Todo;
import tasklist.Deadline;
import tasklist.Event;
import storage.Storage;

import java.util.Scanner;
import java.util.ArrayList;

public class Jarvis {
    public static final String JARVIS_LOGO = "       __  ___      ____ _    __  ____ _____\n"
            + "      / / /   |    / __ \\ |  / / /  _// ___/\n"
            + " __  / / / /| |   / /_/ / | / /  / /  \\__ \\ \n"
            + "/ /_/ / / ___ |_ / _, _/| |/ / _/ /_ ___/ / \n"
            + "\\____(_)_/  |_(_)_/ |_(_)___(_)___(_)____/  \n";
    public static final String LINE_W_NL = "____________________________________________________________\n";
    public static final String LINE = "____________________________________________________________";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    public static void main(String[] args) {
        printGreetMessage();
        ArrayList<Task> taskList = new ArrayList<>();
        Storage.findTaskFile(taskList);
        Scanner scanner = new Scanner(System.in);
        String userLine;
        boolean isOn = true;
        while (isOn) {
            userLine = scanner.nextLine().trim();
            if (userLine.toLowerCase().equals(COMMAND_BYE)) {
                isOn = false;
            }
            handleUserLine(userLine, taskList);
        }
    }

    private static void handleUserLine(String userLine, ArrayList<Task> taskList) {
        String taskKeyword = extractTask(userLine);
        try {
            switch (taskKeyword) {
            case COMMAND_LIST:
                listTasks(taskList);
                break;
            case COMMAND_DONE:
                markTaskDone(userLine, taskList);
                break;
            case COMMAND_TODO:
                addTodoTask(userLine, taskList);
                printAddedTask(taskList.get(taskList.size()-1), taskList);
                break;
            case COMMAND_DEADLINE:
                addDeadlineTask(userLine, taskList);
                printAddedTask(taskList.get(taskList.size()-1), taskList);
                break;
            case COMMAND_EVENT:
                addEventTask(userLine, taskList);
                printAddedTask(taskList.get(taskList.size()-1), taskList);
                break;
            case COMMAND_DELETE:
                deleteTask(userLine, taskList);
                break;
            case COMMAND_BYE:
                printByeMessage();
                break;
            default:
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(LINE_W_NL
                    + "Apologies Sir, I don't quite get what you mean.\n"
                    + "Try a new command!\n"
                    + LINE);
        } catch (NumberFormatException e) {
            System.out.println(LINE_W_NL
                    + "Apologies Sir, your input is missing something.\n"
                    + "Try a new command!\n"
                    + LINE);
        } catch (ArithmeticException e) {
            if (taskList.size() == 0) {
                System.out.println(LINE_W_NL
                        + "Apologies Sir, your list is currently empty.\n"
                        + LINE);
            } else {
                System.out.println(LINE_W_NL
                        + "Apologies Sir, you picked an out of range number.\n"
                        + "Please choose a positive integer until " + taskList.size() + "!\n"
                        + LINE);
            }
        } catch (DeadlineException e) {
            System.out.println(LINE_W_NL
                    + "Apologies Sir, you did not mention the deadline.\n"
                    + "Please include the deadline in your command!\n"
                    + LINE);
        } catch (EventException e) {
            System.out.println(LINE_W_NL
                    + "Apologies Sir, you did not mention the venue.\n"
                    + "Please include the venue in your command!\n"
                    + LINE);
        }
    }

    public static void listTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            throw new ArithmeticException();
        } else {
            System.out.println(LINE_W_NL + "Here are the tasks in your list Sir:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    public static void markTaskDone(String userLine, ArrayList<Task> taskList) {
        String[] extractDoneTask = userLine.toLowerCase().split(" ", 2);
        int taskNum = Integer.parseInt(extractDoneTask[1]);
        if (taskNum < 0 || taskNum > taskList.size()) {
            throw new ArithmeticException();
        }
        taskList.get(taskNum-1).markAsDone();
        System.out.println(LINE_W_NL
                + "Good one Sir! I've marked this task as done:\n"
                + " " + taskList.get(taskNum-1).toString() + "\n"
                + LINE);
        Storage.fillJarvisFile(taskList);
    }

    public static void deleteTask(String userLine, ArrayList<Task> taskList) {
        String[] extractDeleteTask = userLine.toLowerCase().split(" ", 2);
        int taskNum = Integer.parseInt(extractDeleteTask[1]);
        if (taskNum < 0 || taskNum > taskList.size()) {
            throw new ArithmeticException();
        }
        System.out.println(LINE_W_NL
                + "Noted Sir! I've removed this task:\n"
                + " " + taskList.get(taskNum-1).toString() + "\n"
                + "You now have " + (taskList.size()-1) + " tasks in the list Sir!\n"
                + LINE);
        taskList.remove(taskNum-1);
        Storage.fillJarvisFile(taskList);
    }

    public static void addTodoTask(String userLine, ArrayList<Task> taskList) {
        String[] todoInputs = userLine.split(" ", 2);
        if (todoInputs.length < 2) {
            throw new NumberFormatException();
        }
        String description = todoInputs[1];
        Todo t = new Todo(description);
        taskList.add(t);
        Storage.fillJarvisFile(taskList);
    }

    public static void addDeadlineTask(String userLine, ArrayList<Task> taskList) throws DeadlineException {
        String[] deadlineInputs = userLine.split(" ", 2);
        if (deadlineInputs.length < 2) {
            throw new NumberFormatException();
        }
        if (!userLine.contains("/by")) {
            throw new DeadlineException();
        }
        String[] deadlineDescriptions = deadlineInputs[1].split(" /by ");
        String description = deadlineDescriptions[0];
        String by = deadlineDescriptions[1];
        Deadline t = new Deadline(description, by);
        taskList.add(t);
        Storage.fillJarvisFile(taskList);
    }

    public static void addEventTask(String userLine, ArrayList<Task> taskList) throws EventException {
        String[] eventInputs = userLine.split(" ", 2);
        if (eventInputs.length < 2) {
            throw new NumberFormatException();
        }
        if (!userLine.contains("/at")) {
            throw new EventException();
        }
        String[] eventDescriptions = eventInputs[1].split(" /at ");
        String description = eventDescriptions[0];
        String at = eventDescriptions[1];
        Event t = new Event(description, at);
        taskList.add(t);
        Storage.fillJarvisFile(taskList);
    }

    public static void addTodoFileTask(String description, ArrayList<Task> taskList) {
        Todo t = new Todo(description);
        taskList.add(t);
    }

    public static void addDeadlineFileTask(String description, String by, ArrayList<Task> taskList) {
        Deadline t = new Deadline(description, by);
        taskList.add(t);
    }

    public static void addEventFileTask(String description, String at, ArrayList<Task> taskList) {
        Event t = new Event(description, at);
        taskList.add(t);
    }

    private static void printAddedTask(Task task, ArrayList<Task> taskList) {
        System.out.println(LINE_W_NL
                + "Noted Sir. I've added this task:\n"
                + " " + task.toString() + "\n"
                + "You currently have " + taskList.size() + " tasks in your list Sir.\n"
                + LINE);
    }

    private static String extractTask(String userLine) {
        String[] splitString = userLine.toLowerCase().split(" ",2);
        return splitString[0];
    }

    private static void printGreetMessage() {
        System.out.println(JARVIS_LOGO
                + LINE_W_NL
                + "Good day Sir! J.A.R.V.I.S. here!\n"
                + LINE);
    }

    private static void printByeMessage() {
        System.out.println(LINE_W_NL
                + "As always Sir, a great pleasure watching you work!\n"
                + LINE);
    }
}
