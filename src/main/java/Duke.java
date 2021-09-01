import java.util.Scanner;


public class Duke {
    public static void hi() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void running() {
        Task[] tasks= new Task[100];
        int taskNumber = 0;
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            //new scanner
            line = in.nextLine();
            //if goodbye
            if (line.equals("bye")) {
                return;
            }
            //if want to list out tasks or mark as done or add items
            if (line.equals("list")) {
                for (int i = 0; i < taskNumber; i++) {
                    System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                }
            } else if (line.startsWith("done")) {
                int index = Integer.parseInt(line.substring(5)) - 1;
                tasks[index].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
            } else if (line.startsWith("todo")) {
                tasks[taskNumber] = new ToDo(line.substring(5));
                taskNumber++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskNumber - 1]);
                System.out.println("Now you have " + taskNumber + " tasks in the list");
            } else if (line.startsWith("deadline")) {
                String[] words = line.split(" ");
                int index = 0;
                String deadlineDescription = "";
                String by = "";
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals("/by")) {
                        index = i;
                        break;
                    }
                }
                for (int i = 1; i < index; i++) {
                    deadlineDescription = deadlineDescription + words[i] + " ";
                }
                by = words[index + 1];
                tasks[taskNumber] = new Deadline(deadlineDescription, by);
                taskNumber++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskNumber - 1]);
                System.out.println("Now you have " + taskNumber + " tasks in the list");
            } else if (line.startsWith("event")) {
                String[] words = line.split(" ");
                int index = 0;
                String eventDescription = "";
                String at = "";
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals("/at")) {
                        index = i;
                        break;
                    }
                }
                for (int i = 1; i < index; i++) {
                    eventDescription = eventDescription + words[i] + " ";
                }
                for (int i = index + 1; i < words.length; i++) {
                    at = at + words[i] + " ";
                }
                tasks[taskNumber] = new Event(eventDescription, at);
                taskNumber++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskNumber - 1]);
                System.out.println("Now you have " + taskNumber + " tasks in the list");
            }
        }
    }

    public static void main(String[] args) {
        hi();
        running();
        bye();
    }
}
