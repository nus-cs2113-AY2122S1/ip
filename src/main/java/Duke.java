import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws IncorrectCommandInput {
        Scanner sc = new Scanner(System.in);
        LinkedList<Task> savedTasks = new LinkedList<Task>();
        LinkedList<Task> filteredTasks = new LinkedList<Task>();
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList taskList = new TaskList(savedTasks);
        Storage storage = new Storage(taskList, parser);
        taskList = storage.load("duke.txt");
        ui.printWelcomeMessage();
        String userInput = "";
        String commandInput = "";
        String checkedCommandInput = "";
        try {
            userInput = sc.nextLine();
            commandInput = parser.identifyUserInput(userInput)[0];
            checkedCommandInput = commandInputError(commandInput);
        } catch (IncorrectCommandInput e){
            System.out.println("Invalid Command!");
        }
        while(!userInput.contains("bye")){
            switch(checkedCommandInput) {
            case "todo":
                try {
                    addToDoTask(taskList, userInput);
                    printTaskList(taskList, storage);
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please include your description!");
                }
                break;

            case "deadline":
                try {
                    addDeadlineTask(taskList, userInput, parser);
                    printTaskList(taskList, storage);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please include your description!");
                }
                break;

            case "event":
                try {
                    addEventTask(taskList, userInput, parser);
                    printTaskList(taskList, storage);
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please include your description!");
                }
                break;

            case "list":
                listTask(taskList);
                break;

            case "find":
                TaskList filteredTaskList = new TaskList(filteredTasks);
                findTaskByKeyword(taskList, parser.identifyKeyword(userInput), filteredTaskList);
                ui.separator();
                ui.keywordListString();
                listTask(filteredTaskList);
                ui.separator();
                filteredTaskList.clearList();
                break;

            case "done":
                markTaskAsDone(taskList, userInput, storage, parser);
                break;
            case "delete":
                try {
                    deleteTaskFromList(taskList, userInput, storage, parser);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please input a valid index.");
                }
                break;
            default:
                System.out.println("Invalid! Please try again.");
            }
            try {
                userInput = sc.nextLine();
                commandInput = parser.identifyUserInput(userInput)[0];
                checkedCommandInput = commandInputError(commandInput);
            }catch (IncorrectCommandInput e){
                System.out.println("Invalid Command! Please try again");
            }
        }
        ui.printGoodbyeMessage();
    }

    /**
     * Delete task from the list.
     * @param taskList
     * @param userInput
     * @param storage
     * @param parser
     */
    private static void deleteTaskFromList(TaskList taskList, String userInput, Storage storage, Parser parser){
        String userInputIndex = parser.identifyUserInput(userInput)[1];
        int index = Integer.parseInt(userInputIndex);
        taskList.deleteTask(index);
        System.out.println("Item " + userInputIndex + " has been deleted");
        System.out.println("Here is the new list: ");
        listTask(taskList);
        storage.updateOutputFile(taskList);
    }


    /**
     * Adds a new todo task into the list of task.
     * Displays that the task has been added into the list.
     * @param taskList
     * @param userInput
     */
    private static void addToDoTask(TaskList taskList, String userInput) {
        boolean completed = false;
        if(userInput.contains("[X]")){
            completed = true;
        }  else {
            completed = false;
        }
        ToDo toDoTask = new ToDo(userInput.substring(userInput.indexOf(' ',0))
                ,completed);
        taskList.addTasks(toDoTask);
        toDoTask.initialiseToDo();
    }

    /**
     * Adds a new event task into the list of task.
     * Displays that the task has been added into the list.
     * @param taskList
     * @param userInput
     */
    private static void addEventTask(TaskList taskList, String userInput, Parser parser) {
        Events eventTask = new Events(parser.identifyUserTask(userInput),
                false,"/" + parser.identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(eventTask);
        eventTask.initialiseEvent();
    }
    /**
     * Adds a new deadline task into the list of task.
     * Displays that the task has been added into the list.
     * @param taskList
     * @param userInput
     */
    private static void addDeadlineTask(TaskList taskList, String userInput, Parser parser) {
        Deadline deadLineTask = new Deadline(parser.identifyUserTask(userInput),
                false,"/" + parser.identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(deadLineTask);
        deadLineTask.initialiseDeadline();
    }

    /**
     * Locates the task in the list and take note that it is completed.
     * @param taskList
     * @param userInput
     */
    private static void markTaskAsDone(TaskList taskList, String userInput, Storage storage, Parser parser) {
        int taskListIndex;
        Task currentTask;
        try{
            taskListIndex = Integer.parseInt(parser.identifyUserInput(userInput)[1]);
            currentTask = taskList.findTask(taskListIndex);
            currentTask.markTaskAsDone();
            storage.updateOutputFile(taskList);}
        catch(NumberFormatException e){
            System.out.println("Please choose a viable task");
        }
    }

    /**
     * Prints out all the tasks in the list.
     * @param taskList
     */
    private static void listTask(TaskList taskList) {
        System.out.println("______________________________\n");
        taskList.listTasks();
        System.out.println("______________________________\n");
    }

    /**
     * Prints the current number of task in the lists.
     * @param taskList
     */
    private static void printTaskList(TaskList taskList, Storage storage) {
        System.out.println("Now you have " + taskList.countTaskInList()
                + " tasks in the list");
        System.out.println("______________________________\n");
        storage.updateOutputFile(taskList);
    }

    /**
     * Find Task by Keyword
     * @param taskList
     * @param keyword
     * @param keywordTaskList
     */
    private static void findTaskByKeyword(TaskList taskList, String keyword,
                                          TaskList keywordTaskList){
        for(int i = 0; i < taskList.countTaskInList(); i++){
            if(taskList.findTask(i).toString().contains(keyword)){
                keywordTaskList.addTasks(taskList.findTask(i));
            }
        }
    }


    /**
     * Used to check if the input command is valid or not.
     * @param userInput
     * @return
     * @throws IncorrectCommandInput if input is not valid.
     */
    public static String commandInputError(String userInput) throws IncorrectCommandInput {
        switch(userInput) {
        case "todo":
        case "event":
        case "list":
        case "bye":
        case "done":
        case "delete":
        case "find":
        case "deadline":
            return userInput;
        default:
            throw new IncorrectCommandInput();
        }
    }
}
