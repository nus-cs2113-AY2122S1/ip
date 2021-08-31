import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_______________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_______________________________________________________");
        System.out.println("Your instructions, my Liege.");
        Information input = new Information();
        Scanner myObj = new Scanner(System.in);
        boolean condition = true;
        int counter = 0;
        while (condition) {
            String userInput = myObj.nextLine();
            String instruction = userInput.contains(" ") ? userInput.substring(0, userInput.indexOf(" ")): userInput;
            String instructionTask = userInput.contains(" ") ? userInput.substring(userInput.indexOf(" ") + 1) : userInput;

            switch (instruction) {
            case "bye":
                System.out.println("Farewell, my Lord." + "\n" + "_______________________________________________________\n");
                condition = false;
                break;
            case "list":
                for (int i = 0; i < 100; i++) {
                    if (input.List[i] == null) {
                        break;
                    } else {
                        System.out.println((i + 1) + ".[" + input.List[i].getTaskIcon() + "]" + "[" + input.List[i].getStatusIcon() + "] " + input.List[i].getDescription());
                    }
                }
                System.out.println("_______________________________________________________");
                break;
            case "done":
                int whichTask = 0;
                if (!instructionTask.equals(userInput)) {
                    whichTask = Integer.parseInt(instructionTask.replaceAll("[\\D]", ""));
                }
                if (whichTask <= 0) {
                    System.out.println("Please select a valid task from the list");
                    System.out.println("_______________________________________________________");
                } else if (whichTask > counter) {
                    System.out.println("Please select a valid task from the list");
                    System.out.println("_______________________________________________________");
                } else {
                    input.List[whichTask - 1].markAsDone();
                    System.out.println("OK! That task has been marked as complete");
                    System.out.println("_______________________________________________________");
                }
                break;
            case "todo":
                input.List[counter] = new ToDos(instructionTask);
                System.out.println("The task has been added to your todo list");
                System.out.println("_______________________________________________________");
                counter++;
                break;
            case "deadline":
                int indexOfDeadline = instructionTask.indexOf("/by");
                String theTask = instructionTask.substring(0, indexOfDeadline - 1);
                String theDeadline = instructionTask.substring(indexOfDeadline);
                input.List[counter] = new Deadline(theTask, theDeadline);
                System.out.println("The task has been added to your deadlines");
                System.out.println("_______________________________________________________");
                counter++;
                break;
            case "event":
                int indexOfEvent = instructionTask.indexOf("/at");
                String theTask2 = instructionTask.substring(0, indexOfEvent - 1);
                String theEvent = instructionTask.substring(indexOfEvent);
                input.List[counter] = new Events(theTask2, theEvent);
                System.out.println("The task has been added to your events list");
                System.out.println("_______________________________________________________");
                counter++;
                break;
            default:
                input.List[counter] = new Task(userInput);
                System.out.println(userInput + "\n" + "_______________________________________________________\n");
                counter++;
                break;
            }
        }
    }
}
