package Duke;

import Duke.Exception.DukeException;
import Duke.Task.Deadline;
import Duke.Task.Events;
import Duke.Task.ToDos;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        printMessage message = new printMessage();
        message.printIntro();
        Information input = new Information();
        Scanner myObj = new Scanner(System.in);
        boolean condition = true;
        int counter = 0;

        //The main loop where all the input cases are run through, if there was no specific command given, the statement is added as a task to the list.
        while (condition) {
            String userInput = myObj.nextLine();
            String instruction = userInput.contains(" ") ? userInput.substring(0, userInput.indexOf(" ")): userInput;
            String instructionTask = userInput.contains(" ") ? userInput.substring(userInput.indexOf(" ") + 1) : " ";

            switch (instruction) {
            case "bye": // When the command bye is given, the program exits the while loop and stops
                System.out.println("widePeepoSad :(");
                message.printLineBreak();
                condition = false;
                break;
            case "list": // Lists out all the current tasks in the order that they were added in
                for (int i = 0; i < input.List.size(); i++) {
                    System.out.println((i + 1) + ".[" + input.List.get(i).getTaskIcon() + "]" + "[" + input.List.get(i).getStatusIcon() + "] " + input.List.get(i).getDescription());
                }
                message.printLineBreak();
                break;
            case "done": // Has to be input with an integer value indicating the specific task to be marked as completed
                try {
                    int whichTask = Integer.parseInt(instructionTask.replaceAll("[\\D]", ""));
                    if (whichTask <= 0) {
                        message.printListError();
                        message.printLineBreak();
                    } else if (whichTask > counter) {
                        message.printListError();
                        message.printLineBreak();
                    } else {
                        input.List.get(whichTask - 1).markAsDone();
                        System.out.println("YAY! That task has been marked as complete");
                        message.printLineBreak();
                    }
                } catch (NumberFormatException e) {
                    message.printListError();
                    message.printLineBreak();
                }
                break;
            case "todo": // Adds a task to the list that is marked as to be done.
                try {
                    if (instructionTask.isEmpty() || instructionTask.equals(" ")) {
                        throw new DukeException();
                    }
                    input.List.add(counter, new ToDos(instructionTask));
                    System.out.println("Nice! The task has been added to your todo list");
                    message.printLineBreak();
                    counter++;
                } catch (DukeException e) {
                    message.printEmptyDescription();
                    message.printLineBreak();
                }
                break;
            case "deadline": // Adds a task to the list that has a deadline that it needs to be completed by, using "/by" as the input command
                try {
                    if (!instructionTask.contains("/by")) {
                        throw new DukeException();
                    }
                    int indexOfDeadline = instructionTask.indexOf("/by");
                    String theTask = instructionTask.substring(0, indexOfDeadline - 1);
                    String theDeadline = instructionTask.substring(indexOfDeadline);
                    input.List.add(counter, new Deadline(theTask, theDeadline));
                    System.out.println("Nice! The task has been added to your deadlines");
                    message.printLineBreak();
                    counter++;
                } catch (DukeException e) {
                    message.printMissingDeadline();
                    message.printLineBreak();
                }
                break;
            case "event": // Adds a task to the list that will occur in the future by a given date, using "/at" as the input command.
                try {
                    if (!instructionTask.contains("/at")) {
                        throw new DukeException();
                    }
                    int indexOfEvent = instructionTask.indexOf("/at");
                    String theTask2 = instructionTask.substring(0, indexOfEvent - 1);
                    String theEvent = instructionTask.substring(indexOfEvent);
                    input.List.add(counter, new Events(theTask2, theEvent));
                    System.out.println("Nice! The task has been added to your events list");
                    message.printLineBreak();
                    counter++;
                } catch (DukeException e) {
                    message.printMissingEvent();
                    message.printLineBreak();
                }
                break;
            case "help":
                message.printHelp();
                message.printLineBreak();
                break;
            case "delete":
                try {
                    if (instructionTask.isEmpty() || instructionTask.equals(" ")) {
                        throw new DukeException();
                    }
                    int number = Integer.parseInt(instructionTask);
                    input.List.remove(number - 1);
                    System.out.println("Ok! The task has been deleted from your list");
                    message.printLineBreak();
                } catch (DukeException | NumberFormatException | IndexOutOfBoundsException e) {
                    message.printListError();
                    message.printLineBreak();
                }
                break;
            default: // When no specific command is selected, the input is added automatically to the list of tasks.
                message.printIntructionsUnclear();
                message.printLineBreak();
                break;
            }
        }
    }
}
