package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class ListManager implements TaskList{

    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task: ";
    private static final String MESSAGE_TASK_IN_LIST = " tasks in the list.";
    private static final String MESSAGE_LIST_TASK = "Here are the tasks in your list:";
    private static final String MESSAGE_TASK_NOW = "Now you have ";
    private static final String MESSAGE_DELETE = "Noted. I've removed this task: ";
    private static final String MESSAGE_SPACER = ". ";

    private final ArrayList<Task> list;

    public ListManager(ArrayList<Task> list){
        this.list = list;
    }

    public void printList(){
        System.out.println(MESSAGE_LIST_TASK);
        for(int i = 0; i < list.size(); i++){
            int itemIndex= i + 1;
            System.out.println(itemIndex + MESSAGE_SPACER + list.get(i).toString());
        }
    }

    public void printToDo() throws CommandException{
        boolean haveToDo = false;
        for(int i = 1; i <= list.size(); i++){
            if(list.get(i) instanceof ToDo){
                haveToDo = true;
                break;
            }
        }
        if(!haveToDo){
            throw new CommandException(ErrorList.ERROR_EMPTY_TODO_LIST);
        }
        for(int i = 0; i < list.size(); i++){
            int itemIndex= i + 1;
            if(list.get(i) instanceof ToDo ) {
                System.out.println(itemIndex + MESSAGE_SPACER + list.get(i).toString());
            }
        }
    }

    public void printEvent() throws CommandException{
        boolean haveEvent = false;
        for(int i = 1; i <= list.size(); i++){
            if(list.get(i) instanceof Event) {
                haveEvent = true;
                break;
            }
        }
        if(!haveEvent){
            throw new CommandException(ErrorList.ERROR_EMPTY_EVENT_LIST);
        }
        for(int i = 0; i < list.size(); i++){
            int itemIndex= i + 1;
            if(list.get(i) instanceof Event ) {
                System.out.println(itemIndex + MESSAGE_SPACER + list.get(i).toString());
            }
        }
    }

    public void printDeadline() throws CommandException{
        boolean haveDeadline = false;
        for(int i = 1; i <= list.size(); i++){
            if(list.get(i) instanceof Deadline) {
                haveDeadline = true;
                break;
            }
        }
        if(!haveDeadline){
            throw new CommandException(ErrorList.ERROR_EMPTY_DEADLINE_LIST);
        }
        for(int i = 0; i < list.size(); i++){
            int itemIndex= i + 1;
            if(list.get(i) instanceof Deadline ) {
                System.out.println(itemIndex + MESSAGE_SPACER + list.get(i).toString());
            }
        }
    }

    private void printAddItem(Task t){
        System.out.println(MESSAGE_TASK_ADDED + System.lineSeparator() + t.toString() + System.lineSeparator() + MESSAGE_TASK_NOW
                            + list.size() + MESSAGE_TASK_IN_LIST);
    }

    public void addTodo(String description, boolean isFromFile){
        Task t = new ToDo(description);
        list.add(t);
        if(!isFromFile) {
            System.out.println(Logo.dividerWithoutNewLine);
            printAddItem(t);
            System.out.println(Logo.dividerWithoutNewLine);
        }
    }

    public void addEvent(String description, String time, boolean isFromFile){
        Task t = new Event(description, time);
        list.add(t);
        if(!isFromFile) {
            System.out.println(Logo.dividerWithoutNewLine);
            printAddItem(t);
            System.out.println(Logo.dividerWithoutNewLine);
        }
    }

    public void  addDeadline(String description, String deadline, boolean isFromFile){
        Task t = new Deadline(description, deadline);
        list.add(t);
        if(!isFromFile) {
            System.out.println(Logo.dividerWithoutNewLine);
            printAddItem(t);
            System.out.println(Logo.dividerWithoutNewLine);
        }
    }

    public void completeTask(int t, boolean isFromFile) throws CommandException{
        if (t > list.size() || t < 0){
            throw new CommandException(ErrorList.ERROR_DONE_TASK_NOT_IN_LIST);
        }
        Task doneTask = list.get(t);
        doneTask.setDone();
        if (!isFromFile) {
            System.out.println(doneTask.toString());
        }
    }

    public void deleteTask(int t) throws CommandException{
        if(t > list.size() || t < 0){
            throw new CommandException(ErrorList.ERROR_DELETE_TASK);
        }
        Task removeTask = list.get(t);
        list.remove(t);
        System.out.println(MESSAGE_DELETE + System.lineSeparator() + removeTask.toString() + System.lineSeparator() +
                MESSAGE_TASK_NOW + list.size() + MESSAGE_TASK_IN_LIST);
    }

    public int getListSize(){
        return list.size();
    }
}
