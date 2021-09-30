import duke.exception.*;
import duke.task.*;

import java.util.ArrayList;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//try fix

/**
 * Duke is a reminder programme for users to store the things they want to do at what time
 */
public class Duke {
    public static void main(String[] args) throws AssignException, TypingException, FormatException, EventStringException, DeadlineStringException, TodoStringException, IOException {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        int MAX_TASK = 100;
        TaskList Tasks = new TaskList();
        Ui ui = new Ui();
        ui.greet();
        String file = "data/tasks.txt";
        Storage storage = new Storage(file);
        Tasks = storage.getFile();
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        boolean isExit = true;
        int maxlength = 0;
        for(; Tasks.size() < 100 && isExit;){
            String str= sc.nextLine();
            try {
                switch (Parser.processCommand(str)) {
                case BYE:
                    ui.bye();
                    isExit = false;
                    break;
                case LIST:
                    checkList(Tasks.size());
                    ui.printList(Tasks, maxlength);
                    break;
                case FIND:
                    str = str.substring(5, str.length());
                    ui.printFoundTask(Tasks, str, maxlength);
                    break;
                case TODO:
                    checkTodoString(str);
                    maxlength = checkLength(str, maxlength, -5 + 9);//- length of "todo " + "1. [X][X]"
                    str = str.substring(5, str.length());
                    Todo t = new Todo(str);
                    addToList(t, Tasks);
                    ui.printTask(t, Tasks.size());
                    break;
                case DEADLINE:
                    checkDeadlineString(str);
                    checkFormat(str);
                    String timeD = str.substring(str.indexOf("/") + 3, str.length());
                    maxlength = checkLength(str, maxlength, -9 + 11);//- length of "deadline " + "1. [X][X]" + "(xx:" + ")"
                    Deadline d = new Deadline(str.substring(9, str.indexOf("/") - 1), timeD);
                    addToList(d, Tasks);
                    ui.printTask(d, Tasks.size());
                    break;
                case EVENT:
                    checkEventString(str);
                    checkFormat(str);
                    String timeE = str.substring(str.indexOf("/") + 3, str.length());
                    maxlength = checkLength(str, maxlength, -6 + 11);
                    Event e = new Event(str.substring(6, str.indexOf("/") - 1), timeE);
                    addToList(e, Tasks);
                    ui.printTask(e, Tasks.size());
                    break;
                case DELETE:
                    int num = getStringNumber(str);
                    checkNum(num, Tasks.size());
                    Task task = Tasks.get(num - 1);
                    Tasks.remove(num - 1);
                    ui.printDelete(task, Tasks.size());
                    break;
                case DONE:;
                    int num2 = getStringNumber(str);
                    checkNum(num2, Tasks.size());
                    Tasks.get(2 - 1).setDone(true);
                    ui.printDone(Tasks.get(num2 - 1));
                    break;
                case UNKNOWN:
                    FormatError();
                    break;
                default:
                    break;
                }
            } catch (AssignException e){
                  ui.printString("OOPS! not assigned");
            } catch (DeadlineStringException e){
                  ui.printString("OOPS! the deadline description cannot be empty");
            } catch (EventStringException e) {
                  ui.printString("OOPS! the event description cannot be empty");
            } catch (FormatException e) {
                  ui.printString("OOPS! what's the time?");
            } catch (TodoStringException e) {
                  ui.printString("OOPS! the todo description cannot be empty");
            } catch (TypingException e) {
                  ui.printString("OOPS! what do u mean?");
            } catch (EmptyListException e) {
                  ui.printString("OOPS! empty list");
            }
        }
        storage.writeFile(Tasks);
    }

    /**
     * get the number in the string
     * @param str the string
     * @return the number in the string
     */
    private static int getStringNumber(String str){
        String numberOnly = str.replaceAll("[^0-9]", "");
        if(numberOnly.length() == 0) numberOnly = "0"; // to avoid empty string error
        int num = Integer.parseInt(numberOnly);
        return num;
    }

    /**
     * check whether string length is bigger than the maxlength
     * @param str the string
     * @param maxlength the maximum string length
     * @param num the number that needed to be added for the type
     * @return maximum string length
     */
    private static int checkLength(String str, int maxlength, int num){
        if (str.length() + num > maxlength) {
            maxlength = str.length() + num;
        }
        return maxlength;
    }

    /**
     * check the user typed in a proper number
     * @param num the number the user typed in
     * @param j the number of tasks
     * @throws AssignException
     */
    public static void checkNum(int num, int j) throws AssignException {
        if(num > j || num <= 0)
            throw new AssignException();
    }

    /**
     * check whether the list is empty
     * @param num the number of tasks in the list
     * @throws EmptyListException
     */
    public static void checkList(int num) throws EmptyListException {
        if(num < 1)
            throw new EmptyListException();
    }

    /**
     * check whether the todo string is proper
     * @param str the string the user typed in
     * @throws TodoStringException
     */
    public static void checkTodoString(String str) throws TodoStringException {
        str = str.replaceAll(" ", "");
        if(str.length() <= 4)
            throw new TodoStringException();
    }

    /**
     * check whether the deadline string is proper
     * @param str the string the user typed in
     * @throws DeadlineStringException
     */
    public static void checkDeadlineString(String str) throws DeadlineStringException {
        str = str.replaceAll(" ", "");
        if(str.length() <= 8)
            throw new DeadlineStringException();
    }

    /**
     * check whether the event string is proper
     * @param str the string the user typed in
     * @throws EventStringException
     */
    public static void checkEventString(String str) throws EventStringException {
        str = str.replaceAll(" ", "");
        if(str.length() <= 5)
            throw new EventStringException();
    }

    /**
     * check whether the user typed in a good deadline or event format
     * @param str the string the user typed in
     * @throws FormatException
     */
    public static void checkFormat(String str) throws FormatException {
        if(!str.contains("/by") && !str.contains("/at"))
            throw new FormatException();
    }

    /**
     * check whether the user typed in a proper command
     * @throws TypingException
     */
    public static void FormatError() throws TypingException {
        throw new TypingException();
    }

    /**
     * add the task to the list
     * @param t the task
     * @param list the list
     */
    public static void addToList(Task t, TaskList list){
        list.add(t);
    }
}
