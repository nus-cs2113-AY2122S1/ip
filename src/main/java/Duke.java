import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final int COMMAND = 0;
    static final int DESCRIPTION = 0;
    static final int TIME = 1;
    static final String LINE = "--------------------------------";

    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void greet() {
        String logo = "  _                                         _   _            \n" +
                " | |__   __ _ _ __ _ __ _   _   _ __   ___ | |_| |_ ___ _ __ \n" +
                " | '_ \\ / _` | '__| '__| | | | | '_ \\ / _ \\| __| __/ _ \\ '__|\n" +
                " | | | | (_| | |  | |  | |_| | | |_) | (_) | |_| ||  __/ |   \n" +
                " |_| |_|\\__,_|_|  |_|   \\__, | | .__/ \\___/ \\__|\\__\\___|_|   \n" +
                "                        |___/  |_|                         \n" +
                "  S. Lu - I solemnly swear that I am up to no good.\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(
                "       Messrs Moony, Wormtail, Padfoot, and Prongs\n" +
                        "       Purveyors of Aids to Magical Mischief-Makers\n" +
                        "                  are proud to present\n" +
                        "                 --THE MARAUDER'S MAP--");
    }

    public static void hello() {
        System.out.println("I see you are lost. \n" +
                "Read the charm beneath out loud, and I shall serve you.");
        System.out.println("- \"I solemnly swear that I am up to no good.\" ");
    }

    public static void alarm(Alarm typeOfAlarm) {
        switch(typeOfAlarm) {
        case INVALID_COMMAND:
            //System.out.println("Invalid charm. What did Professor Flitwick told you?");
            System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        case BLANK_DESCRIPTION:
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
            break;
        case EMPTY_DONE:
            System.out.println("Give me a number");
            break;
        case EMPTY_TODO:
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            break;
        case EMPTY_EVENT:
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            break;
        case EMPTY_DEADLINE:
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            break;
        case NO_DDL_KEYWORD:
            System.out.println("Missing important keyword, add a /by before time");
            break;
        case NO_EVENT_KEYWORD:
            System.out.println("Missing important keyword, add a /at before time");
            break;
        case INVALID_ID:
            System.out.println("You should give me a valid number");
            break;
        case OUT_OF_RANGE:
            System.out.println("Can't find the item in the list");
            System.out.println("Give me a number from 1 to " + taskCount);
            printList();
            break;
        default:
            System.out.println("UNIDENTIFIED ERROR!");
        }
    }

    public static void exit() {
        System.out.println("Mischief managed.");
    }

    public static void line() {
        System.out.println(LINE);
    }

    public static void add(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("|| Got it. I've added this task");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + taskCount + " in the list.");

    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            Task t = tasks[i];
            System.out.println(t.id + ". " + t);
        }
    }

    public static void markDone(String id)throws DukeException {
        int i = Integer.parseInt(id) - 1;
        if (i == -1 || i > taskCount) {
            throw new DukeException();
        }
        tasks[i].setDone(true);
        System.out.println("Nice!I've marked this task as done:");
        System.out.println(tasks[i].id + ". " + tasks[i].toString());
    }

    public static String[] splitFirstWord (String userInput) {
        String[] output = userInput.split(" ",2);
        return output;
    }

    public static String[] getDetails(String taskInput, String keyword) {
        String[] details = taskInput.split(keyword,2);

        details[TIME] = details[TIME].replace(keyword, "");

        return details;
    }


    public static void identifyInput (String userInput[]) {
        String taskType = userInput[COMMAND];

        line();
        switch (taskType) {
        case"todo":
            try {
                String taskInput = userInput[1];
                Todo t = new Todo(taskInput);
                add(t);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.BLANK_DESCRIPTION);
            }
            break;
        case "deadline":
            try {
                String taskInput = userInput[1];

                try {
                    String[] details1 = getDetails(taskInput, "/by");
                    Deadline d = new Deadline(details1[DESCRIPTION], details1[TIME]);
                    add(d);
                } catch (ArrayIndexOutOfBoundsException e) {
                    alarm(Alarm.NO_DDL_KEYWORD);
                }

            }  catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_DEADLINE);
            }
            break;
        case "event":
            try {
                String taskInput = userInput[1];

                try {
                    String[] details2 = getDetails(taskInput, "/at");
                    Event e = new Event(details2[DESCRIPTION], details2[TIME]);
                    add(e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    alarm(Alarm.NO_EVENT_KEYWORD);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_EVENT);
            }
            break;
        case "list":
            printList();
            break;
        case "done":
            try {
                String taskInput = userInput[1];
                markDone(taskInput);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_DONE);
            } catch (DukeException e) {
                alarm(Alarm.OUT_OF_RANGE);
            } catch (NumberFormatException e) {
                alarm(Alarm.INVALID_ID);
            }
            break;
        default:
            alarm(Alarm.INVALID_COMMAND);
            break;
        }
        line();
    }


    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.startsWith("bye")) {
            String cleanInput = input.trim();
            String[] userInput = cleanInput.split(" ",2);

            identifyInput(userInput);
            input = in.nextLine();
        }
        exit();
    }
}
