import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws IncorrectCommandInput {
        Scanner sc = new Scanner(System.in);
        LinkedList<Task> savedTasks = new LinkedList<Task>();
        TaskList taskList = new TaskList(savedTasks);
        checkExistingOutputFile(taskList);
        printWelcomeMessage();
        String userInput = "";
        String commandInput = "";
        String checkedCommandInput = "";
        try {
            userInput = sc.nextLine();
            commandInput = identifyUserInput(userInput)[0];
            checkedCommandInput = commandInputError(commandInput);
        } catch (IncorrectCommandInput e){
            System.out.println("Invalid Command!");
        }
        while(!userInput.contains("bye")){
            switch(checkedCommandInput) {
            case "todo":
                try {
                    addToDoTask(taskList, userInput);
                    printTaskList(taskList);
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please include your description!");
                }
                break;

            case "deadline":
                try {
                    addDeadlineTask(taskList, userInput);
                    printTaskList(taskList);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please include your description!");
                }
                break;

            case "event":
                try {
                    addEventTask(taskList, userInput);
                    printTaskList(taskList);
                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please include your description!");
                }
                break;

            case "list":
                listTask(taskList);
                break;

            case "done":
                markTaskAsDone(taskList, userInput);
                break;
            case "delete":
                try {
                    deleteTaskFromList(taskList, userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please input a valid index.");
                }
                break;
            default:
                System.out.println("Invalid! Please try again.");
            }
            try {
                userInput = sc.nextLine();
                commandInput = identifyUserInput(userInput)[0];
                checkedCommandInput = commandInputError(commandInput);
            }catch (IncorrectCommandInput e){
                System.out.println("Invalid Command! Please try again");
            }
        }
        printGoodbyeMessage();
    }

    private static void deleteTaskFromList(TaskList taskList, String userInput){
        String userInputIndex = identifyUserInput(userInput)[1];
        int index = Integer.parseInt(userInputIndex);
        taskList.deleteTask(index);
        System.out.println("Item " + userInputIndex + " has been deleted");
        System.out.println("Here is the new list: ");
        listTask(taskList);
        createNewOutputFile(taskList);
    }

    /**
     * Check if there are existing saved files.
     * If not will create a new one.
     * @param taskList
     */
    public static void checkExistingOutputFile(TaskList taskList){
        try{
            Path path = Paths.get("duke.txt");
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(path.toAbsolutePath().toString()));
            String outputFileLineText = "";
            while((outputFileLineText = bufferedReader.readLine()) != null){
                String command = outputFileLineText.substring(0, 3);
                switch(command){
                case "[T]":
                    addToDoTaskFromSavedList(taskList, outputFileLineText);
                    break;
                case "[D]":
                    addDeadlineTaskFromSavedList(taskList,outputFileLineText);
                    break;
                case "[E]":
                    addEventTaskFromSavedList(taskList,outputFileLineText);
                    break;
                }
            }
            bufferedReader.close();
            listTask(taskList);
        } catch (FileNotFoundException e){
            System.out.println("There is no such file, a new file named duke.txt will be created");

        } catch(IOException e){
            System.out.println("There is no text in the file");
            System.out.println(e);
        }
    }

    /**
     * Adds todo task from saved list to program list.
     * @param taskList
     * @param userInput
     */
    private static void addToDoTaskFromSavedList(TaskList taskList, String userInput) {
        boolean completed = false;
        String taskDescription = "";
        if(userInput.contains("[X]")){
            completed = true;
            //taskDescription = userInput.split(" ", 3)[2];
        } else{
            completed = false;
            //taskDescription = userInput.split(" ", 4)[3];
        }
        taskDescription = userInput.substring(userInput.indexOf(" ", 7));
        ToDo toDoTask = new ToDo(taskDescription
                ,completed);
        taskList.addTasks(toDoTask);
        toDoTask.initialiseToDo();
    }

    /**
     * Adds Event task from saved list to program list.
     * @param taskList
     * @param userInput
     */
    private static void addEventTaskFromSavedList(TaskList taskList, String userInput) {
        boolean completed = false;
        String taskDescriptionOnly = "";
        if(userInput.contains("[X]")){
            completed = true;
            //taskDescriptionOnly = userInput.substring(userInput.indexOf(" ", 6), userInput.indexOf("/"));
        } else{
            completed = false;
            //taskDescriptionOnly = userInput.split(" ", 4)[3];
        }
        taskDescriptionOnly = userInput.substring(userInput.indexOf(" ", 7), userInput.indexOf("/"));
        Events eventTask = new Events( taskDescriptionOnly,
                completed,identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(eventTask);
        eventTask.initialiseEvent();
    }

    /**
     * Adds deadline task from saved list to program list.
     * @param taskList
     * @param userInput
     */
    private static void addDeadlineTaskFromSavedList(TaskList taskList, String userInput) {
        boolean completed = false;
        String taskDescriptionOnly = "";
        if(userInput.contains("[X]")){
            completed = true;
            //taskDescriptionOnly = userInput.substring(userInput.indexOf(" ", 6), userInput.indexOf("/"));
        } else{
            completed = false;
            //taskDescriptionOnly = userInput.split(" ", 4)[3];
        }
        taskDescriptionOnly = userInput.substring(userInput.indexOf(" ", 7), userInput.indexOf("/"));
        Deadline deadLineTask = new Deadline(taskDescriptionOnly,
                completed,identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(deadLineTask);
        deadLineTask.initialiseDeadline();
    }

    /**
     * Creates a new output file if there is no saved file
     *
     * @param taskList
     */
    public static void createNewOutputFile(TaskList taskList) {
        try {
            String dir = System.getProperty("user.dir");
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(dir + "\\duke.txt"));
            for (int i = 0; i < taskList.savedTasks.size(); i++) {
                bufferedWriter.write(taskList.savedTasks.get(i).toString() + "\n");
            }
            bufferedWriter.close();
        } catch(IOException e){
            return;
        }
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
    private static void addEventTask(TaskList taskList, String userInput) {
        boolean completed = false;
        if(userInput.contains("[X]")){
            completed = true;
        }  else {
            completed = false;
        }
        Events eventTask = new Events(
                userInput.substring(userInput.indexOf(' ',0), userInput.indexOf('/')),
        completed,"/" + identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(eventTask);
        eventTask.initialiseEvent();
    }

    /**
     * Adds a new deadline task into the list of task.
     * Displays that the task has been added into the list.
     * @param taskList
     * @param userInput
     */
    private static void addDeadlineTask(TaskList taskList, String userInput) {
        boolean completed = false;
        if(userInput.contains("[X]")){
            completed = true;
        }  else {
            completed = false;
        }
        Deadline deadLineTask = new Deadline(
                userInput.substring(userInput.indexOf(' ',0), userInput.indexOf('/'))
                ,completed,"/" + identifyDeadlineCommand(userInput)[1]);
        taskList.addTasks(deadLineTask);
        deadLineTask.initialiseDeadline();
    }

    /**
     * Locates the task in the list and take note that it is completed.
     * @param taskList
     * @param userInput
     */
    private static void markTaskAsDone(TaskList taskList, String userInput) {
        int taskListIndex;
        Task currentTask;
        try{
            taskListIndex = Integer.parseInt(identifyUserInput(userInput)[1]);
            currentTask = taskList.findTask(taskListIndex);
            currentTask.markTaskAsDone();
            createNewOutputFile(taskList);}
        catch(NumberFormatException e){
            System.out.println("Please choose a viable task");
        }
    }

    /**
     * Prints out the goodbye message.
     */
    private static void printGoodbyeMessage() {
        String goodbyeMessage = "______________________________\n"
                + "Bye! Hope to see you again soon!\n"
                + "______________________________\n";
        System.out.print(goodbyeMessage);
    }

    /**
     * Prints out the welcome message.
     */
    private static void printWelcomeMessage() {
        String welcomeMessage = "______________________________\n"
                + "Hello! I'm Friday\n"
                + "What are you doing today?\n"
                + "______________________________\n";
        System.out.print(welcomeMessage);
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
    private static void printTaskList(TaskList taskList) {
        System.out.println("Now you have " + taskList.countTaskInList()
                + " tasks in the list");
        System.out.println("______________________________\n");
        createNewOutputFile(taskList);
    }

    /**
     * Takes the user input and identify the index which,
     * the user wants to mark as completed.
     *
     * @param userInput
     * @return taskIndex.
     */
    public static String[] identifyUserInput(String userInput){
        String[] parts = userInput.split(" ");
        return parts;
    }

    /**
     * Takes the user input and identify the dateline which,
     * the user wants the task to be.
     *
     * @param userInput
     * @return String array of string split at "/".
     */
    public static String[] identifyDeadlineCommand(String userInput){
        String[] parts = userInput.split("/");
        return parts;
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
        case "deadline":
            return userInput;
        default:
            throw new IncorrectCommandInput();
        }
    }
}
