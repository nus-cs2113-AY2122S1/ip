import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LINE = "\t____________________________________________________________\n\t";
    public static final String BYE = LINE
            + "Bye. try not to procrastinate!\n"
            + LINE;
    public static final String GREETING = LINE
            + "Hello! I'm Anderson\n\t"
            + "What do you need to do?\n"
            + LINE;

    public static Task[] FilterNulls(Task[] tasks) {
        Task[] isFilteredNull = new Task[100];
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (tasks[i] != null) {
                isFilteredNull[count] = tasks[i];
                count++;
            }
        }
        return Arrays.copyOf(isFilteredNull, count);
    }

    public static String GetCommand(String userInput) {
        if (userInput.equals("\n")){
            return "again";
        }
        if (userInput.length() > 2){
            String[] isolateCommand = userInput.split(" ");
            return isolateCommand[0];
        }
        return userInput;
    }

    public static String GetItem(String userInput) {
        String item = "";
        if (GetCommand(userInput).equals("Deadline")){
            item = userInput.substring(userInput.indexOf(" ")+1, userInput.indexOf("/"));
        } else if (GetCommand(userInput).equals("Event")){
            item = userInput.substring(userInput.indexOf(" ")+1, userInput.indexOf("/"));
        } else if (userInput.length() > 3){
            item = userInput.substring(userInput.indexOf(" ")+1);
        }
        return item;
    }

    public static String GetTime(String userInput){
        String time = "";
        if (GetCommand(userInput).equals("Deadline")){
            time = userInput.substring(userInput.indexOf("/")+3);
        } else if (GetCommand(userInput).equals("Event")){
            time = userInput.substring(userInput.indexOf("/")+3);
        } else {
            return "";
        }
        return time;
    }

    public static void main(String[] args) {



        Task[] unfilteredTasks = new Task[100];
        int unfilteredCounter = 0;

        System.out.println("\tHello from\n" + LOGO);
        System.out.println(GREETING);

        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        boolean closeDuke = false;

        while (!closeDuke) {
            String command = GetCommand(userInput);
            if (command.equals("done")){
                System.out.println(LINE + "Nice! I've marked this task as done:");
                int taskNumber = Integer.parseInt(GetItem(userInput));
                Task completedTask = unfilteredTasks[(taskNumber - 1)];
                completedTask.markAsDone();
                System.out.println("\t\t" + completedTask + "\n" + LINE);
                userInput = in.nextLine();
            } else if (userInput.equals("list")) {
                Task[] filteredNull = FilterNulls(unfilteredTasks);
                int count = 0;
                if (filteredNull[0] == null) {
                    System.out.println(LINE + "\tAll DONE!\n" + LINE);
                    userInput = in.nextLine();
                } else {
                    System.out.println(LINE + "Here are the tasks in your list:\n");
                    for (Task task : filteredNull) {
                        count++;
                        System.out.println("\t" + count + "." + task);
                    }
                    System.out.println(LINE);
                    userInput = in.nextLine();
                }

            } else if (command.equals("ToDo")){
                unfilteredTasks[unfilteredCounter] = new ToDo(GetItem(userInput));
                System.out.println(LINE + "Got it. I've added this task:\t");
                System.out.println(String.format("\t%d.",unfilteredCounter+1) + unfilteredTasks[unfilteredCounter] + "\n" + String.format("\tNow you have %d tasks in the list.\n", unfilteredCounter+1) + LINE);
                unfilteredCounter++;
                userInput = in.nextLine();
            } else if (command.equals("Deadline")){
                unfilteredTasks[unfilteredCounter] = new Deadline(GetItem(userInput), GetTime(userInput));
                System.out.println(LINE + "Got it. I've added this task:\t");
                System.out.println(String.format("\t%d.",unfilteredCounter+1) + unfilteredTasks[unfilteredCounter] + "\n" + String.format("\tNow you have %d tasks in the list.\n", unfilteredCounter+1) + LINE);
                unfilteredCounter++;
                userInput = in.nextLine();
            } else if (command.equals("Event")){
                unfilteredTasks[unfilteredCounter] = new Event(GetItem(userInput), GetTime(userInput));
                System.out.println(LINE + "Got it. I've added this task:\t");
                System.out.println(String.format("\t%d.",unfilteredCounter+1) + unfilteredTasks[unfilteredCounter] + "\n" + String.format("\tNow you have %d tasks in the list.\n", unfilteredCounter+1) + LINE);
                unfilteredCounter++;
                userInput = in.nextLine();

            } else if (command.toLowerCase().equals("bye")) {
                System.out.println(BYE);
                closeDuke = true;
            } else {
                System.out.println(LINE + "\tInvalid input, Please Try Again :)\n" + LINE);
                userInput = in.nextLine();
            }
        }
    }
}
