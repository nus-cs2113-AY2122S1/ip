import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String LINE  = "_______________________________________________________________\n";

        System.out.println(LINE + "Hello! I'm Duke\n" + "Whatchu want\n" + LINE);

        Scanner in = new Scanner(System.in);

        Task[] Task = new Task[100];

        String userInput;
        String date = "";

        int count = 0;

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
                    Task[doneNumber].setDone();
                    System.out.println(LINE + "Ok! I've marked this task as done:\n" + Task[doneNumber] + "\nYou can add more tasks or view existing ones by typing 'list'!\n" + LINE);
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

            else if (!isBye(userInput) && !isList(userInput)) { //Task words
                if (isTodo(firstWord) && wordLength == 1) { //Todo is empty
                    invalid.setTodoEmpty();
                    isInvalid = 1;
                }
                else if (isTodo(firstWord) && wordLength > 1){ //Todo is not empty
                    Task[count] = new Todo(userInput);
                    count++;
                }

                else if (isDeadline(firstWord) && wordLength == 1 ) { //Deadline is empty
                    invalid.setDeadlineEmpty();
                    isInvalid = 1;
                }

                else if (isDeadline(firstWord) && wordLength > 1 ) { //Deadline is not empty
                    if (userInput.contains("/")) { //Deadline Task has a date
                        date = userInput.substring(userInput.lastIndexOf("/") + 1);
                        Task[count] = new Deadline(userInput, date);
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
                        Task[count] = new Event(userInput, date);
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
                            Task[count - 1] + "\n" + "Now you have " + count + (count == 1 ? " task":" tasks") + " in the list.\n" +
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
                    System.out.println(Task[i]);
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

}
