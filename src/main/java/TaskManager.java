public class TaskManager {
    static final int INDEX_OF_KEYWORD_OF_TASK = 0;

    private Task[] tasks = new Task[100];

    public void printAddedTasked() {
        System.out.println("\t_____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + tasks[Task.getNumberOfTasks() - 1].toString());
        System.out.println("     Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        System.out.println("\t_____________________________________________________________");
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
                System.out.println("\t_____________________________________________________________");
                System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println("\t_____________________________________________________________");
            }
        } else if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("deadline")) {
            try {
                String[] details = keywords[1].split(" /by ");
                isEmptyDescription = false;
                tasks[Task.getNumberOfTasks()] = new Deadline(details[0], details[1]);
                hasNoException = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                if (isEmptyDescription) {
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("\t_____________________________________________________________");
                } else {
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t ☹ OOPS!!! The description of a deadline cannot be in the wrong format.");
                    System.out.println("\t_____________________________________________________________");
                }
            }
        } else if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("event")) {
            try {
                String[] details = keywords[1].split(" /at ");
                isEmptyDescription = false;
                tasks[Task.getNumberOfTasks()] = new Event(details[0], details[1]);
                hasNoException = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                if (isEmptyDescription) {
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t ☹ OOPS!!! The description of an event cannot be empty.");
                    System.out.println("\t_____________________________________________________________");
                } else {
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t ☹ OOPS!!! The description of an event cannot be in the wrong format.");
                    System.out.println("\t_____________________________________________________________");
                }
            }
        }
        if (hasNoException) {
            printAddedTasked();
        }
    }

    public void listTasks() {
        System.out.println("    _____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println("    _____________________________________________________________");
    }

    public void markAsDone(String index) {
        try {
            int indexInteger = Integer.parseInt(index);
            if (indexInteger > 0) {
                tasks[indexInteger - 1].setDone();
                System.out.println("    _____________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("     [X] " + tasks[indexInteger - 1].getDescription());
                System.out.println("    _____________________________________________________________");
            } else {
                System.out.println("\t_____________________________________________________________");
                System.out.println("\t ☹ OOPS!!! The number after done must be larger than 1.");
                System.out.println("\t_____________________________________________________________");
            }
        } catch (NumberFormatException e) {
            System.out.println("\t_____________________________________________________________");
            System.out.println("\t ☹ OOPS!!! The input after done must be a number.");
            System.out.println("\t_____________________________________________________________");
        } catch (NullPointerException e) {
            System.out.println("\t_____________________________________________________________");
            System.out.println("\t ☹ OOPS!!! The task does not exist.");
            System.out.println("\t_____________________________________________________________");
        }
    }
}
