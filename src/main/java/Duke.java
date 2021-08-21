import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Duke Greeting
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        //Initialize Variables
        String strInput = "";
        Task[] taskList = new Task[100];

        //Main loop
        while (!strInput.equals("bye")) {
            strInput = in.nextLine();
            String[] splitInput = strInput.split(" "); //split input into words
            String firstWord = splitInput[0];
            switch (firstWord) {
            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            case "list":
                System.out.println("____________________________________________________________");
                for (int i = 0; i < Task.getListLength(); i++) {
                    System.out.println((i + 1) + "." + taskList[i].getStatusIcon() + " " + taskList[i].getDescr());
                }
                System.out.println("____________________________________________________________");
                break;

            case "done":
                int inputIndex = Integer.parseInt(splitInput[1]);
                taskList[inputIndex - 1].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + taskList[inputIndex - 1].getStatusIcon() + " "
                        + taskList[inputIndex - 1].getDescr());
                System.out.println("____________________________________________________________");
                break;

            default:
                taskList[Task.getListLength()] = new Task(strInput);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + strInput);
                System.out.println("____________________________________________________________");
                break;
            }
        }
    }
}
