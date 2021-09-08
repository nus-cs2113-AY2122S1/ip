import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.*;
public class Duke {
    public static void main(String[] args) throws AssignException, TypingException, FormatException, EventStringException, DeadlineStringException, TodoStringException {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        int MAX_TASK = 100;
        String greetings = " Hello! I'm Duke\n" + " What can I do for you?\n";
        System.out.println(greetings);
        Task[] Tasks = new Task[MAX_TASK];
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        boolean isExit = true;
        int maxlength = 0;
        for(int j = 0; j < 100 && isExit;){
            String str= sc.nextLine();
            try {
                if (str.equals("bye")) {
                    str = "Bye. Hope to see you again soon!";
                    printString(str);
                    isExit = false;
                } else if (str.equals("list")) {
                    printList(Tasks, maxlength);
                } else if (str.contains("todo")) {
                    checkTodoString(str);
                    if (str.length() - 5 + 9 > maxlength)
                        maxlength = str.length() - 5 + 9;//- length of "todo " + "1. [X][X]"
                    str = str.substring(5, str.length());
                    Todo t = new Todo(str);
                    addToList(t, Tasks, j);
                    j++;
                    printTask(t, j);
                } else if (str.contains("deadline")) {
                    checkDeadlineString(str);
                    checkFormat(str);
                    String time = str.substring(str.indexOf("/") + 3, str.length());
                    if (str.length() - 9 + 11 > maxlength)
                        maxlength = str.length() - 9 + 11;//- length of "deadline " + "1. [X][X]" + "(xx:" + ")"
                    Deadline t = new Deadline(str.substring(9, str.indexOf("/") - 1), time);
                    addToList(t, Tasks, j);
                    j++;
                    printTask(t, j);
                } else if (str.contains("event")) {
                    checkEventString(str);
                    checkFormat(str);
                    String time = str.substring(str.indexOf("/") + 3, str.length());
                    if (str.length() - 6 + 11 > maxlength) maxlength = str.length() - 6 + 11;
                    Event t = new Event(str.substring(6, str.indexOf("/") - 1), time);
                    addToList(t, Tasks, j);
                    j++;
                    printTask(t, j);
                } else if (str.contains("done")) {
                    String numberOnly = str.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(numberOnly);
                    checkNum(num, j);
                    Tasks[num - 1].setDone(true);
                    printDone(Tasks[num - 1]);
                } else {
                    FormatError();
                    /*if (str.length() + 9 > maxlength) maxlength = str.length() + 9;
                    Task t = new Task(str);
                    addToList(t, Tasks, j);
                    j++;
                    printTask(t, j);*/
                }
            } catch (AssignException e){
                  printString("OOPS! not assigned");
            } catch (DeadlineStringException e){
                  printString("OOPS! the deadline description cannot be empty");
            } catch (EventStringException e) {
                  printString("OOPS! the event description cannot be empty");
            } catch (FormatException e) {
                  printString("OOPS! what's the time?");
            } catch (TodoStringException e) {
                  printString("OOPS! the todo description cannot be empty");
            } catch (TypingException e) {
                  printString("OOPS! what do u mean?");
            }
        }
    }

    public static void checkNum(int num, int j) throws AssignException {
        if(num > j)
            throw new AssignException();
    }

    public static void checkTodoString(String str) throws TodoStringException {
        str = str.replaceAll(" ", "");
        if(str.length() <= 4)
            throw new TodoStringException();
    }

    public static void checkDeadlineString(String str) throws DeadlineStringException {
        str = str.replaceAll(" ", "");
        if(str.length() <= 8)
            throw new DeadlineStringException();
    }

    public static void checkEventString(String str) throws EventStringException {
        str = str.replaceAll(" ", "");
        if(str.length() <= 5)
            throw new EventStringException();
    }

    public static void checkFormat(String str) throws FormatException {
        if(!str.contains("/by") && !str.contains("/at"))
            throw new FormatException();
    }

    public static void FormatError() throws TypingException {
        throw new TypingException();
    }

    public static void addToList(Task t, Task[] list, int num){
        list[num] = t;
    }

    public static void printString(String str){
        int length = str.length();
        System.out.print("      ");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("");
        System.out.println("     |" + str + "|");
        System.out.print("     |");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printDone(Task t){
        String str = "Nice! I've marked this task as done: ";
        int length = Math.max(t.getLength(), str.length());
        System.out.print("      ");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + str);
        for(int n = 0; n < length - str.length(); n++) System.out.print(" ");
        System.out.println("|");
        switch(t.getType()){
        case TODO:
            System.out.print("     |[T][" + t.getStatusIcon() + "]"+ t.getDescription());
            break;
        case DEADLINE:
            System.out.print("     |[D][" + t.getStatusIcon() + "]" + t.getDescription() + "(by: " + t.getBy() + ")");
            break;
        case EVENT:
            System.out.print("     |[E][" + t.getStatusIcon() + "]" + t.getDescription() + "(at: " + t.getBy() + ")");
            break;
        }
        for(int n = 0; n < length - t.getLength(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printList(Task[] list, int num){
        String listing = "Here are the tasks in your list:";
        if(listing.length() > num) num = listing.length();
        System.out.print("      ");
        for(int i = 0; i < num; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + listing);
        for(int n = 0; n < num - listing.length(); n++) System.out.print(" ");
        System.out.println("|");
        for(int j = 1; list[j-1] != null; j++) {
            switch(list[j-1].getType()){
            case TODO:
                System.out.print("     |" + j + ". [T][" + list[j-1].getStatusIcon() + "]"+ list[j-1].getDescription());
                break;
            case DEADLINE:
                System.out.print("     |" + j + ". [D][" + list[j-1].getStatusIcon() + "]" + list[j-1].getDescription() + "(by: " + list[j-1].getBy() + ")");
                break;
            case EVENT:
                System.out.print("     |" + j + ". [E][" + list[j-1].getStatusIcon() + "]" + list[j-1].getDescription() + "(at: " + list[j-1].getBy() + ")");
                break;
            }
            for(int n = 0; n < num - list[j-1].getLength() - 3 - (int)Math.log10(j); n++) System.out.print(" ");
            System.out.println("|");
        }
        System.out.print("     |");
        for(int i = 0; i < num; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printTask(Task t, int num){
        String str = "Got it. I've added this task: ";
        String str2 = "Now you have " + num + " tasks in the list.";
        int length = Math.max(t.getLength(), Math.max(str.length(), str2.length()));
        System.out.print("      ");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + str);
        for(int n = 0; n < length - str.length(); n++) System.out.print(" ");
        System.out.println("|");
        switch(t.getType()){
        case TODO:
               System.out.print("     |[T][" + t.getStatusIcon() + "]"+ t.getDescription());
               break;
        case DEADLINE:
            System.out.print("     |[D][" + t.getStatusIcon() + "]" + t.getDescription() + "(by: " + t.getBy() + ")");
            break;
        case EVENT:
            System.out.print("     |[E][" + t.getStatusIcon() + "]" + t.getDescription() + "(at: " + t.getBy() + ")");
            break;
        }
        for(int n = 0; n < length - t.getLength(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |" + str2);
        for(int n = 0; n < length - str2.length(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("|");
    }
}
