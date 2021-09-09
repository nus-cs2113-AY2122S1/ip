public class TaskManager {
    static final int INDEX_OF_KEYWORD_OF_TASK = 0;

    private Task[] tasks = new Task[100];

    public static void printDividerLine() {
        System.out.println("\t_____________________________________________________________________________");
    }

    public void printAddedTasked() {
        printDividerLine();
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t   " + tasks[Task.getNumberOfTasks() - 1].toString());
        System.out.println("\t Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        printDividerLine();
    }

    public void printIncorrectInput(boolean isEmptyDescription, String keyword) {
        printDividerLine();
        if (keyword.equalsIgnoreCase("todo")) {
            System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (keyword.equalsIgnoreCase("deadline")) {
            if (isEmptyDescription) {
                System.out.println("\t ☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                System.out.println("\t ☹ OOPS!!! The description of a deadline cannot be in the wrong format.");
            }
        } else {
            if (isEmptyDescription) {
                System.out.println("\t ☹ OOPS!!! The description of an event cannot be empty.");
            } else {
                System.out.println("\t ☹ OOPS!!! The description of an event cannot be in the wrong format.");
            }
        }
        printDividerLine();
    }

    public void addTask(String line) {
        String[] keywords = line.split(" ", 2);
        boolean hasNoException = false;
        boolean isEmptyDescription = true;

        if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("todo")) {
            try {
                tasks[Task.getNumberOfTasks()] = new Todo(keywords[1]);
                hasNoException = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                printIncorrectInput(false, keywords[INDEX_OF_KEYWORD_OF_TASK]);
            }
        } else if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("deadline")) {
            try {
                String[] details = keywords[1].split(" /by ");
                isEmptyDescription = false;
                tasks[Task.getNumberOfTasks()] = new Deadline(details[0], details[1]);
                hasNoException = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                printIncorrectInput(isEmptyDescription, keywords[INDEX_OF_KEYWORD_OF_TASK]);
            }
        } else if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("event")) {
            try {
                String[] details = keywords[1].split(" /at ");
                isEmptyDescription = false;
                tasks[Task.getNumberOfTasks()] = new Event(details[0], details[1]);
                hasNoException = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                printIncorrectInput(isEmptyDescription, keywords[INDEX_OF_KEYWORD_OF_TASK]);
            }
        }
        if (hasNoException) {
            printAddedTasked();
        }
    }

    public void listTasks() {
        printDividerLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks[i].toString());
        }
        printDividerLine();
    }

    public void markAsDone(String index) {
        try {
            int indexInteger = Integer.parseInt(index);
            if (indexInteger > 0) {
                tasks[indexInteger - 1].setDone();
                printDividerLine();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t [X] " + tasks[indexInteger - 1].getDescription());
            } else {
                printDividerLine();
                System.out.println("\t ☹ OOPS!!! The number after done must be larger than 1.");
            }
            printDividerLine();
        } catch (NumberFormatException e) {
            printDividerLine();
            System.out.println("\t ☹ OOPS!!! The input after done must be a number.");
            printDividerLine();
        } catch (NullPointerException e) {
            printDividerLine();
            System.out.println("\t ☹ OOPS!!! The task does not exist.");
            printDividerLine();
        }
    }
}
