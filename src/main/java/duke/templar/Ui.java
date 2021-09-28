package duke.templar;

import java.util.ArrayList;

public class Ui {

    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    public Ui() {

    }

    public static void printHelloMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("[The Templar:]");
        System.out.println("Greetings, Hero. Meet Vector Unit 202 - codename DUKE.\n" + "The state of the art AI assassin you requested. Give DUKE a mission, and it shall be done.");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printService() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("[The Templar:]");
        System.out.println("What further assistance do you require?");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printHero() {
        System.out.println("[Hero:]");
    }

    public static void printGoodbyeMsg() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("[The Templar:]\n" + "DUKE shall carry out his mission. Farewell, Hero.");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printFileNotFoundExceptionMsg() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("[Templar:] ");
        System.out.println("It appears your file cannot be found, Hero. Lets create one, shall we?");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printCreateFailMsg() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("[Templar:] ");
        System.out.println("Your file failed to create, Hero.");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printWriteFailMsg() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("[Templar:] ");
        System.out.println("Your file failed to write, Hero.");
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printTaskDone(int taskNumber, ArrayList<Task> tasks) {
        System.out.println("TARGET NEUTRALISED: " + taskNumber + ". " + tasks.get(taskNumber - 1));
    }

    /*
    method prints the current updated task list when called
    *
    @params tasks the array of tasks
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("PENDING HIT LIST:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.size() != 0) {
                System.out.println(i + 1 + "." + tasks.get(i));
            }
        }
        System.out.println(MESSAGE_DIVIDER);

    }

    public static void printTaskAcquired(Task newTask, ArrayList<Task> tasks) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("[DUKE:]");
        System.out.println("...understood.");
        System.out.println("============= TASK ACQUIRED: " + newTask + "=============");
        System.out.println("current execution total: " + tasks.size());
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void printCommands(String[] validCommands) {
        System.out.println("VALID COMMANDS ARE:");
        for (int i = 1; i < validCommands.length; i++) {
            System.out.print(validCommands[i] +" ");
        }
        System.out.print("\n");
    }

    public static void printFoundTasks() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("TASKS FOUND ARE:");
    }

}
