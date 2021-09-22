package duke;
import java.util.ArrayList;

public class TaskList {

    public static String line = "------------------------------------------------------------------------------------------\n";
    //declare task array and keep track of how many tasks stored
    public static ArrayList<Task> t = new ArrayList<>();
    public static int taskCount = 0;
    public static int quitFlag = 0; //if 0, take user input. Otherwise, don't.

    //updates flag so that no more user input is taken
    public static void updateQuitFlag() {
        quitFlag = 1;
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
    public static void sayFind(String input){                                                                            //find book
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
}
