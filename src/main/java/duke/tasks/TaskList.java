package duke.tasks;

import duke.exceptions.*;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks = new ArrayList<>();

    public String ADDED = "Got it. I've added this task:";
    public String REMOVED = "Noted. I've removed this task:";
    public String ACKNOWLEDGED = "Nice! I've marked this task as done:";
    public String LIST_START = "Here are the tasks in your list:";
    public String FIND_START = "Here are the matching tasks in your list:";

    public String TaskListResponse(String line, int index){
        return line + "\n" + tasks.get(index).getDescription();
    }

    public void noResponse () throws EmptyException {
        throw new EmptyException();
    }

    public String printTaskCount() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    //displays the list to the user
    public String printList() throws EmptyListException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        }
        String listResponse = LIST_START;
        for (int i = 1; i <= tasks.size(); i += 1) {
            listResponse = listResponse + "\n" +  i + ". " + tasks.get(i - 1).printTask();
        }
        return listResponse;
    }

    public TaskList() {
    }

    //executes program to mark a task as done
    public String taskDone(String line)
            throws DoneFormatException, AlreadyDoneException, DoneRangeException {
        if (!line.matches("\\d+")) {
            throw new DoneFormatException();
        }
        int index = Integer.parseInt(line) - 1;
        if (index >= tasks.size()) {
            throw new DoneRangeException();
        } else if (tasks.get(index).isDone()) {
            throw new AlreadyDoneException();
        } else {
            tasks.get(index).setDone();
            return TaskListResponse(ACKNOWLEDGED, index);
        }
    }

    //adds a todo task to the list
    public String addTodo(String line) throws TodoException {
        if (line.equals("") || line.equals("todo")) {
            throw new TodoException();
        } else {
            tasks.add(new Todo(line));
            return TaskListResponse(ADDED, tasks.size()-1) +"\n" + printTaskCount();
        }
    }

    //adds a deadline task to the list
    public String addDeadline(String line) throws DeadlineException {
        if (!line.matches("(.*)/by(.*)")) {
            throw new DeadlineException();
        } else {
            //extracting the description and date
            String description = line.replaceAll("/.+", "");
            String by = line.replaceAll(".+/by", "");
            tasks.add(new Deadline(description, by));
            return TaskListResponse(ADDED, tasks.size()-1) +"\n" + printTaskCount();
        }
    }

    //adds an event task to the list
    public String addEvent(String line) throws EventException {
        if (!line.matches("(.*)/at(.*)")) {
            throw new EventException();
        } else {
            //extracting the description and date
            String description = line.replaceAll("/.+", "");
            String at = line.replaceAll(".+/at", "");
            tasks.add(new Event(description, at));
            return TaskListResponse(ADDED, tasks.size()-1) +"\n" + printTaskCount();
        }
    }

    //deletes task from the list
    public String deleteTask(String line) throws DeleteFormatException, DeleteRangeException {
        if (!line.matches("\\d+")) {
            throw new DeleteFormatException();
        }
        int index = Integer.parseInt(line) - 1;
        if (index >= tasks.size()) {
            throw new DeleteRangeException();
        } else {
            String response = TaskListResponse(REMOVED, index);
            tasks.remove(index);
            return response +"\n" + printTaskCount();
        }
    }

    //finds matching tasks in the list
    public String findTask(String line) throws NotFoundException {
        String foundList = FIND_START;
        int tasksFound = 0;
        for (int i = 1; i <= tasks.size(); ++i) {
            if (tasks.get(i - 1).getDescription().toLowerCase().contains(line.toLowerCase())) {
                tasksFound += 1;
                foundList = foundList + "\n" + i + ". " + tasks.get(i - 1).printTask();
            }
        }

        if (tasksFound == 0){
            throw new NotFoundException();
        }
        return foundList;
    }

    //clears existing list
    public void clearList(){
        tasks.clear();
    }
}
