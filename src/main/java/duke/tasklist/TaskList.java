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
    public void storageDoneTask(int index) {
        tasks.get(index).setDone();
    }

    public void addToDoTask(String userInput) {
        String description = validateToDo(userInput);
        if (description == null) {
            return;
        }
        ToDo todo = new ToDo(description);
        tasks.add(todo);
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
            description = words[1];
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return description;
    }

    public void addDeadlineTask(String userInput) {
        String[] information = validateDeadline(userInput);
        if (information[0].equals("") || information[1].equals("")) {
            return;
        }
        Deadline deadline = new Deadline(information[0], information[1]);
        tasks.add(deadline);
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

    public void addEventTask(String userInput) {
        String[] information = validateEvent(userInput);
        if (information[0].equals("") || information[1].equals("")) {
            return;
        }
        Event event = new Event(information[0], information[1]);
        tasks.add(event);
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
