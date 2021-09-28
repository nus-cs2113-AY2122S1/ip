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

public class TaskList implements TaskListInterface {

    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task: ";
    private static final String MESSAGE_TASK_IN_LIST = " tasks in the list.";
    private static final String MESSAGE_LIST_TASK = "Here are the tasks in your list:";
    private static final String MESSAGE_FIND_TASK = "Here are the matching tasks in your list:";
    private static final String MESSAGE_TASK_NOW = "Now you have ";
    private static final String MESSAGE_DELETE = "Noted. I've removed this task: ";
    private static final String MESSAGE_SPACER = ". ";

    private final ArrayList<Task> list;

    public TaskList(){
        list = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> list){
        this.list = list;
    }

    private void printer(ArrayList<Task> listToPrint){
        System.out.println(MESSAGE_LIST_TASK);
        for(int i = 0; i < listToPrint.size(); i++){
            int itemIndex= i + 1;
            System.out.println(itemIndex + MESSAGE_SPACER + listToPrint.get(i).toString());
        }
    }

    private void printerForFind(ArrayList<Task> listToPrint){
        System.out.println(MESSAGE_FIND_TASK);
        for(int i = 0; i < listToPrint.size(); i++){
            int itemIndex= i + 1;
            System.out.println(itemIndex + MESSAGE_SPACER + listToPrint.get(i).toString());
        }
    }

    public void printList(){
        printer(list);
    }

    public void printDate(LocalDate dateSearched) throws CommandException{
        boolean haveDate = false;
        ArrayList<Task> listOfTaskWithDate = new ArrayList<>();
        for (Task t: list){
            String date = t.getDate(t.convertStringToDate(), true);
            if(!date.equals("")) {
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
        printerForFind(listOfTaskWithDate);
    }

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
        printerForFind(listOfTaskWithWord);
    }

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
        printer(listOfToDo);
    }

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
        printer(listOfEvent);
    }

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
        printer(listOfDeadline);
    }

    private void printAddItem(Task t){
        System.out.println(MESSAGE_TASK_ADDED + System.lineSeparator() + t.toString() + System.lineSeparator() + MESSAGE_TASK_NOW
                + list.size() + MESSAGE_TASK_IN_LIST);
    }

    public void addTodo(String description, boolean isFromFile){
        Task t = new ToDo(description);
        list.add(t);
        if(!isFromFile) {
            printAddItem(t);
            Storage storage = new Storage();
            storage.appendTask(t);
        }
    }

    public void addEvent (String description, String eventDateTime, boolean isFromFile){
        Task t = new Event(description, eventDateTime);
        list.add(t);
        if (!isFromFile) {
            printAddItem(t);
            Storage storage = new Storage();
            storage.appendTask(t);
        }
    }

    public void addDeadline(String description, String deadlineDateTime, boolean isFromFile){
        Task t = new Deadline(description, deadlineDateTime);
        list.add(t);
        if (!isFromFile) {
            printAddItem(t);
            Storage storage = new Storage();
            storage.appendTask(t);
        }
    }

    public void completeTask(int t, boolean isFromFile) throws CommandException{
        Task doneTask = list.get(t);
        doneTask.setDone();
        if (!isFromFile) {
            System.out.println(doneTask.toString());
            Storage storage = new Storage();
            storage.writeTask(list);
        }
    }

    public void deleteTask(int t){
        Task removeTask = list.get(t);
        list.remove(t);
        printDeleteTask(removeTask);
        Storage storage = new Storage();
        storage.writeTask(list);
    }

    private void printDeleteTask(Task t){
        System.out.println(MESSAGE_DELETE + System.lineSeparator() + t.toString() + System.lineSeparator() +
                MESSAGE_TASK_NOW + list.size() + MESSAGE_TASK_IN_LIST);
    }

    public int getListSize(){
        return list.size();
    }
}
