package duke.TaskList;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.Storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Serve as an interface for Arraylist storing the tasks
 * Responsible for manipulation and interaction of the Arraylist
 */
public class TaskList implements TaskListInterface {

    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task: ";
    private static final String MESSAGE_TASK_IN_LIST = " tasks in the list.";
    private static final String MESSAGE_LIST_TASK = "Here are the tasks in your list:";
    private static final String MESSAGE_LIST_TODO = "Here are the ToDo tasks in your list:";
    private static final String MESSAGE_LIST_EVENT = "Here are the Event tasks in your list:";
    private static final String MESSAGE_LIST_DEADLINE = "Here are the Deadline tasks in your list:";
    private static final String MESSAGE_LIST_FIND = "Here are the matching tasks in your list:";
    private static final String MESSAGE_LIST_FIND_DATE = "Here are the tasks with matching dates in your list:";
    private static final String MESSAGE_TASK_NOW = "Now you have ";
    private static final String MESSAGE_DELETE = "Noted. I've removed this task: ";
    private static final String MESSAGE_SPACER = ". ";
    private static final String EMPTY_STRING = "";

    private final ArrayList<Task> list;

    /**
     * Constructor for the class to assign the arraylist to be manipulated
     *
     * @param list Arraylist that store the tasks
     */
    public TaskList(ArrayList<Task> list){
        this.list = list;
    }

    /**
     * Loops through a list of task and print each task
     *
     * @param listToPrint Arraylist containing tasks to print
     */
    private void printer(ArrayList<Task> listToPrint){
        for(int i = 0; i < listToPrint.size(); i++){
            int itemIndex= i + 1;
            System.out.println(itemIndex + MESSAGE_SPACER + listToPrint.get(i).toString());
        }
    }

    /**
     * Print all the task in the list
     */
    public void printList(){
        System.out.println(MESSAGE_LIST_TASK);
        printer(list);
    }

