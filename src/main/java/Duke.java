import parser.Action;
import parser.Command;
import parser.Parser;
import task.Task;
import task.TaskManager;

import java.util.Scanner;

// The Terminal

public class Duke {
	

    static TaskManager taskMgr;

    public static void main(String[] args) {
        //Initialize helper variables
        Command command;
        Action action;
        boolean isFinished = false;
        taskMgr = new TaskManager();

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
                break;
                
            case NO_ACTION:
                break;
                
            case ADD_TASK:
                addTask(command);
                break;

            case DELETE_TASK:
                removeTask(command);
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
            System.out.printf("%s\n", completedTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeTask(Command command) {
        String indexParam = command.getParam("index");
        int index;

        try {
            index = Integer.parseInt(indexParam);
        } catch(NumberFormatException e) {
            System.out.println("Index is not a number");
            return;
        }

        try {
            Task completedTask = taskMgr.removeTask(index);
            System.out.println("Noted I've removed this task:");
            System.out.printf("%s\n", completedTask);
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
        System.out.printf("%s\n", newTask);

        int numOfTasks = taskMgr.getTasklistLength();
        String plural = (numOfTasks <= 1) ? "task" : "tasks";

        System.out.printf("Now you have %d %s in the list.\n\n", numOfTasks, plural);

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