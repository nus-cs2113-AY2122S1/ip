import todo.Deadline;
import todo.Event;
import todo.Task;
import todo.ToDo;

import java.util.Scanner;

//Check if valid command (Done, List, Bye, Action)
//If done, mark as done
//If action, check what action is it
//If there is a date, what is the date

public class Duke {

    private static int currentIndex = 0;
    private static int doneTask = 0;
    private static Task[] toDoList;
    public static void main(String[] args) {
        int LIST_SIZE = 100;
        String line = "";
        toDoList = new Task[LIST_SIZE];
        showWelcomeScreen();
        while (currentIndex <= LIST_SIZE && !line.contains("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            assert line != null;
            String[] words = line.split(" ", 2);
            checkValidCommand(words);
        }
        return;
    }

    private static void checkValidCommand(String[] words) {
        String command = words[0];
        if (command.equals("done")) { //Checks what is the command
            int index = Integer.parseInt(words[1]);
            toDoList[index].setDone(true);
            completeSuccess(index);
        } else if (command.equals("list")) {
            listAllTask();
        } else if (command.equals("bye")) {
            showByeScreen();
            return;
        } else { //is a valid task
            splitInput(words);
        }
    }


    private static void listAllTask() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= currentIndex; i++) {
            System.out.println(i + ". " + toDoList[i].toString());
        }
        getTasksLeft();
        Task.printDivider();
    }

    private static void splitInput(String[] words) {
        String task = null;
        String date = null;
        String type = words[0];
        String[] taskDescription = words[1].split("/", 2); //removes command and splits into action and date
        if(taskDescription.length > 1){
            String[] inputDate = taskDescription[1].split(" ", 2); //extract date without prefix
            task = taskDescription[0].trim();
            date = inputDate[1];
        } else {
            task = words[1];
        }
        
        checkValidAction(words, task, date, type);
    }

    private static void checkValidAction(String[] words, String task, String date,
            String type) {
        if(type.equals("deadline")) {
            currentIndex++;
            toDoList[currentIndex] = new Deadline(task, date);
            addSuccess();
        } else if(type.equals("event")) {
            currentIndex++;
            toDoList[currentIndex] = new Event(task, date);
            addSuccess();
        } else if(type.equals("todo")) {
            currentIndex++;
            toDoList[currentIndex] = new ToDo(task, date);
            addSuccess();
        } else{ //invalid command
            Task.printDivider();
            System.out.println("Invalid command, try again.");
            Task.printDivider();
        }
    }

    private static void addSuccess() {
        System.out.println("Got it. I've added this task:");
        System.out.println(toDoList[currentIndex].toString());
        getTasksLeft();
        Task.printDivider();
    }

    private static void completeSuccess(int index) {
        Task.printDivider();
        System.out.println("Got it. I've marked this task as complete:");
        System.out.println(toDoList[index].toString());
        doneTask++;
        getTasksLeft();
        Task.printDivider();
    }

    private static void getTasksLeft() {
        System.out.println("Now you have " + (currentIndex - doneTask) + " tasks in the list.");
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

