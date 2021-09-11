
import FileManager.FileReader;
import FileManager.FileSaver;
import InputHandle.Tasks.TaskList;
import InputHandle.command.*;
import InputHandle.exception.CommandNotExistException;
import InputHandle.exception.DukeException;
import InputHandle.exception.TaskIndexMissingException;


import java.util.Scanner;


public class UserServer {
    private String userName;
    private TaskList userTasks;
    private Scanner sc = new Scanner(System.in);

    private static final String DIVISIONLINE = "    ____________________________________________________________\n";
    private String GREETINGS1 = "     Hello! ";
    private String GREETINGS2 = "\n" + "     What can I do for you?\n";


    public UserServer(String userName) {
        this.userName = userName;
        this.userTasks = new TaskList();
    }

    public UserServer(String userName, TaskList tasksList) {
        this.userName = userName;
        if (tasksList != null) {
            this.userTasks = tasksList;
        } else {
            this.userTasks = new TaskList();
        }
    }

    public void serviceBegin() {
        System.out.print(DIVISIONLINE + GREETINGS1 + userName + GREETINGS2 + DIVISIONLINE);
        String userInput;
        UserCommand input;

        do {
            userInput = readInput();
            try {
                input = handleCommand(userInput);
            } catch (DukeException e) {
                System.out.print(DIVISIONLINE);
                System.out.println(e);
                System.out.print(DIVISIONLINE);
                input = null;
            }

            if (input != null) {
                commandExecute(input);
            }

        } while (! (input instanceof QuitCommand));
        sc.close();
    }

    public void save(String userName) {
        FileSaver saver = new FileSaver(userName);
        saver.save(userTasks);
    }

    public void save() {
        FileSaver saver = new FileSaver();
        saver.save(userTasks);
    }


    private void commandExecute(UserCommand input) {
        System.out.print(DIVISIONLINE);
        try {
            input.execute();
        } catch (DukeException e) {
            System.out.println(e);
        }
        System.out.print(DIVISIONLINE);
    }

    private String readInput() {
        return sc.nextLine();
    }


    private UserCommand handleCommand(String userInput) throws DukeException {
        String[] inputSplits = userInput.split(" ");
        UserCommand input;

        switch (inputSplits[0]) {
        case "bye":
            input = new QuitCommand();
            break;
        case "list":
            input = new ListCommand(userTasks);
            break;
        case "done": case "delete":
            try {
                if (inputSplits[0].equals("done")) {
                    input = new DoneCommand(Integer.parseInt(inputSplits[1]), userTasks);
                } else {
                    input = new DeleteCommand(Integer.parseInt(inputSplits[1]), userTasks);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TaskIndexMissingException();
            }
            break;
        case "todo" : case "deadline" : case "event":
            input = new AddTaskCommand(userInput, userTasks);
            break;
        default:
            throw new CommandNotExistException();
        }
        return input;
    }
}
