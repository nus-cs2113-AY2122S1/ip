package duke;

import duke.commands.*;
import duke.tasks.*;

public class Parser {
    public static Command parse(String fullCommand) {
        String[] inputArray = fullCommand.split(" ", 2);
        String inputCommand = inputArray[0];
        String inputAction = null;
        if (inputArray.length > 1) {
            inputAction = inputArray[1];
        }
        switch (inputCommand) {
            case "":
                break; //to give something else
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "todo":
                if (inputAction != null) {
                    Todo newTodo = new Todo(inputAction);
                    return new AddCommand(newTodo);
                }
                System.out.println("INVALID: Empty 'todo' input");
                break;
            case "deadline":
                try {
                    String DeadlineToDo = inputAction.split("/", 2)[0];
                    String dueDate = inputAction.split("/by", 2)[1];
                    Deadline newDeadline = new Deadline(DeadlineToDo, dueDate);
                    return new AddCommand(newDeadline);
                }
                catch (NullPointerException e){
                    System.out.println("INVALID: Empty 'deadline' input");
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("INVALID: Insufficient 'deadline' description or time");
                }
                break;
            case "event":
                try {
                    String EventToDo = inputAction.split("/", 2)[0];
                    String dueTime = inputAction.split("/at", 2)[1];
                    Event newEvent = new Event(EventToDo, dueTime);
                    return new AddCommand(newEvent);
                }
                catch (NullPointerException e){
                    System.out.println("INVALID: Empty 'event' description");
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("INVALID: Insufficient 'event' description or time");
                }
                break;
            case "done":
                try {
                    return new DoneCommand(Integer.parseInt(inputAction) - 1);
                }
                catch (NullPointerException e){
                    System.out.println("INVALID: Empty 'done' index");
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("INVALID: Index out of bounds");
                }
                break;
            case "delete":
                try {
                    return new DeleteCommand(Integer.parseInt(inputAction) - 1);
                }
                catch (NullPointerException e){
                    System.out.println("INVALID: Empty 'delete' index");
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("INVALID: Index out of bounds");
                }
                break;
            case "find":
                try {
                    return new FindCommand(inputAction);
                }
                catch (NullPointerException e){
                    System.out.println("INVALID: Empty 'find' keyword");
                }
                break;
            default:
                System.out.println("INVALID: Invalid command");
                break;
        }
        return null;
    }
}
