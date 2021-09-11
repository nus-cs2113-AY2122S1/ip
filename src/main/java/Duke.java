import exceptions.*;
import todo.Deadline;
import todo.Event;
import todo.Task;
import todo.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

//Check if valid command (Done, List, Bye, Action)
//If done, mark as done
//If action, check what action is it
//If there is a date, what is the date

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<String> commands = new ArrayList<>();

    public static void main(String[] args) {
        String line = "";
        showWelcomeScreen();
        addCommands();
        while (!line.contains("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            assert line != null;
            String[] words = line.split(" ", 2);
            try{
                checkValidCommand(words);
            } catch (InvalidCommandError e) {
                printDivider();
                System.out.println("Invalid command. Try again!");
                printDivider();
            }

        }
        return;
    }

    private static void addCommands() {
        commands.add("done");
        commands.add("todo");
        commands.add("event");
        commands.add("deadline");
        commands.add("delete");
        commands.add("list");
    }
    private static void checkValidCommand(String[] words) throws InvalidCommandError {
        String command = words[0];
        if(!commands.contains(command)){
            throw new InvalidCommandError();
        }
        if (command.equals("done")) { //Checks what is the command
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).setDone(true);
            completeSuccess(index);
        } else if (command.equals("list")) {
            listAllTask();
        } else if (command.equals("bye")) {
            showByeScreen();
            return;
        } else if (command.equals("delete")) {
            removeTask(Integer.parseInt(words[1]));
        } else { //is a valid task
            checkValidInput(words);
        }
    }

    private static void checkValidInput(String[] words) {
        try{
            splitInput(words);
        } catch (EmptyDescriptionError e) {
            printDivider();
            System.out.println("Missing description!!!");
            printDivider();
        } catch (MissingDateError e) {
            printDivider();
            System.out.println("Missing date!!");
            printDivider();
        } catch (InvalidDescriptionError e) {
            printDivider();
            System.out.println("Description cannot be whitespace!!");
            printDivider();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printDivider();
            System.out.println("Something went wrong! Please try again!");
            printDivider();
        } catch (EmptyDateError e) {
            printDivider();
            System.out.println("Date cannot be empty!");
            printDivider();
        }
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

    private static void checkValidAction(String[] words, String task, String date,
                                         String type) {
        if (type.equals("deadline")) {
            tasks.add(new Deadline(task, date));
            addSuccess();
        } else if (type.equals("event")) {
            tasks.add(new Event(task, date));
            addSuccess();
        } else if (type.equals("todo")) {
            tasks.add(new ToDo(task, date));
            addSuccess();
        } else { //invalid command
            printDivider();
            System.out.println("Invalid command, try again.");
            printDivider();
        }
    }

    private static void listAllTask() {
        printDivider();
        if(tasks.size() == 0) {
            System.out.println("You have no tasks at the moment!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
            getTasksLeft();
        }
        printDivider();
    }

    private static void removeTask(int index) {
        
        try {
            Task t = tasks.get(index - 1);
            printDivider();
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            tasks.remove(index - 1);
            getTasksLeft();
            printDivider();
        } catch (IndexOutOfBoundsException e) {
            printDivider();
            System.out.println("Something went wrong! Please try again!");
            printDivider();
        }

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

    private static void addSuccess() {
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        getTasksLeft();
        printDivider();
    }

    private static void completeSuccess(int index) {
        printDivider();
        System.out.println("Got it. I've marked this task as complete:");
        System.out.println(tasks.get(index - 1).toString());
        getTasksLeft();
        printDivider();
    }

    private static void getTasksLeft() {
        printDivider();
        int undoneTasks = 0;
        for(int i = 0; i < tasks.size(); i++) {
            if(!tasks.get(i).getStatus()) {
                undoneTasks++;
            }
        }
        System.out.println("Now you have " + undoneTasks + " tasks in the list.");
    }

    private static void showByeScreen() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public final static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

    private static void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?\n");
        printDivider();
    }
}

