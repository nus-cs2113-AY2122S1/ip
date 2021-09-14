import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String LINE  = "_______________________________________________________________\n";

        System.out.println(LINE + "Hello! I'm Duke\n" + "Whatchu want\n" + LINE);

        Scanner in = new Scanner(System.in);

        //Task[] Task = new Task[100];
        ArrayList<Task> Task = new ArrayList<>();

        String userInput;
        String date = "";

        int count = 0;

        String fileName = "C:\\data\\duke.txt";
        try {
            File myFile = new File(fileName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName() + "\n");
            } else {
                System.out.println("File already exists.\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.\n");
            e.printStackTrace();
        }

        do {
            userInput = in.nextLine();
            String[] wordArr = userInput.split(" "); // stores string of multiple words in a string Array
            String firstWord = wordArr[0];

            DukeException invalid = new DukeException(userInput);
            int wordLength = wordArr.length;
            int isInvalid = 0; //functions as boolean

            if (isDone(firstWord)  && wordLength > 1) { //complete Tasks
                int doneNumber = -1;
                if (isNumber(wordArr[1])) {
                    doneNumber = Integer.parseInt(wordArr[1]) - 1; // gets Task number
                }
                if (isNumber(wordArr[1]) && doneNumber < count) {
                    //Task[doneNumber].setDone();
                    Task.get(doneNumber).setDone();
                    System.out.println(LINE + "Ok! I've marked this task as done:\n" + Task.get(doneNumber) + "\nYou can add more tasks or view existing ones by typing 'list'!\n" + LINE);
                }
                else {
                    invalid.setDoneNoNumber();
                    System.out.println(LINE + invalid + LINE);
                }
            }

            else if (isDone(firstWord)  && wordLength <= 1) { //complete Tasks
                invalid.setNotDone();
                System.out.println(LINE + invalid + LINE);
            }

            else if (isDelete(firstWord)  && wordLength > 1) { //complete Tasks
                int deleteNumber = -1;
                if (isNumber(wordArr[1])) {
                    deleteNumber = Integer.parseInt(wordArr[1]) - 1; // gets Task number to delete
                }
                if (isNumber(wordArr[1]) && deleteNumber < count) {
                    //Task[doneNumber].setDone();
                    System.out.println(LINE + "Ok! I've removed this task:\n" + Task.get(deleteNumber) + "\nYou can add more tasks or view existing ones by typing 'list'!\n" + LINE);
                    Task.remove(deleteNumber);
                    count--;
                }
                else {
                    invalid.setDeleteNoNumber();
                    System.out.println(LINE + invalid + LINE);
                }
            }

            else if (isDelete(firstWord)  && wordLength <= 1) { //complete Tasks
                invalid.setNotDelete();
                System.out.println(LINE + invalid + LINE);
            }

            else if (!isBye(userInput) && !isList(userInput)) { //Task words
                if (isTodo(firstWord) && wordLength == 1) { //Todo is empty
                    invalid.setTodoEmpty();
                    isInvalid = 1;
                }
                else if (isTodo(firstWord) && wordLength > 1){ //Todo is not empty
                    Task.add(count, new Todo(userInput));
                    count++;
                }

                else if (isDeadline(firstWord) && wordLength == 1 ) { //Deadline is empty
                    invalid.setDeadlineEmpty();
                    isInvalid = 1;
                }

                else if (isDeadline(firstWord) && wordLength > 1 ) { //Deadline is not empty
                    if (userInput.contains("/")) { //Deadline Task has a date
                        date = userInput.substring(userInput.lastIndexOf("/") + 1);
                        Task.add(count, new Deadline(userInput, date));
                        count++;
                    }
                    else { //Deadline Task has no date
                        invalid.setDeadlineNoDate();
                        isInvalid = 1;
                    }
                }

                else if (isEvent(firstWord) && wordLength == 1 ) { //Event task
                    invalid.setEventEmpty();
                    isInvalid = 1;
                }

                else if (isEvent(firstWord) && wordLength > 1 ) { //Event task
                    if (userInput.contains("/")) { //Event Task has a date
                        date = userInput.substring(userInput.lastIndexOf("/") + 1);
                        Task.add(count, new Event(userInput, date));
                        count++;
                    }
                    else { //Deadline Task has no date
                        invalid.setEventNoDate();
                        isInvalid = 1;
                    }
                }

                else { //Other invalid commands
                    invalid.setInvalidCommand();
                    isInvalid = 1;
                }

                switch(isInvalid) { //handles errors
                    case 0: System.out.println(LINE + "Ok! I've added this " + firstWord.toLowerCase() + ":\n" +
                            Task.get(count - 1) + "\n" + "Now you have " + count + (count == 1 ? " task":" tasks") + " in the list.\n" +
                            "Type 'list' to view your tasks!\n" + LINE);
                            break;
                    case 1: System.out.println(LINE + invalid + LINE);
                            break;
                }

            }

            else if (isList(userInput)) { //List tasks

                System.out.println(LINE + "Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.print(i + 1 + ". ");
                    System.out.println(Task.get(i));

                    String text = toString(Task.get(i), i+1);
                    if (i == 0) {
                        try {
                            writeToFile(fileName, text + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    }
                    else {
                        try {
                            appendToFile(fileName, text + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    }
                }
                System.out.print("You can mark them as done by typing 'done' + task number!\n" + LINE);
            }

        } while (!isBye(userInput)); //Exit

        System.out.println(LINE + "Bye. Hope to see you again soon!\n" + LINE);

    }

    public static boolean isBye(String word) {
        return word.equalsIgnoreCase("bye");
    }

    public static boolean isList(String word) {
        return word.equalsIgnoreCase("list");
    }

    public static boolean isDone(String word) {
        return word.equalsIgnoreCase("done");
    }

    public static boolean isTodo(String word) {
        return word.equalsIgnoreCase("todo");
    }

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
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDelete(String word) {
        return word.equalsIgnoreCase("delete");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static String toString(Task task, int i) {
        return i + ". " + task;
    }

}
