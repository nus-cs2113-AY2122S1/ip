import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void printLine() {
        for(int i =0;i <= 50;i++) {
            System.out.print("~");
        }
        System.out.println(" ");
    }

    public static void echoMessage(String line) {
        System.out.println("Owl: I've remembered that!\nOwl: You said this: " + line);
    }

    public static void listTask(Task[] taskList) {
        int taskIndex = 0;
        int index = 1;
        printLine();
        System.out.println("This are all the things I've remembered for you:");
        printLine();
        for(Task task:taskList) {
            Task theTask = taskList[taskIndex];
            System.out.println(index + ".[" + (theTask.getStatus()) + "] "+ theTask.getDescription());
            index++;
            taskIndex++;
        }
        printLine();
    }

    public static void main(String[] args) {

        String LOGO = " ______   _       _   _\n"
                + "|  __  | | | ___ | | | | \n"
                + "| |  | | | |/   \\| | | | \n"
                + "| |__| | |   / \\   | | |____\n"
                + "|______| |__/   \\__| |______|\n";
        System.out.println(LOGO);
        printLine();

        Scanner in = new Scanner(System.in);

        System.out.println("SQUAWK!!!");
        System.out.println("How can I help you?");
        printLine();

        Task[] taskList = new Task[100];
        int taskIndex = 0;

        String command;
        command = in.nextLine();
        boolean isFinish = false;

        //while the command is not bye it keeps asking for more
        while(!command.equals("bye")) {
            String[] breakDown = command.split(" ");
            int size = breakDown.length;
            //If its a 2 part command and first is done second is digit
            if(size == 2 && breakDown[0].equals("done")) {
                int taskNumber = Integer.parseInt(breakDown[1]);
                int arrayIndex = taskNumber-1;
                taskList[arrayIndex].markDone();
                printLine();
                System.out.println("Oooh I see you've done task " + taskNumber);
                printLine();
                isFinish = true;
            }

            if(command.equals("list")) {
                if(taskIndex > 0) {
                    listTask(Arrays.copyOf(taskList,taskIndex));
                }
                isFinish = true;
            }

            if(!command.isEmpty() && !isFinish) {
                taskList[taskIndex] = new Task(command);
                taskIndex++;
                printLine();
                echoMessage(command);
                printLine();
            }
            command = in.nextLine();
            isFinish = false;
        }

        //If command is bye
        printLine();
        System.out.println("SQUAWK! See you next time! :)");
        printLine();
    }
}

