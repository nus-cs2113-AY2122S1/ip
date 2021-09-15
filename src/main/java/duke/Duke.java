package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> list = new ArrayList<>();
    public static int counter = 0;

    public static void main(String[] args) {

        // Welcome Message
        printWelcomeMessage();

        // Active Chat
        activeChat();

        // Goodbye Message
        printGoodbyeMessage();
    }

    private static void activeChat() {
        boolean isBye = false;
        String input;
        Scanner in = new Scanner(System.in);
        //Task[] list = new Task[100];

        while(!isBye){
            //store input
            input = in.nextLine();
            //process input
            if (input.equals("bye")){ //check if bye
                isBye = true;
            } else if (input.equals("list")) { //check if list
                processList();
            } else if (input.contains("done") ) { //check if done
                processDone(input);
            } else if (input.contains("delete") ) { //check if delete
                processDelete(input);
            } else {
                try {
                    counter = processTasks(input); //process tasks
                } catch ( IllegalTaskException e ) {
                    System.out.println("Please include /by for deadline and /at for event");
                }
            }
            //test size
            System.out.println(list.size());
        }
    }

    private static void processDelete(String input) {
        int deletePos = input.indexOf("delete");
        if (list.size() != 0) {
            if (input.length() < deletePos + 7) {
                printDeleteButNotSpecificMessage(); //when input contains delete but no number
            } else {
                processValidDelete(input, deletePos); //when input contains delete and specified number
            }
        } else {
            printDeleteButEmptyMessage(); //when input contains delete but list is empty
        }
    }

    private static void printDeleteButEmptyMessage() {
        System.out.println("--------------------");
        System.out.println("Unable to delete.");
        System.out.println("List is empty. Time to get productive!");
        System.out.println("--------------------");
    }

    private static void processValidDelete(String input, int deletePos) {
        String itemNumDone = input.substring(deletePos + 7, deletePos + 8);
        int itemNum = Integer.parseInt(itemNumDone);
        printDeleteMessage(itemNum);
        list.remove(itemNum - 1);
        counter -= 1;
    }

    private static void printDeleteMessage(int itemNum) {
        System.out.println("--------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println( itemNum + ".[" + list.get(itemNum - 1).getStatusIcon() + "] " + list.get(itemNum - 1).getDescription() );
        System.out.println("Now you have " + ( list.size() - 1 )+ " tasks in the list.");
        System.out.println("--------------------");
    }

    private static void printDeleteButNotSpecificMessage() {
        System.out.println("Please specify which task is to be deleted.");
    }

    private static void processList() {
        if (list.size() != 0){
            printListMessage(counter);
        } else {
            printListButEmptyMessage();
        }
    }

    private static void processDone(String input) {
        int donePos = input.indexOf("done");
        if (list.size() != 0) {
            if (input.length() < donePos + 5) {
                printDoneButNotSpecificMessage(); //when input contains done but no number
            } else {
                processValidDone(input, donePos); //when input contains done and specified number
            }
        } else {
            printDoneButEmptyMessage(); //when input contains done but list is empty
        }
    }

    private static void processValidDone(String input, int donePos) {
        String itemNumDone = input.substring(donePos + 5, donePos + 6);
        int itemNum = Integer.parseInt(itemNumDone);
        list.get(itemNum - 1).setDone();
        printDoneMessage(itemNum);
    }

    private static int processTasks(String input) throws IllegalTaskException{
        if ( ( input.contains("deadline") || input.contains("event") ) && !input.contains("/")){
            throw new IllegalTaskException();
        }
        if (input.contains("todo")) {
            String description = input.substring(5);
            ToDo newTask = new ToDo(description);
            list.add(counter, newTask);
            counter += 1;
            printAddedTaskMessage(newTask, counter);
        } else if (input.contains("deadline")) {
            int donePos = input.indexOf("/"); //finds pos of '/'
            String description = input.substring(9,donePos);
            if (!input.substring(donePos + 1, donePos + 3).equals("by")) {
                throw new IllegalTaskException();
            }
            String date = input.substring(donePos + 4);
            Deadline newTask = new Deadline(description,date);
            list.add(counter, newTask);
            counter += 1;
            printAddedTaskMessage(newTask, counter);
        } else if (input.contains("event")) {
            int donePos = input.indexOf("/"); //finds pos of '/'
            String description = input.substring(6,donePos);
            if (!input.substring(donePos + 1, donePos + 3).equals("at")) {
                throw new IllegalTaskException();
            }
            String date = input.substring(donePos + 4);
            Event newTask = new Event(description,date);
            list.add(counter, newTask);
            counter += 1;
            printAddedTaskMessage(newTask, counter);
        } else {
            System.out.println("Please specify tasks: todo, deadline or event");
            System.out.println("Example - type in the following: todo read book");
        }
        return counter;
    }

    private static void printDoneMessage(int itemNum) {
        System.out.println("--------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println( itemNum + ".[" + list.get(itemNum - 1).getStatusIcon() + "] " + list.get(itemNum - 1).getDescription() );
        System.out.println("--------------------");
    }

    private static void printListMessage(int counter) {
        System.out.println("--------------------");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < counter; i += 1){
            printListOfTaskSubMessage(list.get(i), i);
        }
        System.out.println("--------------------");
    }

    private static void printListButEmptyMessage() {
        System.out.println("--------------------");
        System.out.println("List is empty. Time to get productive!");
        System.out.println("--------------------");
    }

    private static void printDoneButNotSpecificMessage() {
        System.out.println("Please specify which task is done.");
    }

    private static void printDoneButEmptyMessage() {
        System.out.println("--------------------");
        System.out.println("Unable to tick off list.");
        System.out.println("List is empty. Time to get productive!");
        System.out.println("--------------------");
    }

    private static void printAddedTaskMessage(Task task, int i) {
        System.out.println("--------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + task.getLetter() + "] "
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getDate() );
        System.out.println("Now you have " + i + " tasks in the list.");
        System.out.println("--------------------");
    }

    private static void printListOfTaskSubMessage(Task task, int i) {
        System.out.println(i + 1
                + ".[" + task.getLetter() + "] "
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getDate() );
    }

    private static void printGoodbyeMessage() {
        System.out.println("--------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" ");
        System.out.println("--------------------");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("--------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(" ");
        System.out.println("--------------------");
    }
}
