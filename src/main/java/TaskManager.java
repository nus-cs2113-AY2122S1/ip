import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds task to taskList ArrayList
     *
     * @param task Task to be checked
     */
    public void add(String task) throws DukeException {
        String[] command = task.split(" ");
        if (command.length < 2) {
            throw new DukeException();
        }
        String taskType = getCommand(task);
        Task newTask;
        String description = getDescription(task);
        switch (taskType) {
        case "todo":
            newTask = new ToDo(description);
            taskList.add(newTask);
            break;
        case "deadline":
            String by = getDate(task);
            newTask = new Deadline(description, by);
            taskList.add(newTask);
            break;
        case "event":
            String at = getDate(task);
            newTask = new Event(description, at);
            taskList.add(newTask);
            break;
        default:
            System.out.println("     Invalid command, please try again");
            return;
        }

        System.out.println("     Got it. I've added this task:\n       " + newTask);
        printSize();
    }



    public void checkDone(String[] command) {
        try {
            Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        taskList.get(Integer.parseInt(command[1]) - 1).taskDone();
    }

    public String getDescription(String task) {
        String description;
        int separator;
        if (getCommand(task).equals("todo")) {
            description = task.substring(5);
        } else if (getCommand(task).equals("deadline")) {
            separator = task.indexOf("/by");
            description = task.substring(9, separator);
        } else if (getCommand(task).equals("event")) {
            separator = task.indexOf("/at");
            description = task.substring(6, separator);
        } else {
            description = null;
        }
        return description;
    }

    public String getCommand(String task) {
        String[] command = task.split(" ");
        String taskType = command[0];

        return taskType;
    }

    public void printSize() {
        if (getSize() == 1) {
            System.out.println("     Now you have " + 1 + " task in the list.");
        } else {
            System.out.println("     Now you have " + getSize() + " tasks in the list.");
        }
    }

    /**
     * Returns name of task given task number
     *
     * @param index index of task in taskList ArrayList
     * @return Name of Task
     */
    public String getName(int index) {
        return taskList.get(index - 1).toString();
    }


    public int getSize() {
        return taskList.size();
    }


    public void list() {
        try {
            if (taskList.size() == 0) {
                System.out.println("     ☹ OOPS!!! List is empty");
                return;
            }
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                System.out.println("     " + (i + 1) + "." + t);
            }
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Error! Please contact admin");
        }

    }


    public String getDate(String description) {
        String date;
        String taskType = getCommand(description);
        int indexOfSeparator;
        switch (taskType) {
        case "deadline":
            indexOfSeparator = description.indexOf("/by");
            date = description.substring(indexOfSeparator + 3);
            if (date.isEmpty()) {
                System.out.println("     ☹ OOPS!!! Please enter a date");

            }
            break;
        case "event":
            indexOfSeparator = description.indexOf("/at");
            date = description.substring(indexOfSeparator + 3);
            if (date.isEmpty()) {
                System.out.println("     ☹ OOPS!!! Please enter a date");
            }
            break;
        default:
            date = null;
            break;
        }
        return date;
    }


}
