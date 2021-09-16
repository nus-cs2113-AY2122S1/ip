import java.util.Scanner;
import java.lang.String;
import java.util.ArrayList;

public class Duke {

    public static final String line = "____________________________________________________________\n";
    public static final int MAX_NUM_OF_TASKS = 100;
    public static final int TODO_POS = 5;
    public static final int BY_POS = 4;
    public static final int DEADLINE_POS = 9;
    public static final int AT_POS = 4;
    public static final int EVENT_POS = 6;

    public static void main(String[] args) {

        greeting();

        ArrayList<Task> tasks = new ArrayList<Task>();

        Scanner input = new Scanner(System.in);
        while (true) {
            String inputString = input.nextLine();
            if (inputString.equals("bye")) {
                saveDataToFile("tasks", tasks);
                exit();
                break;
            } else if (inputString.equals("list")) {
                try {
                    printList(tasks);
                }
                catch(DukeException d) {
                    System.out.println(line
                            + d.getMessage() + "\n" + line);
                }
            } else if (inputString.contains("done") == true) {
                try {
                    setDone(inputString, tasks);
                }
                catch(IndexOutOfBoundsException i) {
                    System.out.println(line
                            + "☹ OOPS!!! The index of done indstruction cannot be empty.\n"
                            + line);
                }
                catch(NumberFormatException n) {
                    System.out.println(line
                            + "☹ OOPS!!! The index of done indstruction should be a number.\n"
                            + line);
                }
                catch(NullPointerException p) {
                    System.out.println(line
                            + "☹ OOPS!!! The index is out of range.\n"
                            + line);
                }
            } else if (inputString.contains("delete") == true) {
                deleteTask(inputString, tasks);
            } else {
                try {
                    addTask(inputString, tasks);
                }
                catch(IndexOutOfBoundsException i) {
                    String type = "";
                    if (inputString.contains("todo")) {
                        type = "todo";
                    } else if (inputString.contains("deadline")) {
                        type = "deadline";
                    } else if (inputString.contains("event")) {
                        type = "event";
                    }
                    if (type == "") {
                        System.out.println(line
                                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                + line);
                    } else {
                        System.out.println(line
                                + "☹ OOPS!!! The description of a " + type + " cannot be empty.\n"
                                + line);
                    }
                }
            }
        }
    }

    public static final void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(line
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + line);
    }

    public static final void exit() {
        System.out.println(line
                + "Bye. Hope to see you again soon!\n"
                + line);
    }

    public static final void printList(ArrayList<Task> tasks) throws DukeException{
        if (tasks.size() == 0) {
            throw new DukeException("The list is empty!");
        } else {
            System.out.println(line
                    + "Here are the tasks in your list: ");

            for (int index = 0; index < tasks.size(); index++) {
                System.out.println((index + 1) + ". "
                        + tasks.get(index).toString());
            }
            System.out.println(line);
        }
    }

    public static final void setDone(String input, ArrayList<Task> tasks) {
        String num = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c) == true) {
                num += c;
            }
        }

        int index = Integer.parseInt(num);
        tasks.get(index - 1).markedAsDone();
        System.out.println(line
                + "Nice! I've marked this task as done: \n"
                + tasks.get(index - 1) + "\n"
                + line);
    }

    public static final void addTask(String input, ArrayList<Task> tasks) throws NullPointerException {
        if (input.contains("todo") == true) {
            tasks.add(new Todo(input.substring(TODO_POS)));
        } else if (input.contains("deadline") == true) {
            String by = input.substring(input.indexOf("/") + BY_POS);
            tasks.add(new Deadline(input.substring(DEADLINE_POS,
                    input.indexOf("/")), by));
        } else if (input.contains("event") == true) {
            String at = input.substring(input.indexOf("/") + AT_POS);
            tasks.add(new Event(input.substring(EVENT_POS,
                    input.indexOf("/")), at));
        } else {
            System.out.print(tasks.get(tasks.size() + 1).toString());
        }

        System.out.println(line
                + "Got it. I've added this task: \n"
                + tasks.get(tasks.size() - 1) + "\n"
                + "Now you have " + tasks.size()
                + " tasks in the list\n"
                + line);
    }

    public static final void deleteTask(String input, ArrayList<Task> tasks) {
        String num = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c) == true) {
                num += c;
            }
        }

        int index = Integer.parseInt(num);
        Task removedTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println(line
                + "Noted. I've removed this task: \n"
                + removedTask.toString()
                + "\nNow you have " + tasks.size()
                + " tasks in the list.\n" + line);
    }
