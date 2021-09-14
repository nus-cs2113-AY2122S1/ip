package duke;

import duke.command.EmptyCommand;
import duke.command.IllegalToDoException;
import duke.command.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LINE = "\t____________________________________________________________\n\t";
    public static final String BYE = LINE
            + "Bye. try not to procrastinate!\n"
            + LINE;
    public static final String GREETING = LINE
            + "Hello! I'm Anderson\n\t"
            + "What do you need to do?\n"
            + LINE;
/*
    public static ArrayList<String> FilterNulls(ArrayList<String> tasks) {
        ArrayList<String> isFilteredNull = new ArrayList<String>();
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (tasks[i] != null) {
                isFilteredNull[count] = tasks[i];
                count++;
            }
        }
        return Arrays.copyOf(isFilteredNull, count);
    }*/

    public static String getCommand(String userInput) {
        if (userInput.length() > 2) {
            String[] isolateCommand = userInput.split(" ");
            return isolateCommand[0];
        }
        return userInput;
    }

    public static String GetItem(String userInput) throws IllegalToDoException, InvalidCommandException, EmptyCommand {
        String item = "";
        String command = getCommand(userInput);
        if (isEvent(command)) {
            item = notToDoItem(userInput);
        } else if (isDeadline(command)) {
            item = notToDoItem(userInput);
        } else if (isInvalidCommand(command)) {
            throw new InvalidCommandException();
        } else if (userInput.length() > 3) {
            item = getRequiredSubstring(userInput, " ", 1);
            if (item.trim().equals("") || item.equalsIgnoreCase("todo")) {
                throw new IllegalToDoException();
            }
        } else if (isEmpty(command)){
            throw new EmptyCommand();
        }
        return item;
    }

    private static String getRequiredSubstring(String userInput, String s, int i) {
        return userInput.substring(userInput.indexOf(s) + i);
    }

    private static String notToDoItem(String userInput) {
        return userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/"));
    }

    public static String getTime(String userInput) {
        String time;
        String command = getCommand(userInput);
        if (isDeadline(command)) {
            time = getRequiredSubstring(userInput, "/", 3);
        } else if (isEvent(command)) {
            time = getRequiredSubstring(userInput, "/", 3);
        } else {
            return "";
        }
        return time;
    }

    public static void main(String[] args) {

        ArrayList<Task> unfilteredTasks = new ArrayList<Task>();
        int unfilteredCounter = 0;

        printGreetings();

        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        boolean closeDuke;

        do {

            String command = getCommand(userInput);
            try {
                String newTask = GetItem(userInput);
                String time = getTime(userInput);

                doDone(command, unfilteredTasks, userInput);
                doList(command, unfilteredTasks);
                unfilteredTasks = doDelete(command,unfilteredTasks, userInput);

                unfilteredTasks = addNewTask(command, unfilteredTasks, newTask, time);
                AcknowledgeAddition(command, unfilteredTasks, unfilteredCounter);
                unfilteredCounter = (isInvalidCommand(command) || isList(command) || isDone(command) ||isBye(command)) ? unfilteredCounter : (isDelete(command)) ? unfilteredCounter - 1 : unfilteredCounter + 1;

            } catch (IllegalToDoException e) {
                IllegalToDoException.printMessage();
            } catch (EmptyCommand e){
                EmptyCommand.printMessage();
            }catch (InvalidCommandException e) {
                InvalidCommandException.printMessage();
            }


            if (!isBye(command)) {
                userInput = in.nextLine();
            }
            doBye(command);
            closeDuke = isBye(command);
        } while (!closeDuke);
    }

    private static ArrayList<Task> addNewTask(String command, ArrayList<Task> allTasks, String newTask, String time){
        if (isToDo(command)){
            allTasks.add(new ToDo(newTask));
        } else if (isDeadline(command)){
            allTasks.add(new Deadline(newTask, time));
        } else if (isEvent(command)){
            allTasks.add(new Event(newTask, time));
        }
        return allTasks;
    }

    private static void printGreetings() {
        System.out.println("\tHello from\n" + LOGO);
        System.out.println(GREETING);
    }

    private static boolean isToDo(String command) {
        return command.equalsIgnoreCase("todo");
    }

    private static boolean isBye(String command) {
        return command.equalsIgnoreCase("bye");
    }

    private static boolean isDeadline(String command) {
        return command.equalsIgnoreCase("deadline");
    }

    private static boolean isEvent(String command) {
        return command.equalsIgnoreCase("event");
    }

    private static boolean isList(String userInput) {
        return userInput.equalsIgnoreCase("list");
    }

    private static boolean isDone(String command) {
        return command.equalsIgnoreCase("done");
    }

    private static boolean isDelete(String command) {
        return command.equalsIgnoreCase("delete");
    }

    private static boolean isEmpty(String userInput) {
        return userInput.trim().equals("");
    }

    private static boolean isInvalidCommand(String command) {
        return !isEvent(command) && !isDeadline(command) && !isToDo(command) && !isList(command) && !isBye(command) && !isDone(command) && !isDelete(command);
    }

    private static void doDone(String command, ArrayList<Task> fullTaskList, String userIn) {
        if (isDone(command)) {
            markedDoneMessage(fullTaskList, userIn);
        }
    }

    private static ArrayList<Task> doDelete(String command, ArrayList<Task> fullTaskList, String userIn){
        if (isDelete(command)) {
                System.out.println(LINE + "Noted! I've removed this task:");
                int taskNumber = 0;
                try {
                    taskNumber = Integer.parseInt(GetItem(userIn));
                } catch (IllegalToDoException e) {
                    System.out.println("Invalid ToDo");
                } catch (InvalidCommandException e) {
                    System.out.println("Invalid Command");
                } catch (EmptyCommand e) {
                    System.out.println("Empty Command");
                }
                //System.out.println("\t\t" + fullTaskList.get(taskNumber - 1) + "\n" + LINE);
                System.out.println(String.format("\t%d.", taskNumber) + fullTaskList.get(taskNumber - 1) + "\n" + String.format("\tNow you have %d tasks in the list.\n", fullTaskList.size() - 1) + LINE);
                fullTaskList.remove(taskNumber - 1);
        }
        return fullTaskList;
    }
    private static void doList(String command, ArrayList<Task> fullTaskList) {
        //Task[] filteredNull = FilterNulls(fullTaskList);
        int count = 0;
        if (isList(command)) {
            if (fullTaskList.size() == 0) {
                System.out.println(LINE + "\tYou had no task to begin with\n" + LINE);
            } else {
                System.out.println(LINE + "Here are the tasks in your list:\n");
                for (Task task : fullTaskList) {
                    count++;
                    System.out.println("\t" + count + "." + task);
                }
                System.out.println(LINE);
            }
        }
    }

    private static void doBye(String command) {
        if (isBye(command)) {
            System.out.println(BYE);
        }
    }

    private static void markedDoneMessage(ArrayList<Task> unfilteredTasks, String userInput) {
        System.out.println(LINE + "Nice! I've marked this task as done:");
        int taskNumber = 0;
        try {
            taskNumber = Integer.parseInt(GetItem(userInput));
        } catch (IllegalToDoException e) {
            System.out.println("Invalid ToDo");
        } catch (InvalidCommandException e) {
            System.out.println("Invalid Command");
        } catch (EmptyCommand e) {
            System.out.println("Empty Command");
        }
        Task completedTask = unfilteredTasks.get(taskNumber - 1);
        completedTask.markAsDone();
        System.out.println("\t\t" + completedTask + "\n" + LINE);
    }

    private static void AcknowledgeAddition(String command, ArrayList<Task> unfilteredTasks, int unfilteredCounter) {
        if (isToDo(command) || isDeadline(command) || isEvent(command)) {
            System.out.println(LINE + "Got it. I've added this task:\t");
            System.out.println(String.format("\t%d.", unfilteredCounter + 1) + unfilteredTasks.get(unfilteredCounter) + "\n" + String.format("\tNow you have %d tasks in the list.\n", unfilteredCounter + 1) + LINE);
        }
    }
}
