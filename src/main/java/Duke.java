import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dashes = "____________________________________________________________\n";

        System.out.println(logo);
        System.out.println(dashes +
                " Hello! I'm Duke, the list robot!\n" +
                " Tell me what you want added! Type 'list' to display all. Type 'bye' to exit.\n" + dashes);

        String line;
        Task[] list = new Task[100];
        int listCount = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(dashes);
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int itemCount = 1;
                for (Task item : list) {
                    if (item != null) {
                        System.out.println(itemCount + "." + item);
                        itemCount++;
                    }
                }
            } else if (line.startsWith("done")) {
                String[] words = line.split(" ");
                int taskIndex = Integer.parseInt(words[1]) - 1;
                Task currentTask = list[taskIndex];
                currentTask.setDone();
                System.out.println("Nice! You did the following task:"
                        + "\n [" + currentTask.getStatusIcon() + "] "
                        + currentTask.getDescription());
            } else if (line.startsWith("todo")) {
                int separatorIndex = line.indexOf(" ");
                list[listCount] = new Todo(line.substring(separatorIndex+1));
                System.out.println("Got it! I've added this task: ");
                System.out.println(" "+ list[listCount]);
                int totalListCount = listCount+1;
                System.out.println("Now you have " + totalListCount + " tasks in the list.");
                listCount++;
            }  else if (line.startsWith("deadline")) {
                int separatorIndexStart = line.indexOf(" ");
                int separatorIndexEnd = line.indexOf("/");
                list[listCount] = new Deadline(line.substring(separatorIndexStart+1,separatorIndexEnd-1),line.substring(separatorIndexEnd+4));
                System.out.println("Got it! I've added this task: ");
                System.out.println(" " + list[listCount]);
                int totalListCount = listCount+1;
                System.out.println("Now you have " + totalListCount + " tasks in the list.");
                listCount++;
            }   else if (line.startsWith("event")) {
                int separatorIndexStart = line.indexOf(" ");
                int separatorIndexEnd = line.indexOf("/");
                list[listCount] = new Event(line.substring(separatorIndexStart+1,separatorIndexEnd-1),line.substring(separatorIndexEnd+4));
                System.out.println("Got it! I've added this task: ");
                System.out.println(" " + list[listCount]);
                int totalListCount = listCount+1;
                System.out.println("Now you have " + totalListCount + " tasks in the list.");
                listCount++;
            } else {
                Task task = new Task(line);
                list[listCount] = task;
                listCount++;
                System.out.println("added: " + line);
            }
            System.out.println(dashes);
            line = in.nextLine();
        }

        System.out.println(dashes +
                " Bye. Do visit next time! More upgrades coming soon :-)\n" + dashes);
    }
}
