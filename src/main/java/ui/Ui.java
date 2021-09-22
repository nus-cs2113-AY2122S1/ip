package ui;

import task.Task;
import task.TaskList;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    /**
     * Decorative prefixes to format program's output more neatly/cleaner.
     */
    public static final String CONSOLE_LINE_PREFIX = "____________________________________________________________";
    public static final String SPACE_PREFIX = " ";
    public static final String LINE_BREAK = "\n";

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.sc = new Scanner(in);
    }

    /**
     * Prints the Duke program logo.
     *
     * Credits
     * ASCII Art Logo generated using
     * https://patorjk.com/software/taag/#p=display&f=Dancing%20Font&t=Duke
     */
    public void printLogo() {
        String logo = "  ____     _   _    _  __  U _____ u" + LINE_BREAK
                + " |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/" + LINE_BREAK
                + "/| | | | \\| |\\| |  | ' /    |  _|\"" + LINE_BREAK
                + "U| |_| |\\ | |_| |U/| . \\u   | |___" + LINE_BREAK
                + " |____/ u<<\\___/   |_|\\_\\   |_____|" + LINE_BREAK
                + "  |||_  (__) )(  ,-,>> \\\\,-.<<   >>" + LINE_BREAK
                + " (__)_)     (__)  \\.)   (_/(__) (__)";
        System.out.println(logo);
    }

    /**
     * Prints out the program greeting message.
     */
    public void printGreeting() {
        String greeting = CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Hello! You probably know that Iron Man has the best AI-assistant called Jarvis" + LINE_BREAK
                + SPACE_PREFIX + "and Spiderman has hmmm, maybe his tingly spidey senses?" + LINE_BREAK
                + SPACE_PREFIX + "But don't worry! You have me, Duke! I am your personal SIDEKICK that does \"something\"!" + LINE_BREAK
                + SPACE_PREFIX + "What is \"something\" you want me to do?" + LINE_BREAK
                + CONSOLE_LINE_PREFIX;
        System.out.println(greeting);
    }

    /**
     * Prints out the program farewell (exit) message.
     */
    public void printFarewell() {
        String farewell = CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Bye, have a nice day! From your friendly neighbourhood assistant, Duke~" + LINE_BREAK
                + SPACE_PREFIX + "(NICE, I can finally binge watch Rick and Morty~)" + LINE_BREAK
                + CONSOLE_LINE_PREFIX;
        System.out.println(farewell);
    }

    /**
     * Prints out the respective error messages.
     *
     * @param errorMessage Error Message of the encountered error/exception
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Returns the user input from I/O
     *
     * @return User input
     */
    public String getUserInput() {
        String userInput;
        System.out.print(SPACE_PREFIX + "What's your plans/command for today (No... I am not hitting on you) : ");
        userInput = sc.nextLine();
        return userInput;
    }

    /**
     * Print added to Tasks message.
     *
     * @param taskInformation The task's name
     */
    public void printAddedTaskMessage(String taskInformation) {
        System.out.println(CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Here you go..." + LINE_BREAK + " Added to stuff you would definitely forget to do (*facepalm*):"
                + LINE_BREAK + SPACE_PREFIX
                + taskInformation
                + LINE_BREAK
                + CONSOLE_LINE_PREFIX);
    }

    /**
     * Print deleted Task message.
     *
     * @param deletedTask The deleted task
     * @param taskCounter The number of remaining tasks left
     */
    public void printDeletedTaskMessage(Task deletedTask, int taskCounter) {
        System.out.println(CONSOLE_LINE_PREFIX + LINE_BREAK
                + SPACE_PREFIX + "Roger that! I am Thanos and I have snapped away : " + LINE_BREAK
                + SPACE_PREFIX + deletedTask
                + LINE_BREAK
                + SPACE_PREFIX + "Now you have " + taskCounter + " tasks left!"
                + LINE_BREAK
                + CONSOLE_LINE_PREFIX);
    }

    /**
     * Print "Task has been marked" message.
     *
     * @param doneTask The task that has been marked as done
     */
    public void printMarkedTaskDoneMessage(Task doneTask) {
        System.out.println(SPACE_PREFIX + "Great! You didn't forget to do it! I have marked it as done!" + LINE_BREAK
                + SPACE_PREFIX + doneTask + LINE_BREAK
                + CONSOLE_LINE_PREFIX);
    }

    /**
     * Prints all the Tasks.
     */
    public void printTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println(CONSOLE_LINE_PREFIX + LINE_BREAK + SPACE_PREFIX + "Hi there! You have no dates! LITERALLY"
                    + LINE_BREAK + CONSOLE_LINE_PREFIX);
        } else {
            System.out.println(SPACE_PREFIX + "EEEEEOOOOOO~ ALL RIGHT~ Oops was jamming away in my virtual garage, here's your PLAN/S...");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println(SPACE_PREFIX + (i + 1) + "." + SPACE_PREFIX + tasks.getTaskAtIndex(i));
            }
            System.out.println(CONSOLE_LINE_PREFIX);
        }
    }
}
