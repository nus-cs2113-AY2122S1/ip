import exceptions.*;
import todo.Deadline;
import todo.Event;
import todo.Task;
import todo.ToDo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

//Check if valid command (Done, List, Bye, Action)
//If done, mark as done
//If action, check what action is it
//If there is a date, what is the date

public class Duke {

    private static int tasksTotal = 0;
    private static int tasksDone = 0;
    private static Task[] tasks;
    private static String[] commands = {"todo", "event", "deadline"};
    private static String filePath = "duke.txt";

    public static void main(String[] args) {
        int LIST_SIZE = 100;
        String line = "";
        tasks = new Task[LIST_SIZE];
        showWelcomeScreen();
        //readFile();
        while (tasksTotal <= LIST_SIZE && !line.contains("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            assert line != null;
            String[] words = line.split(" ", 2);
            try{
                checkValidCommand(words);
            } catch (InvalidCommandError e) {
                System.out.println("Invalid command. Try again!");
            }

        }
        return;
    }

    private static void readFile() {
        try {
            readFileContents(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void readFileContents (String filePath) throws FileNotFoundException{
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            ;
        }
    }

    private static void checkValidCommand(String[] words) throws InvalidCommandError {
        String command = words[0];

        if (command.equals("done")) { //Checks what is the command
            int index = Integer.parseInt(words[1]);
            tasks[index].setDone(true);
            completeSuccess(index);
        } else if (command.equals("list")) {
            listAllTask();
        } else if (command.equals("bye")) {
            showByeScreen();
            return;
        } else if (command.equals("save")) {
            try {
                saveTasks(filePath, tasks);
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }
            
        } else { //is a valid task
            checkValidInput(words);
        }
    }

    private static void checkValidInput(String[] words) {
        try{
            splitInput(words);
        } catch (EmptyDescriptionError e) {
            System.out.println("Missing description!!!");
            Task.printDivider();
        } catch (MissingDateError e) {
            System.out.println("Missing date!!");
            Task.printDivider();
        } catch (InvalidDescriptionError e) {
            System.out.println("Description cannot be whitespace!!");
            Task.printDivider();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.print("Something went wrong! Please try again!");
            Task.printDivider();
        } catch (EmptyDateError e) {
            System.out.println("Date cannot be empty!");
            Task.printDivider();
        }
    }


    private static void listAllTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasksTotal; i++) {
            System.out.println(i + ". " + tasks[i].toString());
        }
        getTasksLeft();
        Task.printDivider();
    }

    private static void splitInput(String[] words)
            throws EmptyDescriptionError, MissingDateError, InvalidDescriptionError, EmptyDateError {
        String task = null;
        String date = null;
        String type = words[0];
        if(words.length <= 1) {
            throw new EmptyDescriptionError();
        } else if(checkEmptyDescription(words[1])) {
            throw new InvalidDescriptionError(); //check that description is not just whitespaces
        }

        String[] taskDescription = words[1].split("/", 2); //removes command and splits into action and date
        if(taskDescription.length <= 1) {
            if(type.equals("todo")){ //todo command
                task = words[1];
            } else { //any other commands which requires dates
                throw new MissingDateError();
            }
        }
        else { //have action and date
            String[] inputDate = taskDescription[1].split(" ", 2); //extract date without prefix
            if(checkEmptyDate(inputDate)) {
                throw new EmptyDateError();
            }
            task = taskDescription[0].trim();
            date = inputDate[1];
        }

        checkValidAction(words, task, date, type);
    }

    private static Boolean checkEmptyDate(String[] input) {
        if(input.length == 1 || input[1].trim().length() == 0) {
            return true;
        }
        return false;
    }

    private static Boolean checkEmptyDescription(String word) {
        return word.trim().length() == 0;
    }


    private static void checkValidAction(String[] words, String task, String date,
                                         String type) {
        if (type.equals("deadline")) {
            tasksTotal++;
            tasks[tasksTotal] = new Deadline(task, date);
            addSuccess();
        } else if (type.equals("event")) {
            tasksTotal++;
            tasks[tasksTotal] = new Event(task, date);
            addSuccess();
        } else if (type.equals("todo")) {
            tasksTotal++;
            tasks[tasksTotal] = new ToDo(task, date);
            addSuccess();
        } else { //invalid command
            Task.printDivider();
            System.out.println("Invalid command, try again.");
            Task.printDivider();
        }
    }

    private static void saveTasks(String filePath, Task[] tasks) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        String stringToAdd = "";
        String taskDescription, type, date;
        Boolean isDone;
        for(int i = 1; i <= tasksTotal; i++) {
            taskDescription = tasks[i].toString().split(" ", 3)[1];
            type = tasks[i].getType();
            isDone = tasks[i].getStatus();
            stringToAdd +=  type+ "|" + isDone + "|" + taskDescription;
            if(type == "e" || type == "d") {
                String[] splitString = tasks[i].toString().split(":", 2);
                String removedSpace = splitString[1].trim();
                date = removedSpace.replace(")", "");
                stringToAdd += "|" + date;
            }
            stringToAdd += System.lineSeparator();
        }
        System.out.println(stringToAdd);
        fw.write(stringToAdd);
        fw.close();
    }

    private static void addSuccess() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[tasksTotal].toString());
        getTasksLeft();
        Task.printDivider();
    }

    private static void completeSuccess(int index) {
        Task.printDivider();
        System.out.println("Got it. I've marked this task as complete:");
        System.out.println(tasks[index].toString());
        tasksDone++;
        getTasksLeft();
        Task.printDivider();
    }

    private static void getTasksLeft() {
        System.out.println("Now you have " + (tasksTotal - tasksDone) + " tasks in the list.");
    }

    private static void showByeScreen() {
        Task.printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        Task.printDivider();
    }

    private static void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Task.printDivider();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?\n");
        Task.printDivider();
    }
}

