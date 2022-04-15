package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks status of task to done when parsing txt file.
     *
     * @param index index of task
     */
    public void doneTask(int index) {
        tasks.get(index).setDone();
    }

    /**
     * Adds a todo task to list of tasks
     *
     * @param userInput user input
     * @return true if task is added successfully, false otherwise
     */
    public boolean addToDoTask(String userInput) {
        String description = validateToDo(userInput);
        boolean noDescription = description.equals("");

        if (noDescription) {
            return false;
        }
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        return true;
    }

    /**
     * Check for errors in input by user
     *
     * @param userInput user input
     * @return description of task
     */
    private String validateToDo(String userInput) {
        String description = "";
        try {
            String[] words = userInput.split(" ", 2);
            boolean emptyCommand = (words.length == 1);
            if (emptyCommand) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            description = words[1].trim();

            boolean noDescription = description.equals("");
            if (noDescription) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return description;
    }

    /**
     * Adds a deadline task to list of tasks
     *
     * @param userInput user input
     * @return true if task is added successfully, false otherwise
     */
    public boolean addDeadlineTask(String userInput) {
        String[] information = validateDeadline(userInput);
        boolean noDescription = information[0].equals("");
        boolean noDatelineTime = information[1].equals("");

        if (noDescription || noDatelineTime) {
            return false;
        }
        Deadline deadline = new Deadline(information[0], information[1]);
        tasks.add(deadline);
        return true;
    }

    /**
     * Check for errors in input by user
     *
     * @param userInput user input
     * @return description and dateline of task
     */
    private String[] validateDeadline(String userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            String[] words = userInput.split(" ", 2);
            boolean emptyCommand = (words.length == 1);
            if (emptyCommand) {
                throw new DukeException("The description of a deadline task cannot be empty.");
            }

            int keywordIndex = words[1].indexOf("/by");
            boolean noKeyword = (keywordIndex == -1);
            if (noKeyword) {
                throw new DukeException("The deadline time of the deadline task cannot be empty.");
            }

            information[0] = words[1].substring(0, keywordIndex).trim();
            information[1] = words[1].substring(keywordIndex + 3).trim();

            boolean noDescription = information[0].equals("");
            boolean noDatelineTime = information[1].equals("");

            if (noDescription || noDatelineTime) {
                throw new DukeException("The description or dateline time cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return information;
    }

    /**
     * Adds an event task to list of tasks
     *
     * @param userInput user input
     * @return true if task is added successfully, false otherwise
     */
    public boolean addEventTask(String userInput) {
        String[] information = validateEvent(userInput);
        boolean noDescription = information[0].equals("");
        boolean noDatelineTime = information[1].equals("");

        if (noDescription || noDatelineTime) {
            return false;
        }
        Event event = new Event(information[0], information[1]);
        tasks.add(event);
        return true;
    }

    /**
     * Check for errors in input by user
     *
     * @param userInput user input
     * @return description and event of task
     */
    private String[] validateEvent(String userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            String[] words = userInput.split(" ", 2);
            boolean emptyCommand = (words.length == 1);
            if (emptyCommand) {
                throw new DukeException("The description of a event task cannot be empty.");
            }

            int keywordIndex = words[1].indexOf("/at");
            boolean noKeyword = (keywordIndex == -1);
            if (noKeyword) {
                throw new DukeException("The event time of the event task cannot be empty.");
            }

            information[0] = words[1].substring(0, keywordIndex).trim();
            information[1] = words[1].substring(keywordIndex + 3).trim();

            boolean noDescription = information[0].equals("");
            boolean noEventTime = information[1].equals("");

            if (noDescription || noEventTime) {
                throw new DukeException("The description or dateline time cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return information;
    }

}
