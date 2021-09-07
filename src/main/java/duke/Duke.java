package duke;

import java.util.Scanner;

public class Duke {
    public static final String SEPARATE_LINE = "____________________________________________________________";
    public static final String INSTRUCTION = "Here are what I can do:\n"
            + "echo description: I will repeat your descriotion\n"
            + "todo description: I will add a task with no time constraint to your task list\n"
            + "deadline description /by time: I will add a task with deadline to your task list\n"
            + "event description /at time: I will add a task with its time to your task list\n"
            + "done description: I will mark this task as done\n"
            + "list: I will show your task list\n"
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

    public static void checkEcho(String command) throws NoDescriptionException{
        if (command.equals("echo") || command.equals("echo ")) {
            throw new NoDescriptionException();
        }
    }

    public static void handleEcho(String command) {
        boolean hasDescription = true;
        System.out.println(SEPARATE_LINE);
        try {
            checkEcho(command);
        } catch (NoDescriptionException e) {
            hasDescription = false;
        }

        if (hasDescription) {
            System.out.println(command.substring(5));
        } else {
            System.out.println("Sorry, I have no idea what you want me to echo");
            System.out.println("Please use the format \"echo description\"");
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
        } else if (command.charAt(slashIndex + 3) != ' ') {
            throw new NoSpaceException();
        } else if (command.charAt(slashIndex - 1) != ' ') {
            throw new NoSpaceException();
        }
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
        } else if (command.charAt(slashIndex + 3) != ' ') {
            throw new NoSpaceException();
        } else if (command.charAt(slashIndex - 1) != ' ') {
            throw new NoSpaceException();
        }
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
        System.out.println(SEPARATE_LINE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task.toString());
        System.out.println(SEPARATE_LINE);
    }

    public static void printDone(Task[] tasks, int size, String command) {
        String description = command.substring(5);
        for (int i = 0; i < size; i++) {
            if (description.equals(tasks[i].getDescription())){
                setTaskDone(tasks[i]);
                break;
            }
        }
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
                boolean proceed = true;
                try {
                    checkToDo(command);
                } catch (NoDescriptionException e) {
                    System.out.println("What task do you want to add?");
                    proceed = false;
                } catch (NoSpaceException e) {
                    System.out.println("Please use a space to seperate command, task, and time (if any)");
                    proceed = false;
                }
                if (proceed) {
                    size++;
                    tasks[size-1] = new ToDo(command.substring(5), size);
                    printToDo(tasks[size-1], size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.startsWith("deadline")) {
                System.out.println(SEPARATE_LINE);
                boolean proceed = true;
                try {
                    checkDeadline(command);
                } catch (NoDescriptionException e) {
                    System.out.println("What task do you want to add?");
                    proceed = false;
                } catch (NoTimeException e) {
                    System.out.println("When is the deadline?");
                    proceed = false;
                } catch (NoSpaceException e) {
                    System.out.println("Please use a space to seperate command, task, and time (if any)");
                    proceed = false;
                }
                if (proceed) {
                    size++;
                    tasks[size-1] = setupDeadline(size, command);
                    printDeadline(tasks[size-1], size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.startsWith("event")) {
                System.out.println(SEPARATE_LINE);
                boolean proceed = true;
                try {
                    checkEvent(command);
                } catch (NoDescriptionException e) {
                    System.out.println("What task do you want to add?");
                    proceed = false;
                } catch (NoTimeException e) {
                    System.out.println("When is the time?");
                    proceed = false;
                } catch (NoSpaceException e) {
                    System.out.println("Please use a space to seperate command, task, and time (if any)");
                    proceed = false;
                }

                if (proceed) {
                    size++;
                    tasks[size - 1] = setupEvent(size, command);
                    printEvent(tasks[size-1], size);
                }
                System.out.println(SEPARATE_LINE);
            } else if (command.equals("list")) {
                printList(tasks, size);
            } else if (command.startsWith("done")) {
                printDone(tasks, size, command);
            } else {
                System.out.println(SEPARATE_LINE);
                System.out.println("I'm sorry, but I don't know what that means");
                System.out.println(SEPARATE_LINE);
            }
        }
    }
}

