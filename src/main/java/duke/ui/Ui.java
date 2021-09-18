package duke.ui;

import duke.command.Command;
import duke.command.CommandResult;
import duke.Duke;

import java.util.Scanner;

/**
 * Managing output messages and reading inputs from the user.
 */
public class Ui {
    private static final String LOGO = "    ____        _        \n"
            + "   |  _ \\ _   _| | _____ \n"
            + "   | | | | | | | |/ / _ \\\n"
            + "   | |_| | |_| |   <  __/\n"
            + "   |____/ \\__,_|_|\\_\\___|\n";

    private static final String BORDER_LINE = "------------------------------------------------";
    
    private static Scanner in = new Scanner(System.in);
    
    public Ui() {
    }
    
    public void printWelcomeMessage() {
        System.out.println(LOGO + System.lineSeparator()
                + BORDER_LINE + System.lineSeparator()
                + "    Hello!, I'm Duke" + System.lineSeparator()
                + "    How can I help you?" + System.lineSeparator()
                + BORDER_LINE);
    }

    public void printGoodbyeMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Bye, see you again!" + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public void printInvalidTaskInFileMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK DETECTED" + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public Command getCommand() {
        String userInput = in.nextLine();
        String[] words = userInput.split(" ", 2);
        try {
            return new Command(words[0].toLowerCase(), words[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Command(words[0].toLowerCase(), CommandResult.BLANK_DESCRIPTION);
        }
    }

    private void printInvalidAddTaskMessage(CommandResult result) {
        String description = result.getResultDescription();
        switch (description) {
        case CommandResult.INVALID_TODO:
            description = "TODO FORMAT: todo [description]" + System.lineSeparator()
                    + "      NOTE: DO NOT INCLUDE \"--\" IN YOUR TASK DESCRIPTION";
            break;
        case CommandResult.INVALID_DEADLINE:
            description = "DEADLINE FORMAT: deadline [description] /by [date]" + System.lineSeparator()
                    + "      WITH DATE FORMAT YYYY-MM-DD" + System.lineSeparator()
                    + "      NOTE: DO NOT INCLUDE \"--\" IN YOUR TASK DESCRIPTION";
            break;
        case CommandResult.INVALID_EVENT:
            description = "EVENT FORMAT: event [description] /at [date]" + System.lineSeparator()
                    + "      WITH DATE FORMAT YYYY-MM-DD" + System.lineSeparator()
                    + "      NOTE: DO NOT INCLUDE \"--\" IN YOUR TASK DESCRIPTION";
            break;
        default:
            description = "SOMETHING WENT WRONG";
        }
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK DESCRIPTION PROVIDED" + System.lineSeparator()
                + "      " + description + System.lineSeparator()
                + BORDER_LINE);
    }

    private void printInvalidTaskNotExistedMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK NUMBER PROVIDED" + System.lineSeparator()
                + BORDER_LINE);
    }
    
    private void printInvalidTaskAlreadyDoneMessage(CommandResult result) {
        int taskNumber = Integer.parseInt(result.getCommand().getCommandDescription());
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Task " + taskNumber + " has already been marked as done!" + System.lineSeparator()
                + BORDER_LINE);
    }

    private void printTaskList() {
        int taskSize = Duke.getTaskList().getTasks().size();
        System.out.println(BORDER_LINE);
        if (taskSize == 0) {
            System.out.println("    The list is currently empty!");
        } else {
            for (int i = 0; i < taskSize; i++) {
                System.out.println("    " + (i + 1) + "." + Duke.getTaskList().getTasks().get(i));
            }
        }
        System.out.println(BORDER_LINE);
    }
    
    private void printMarkDoneMessage(CommandResult result) {
        int taskNumber = Integer.parseInt(result.getCommand().getCommandDescription());
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    The following task is now marked as done:" + System.lineSeparator()
                + "      " + Duke.getTaskList().getTasks().get(taskNumber - 1) + System.lineSeparator()
                + BORDER_LINE);
    }

    private void printAddTaskMessage() {
        int taskSize = Duke.getTaskList().getTasks().size();
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Task added: " + System.lineSeparator()
                + "      " + Duke.getTaskList().getTasks().get(taskSize - 1) + System.lineSeparator()
                + "    You have " + taskSize + " tasks in the list." + System.lineSeparator()
                + BORDER_LINE);
    }

    private void printDeleteTaskMessage(String taskDescription) {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    The following task is removed from the list: " + System.lineSeparator()
                + "      " + taskDescription + System.lineSeparator()
                + "    You now have " + Duke.getTaskList().getTasks().size() + " tasks in the list." + System.lineSeparator()
                + BORDER_LINE);
    }
    
    private void printTaskListWithKeyword(String keyword) {
        int taskSize = Duke.getTaskList().getTasks().size();
        System.out.println(BORDER_LINE);
        if (taskSize == 0) {
            System.out.println("    The list is currently empty!");
        } else {
            int taskWithKeywordCount = 0;
            System.out.println("    Finding tasks with keyword \"" + keyword + "\":");
            for (int i = 0; i < taskSize; i++) {
                String taskDescription = Duke.getTaskList().getTasks().get(i).getDescription();
                if (taskDescription.contains(keyword)) {
                    System.out.println("    " + (taskWithKeywordCount + 1) + "." + Duke.getTaskList().getTasks().get(i));
                    taskWithKeywordCount++;
                }  
            }
            if (taskWithKeywordCount == 0) {
                System.out.println("    There is no task containing the keyword \"" + keyword + "\"");
            }
        }
        System.out.println(BORDER_LINE);
    }

    private void printInvalidCommandMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID COMMAND" + System.lineSeparator()
                + BORDER_LINE);
    }

    /**
     * Print successful command result.
     * 
     * @param result command result from user's input.
     */
    private void printSuccessCommandResult(CommandResult result) {
        String commandType = result.getCommand().getCommandType();
        switch (commandType) {
        case Command.COMMAND_LIST:
            printTaskList();
            break;
        case Command.COMMAND_DONE:
            printMarkDoneMessage(result);
            break;
        case Command.COMMAND_ADD_TODO:
        case Command.COMMAND_ADD_DEADLINE:
        case Command.COMMAND_ADD_EVENT:
            printAddTaskMessage();
            break;
        case Command.COMMAND_DELETE:
            printDeleteTaskMessage(result.getResultDescription());
            break;
        case Command.COMMAND_FIND:
            printTaskListWithKeyword(result.getResultDescription());
            break;    
        case Command.COMMAND_EXIT:
            break;
        default:
            printInvalidCommandMessage();
            break;
        }
    }

    /**
     * Print command result that failed to be executed.
     * 
     * @param result command result from user's input.
     */
    private void printFailCommandResult(CommandResult result) {
        String commandType = result.getCommand().getCommandType();
        switch (commandType) {
        case Command.COMMAND_DONE:
            switch (result.getResultDescription()) {
            case CommandResult.INVALID_NUMBER:
                printInvalidTaskNotExistedMessage();
                break;
            case CommandResult.INVALID_TASK_ALREADY_DONE:
                printInvalidTaskAlreadyDoneMessage(result);
                break;
            }
            break;
        case Command.COMMAND_DELETE:
            printInvalidTaskNotExistedMessage();
            break;
        case Command.COMMAND_ADD_TODO:
        case Command.COMMAND_ADD_DEADLINE:
        case Command.COMMAND_ADD_EVENT:
            printInvalidAddTaskMessage(result);
            break;
        default:
            printInvalidCommandMessage();
            break;
        }
    }

    /**
     * Print command result of each user's command.
     * 
     * @param result command result from user's input.
     */
    public void printCommandResult(CommandResult result) {
        String commandResult = result.getResult();
        switch (commandResult) {
        case CommandResult.EXECUTION_SUCCESS:
            printSuccessCommandResult(result);
            break;
        case CommandResult.EXECUTION_FAIL:
            printFailCommandResult(result);
            break;
        default:
            System.out.println("SOMETHING WENT WRONG");
            break;
        }
    }
}
