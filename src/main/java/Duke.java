import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    /*
    method prints the current updated task list when called
    *
    @params tasks the array of tasks
    @params taskCount the current number of tasks
    @params taskDone an array of equal length to tasks that stores 1 or 0 to indicate if the task at that index is done or not
     */

    public static void printList(Task[] tasks, int taskCount, int[] taskDone) {
        //String[] currList = Arrays.copyOf(tasks, taskCount + 1);
        System.out.println("____________________________________________________________");
        System.out.println("PENDING HIT LIST:");
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                    System.out.println(i + 1 + "." + tasks[i].toString());
            }
        }
        System.out.println("____________________________________________________________");

    }

    /*
    method returns which task (in tasks array) has been completed and also prints the result
    *
    @params line the input string
    @params tasks the array of tasks
    *
    @return the task (in tasks array) that has been marked done
     */
    public static int doneTask(int taskNumber, Task[] tasks) {
        tasks[taskNumber-1].setDone(true); //mark task as done
        System.out.println("TARGET NEUTRALISED: " + taskNumber + ". " + tasks[taskNumber-1]);
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
        System.out.println("Greetings, Hero. Meet Vector Unit 202 - codename DUKE.\n" + "The state of the art AI assassin you requested. Give DUKE a mission, and it shall be done.");
        System.out.println("____________________________________________________________");

        //declarations

        String[] validCommands = new String[] {
                "todo" ,
                "deadline",
                "event" ,
                "list" ,
                "done" ,
                "bye" ,
        };
        boolean valid = false;
        boolean endSession = false;
        boolean isTask = false;
        int taskCount = 0;
        Task[] tasks = new Task[100];
        int[] taskDone = new int[100]; //this array stores 1 or 0 - task done or not

        while (!endSession) {
            if (isTask) {
                System.out.println("[The Templar:]");
                System.out.println("What further assistance do you require?");
            }
            System.out.println("____________________________________________________________");
            System.out.println("[Hero:]");
            String line; // the task
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            valid = false; //default case
            for (int i = 0; i <validCommands.length; i++) {
                if(line.startsWith(validCommands[i])) {
                    valid = true;
                }
            }
            if (!valid) {
                System.out.println("mission format incorrect - try again, Hero.");
                continue;
            }

            if(line.startsWith("deadline")) {
                String[] firstSplit = line.split(" ", 2);
                String[] secondSplit = firstSplit[1].split(" /by ",2);
                String deadlineDescription = secondSplit[0];
                String deadlineDate = secondSplit[1];
                tasks[taskCount] = new Deadline(deadlineDescription, deadlineDate);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("[DUKE:]");
                System.out.println("...understood.");
                System.out.println("============= TASK ACQUIRED: " + tasks[taskCount-1].toString() + "=============");
                System.out.println("current execution total: " + taskCount);
                System.out.println("____________________________________________________________");
            }
            else if (line.startsWith("todo")) {
                String[] todoSplit = line.split(" ", 2);
                String todoDescription = todoSplit[1];
                tasks[taskCount] = new ToDo(todoDescription);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("[DUKE:]");
                System.out.println("...understood.");
                System.out.println("============= TASK ACQUIRED: " + tasks[taskCount-1].toString() + "=============");
                System.out.println("current execution total: " + taskCount);
                System.out.println("____________________________________________________________");

            }
            else if (line.startsWith("event")) {
                String[] firstSplit = line.split(" ", 2);
                String[] secondSplit = firstSplit[1].split(" /at ",2);
                String eventDescription = secondSplit[0];
                String eventDate = secondSplit[1];
                tasks[taskCount] = new Event(eventDescription, eventDate);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("[DUKE:]");
                System.out.println("...understood.");
                System.out.println("============= TASK ACQUIRED: " + tasks[taskCount-1].toString() + "=============");
                System.out.println("current execution total: " + taskCount);
                System.out.println("____________________________________________________________");
            }

            isTask = true; // first answer has been given


            if (line.contains("list")) {
                printList(tasks, taskCount, taskDone); //print the current list
            }

            else if (line.startsWith("done")) {
                String[] number = line.split(" ");
                int taskNumber = Integer.parseInt(number[1]);
                if (taskNumber <= taskCount) {
                    taskDone[doneTask(taskNumber, tasks)] = 1; //mark task as done in memory
                }
                else {
                    System.out.println("mission format incorrect - try again, Hero.");
                    continue;
                }
            }

            else if (line.contains("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("[The Templar:]\n" + "DUKE shall carry out his mission. Farewell, Hero.");
                System.out.println("____________________________________________________________");
                endSession = true;
            }
        }
    }

}
