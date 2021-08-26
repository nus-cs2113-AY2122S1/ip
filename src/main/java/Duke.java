import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void printList(String[] tasks, int taskCount, int[] taskDone) {
        String[] currList = Arrays.copyOf(tasks, taskCount + 1);
        System.out.println("____________________________________________________________");
        System.out.println("PENDING HIT LIST:");
        for (int i = 0; i < currList.length; i++) {
            if (currList[i] != null) {
                if (taskDone[i] == 1) {
                    System.out.println(i + 1 + "." + " [x] " + currList[i]);
                }
                else {
                    System.out.println(i + 1 + "." + " [ ] " + currList[i]);
                }
            }
        }
        System.out.println("____________________________________________________________");

    }

    public static int doneTask(String line, String[] tasks) {
        String[] number = line.split(" ");
        int taskNumber = Integer.parseInt(number[1]);
        System.out.println("TARGET NEUTRALISED: " + taskNumber + "." + " [x] " + tasks[taskNumber-1]);
        return taskNumber-1;

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("[The Templar:]");
        System.out.println("Greetings, Hero. Meet Vector Unit 202 - codename DUKE.\n" + "The state of the art AI assassin you requested. Give DUKE a task, and it shall be done.");
        System.out.println("____________________________________________________________");

        //declarations
        boolean isDone = false;
        boolean isTask = false;
        int taskCount = 0;
        String[] tasks = new String[100];
        int[] taskDone = new int[100];

        while (!isDone) {
            /*if (isTask == true) {
                System.out.println("[The Templar:]");
                System.out.println("What further tasks do you require?");
            }*/
            System.out.println("____________________________________________________________");
            System.out.println("[Hero:]");
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            //added
            System.out.println("[The Templar:]");
            System.out.println("____________________________________________________________");
            System.out.println(line);

            if (!line.contains("list") && !line.contains("done")) {
                tasks[taskCount] = line;
                taskCount++;
            }

            isTask = true; // first answer has been given

            /*if (!line.contains("bye") && !line.contains("list") && !line.contains("done")) {
                System.out.println("____________________________________________________________");
                System.out.println("[DUKE:]");
                System.out.println("...understood.");
                System.out.println("============= TASK ACQUIRED:" + line + " =============");
                System.out.println("____________________________________________________________");
            }*/

            if (line.contains("list")) {
                printList(tasks, taskCount, taskDone);
            }

            if (line.contains("done")) {
                taskDone[doneTask(line, tasks)] = 1; //mark task as done in memory
            }

            if (line.contains("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("[The Templar:]\n" + "DUKE shall carry out his mission. Farewell, Hero.");
                System.out.println("____________________________________________________________");
                isDone = true;
            }
        }
    }

}
