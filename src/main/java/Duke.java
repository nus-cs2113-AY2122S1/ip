import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        printMessage message = new printMessage();
        message.printIntro();
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
                System.out.println("widePeepoSad :(" + "\n" + "_______________________________________________________\n");
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
                message.printLineBreak();
                break;
            case "done":
                int whichTask = 0;
                if (!instructionTask.equals(userInput)) {
                    whichTask = Integer.parseInt(instructionTask.replaceAll("[\\D]", ""));
                }
                if (whichTask <= 0) {
                    message.printListError();
                    message.printLineBreak();
                } else if (whichTask > counter) {
                    message.printListError();
                    message.printLineBreak();
                } else {
                    input.List[whichTask - 1].markAsDone();
                    System.out.println("OK! That task has been marked as complete");
                    message.printLineBreak();
                }
                break;
            case "todo":
                input.List[counter] = new ToDos(instructionTask);
                System.out.println("The task has been added to your todo list");
                message.printLineBreak();
                counter++;
                break;
            case "deadline":
                int indexOfDeadline = instructionTask.indexOf("/by");
                String theTask = instructionTask.substring(0, indexOfDeadline - 1);
                String theDeadline = instructionTask.substring(indexOfDeadline);
                input.List[counter] = new Deadline(theTask, theDeadline);
                System.out.println("The task has been added to your deadlines");
                message.printLineBreak();
                counter++;
                break;
            case "event":
                int indexOfEvent = instructionTask.indexOf("/at");
                String theTask2 = instructionTask.substring(0, indexOfEvent - 1);
                String theEvent = instructionTask.substring(indexOfEvent);
                input.List[counter] = new Events(theTask2, theEvent);
                System.out.println("The task has been added to your events list");
                message.printLineBreak();
                counter++;
                break;
            default:
                input.List[counter] = new Task(userInput);
                message.printLineBreak();
                counter++;
                break;
            }
        }
    }
}
