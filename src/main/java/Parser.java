import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.Scanner;

public class Parser {

    public static boolean hasListKeyword(String arg) {
        return arg.trim().matches("^list$");
    }

    public static boolean hasDoneKeyword(String arg) {
        return arg.trim().matches("^[done]+\\s+[0-9]+$");
    }

    public static boolean hasTodoKeyword(String arg) {
        return arg.trim().toLowerCase().contains("todo");
    }

    public static boolean hasDeadlineKeyword(String arg) {
        return arg.trim().toLowerCase().contains("deadline");
    }

    public static boolean hasEventKeyword(String arg) {
        return arg.trim().toLowerCase().contains("event");
    }

    public static boolean hasDeleteKeyword(String arg) {
        return arg.trim().toLowerCase().contains("delete");
    }

    /**
     * Returns the required value for keyword which is the first word keyed in by user.
     *
     * @param query user raw data input.
     * @return keyword value from Keyword enum class.
     */
    public static Keyword getKeywordStatus(String query) {
        Keyword keyword;
        if (hasDoneKeyword(query)) {
            keyword = Keyword.DONE_TASK;
        } else if (hasTodoKeyword(query)) {
            keyword = Keyword.TODO_TASK;
        } else if (hasDeadlineKeyword(query)) {
            keyword = Keyword.DEADLINE_TASK;
        } else if (hasEventKeyword(query)) {
            keyword = Keyword.EVENT_TASK;
        } else if (hasListKeyword(query)) {
            keyword = Keyword.LIST_ITEMS;
        } else if (query.trim().equals("bye")) {
            keyword = Keyword.GOODBYE_KEYWORD;
        } else if (hasDeleteKeyword(query)) {
            keyword = Keyword.DELETE_KEYWORD;
        } else {
            keyword = Keyword.NO_KEYWORD;
        }
        return keyword;
    }

    /**
     * Returns the list number which is assigned to the task.
     *
     * @param arg user input that contains word [done].
     * @return task number.
     */
    public static int getTaskNum(String arg) {
        String[] words = arg.trim().split("[\\s]+");
        return Integer.parseInt(words[1]);
    }

    /**
     * Returns the number or unchecked tasks.
     *
     * @param tasks ArrayList<Task> for Tasks.
     * @return int for number of uncompleted tasks.
     */
    public static int getNumOfUncompletedTasks(ArrayList<Task> tasks) {
        int numOfUncompletedTasks = 0;
        for (Task task : tasks) {
            if (!task.isDone) {
                numOfUncompletedTasks++;
            }
        }
        return numOfUncompletedTasks;
    }

    /**
     * Returns the description of the task only, without the date or the keyword.
     *
     * @param query user raw data input.
     * @return description of task.
     */
    public static String getQueryDescription(String query) {
        String[] words = query.trim().split("[\\s]+");
        String[] allButFirstWord = Arrays.copyOfRange(words, 1, words.length);
        StringBuilder sentenceAfterDeletion = new StringBuilder();
        for (String word : allButFirstWord) {
            if (word.contains("/")) {
                break;
            } else {
                sentenceAfterDeletion.append(word).append(" ");
            }
        }
        return sentenceAfterDeletion.toString();
    }

    /**
     * Returns date value for tasks which need a date input field. If user does not have proper date formatting, (i.e.
     * '/by' or '/at') this function returns an empty string.
     *
     * @param query user raw data input.
     * @return date value.
     */
    public static String getDate(String query) {
        int slashPosition = query.indexOf("/");
        if (slashPosition == -1) {
            return "";
        } else {
            int datePosition = slashPosition + 3;
            return query.substring(datePosition).trim();
        }
    }

    /**
     * Function waits for user input, or takes input from ./list.txt.
     */
    public static void waitForQuery() {
        String query = "";
        Storage.loadTask();
        Scanner userInput = new Scanner(System.in);
        while (!query.equals("bye")) {
            System.out.print("=>");
            if (userInput.hasNextLine()) {
                query = userInput.nextLine();
                Storage.saveTask(query);
            }
            TaskList.addTask(query);
        }
    }
}

