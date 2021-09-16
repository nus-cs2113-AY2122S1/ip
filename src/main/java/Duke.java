import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("...................................................");
        System.out.println("Hi! I'm Duke\n" + "How can I help make your life easier?");
        System.out.println("...................................................");
        Scanner in = new Scanner(System.in);
        String lineIn = "";
        Task[] listIn = new Task[100];
        int totalNumber = 0;
        while(!lineIn.equals("bye")) {
            lineIn = in.nextLine();
            String[] lineInput = lineIn.split(" ");
            System.out.println("...................................................");
            if (lineInput[0].equals("bye")) {
                break;
            }
            if (lineInput[0].equals("list")) {
                showTask(listIn,totalNumber);
            } else if (lineInput[0].equals("done")){
                doneTask(listIn,lineInput);
            } else if (lineInput[0].equals("event") || lineInput[0].equals("deadline") || lineInput[0].equals("todo")) {
                recordTask(listIn,lineIn,totalNumber,lineInput[0]);
                totalNumber++;
            }
        }
        System.out.println("Byebye! Have a wonderful day!");
        System.out.println("...................................................");
    }
    private static void showTask(Task[] listIn, int totalNumber) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < totalNumber; i++) {
            System.out.println((i + 1) + "." + listIn[i].toString());
        }
    }

    private static void doneTask(Task[] listIn, String[] lineInput) {
        int inputIndex = Integer.parseInt(lineInput[1]) - 1;
        listIn[inputIndex].markAsDone();
        System.out.println("Wonderful! This task is now marked as done: ");
        System.out.println(listIn[inputIndex].toString());
    }

    private static void recordTask(Task[] listIn, String lineInput, int totalNumber, String firstInput) {
        System.out.println("Got it. I've added this task:");
        if (firstInput.equals("event")) {
            listIn[totalNumber] = new Event(lineInput.substring(6, lineInput.indexOf("/")), lineInput.substring(lineInput.indexOf("/") + 3));
        } else if (firstInput.equals("deadline")) {
            listIn[totalNumber] = new Deadline(lineInput.substring(9, lineInput.indexOf("/")), lineInput.substring(lineInput.indexOf("/") + 3));
        } else {
            listIn[totalNumber] = new ToDo(lineInput.substring(5));
        }
        System.out.println(listIn[totalNumber].toString());
        System.out.println("Now you have " + (totalNumber + 1) + " tasks in your list");
        System.out.println("...................................................");
    }
}
