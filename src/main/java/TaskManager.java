public class TaskManager {
    static final int DESCRIPTION_AFTER_BY_OR_AT = 3;
    static final int INDEX_OF_KEYWORD_OF_TASK = 0;

    private Task[] tasks = new Task[100];

    public void addTask(String line) {
        String[] keywords = line.split(" ", 2);
        if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("todo")) {
            tasks[Task.getNumberOfTasks()] = new Todo(keywords[1]);
        } else if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("deadline")) {
            String[] details = keywords[1].split(" /");
            tasks[Task.getNumberOfTasks()] = new Deadline(details[0], details[1].substring(DESCRIPTION_AFTER_BY_OR_AT));
        } else if (keywords[INDEX_OF_KEYWORD_OF_TASK].equalsIgnoreCase("event")) {
            String[] details = keywords[1].split(" /");
            tasks[Task.getNumberOfTasks()] = new Event(details[0], details[1].substring(DESCRIPTION_AFTER_BY_OR_AT));
        }
        System.out.println("    _____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + tasks[Task.getNumberOfTasks() - 1].toString());
        System.out.println("     Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        System.out.println("    _____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("    _____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println("    _____________________________________________________________");
    }

    public void markAsDone(int index) {
        tasks[index - 1].setDone();
        System.out.println("    _____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("     [X] " + tasks[index - 1].getDescription());
        System.out.println("    _____________________________________________________________");
    }
}
