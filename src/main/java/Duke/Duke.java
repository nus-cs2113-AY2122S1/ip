package Duke;

import Exceptions.InsufficientParametersException;
import Exceptions.UnknownCommandException;

public class Duke {

    public static String handleInput(TaskManager taskManager, String[] input)
            throws UnknownCommandException, InsufficientParametersException {
        switch (input[0]) {
        case "bye":
            return "bye";
        case "list":
            return taskManager.listTasks();
        case "done":
            if (input.length < 2) {
                throw new InsufficientParametersException();
            }
            return taskManager.markAsDone(Integer.parseInt(input[1]));
        case "todo":
            if (input.length < 2) {
                throw new InsufficientParametersException();
            }
            return taskManager.addTodoTask(input[1]);
        case "deadline":
            if (input.length < 2 || !input[1].contains("/by")) {
                throw new InsufficientParametersException();
            }
            return taskManager.addDeadlineTask(input[1]);
        case "event":
            if (input.length < 2 || !input[1].contains("/at")) {
                throw new InsufficientParametersException();
            }
            return taskManager.addEventTask(input[1]);
        case "help":
            return taskManager.showCommandHelp();
        default:
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
<<<<<<< HEAD
        taskManager.greet();
        Scanner line = new Scanner(System.in);
        while(true) {
            String[] input = line.nextLine().split(" ", 2);
            switch (input[0]) {
            case "bye":
                taskManager.exitMessage();
                System.exit(0);
            case "list":
                taskManager.listTasks();
                break;
            case "done":
                try {
                    taskManager.markAsDone(Integer.parseInt(input[1]));
                } catch (NumberFormatException e) {
                    System.out.println("You did not enter a number");
                }
                break;
            case "todo":
                taskManager.addTodoTask(input[1]);
                break;
            case "deadline":
                taskManager.addDeadlineTask(input[1]);
                break;
            case "event":
                taskManager.addEventTask(input[1]);
                break;
            default:
                taskManager.handleUnknownCommand();
                break;
=======
        UserInterface userInterface = new UserInterface();
        userInterface.printGreeting();
        while (true) {
            String[] input = userInterface.getUserInput().split(" ", 2);
            try {
                String infoForUser = handleInput(taskManager, input);
                if (infoForUser == "bye") {
                    userInterface.printGoodbye();
                    System.exit(0);
                } else {
                    userInterface.printInfo(infoForUser);
                }
            } catch (UnknownCommandException e) {
                userInterface.printUnknownCommand();
            } catch (NumberFormatException e) {
                userInterface.printNotNumber();
            } catch (InsufficientParametersException e) {
                UserInterface.printInsufficientParameters();
>>>>>>> master
            }
        }
    }
}

