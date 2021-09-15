package duke.templar;

import duke.exception.*;

import java.util.Scanner;
import java.util.Arrays;


public class Duke {

    public static final String MESSAGE_DIVIDER = "____________________________________________________________";
    public static int taskCount = 0;
    public static Task[] tasks = new Task[100];

    public static void main(String[] args) throws CommandInvalidException, DeadlineInvalidFormatException, TodoInvalidFormatException, EventInvalidFormatException, TaskNumberInvalidException, NoSuchTaskException {
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


        boolean endSession = false;

        while (!endSession) {

            System.out.println(MESSAGE_DIVIDER);
            System.out.println("[Hero:]");

            String line; // the task
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            TaskManager activateTaskManager = new TaskManager();
            activateTaskManager.processInput(line, tasks, taskCount);
            taskCount++;

            if (line.contains("bye")) {
                System.out.println(MESSAGE_DIVIDER);
                System.out.println("[The Templar:]\n" + "DUKE shall carry out his mission. Farewell, Hero.");
                System.out.println(MESSAGE_DIVIDER);
                endSession = true;
            }
        }
    }

}