    /**
     * Print all the task with a specific date
     * Sort out the task with the same date into a new list
     * Print the new list
     *
     * @param dateSearched Date being search as LocalDate object
     * @throws CommandException if no task with the date searched was found
     */
    public void printDate(LocalDate dateSearched) throws CommandException{
        boolean haveDate = false;
        ArrayList<Task> listOfTaskWithDate = new ArrayList<>();
        for (Task t: list){
            String date = t.getDate(t.convertStringToDate(), true);
            if(!date.equals(EMPTY_STRING)) {
                LocalDate localDate = LocalDate.parse(date);
                if (dateSearched.equals(localDate)) {
                    haveDate = true;
                    listOfTaskWithDate.add(t);
                }
            }
        }
        if(!haveDate){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_DATE_INPUT);
        }
        System.out.println(MESSAGE_LIST_FIND_DATE);
        printer(listOfTaskWithDate);
    }

    /**
     * Print all tasks containing a specific string
     * Sort out all tasks containing the string to a new list
     * Print the new list
     *
     * @param wordSearch String to be searched
     * @throws CommandException if no tasks with the word to be searched was found
     */
    public void printWord(String wordSearch) throws CommandException{
        boolean haveWord = false;
        ArrayList<Task> listOfTaskWithWord = new ArrayList<>();
        for(Task t : list){
            if(t.getDescription().contains(wordSearch)){
                listOfTaskWithWord.add(t);
                haveWord = true;
            }
        }
        if(!haveWord){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_LIST);
        }
        System.out.println(MESSAGE_LIST_FIND);
        printer(listOfTaskWithWord);
    }

    /**
     * Print all the Todo task in the list
     * Sort out all the Todo task into a new list
     * Print the new list
     *
     * @throws CommandException if no Todo task is found in list
     */
    public void printToDo() throws CommandException {
        boolean haveToDo = false;
        ArrayList<Task> listOfToDo = new ArrayList<>();
        for(Task t: list){
            if(t instanceof ToDo){
                haveToDo = true;
                listOfToDo.add(t);
            }
        }
        if(!haveToDo){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_TODO_LIST);
        }
        System.out.println(MESSAGE_LIST_TODO);
        printer(listOfToDo);
    }

    /**
     * Print all the Event Task in list
     * Sort out the Event Task into a new list
     * Print the new list
     *
     * @throws CommandException if Event task is not found in list
     */
    public void printEvent() throws CommandException{
        boolean haveEvent = false;
        ArrayList<Task> listOfEvent = new ArrayList<>();
        for (Task t : list) {
            if (t instanceof Event) {
                haveEvent = true;
                listOfEvent.add(t);
            }
        }
        if(!haveEvent){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_EVENT_LIST);
        }
        System.out.println(MESSAGE_LIST_EVENT);
        printer(listOfEvent);
    }

    /**
     * Print all Deadline task list
     * Sort out Deadline task into new list
     * Print new list
     *
     * @throws CommandException if Deadline task is not found in list
     */
    public void printDeadline() throws CommandException{
        boolean haveDeadline = false;
        ArrayList<Task> listOfDeadline = new ArrayList<>();
        for (Task task : list) {
            if (task instanceof Deadline) {
                haveDeadline = true;
                listOfDeadline.add(task);
            }
        }
        if(!haveDeadline){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_DEADLINE_LIST);
        }
        System.out.println(MESSAGE_LIST_DEADLINE);
        printer(listOfDeadline);
    }

    /**
     * Print message when a task is added
     *
     * @param t Task added to the list
     */
    private void printAddItem(Task t){
        System.out.println(MESSAGE_TASK_ADDED + System.lineSeparator() + t.toString() + System.lineSeparator() + MESSAGE_TASK_NOW
                + list.size() + MESSAGE_TASK_IN_LIST);
    }

    /**
     * Adds ToDo task to list
     * Print task added
     * Write task to text file
     *
     * @param description Description of the task
     * @param isFromFile Boolean variable representing if task added is from text file
     */
    public void addTodo(String description, boolean isFromFile){
        Task t = new ToDo(description);
        list.add(t);
        if(!isFromFile) {
            printAddItem(t);
            Storage storage = new Storage();
            storage.appendTask(t);
        }
    }

    /**
     * Add Event Task to list
     * Print task added
     * Write task to text file
     *
     * @param description Description of event
     * @param eventDateTime Date and Time of event in String
     * @param isFromFile Boolean variable representing if task added is from text file
     */
    public void addEvent (String description, String eventDateTime, boolean isFromFile){
        Task t = new Event(description, eventDateTime);
        list.add(t);
        if (!isFromFile) {
            printAddItem(t);
            Storage storage = new Storage();
            storage.appendTask(t);
        }
    }

    /**
     * Add Deadline Task to list
     * Print task added
     * Write task to text file
     *
     * @param description Description of deadline
     * @param deadlineDateTime Date and Time of deadline in String
     * @param isFromFile Boolean variable representing if task added is from text file
     */
    public void addDeadline(String description, String deadlineDateTime, boolean isFromFile){
        Task t = new Deadline(description, deadlineDateTime);
        list.add(t);
        if (!isFromFile) {
            printAddItem(t);
            Storage storage = new Storage();
            storage.appendTask(t);
        }
    }

    /**
     * Set task as done
     * Print out task set as done
     * Write whole list to text file to update task as done
     *
     * @param t Index of Task to set as done
     * @param isFromFile Boolean variable representing if task added is from text file
     */
    public void completeTask(int t, boolean isFromFile){
        Task doneTask = list.get(t);
        doneTask.setDone();
        if (!isFromFile) {
            System.out.println(doneTask.toString());
            Storage storage = new Storage();
            storage.writeTask(list);
        }
    }

    /**
     * Remove task from list
     * Print task deleted
     * Write whole list text file to remove task in text file
     *
     * @param t Index of Task to delete
     */
    public void deleteTask(int t){
        Task removeTask = list.get(t);
        list.remove(t);
        printDeleteTask(removeTask);
        Storage storage = new Storage();
        storage.writeTask(list);
    }

    /**
     * Print task deleted message
     * @param t Task to delete
     */
    private void printDeleteTask(Task t){
        System.out.println(MESSAGE_DELETE + System.lineSeparator() + t.toString() + System.lineSeparator() +
                MESSAGE_TASK_NOW + list.size() + MESSAGE_TASK_IN_LIST);
    }

    /**
     * Remove all task in list
     * Print list is empty
     * Write empty string to text file to clear text file
     */
    public void clearTask(){
        list.clear();
        Storage storage = new Storage();
        storage.writeClearTask();
    }

    /**
     * To get the number of tasks stored in the arraylist
     *
     * @return size of the arraylist
     */
    public int getListSize(){
        return list.size();
    }
}
