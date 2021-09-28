package duke;

/**
 * This class deals with making sense of the user command and calling the necessary methods to execute the user command
 */
public class Parser {

    /**
     * This method determines what command the User inputs and calls the necessary methods and creates the necessary Task objects
     * to be stored in the Task ArrayList
     * @param command The input from the User
     */
    public static void distinguishCommand(String command) {
        //split into word array
        String[] words = command.split(" ");
        String firstWord = words[0];

        //determine command
        switch (firstWord) {
        case "list":
            TaskList.printList();
            break;
        case "done":
            try {
                TaskList.markTaskDone(words[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a done cannot be empty.");
            }
            break;
        case "todo":
            try {
                ToDos newToDo = new ToDos(command.substring(5));
                TaskList.addTask(newToDo);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                Deadlines newDeadline = TaskList.createNewDeadline(command.substring(9));
                TaskList.addTask(newDeadline);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case "event":
            //extract out event description and timeAllocation
            try {
                Events newEvent = TaskList.createNewEvent(command.substring(6));
                TaskList.addTask(newEvent);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            }
            break;
        case "delete":
            try {
                TaskList.deleteTask(words[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            }
            break;
        case "find":
            try {
                TaskList.findTask(command.substring(5));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            }
            break;
        default:
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");

            break;
        }


    }





}
