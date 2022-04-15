import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Parser program understands user commands and executes respective tasks.
 */

public class Parser {
    protected ArrayList<Task> Task;
    protected String userInput;
    protected String date = "";
    protected StorageFile Store;
    protected int count;
    Scanner in = new Scanner(System.in);
    ui ui = new ui();
    TaskList TaskList = new TaskList();
    String LINE  = "_______________________________________________________________\n";

    public Parser(ArrayList<Task> Task, int count, StorageFile Store) {
        this.Task = Task;
        this.count = count;
        this.Store = Store;
    }

    /**
     * Runs Duke program until user command given is "bye". Adds todo, deadline or event tasks to Task object based on
     * user commands and details given such as dates. Also performs other functions using user command such as removing
     * tasks, marking them as done, or listing the tasks. In any case of invalid commands, it calls to DukeException to
     * print out invalid statements and prompt user to give a more appropriate command input.
     */
    public void run() {
        do {
            userInput = in.nextLine();
            String[] wordArr = userInput.split(" "); // stores string of multiple words in a string Array
            String firstWord = wordArr[0];

            DukeException invalid = new DukeException(userInput); //to call when invalid commands are given
            int wordLength = wordArr.length;
            int isInvalid = 0; //functions as boolean

            if (isDone(firstWord)  && wordLength > 1) { //complete Tasks
                int doneNumber = -1;
                if (isNumber(wordArr[1])) {
                    doneNumber = Integer.parseInt(wordArr[1]) - 1; // gets Task number
                }
                if (isNumber(wordArr[1]) && doneNumber < count) {
                    Task.get(doneNumber).setDone(); //marks Task as done
                    ui.taskDone(Task.get(doneNumber)); //prints task done message
                }
                else {
                    invalid.setDoneNoNumber();
                    System.out.println(LINE + invalid + LINE);
                }
            }

            else if (isDone(firstWord)  && wordLength <= 1) { //invalid complete Task command
                invalid.setNotDone();
                System.out.println(LINE + invalid + LINE);
            }

            else if (isDelete(firstWord)  && wordLength > 1) { //deletes Tasks
                int deleteNumber = -1;
                if (isNumber(wordArr[1])) {
                    deleteNumber = Integer.parseInt(wordArr[1]) - 1; // gets Task number to delete
                }
                if (isNumber(wordArr[1]) && deleteNumber < count) {
                    ui.deleteTask(Task.get(deleteNumber)); //prints message saying task removed
                    TaskList.removeTask(Task, deleteNumber); //removes Task
                    count--;
                }
                else {
                    invalid.setDeleteNoNumber();
                    System.out.println(LINE + invalid + LINE);
                }
            }

            else if (isDelete(firstWord)  && wordLength <= 1) { //invalid delete Task command
                invalid.setNotDelete();
                System.out.println(LINE + invalid + LINE);
            }


            else if (isFind(firstWord)  && wordLength <= 1) { //invalid find Task command
                invalid.setFindNoWord();
                System.out.println(LINE + invalid + LINE);
            }

            else if (isFind(firstWord) && wordLength > 1) { //finds tasks
                int index = 1;
                boolean wordExists = false;

                System.out.println(LINE);
                for (int i = 0; i < count; i++) {
                    String currentTask = "" + Task.get(i); //converts task object to string
                    String keyword = wordArr[1];

                    if (currentTask.contains(keyword)) { //find tasks containing keyword and prints them
                        wordExists = true;
                        System.out.print(index + ". ");
                        System.out.println(Task.get(i) + " - Task no: " + (i+1));
                        index++;
                    }
                }
                if (!wordExists) { //prints error message if keyword is not found
                    ui.wordNotFound();
                }
                System.out.println(LINE);
            }


            else if (!isBye(userInput) && !isList(userInput)) { //Task words
                if (isTodo(firstWord) && wordLength == 1) { //Todo is empty
                    invalid.setTodoEmpty();
                    isInvalid = 1;
                }
                else if (isTodo(firstWord) && wordLength > 1){ //Todo is not empty
                    TaskList.addTodo(Task, count, userInput);
                    count++;
                }

                else if (isDeadline(firstWord) && wordLength == 1 ) { //Deadline is empty
                    invalid.setDeadlineEmpty();
                    isInvalid = 1;
                }

                else if (isDeadline(firstWord) && wordLength > 1 ) { //Deadline is not empty
                    if (userInput.contains("/") && userInput.contains("-")) { //Deadline has a date
                        date = userInput.substring(userInput.lastIndexOf("/"));
                        TaskList.addDeadline(Task, count, userInput, date);
                        count++;
                    }
                    else { //Deadline Task has no date
                        invalid.setDeadlineNoDate();
                        isInvalid = 1;
                    }
                }

                else if (isEvent(firstWord) && wordLength == 1 ) { //Event is empty
                    invalid.setEventEmpty();
                    isInvalid = 1;
                }

                else if (isEvent(firstWord) && wordLength > 1 ) { //Event is not empty
                    if (userInput.contains("/")) { //Event has a date/place
                        date = userInput.substring(userInput.lastIndexOf("/"));
                        TaskList.addEvent(Task, count, userInput, date);
                        count++;
                    }
                    else { //Event has no date
                        invalid.setEventNoDate();
                        isInvalid = 1;
                    }
                }

                else { //Other invalid commands
                    invalid.setInvalidCommand();
                    isInvalid = 1;
                }

                switch(isInvalid) { //handles errors
                    case 0: ui.addedTask(firstWord.toLowerCase(), Task.get(count - 1), count);
                        break;
                    case 1: System.out.println(LINE + invalid + LINE);
                        break;
                }

            }

            else if (isList(userInput)) { //List tasks

                ui.list();
                for (int i = 0; i < count; i++) {
                    System.out.print(i + 1 + ". ");
                    System.out.println(Task.get(i));
                }
                System.out.print(LINE);
            }

            for (int i = 0; i < count; i++) {
                String text = toString(Task.get(i), i+1);
                Store.write(i, text);
            }

        } while (!isBye(userInput)); //Exits
        ui.bye();
    }

    public static String toString(Task task, int i) {
        return i + ". " + task;
    }

    public static boolean isBye(String word) {
        return word.equalsIgnoreCase("bye");
    }

    public static boolean isList(String word) {
        return word.equalsIgnoreCase("list");
    }

    public static boolean isDone(String word) { return word.equalsIgnoreCase("done"); }

    public static boolean isTodo(String word) { return word.equalsIgnoreCase("todo"); }

    public static boolean isDeadline(String word) {
        return word.equalsIgnoreCase("deadline");
    }

    public static boolean isEvent(String word) {
        return word.equalsIgnoreCase("event");
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDelete(String word) {
        return word.equalsIgnoreCase("delete");
    }

    public static boolean isFind(String word) {
        return word.equalsIgnoreCase("find");
    }

}
