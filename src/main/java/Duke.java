import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;

// The Terminal

public class Duke {

    static String rootDirectory = System.getProperty("user.dir");
    static String dataLocation = "data";
    static String dataFilename = "tasks.txt";
    static TaskManager taskMgr;

    public static void main(String[] args) {


        //Initialize helper variables
        Command command;
        Action action;
        boolean isFinished = false;
        taskMgr = new TaskManager();

        try {
            loadData();
        }catch (IOException e) {
            System.out.println("There is an issue loading the data file.");
        }

        printGreeting();

        //INPUT LOOP
        while(true) {
            String userInput = getUserInput();

            try {
                command = Parser.parse(userInput);
                action = command.getAction();
            }catch(Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (action) {
            case BYE:
                isFinished = true;
                break;
                
            case LIST:
            	listTask();
                break;
                
            case DO_TASK:
                doTask(command);
                updateTaskFile();
                break;
                
            case NO_ACTION:
                break;
                
            case ADD_TASK:
                addTask(command);
                updateTaskFile();
                break;
                
            default:
            	printError();
            }

            System.out.println();
            if (isFinished)
                break;
        }

        //EXIT
        printBye();
    }
    
    private static void printBye() {
    	System.out.println("Bye. Hope to see you again soon!");
    }
    
    private static void listTask() {
        String output = taskMgr.listTasks();
        System.out.print(output);
    }

    private static void checkAndSetData() throws IOException {
        //Check if data folder exists, then check if data file exists.
        Path dataFolderPath = Paths.get(rootDirectory, dataLocation);
        Path dataFilePath = Paths.get(rootDirectory, dataLocation, dataFilename);

        boolean directoryExists = Files.exists(dataFolderPath);
        boolean dataFileExists = Files.exists(dataFilePath);

        if(!directoryExists) {
            Files.createDirectory(dataFolderPath);
        }

        if(!dataFileExists) {
            Files.createFile(dataFilePath);
        }
    }

    private static void loadData() throws IOException, NullPointerException{
        checkAndSetData();
        Path dataFilePath = Paths.get(rootDirectory, dataLocation, dataFilename);
        readDataFile(dataFilePath);
    }

    private static void readDataFile(Path filePath) throws NullPointerException, FileNotFoundException {
        if(taskMgr == null)
            throw new NullPointerException("Task Manager not instantiated yet."); //Should never throw this

        File dataFile = new File(filePath.toString());
        Scanner s = new Scanner(dataFile);
        String delimiter = ";";
        String input, type, done, name, otherArgs;
        HashMap<String, String> params;


        while(s.hasNext()) {
            params = new HashMap<>();
            input = s.nextLine().strip();
            String[] splitInput = input.split(delimiter);

            type = splitInput[0];
            done = splitInput[1];
            name = splitInput[2];

            if(splitInput.length > 3) {
                otherArgs = splitInput[3];
            }
            else {
                otherArgs = null;
            }

            params.put("task", name);
            params.put("done", done);

            switch(type) {
            case "T":
                params.put("type", "todo");
                break;
            case "D":
                params.put("type", "deadline");
                params.put("by", otherArgs);
                break;
            case "E":
                params.put("type", "event");
                params.put("at", otherArgs);
                break;
            default:
                break;
            }

            taskMgr.addTask(params);
        }
    }

    private static void updateTaskFile() {
        String fileDataToWrite = taskMgr.toFileString();

        try {
            checkAndSetData();
            Path dataFilePath = Paths.get(rootDirectory, dataLocation, dataFilename);

            FileWriter fw = new FileWriter(dataFilePath.toString());
            fw.write(fileDataToWrite);
            fw.close();
        }catch(IOException e){
            System.out.println("Cannot update task file.");
        }
    }

    private static void doTask(Command command) {
    	String indexParam = command.getParam("index");
        int index;
        
        try {
            index = Integer.parseInt(indexParam);
        } catch(NumberFormatException e) {
        	System.out.println("Index is not a number");
        	return;
        }

        try {
            Task completedTask = taskMgr.doTask(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("[%s][%s] %s\n",
                    completedTask.getTypeIcon(),
                    completedTask.getStatusIcon(),
                    completedTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void addTask(Command command) {
    	Task newTask;
        try{
            newTask = taskMgr.addTask(command.getParams());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Got it. I've added this task: ");
        System.out.printf("[%s][ ] %s\n", newTask.getTypeIcon(), newTask);

        int numOfTasks = taskMgr.getTasklistLength();
        String plural = (numOfTasks <= 1) ? "task" : "tasks";

        System.out.printf("Now you have %d %s in the list.\n", numOfTasks, plural);

    }
    
    private static void printError() {
    	System.out.println("Invalid command");
    }

    private static String getUserInput() {
        System.out.print("duke:$ ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static void printGreeting() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
    }

}