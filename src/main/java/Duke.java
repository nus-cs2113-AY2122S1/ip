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
            }
        }
    }
}

