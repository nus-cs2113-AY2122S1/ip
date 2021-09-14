package duke;

import java.util.Scanner;

import duke.exception.LargeTaskNumberException;
import duke.exception.NoSpaceException;
import duke.exception.NoTimeException;
import duke.exception.NoDescriptionException;
import duke.exception.LargeTaskNumberException;
import duke.list.Task;
import duke.list.ToDo;
import duke.list.Deadline;
import duke.list.Event;

public class Duke {
    public static final String SEPARATE_LINE = "____________________________________________________________";
    public static final String INSTRUCTION = "Here are what I can do:\n"
            + "echo description: I will repeat your description\n"
            + "todo description: I will add a task with no time constraint to your task list\n"
            + "deadline description /by time: I will add a task with deadline to your task list\n"
            + "event description /at time: I will add a task with its time to your task list\n"
            + "done taskNumber: I will mark this task as done\n"
            + "list: I will show your task list\n"
            + "help: view the commands you can use\n"
            + "bye: finish using Duke";
    
    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(SEPARATE_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(INSTRUCTION);
        System.out.println(SEPARATE_LINE);
    }

    public static void printBye() {
        System.out.println(SEPARATE_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATE_LINE);
    }

    public static void checkEcho(String command) throws NoDescriptionException, NoSpaceException{
        if (command.equals("echo") || command.equals("echo ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("echo ")) {
            throw new NoSpaceException();
        }
    }

    public static void handleEcho(String command) {
        boolean proceed = true;
        System.out.println(SEPARATE_LINE);
        try {
            checkEcho(command);
        } catch (NoDescriptionException e) {
            System.out.println("Sorry, I have no idea what you want me to echo");
            proceed = false;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            proceed = false;
        }

        if (proceed) {
            System.out.println(command.substring(5));
        }
        System.out.println(SEPARATE_LINE);
    }

    public static void checkToDo(String command) throws NoDescriptionException, NoSpaceException{
        if (command.equals("todo") || command.equals("todo ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("todo ")) {
            throw new NoSpaceException();
        }
    }

    public static boolean proceedTodo(String command) {
        try {
            checkToDo(command);
        } catch (NoDescriptionException e) {
            System.out.println("What task do you want to add?");
            return false;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            return false;
        }
        return true;
    }

    public static void printToDo(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void checkDeadline(String command) throws NoDescriptionException, NoTimeException, NoSpaceException{
        int slashIndex = command.indexOf("/");
        if (command.equals("deadline") || command.equals("deadline ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("deadline ")) {
            throw new NoSpaceException();
        } else if (!command.contains("/")) {
            throw new NoTimeException();
        }  else if (slashIndex == 9) {
            throw new NoDescriptionException();
        }  else if (command.charAt(slashIndex - 1) != ' ') {
            throw new NoSpaceException();
        } else if ((command.length() == slashIndex + 3) || (command.length() == slashIndex + 4)) {
            throw new NoTimeException();
        } else if (command.charAt(slashIndex + 3) != ' ') {
            throw new NoSpaceException();
        }
    }

    public static boolean proceedDeadline(String command) {
        try {
            checkDeadline(command);
        } catch (NoDescriptionException e) {
            System.out.println("What task do you want to add?");
            return false;
        } catch (NoTimeException e) {
            System.out.println("When is the deadline?");
            return false;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            return false;
        }
        return true;
    }

    public static Deadline setupDeadline(int size, String command) {
        int slashIndex = command.indexOf("/");
        int startIndex = command.indexOf(" ");
        String description = command.substring(startIndex+1, slashIndex-1);
        String by = command.substring(slashIndex+4);
        return (new Deadline(description, size, by));
    }

    public static void printDeadline(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void checkEvent(String command) throws NoDescriptionException, NoTimeException, NoSpaceException{
        int slashIndex = command.indexOf("/");
        if (command.equals("event") || command.equals("event ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("event ")) {
            throw new NoSpaceException();
        } else if (!command.contains("/")) {
            throw new NoTimeException();
        } else if (slashIndex == 6) {
            throw new NoDescriptionException();
        } else if (command.charAt(slashIndex - 1) != ' ') {
            throw new NoSpaceException();
        } else if ((command.length() == slashIndex + 3) || (command.length() == slashIndex + 4)) {
            throw new NoTimeException();
        } else if (command.charAt(slashIndex + 3) != ' ') {
            throw new NoSpaceException();
        }
    }

    public static boolean proceedEvent(String command) {
        try {
            checkEvent(command);
        } catch (NoDescriptionException e) {
            System.out.println("What task do you want to add?");
            return false;
        } catch (NoTimeException e) {
            System.out.println("When is the time?");
            return false;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            return false;
        }
        return true;
    }

    public static Event setupEvent(int size, String command) {
        int slashIndex = command.indexOf("/");
        int startIndex = command.indexOf(" ");
        String description = command.substring(startIndex+1, slashIndex-1);
        String at = command.substring(slashIndex+4);
        return (new Event(description, size, at));
    }

    public static void printEvent(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void printList(Task[] tasks, int size) {
        System.out.println(SEPARATE_LINE);
        for (int i = 0; i < size; i++) {
            System.out.println(tasks[i].getStringNo() + "." + tasks[i].toString());
        }
        System.out.println(SEPARATE_LINE);
    }

    public static void setTaskDone(Task task) {
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task.toString());
    }

    public static void handleDone(String command) throws NoDescriptionException, NoSpaceException {
        if (command.equals("done") || command.equals("done ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("done ")) {
            throw new NoSpaceException();
        }
    }

    public static void printDone(Task[] tasks, String command) {
        try {
            handleDone(command);
            String taskNumber = command.substring(5);
            int number = Integer.parseInt(taskNumber);
            setTaskDone(tasks[number-1]);
        } catch (NoDescriptionException e) {
            System.out.println("Which task do you want to mark as done?");
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
        } catch (NullPointerException e) {
            System.out.println("Sorry, this is not one of the task");
        } catch (NumberFormatException e) {
            System.out.println("Please use the number of the task");
        }
    }

    public static void handleDelete(String command) throws NoDescriptionException, NoSpaceException {
        if (command.equals("delete") || command.equals("delete ")) {
            throw new NoDescriptionException();
        } else if (!command.startsWith("delete ")) {
            throw new NoSpaceException();
        }
    }

    public static void checkNumber(int number, int size) throws LargeTaskNumberException {
        if (number > size) {
            throw new LargeTaskNumberException();
        }
    }

    public static int findIndex(int size, String command) {
        int number;
        try {
            handleDelete(command);
            String taskNumber = command.substring(7);
            number = Integer.parseInt(taskNumber);
            checkNumber(number, size);
        } catch (NoDescriptionException e) {
            System.out.println("Which task do you want to delete?");
            return -1;
        } catch (NoSpaceException e) {
            System.out.println("Please use a space to separate command, task, and time (if any)");
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Please use the number of the task");
            return -1;
        } catch (LargeTaskNumberException e) {
            System.out.println("Sorry, this is not one of the task");
            return -1;
        }
        return number-1;
    }

    public static void main(String[] args) {
        printGreeting();
        int size = 0;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                printBye();
                break;
            } else if (command.startsWith("echo")){
                handleEcho(command);
            } else if (command.startsWith("todo")) {
                System.out.println(SEPARATE_LINE);
                boolean proceed = proceedTodo(command);
                if (proceed) {
                    size++;
                    tasks[size-1] = new ToDo(command.substring(5), size);
                    printToDo(tasks[size-1], size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.startsWith("deadline")) {
                System.out.println(SEPARATE_LINE);
                boolean proceed = proceedDeadline(command);
                if (proceed) {
                    size++;
                    tasks[size-1] = setupDeadline(size, command);
                    printDeadline(tasks[size-1], size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.startsWith("event")) {
                System.out.println(SEPARATE_LINE);
                boolean proceed = proceedEvent(command);
                if (proceed) {
                    size++;
                    tasks[size - 1] = setupEvent(size, command);
                    printEvent(tasks[size-1], size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.equals("list")) {
                printList(tasks, size);
            } else if (command.startsWith("done")) {
                System.out.println(SEPARATE_LINE);
                printDone(tasks, command);
                System.out.println(SEPARATE_LINE);
            } else if (command.startsWith("delete")) {
                System.out.println(SEPARATE_LINE);
                int index = findIndex(size, command);
                if (index >= 0) {
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(tasks[index].toString());
                    size --;
                    for (int i = index; i < size; i++) {
                        tasks[i] = tasks[i + 1];
                        tasks[i].setNoOfTask(i + 1);
                    }
                    System.out.println("Now you have " + size + " tasks in the list.");
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.equals("help")) {
                System.out.println(SEPARATE_LINE);
                System.out.println(INSTRUCTION);
                System.out.println(SEPARATE_LINE);
            } else {
                System.out.println(SEPARATE_LINE);
                System.out.println("I'm sorry, but I don't know what that means");
                System.out.println(SEPARATE_LINE);
            }
        }
    }
}

