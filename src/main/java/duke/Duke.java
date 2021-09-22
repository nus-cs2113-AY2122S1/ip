package duke;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Duke {
    //declarations
    public static String line = "------------------------------------------------------------------------------------------\n";
    private static int quitFlag = 0; //if 0, take user input. Otherwise, don't.

    //declare task array and keep track of how many tasks stored
    public static ArrayList<Task> t = new ArrayList<>();
    public static int taskCount = 0;

    //Program starts with this greeting
    public static void start() throws DukeException {
        try {
            loadData();
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(line + "Hello! I'm Duke.\n" + logo + "What can i do for you?\n" + line);
        } catch (FileNotFoundException e) {
            System.out.println("Saved file could not be found. I've used one of your wishes to create a new file for you! Thank me later.");
        }
    }

    //Saves Task list into local file
    public static void saveData(ArrayList<Task> t) {
        try {
            String path = new File("userData.txt").getAbsolutePath();
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
            for (int i = 0; i < taskCount; i++) {
                String input = t.get(i).toSave() + "\n";
                Files.write(Paths.get(path), input.getBytes(), StandardOpenOption.APPEND);
            }
        }  catch (IOException e) {
            System.out.println("ERROR: Could not write to file");
        }
    }

    //Loads Task list into Duke
    public static void loadData() throws FileNotFoundException, DukeException {
        String path = new File("userData.txt").getAbsolutePath();
        File f = new File(path);

        Scanner scan = new Scanner(f);
        String taskType;
        String s;
        int taskNumber = 1; //tracks how many tasks read from .txt file so far

        while(scan.hasNext())                                                                                           //todo hello | 1
        {                                                                                                               //deadline hello /by Sunday | 1
            String data = scan.nextLine();                                                                              //event project meeting /at Mon 2-4pm | 0
            String[] arrayString = data.split(" \\| ");
            String[] arrayString2 = arrayString[0].split(" ");
            taskType = arrayString2[0];
            s = Integer.toString(taskNumber);

            switch (taskType) {
            case "todo":
                sayTodo(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    sayDone(s);
                }
                break;
            case "deadline":
                sayDeadline(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    sayDone(s);
                }
                break;
            case "event":
                sayEvent(arrayString[0]);
                if (arrayString[1].equals("1")) {
                    sayDone(s);
                }
                break;
            default:
            }
            taskNumber++;
        }
        scan.close();
    }

    //Program exits with this ending
    public static void sayBye(String input) throws DukeException{                                                       //bye
        if (input.length() != 3) {
            throw new DukeException("You didn't say bye properly, but i get it! Adios for now!\n");                     //bye 9
        } else {
            System.out.println(line + "\n" + "Ciao! More tasks to do later!\n" + line);                                 //bye
            updateQuitFlag();
        }
        System.exit(0);
    }

    //updates flag so that no more user input is taken
    public static void updateQuitFlag() {
        quitFlag = 1;
    }

    //Lists out all tasks stored and their statuses
    public static void sayList(String input) throws DukeException{                                                      //list
        if (input.length() == 4) {
            if (taskCount > 0) {
                System.out.println(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + t.get(i).toString() + "\n");
                }
                System.out.println(line);
            }
            else {
                throw new DukeException("Hold your horses, we haven't even started listing yet!");                      //list when taskCount = 0
            }
        } else {
            throw new DukeException("Invalid input! Ask me nicely to list!");                                           //list 7
        }
    }

    //Marks a stored task as done
    public static void sayDone(String input) throws DukeException {                                                     //done 1
        if (taskCount != 0) {
            try {
                String taskNumber = input.substring(input.lastIndexOf(" ") + 1);
                int finalTaskNumber = Integer.parseInt(taskNumber) - 1;
                t.get(finalTaskNumber).markAsDone();
                System.out.println(line + "\n" + "Kudos! One less thing to stress about!\n");                           //done 1
                System.out.println("  " + t.get(finalTaskNumber).toString() + "\n" + line);
            } catch (NullPointerException e) {
                System.out.println(line + "\nInvalid task number entered! Please try again!\n" + line);                 //done 101
            }
        } else {
            throw new DukeException("Calm down, we haven't even started listing out tasks yet!");                       //done 1 when taskCount = 0
        }
    }

    //Adds a new todo task and prints it                                                                                //todo borrow book
    public static void sayTodo(String input) {
        if (input.length() == 4) {
            System.out.println(line + "\nDid you forget what you were gonna say?\n" + line);                            //todo
        } else {
            int endIndex = input.length();
            String taskName = input.substring(5, endIndex);
            t.add(taskCount, new Todo(taskName));
            System.out.println(line + "\n");
            System.out.println("That's the spirit! I've added this task:\n");                                           //todo borrow book
            System.out.println(t.get(taskCount).toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }

    //Adds a new deadline task and prints it
    public static void sayDeadline(String input) {                                                                      //deadline return book /by Sunday
        if (input.length() == 8) {
            System.out.println(line + "\nAre you kidding me? You didn't even tell me what you were supposed to do!\n"); //deadline
            System.out.println(line);
        } else if (!input.contains("/")) {
            System.out.println(line + "\nAre you kidding me? You forgot to tell me your deadline again?\n" + line);     //deadline return book
        } else {
            int endIndex = input.lastIndexOf("/");
            String taskName = input.substring(9, endIndex);
            int endIndex2 = input.length();
            String by = input.substring(endIndex + 4, endIndex2);
            t.add(taskCount, new Deadline(taskName, by));
            System.out.println(line + "\n");
            System.out.println("Got it. I've added this task:\n");                                                      //deadline return book /by Sunday
            System.out.println(t.get(taskCount).toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }

    //Adds a new event task and prints it
    public static void sayEvent(String input) {                                                                         //event project meeting /at Mon 2-4pm
        if (input.length() == 5) {
            System.out.println(line + "\nAre you kidding me? You didn't even tell me what you were supposed to do!\n"); //event
            System.out.println(line);
        } else if (!input.contains("/")) {
            System.out.println(line + "\nAre you kidding me? You forgot to tell me your event timing again?\n" + line); //event project meeting
        } else {
            int endIndex = input.lastIndexOf("/");
            String taskName = input.substring(6, endIndex);
            int endIndex2 = input.length();
            String at = input.substring(endIndex + 4, endIndex2);
            t.add(taskCount, new Event(taskName, at));
            System.out.println(line + "\n");
            System.out.println("Got it. I've added this task:\n");                                                      //event project meeting /at Tue 3-7pm
            System.out.println(t.get(taskCount).toString());
            taskCount++;
            System.out.println("\nNow you have " + taskCount + " tasks in the list.\n" + line);
        }
    }

    //Deletes a stored task
    public static void sayDelete(String input) throws DukeException {                                                   //delete 1
        if (taskCount != 0) {
            try {
                String taskNumber = input.substring(input.lastIndexOf(" ") + 1);
                int finalTaskNumber = Integer.parseInt(taskNumber) - 1;
                Task taskRemoved = t.get(finalTaskNumber);
                t.remove(finalTaskNumber);
                taskCount--;
                System.out.println(line + "\n" + "One more thing outta your life as always...\n");                      //delete 1
                System.out.println("  " + taskRemoved.toString() + "\n" + "\nYou now have " + taskCount + " tasks left.\n");
                System.out.println(line);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(line + "\nInvalid task number entered! Please try again!\n" + line);                 //delete 101
            }
        } else {
            throw new DukeException("Calm down, we don't even have tasks yet!");                                        //delete 1 when taskCount = 0
        }
    }

    //Give users a way to find a task by searching for a keyword
    public static void search(String input){                                                                            //find book
        String[] arrayString = input.split(" ", 2);
        String keyWord = arrayString[1];
        int matchCount = 0;

        ArrayList<String> filteredList = new ArrayList<>();
        for (Task task : t) {
            if (task.toString().contains(keyWord)) {
                filteredList.add(task.toString());
                matchCount++;
            }
        }
        if (matchCount != 0) {
            System.out.println("\n" + "Lucky for you, i'm really good at digging through your mess:" + "\n" + line);
            for (int i = 0; i < filteredList.size(); i++) {
                System.out.println((i + 1) + ". " + filteredList.get(i) + "\n");
            }
        } else {
            System.out.println("Too bad i couldn't find anything similar. Let's not get too ambitious and just stick to the tasks on hand shall we?" + "\n");
        }
    }

    //Creates scanner, takes in user input & filters it to different methods
    public static void inputSort() throws DukeException {
        System.out.println("Enter your wish: " + "\n" + line);
        while (quitFlag == 0) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            String actionWord = input.split(" ")[0];
            switch (actionWord) {
            case "bye":
                sayBye(input);
                break;
            case "list":
                sayList(input);
                break;
            case "done":
                sayDone(input);
                saveData(t);
                break;
            case "todo":
                sayTodo(input);
                saveData(t);
                break;
            case "deadline":
                sayDeadline(input);
                saveData(t);
                break;
            case "event":
                sayEvent(input);
                saveData(t);
                break;
            case "delete":
                sayDelete(input);
                saveData(t);
                break;
            case "find":
                search(input);
                break;
            default:
                System.out.println(line + "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
            }
        }
    }

    //Main
    public static void main(String[] args) throws DukeException {
        start();
        inputSort();
    }
}

