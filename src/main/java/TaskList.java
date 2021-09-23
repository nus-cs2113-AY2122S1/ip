import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();

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
     * Returns void. Function is responsible for adding different Tasks to the task list.
     *
     * @param query user raw data input
     * @throws NullPointerException if user keys in done [number] when there is no such task.
     */
    public static void addTask(String query) throws IndexOutOfBoundsException {
        Keyword keyword = getKeywordStatus(query);
        switch (keyword) {
        case DONE_TASK:
            try {
                int taskNumber = getTaskNum(query) - 1;
                Task referencedTask = tasks.get(taskNumber);
                referencedTask.markAsDone();
                Ui.printDone(referencedTask);
                System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("There is no such task number...");
            }
            break;
        case TODO_TASK:
            tasks.add(new Todo(getQueryDescription(query)));
            System.out.println("Added a Todo Task: " + getQueryDescription(query));
            System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            break;
        case EVENT_TASK:
            try {
                if (getDate(query).equals("")) {
                    throw new InvalidInputsException.InvalidDateFormatting("Wrong format of date "
                            + "has been entered");
                }
                tasks.add(new Event(getQueryDescription(query), getDate(query)));
                System.out.println("Added an Event Task: " + getQueryDescription(query));
                System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            } catch (InvalidInputsException.InvalidDateFormatting exception) {
                exception.printStackTrace();
                System.out.println("Did you forget to add in the '/by' again?");
            }
            break;
        case DEADLINE_TASK:
            try {
                if (getDate(query).equals("")) {
                    throw new InvalidInputsException.InvalidDateFormatting("Wrong format of date "
                            + "has been entered");
                }
                tasks.add(new Deadline(getQueryDescription(query), getDate(query)));
                System.out.println("Added a Deadline Task: " + getQueryDescription(query));
                System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            } catch (InvalidInputsException.InvalidDateFormatting exception) {
                exception.printStackTrace();
                System.out.println("Did you forget to add in the '/by' again?");
            }
            break;
        case LIST_ITEMS:
            Ui.printList(tasks);
            break;
        case DELETE_KEYWORD:
            try {
                int taskNumber = getTaskNum(query) - 1;
                Task referencedTask = tasks.get(taskNumber);
                tasks.remove(taskNumber);
                Ui.printDeletedMessage(referencedTask);
                System.out.println("Total unchecked items in your list: " + getNumOfUncompletedTasks(tasks));
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("There is no such task number...");
            }
            break;
        case NO_KEYWORD:
            try {
                throw new InvalidInputsException.MissingKeyword("You have to input <todo>, <deadline> or"
                        + " <event> first!");
            } catch (InvalidInputsException.MissingKeyword exception) {
                exception.printStackTrace();
                System.out.println("Invalid keyword!!!");
            }
            break;
        case GOODBYE_KEYWORD:
            Ui.printGoodbyeMessage();
            break;
        }
    }

}
