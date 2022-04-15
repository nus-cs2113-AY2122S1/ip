package task;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Manage the tasks.
 * Any change to the tasks will be performed using the TaskManager class.
 */
public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the current number of tasks.
     * @return The total number of tasks
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the printable string of all tasks.
     * @return A string of all tasks
     */
    public String listAllTasks() {
        return listTasks(tasks);
    }

    /**
     * Prints the list of tasks in a well-formatted string
     * @param listOfTasks A list of tasks
     * @return A well-formatted string of the tasks
     */
    public String listTasks(ArrayList<Task> listOfTasks) {
        if(listOfTasks.size() == 0) {
            return "No task available\n";
        }

        StringBuilder sb = new StringBuilder();
        Task currTask;
        Iterator<Task> it = listOfTasks.iterator();
        int counter = 1;

        while(it.hasNext()) {
            currTask = it.next();
            sb.append(String.format("%d. %s\n",
                    counter++,
                    currTask));
        }

        return sb.toString();
    }

    /**
     * Set a specified task in the task list to completed
     * @param index The index of the task in the task list
     * @return The task that was set to completed
     */
    public Task doTask(int index) {
        index -= 1; //to match array index

        try {
            tasks.get(index).setDone();
        } catch(IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return tasks.get(index);
    }

    /**
     * Add a new task into the task list.
     * @param newTask The new task
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Remove a specified task from the task list
     * @param index The index of the task in the task list
     * @return The task that was removed
     *
     * @throws IndexOutOfBoundsException
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException{
        index -= 1; //to match array index

        try {
            return tasks.remove(index);
        } catch(IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    /**
     * Prints the contents of the task list into a string that can be saved in the data file
     * @return The raw contents of the task list
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        Task currTask;
        Iterator<Task> it = tasks.iterator();
        int counter = 1;
        String statusIcon, fileStatusIcon;

        while(it.hasNext()) {
            currTask = it.next();

            fileStatusIcon = currTask.getDone() ? "1" : "0";

            sb.append(String.format("%s;%s;%s\n",
                    currTask.getTypeIcon(),
                    fileStatusIcon,
                    currTask.toFileString()));
        }

        return sb.toString();
    }

    /**
     * Create a new task based on the parameters specified from user input.
     * @param params The parameters from the user input
     * @return The new Task
     * @throws IllegalArgumentException
     */
    public Task createNewTask(HashMap<String, String> params) throws IllegalArgumentException{
        Task newTask;

        String type = params.get("type");
        String taskDesc = params.get("main");
        String done = params.get("done");

        if(taskDesc == null || taskDesc.equals("")) {
            throw new IllegalArgumentException("Task not specified");
        }

        switch(type) {
            case "todo":
                newTask = new ToDoTask(taskDesc);
                break;
            case "deadline":
                String deadline = params.get("by");
                if(deadline == null)
                    throw new IllegalArgumentException("/by flag value not specified");

                newTask = new DeadlineTask(taskDesc, deadline);
                break;
            case "event":
                String datetime = params.get("at");
                if(datetime == null)
                    throw new IllegalArgumentException("/at flag value not specified");

                newTask = new EventTask(taskDesc, datetime);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type");
        }

        if(done != null && (done.equals("1") || done.equals("Y")) ) {
            newTask.setDone();
        }

        return newTask;
    }

    /**
     * Populates the task list with the contents read from the data file.
     * @param fileOutput The raw contents of the data file
     */
    public void loadTasks(String[] fileOutput) {
        String delimiter = ";";
        String input, type, done, name, otherArgs;
        HashMap<String, String> params;


        for(String line : fileOutput) {
            params = new HashMap<>();
            String[] splitInput = line.split(delimiter);

            type = splitInput[0];
            done = splitInput[1];
            name = splitInput[2];

            if(splitInput.length > 3) {
                otherArgs = splitInput[3];
            }
            else {
                otherArgs = null;
            }

            params.put("main", name);
            params.put("done", done);

            switch(type) {
                case "T":
                    params.put("type", "todo");
                    break;
                case "D":
                    params.put("type", "deadline");
                    params.put("by", otherArgs);
                    break;
                case "E":
                    params.put("type", "event");
                    params.put("at", otherArgs);
                    break;
                default:
                    break;
            }
            Task newTask = createNewTask(params);
            tasks.add(newTask);
        }
    }

    /**
     * Returns an iterator to the task list.
     * This allows the Command objects to access the task list.
     * @return An iterator of the task list
     */
    public Iterator<Task> getAllTasks() {
        return tasks.iterator();
    }

}
