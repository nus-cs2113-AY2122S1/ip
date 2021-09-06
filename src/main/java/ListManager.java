import java.util.ArrayList;

public class ListManager implements TaskList{

    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task: ";
    private static final String MESSAGE_TASK_COMPLETE = "Nice! I've marked this task as done: ";
    private static final String MESSAGE_TASK_IN_LIST = " tasks in the list.";
    private static final String MESSAGE_TASK_NOW = "Now you have ";
    private static final String MESSAGE_SPACER = ". ";
    private final ArrayList<Task> list;

    public ListManager(ArrayList<Task> list){
        this.list = list;
    }

    public void printList(){
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

    public void addTodo(String description){
        Task t = new ToDo(description);
        list.add(t);
        printAddItem(t);
    }

    public void addEvent(String description, String time){
        Task t = new Event(description, time);
        list.add(t);
        printAddItem(t);
    }

    public void  addDeadline(String description, String deadline){
        Task t = new Deadline(description, deadline);
        list.add(t);
        printAddItem(t);
    }

    public void completeTask(int t) throws CommandException{
        if (t > list.size() || t < 0){
            throw new CommandException(ErrorList.ERROR_DONE_TASK_NOT_IN_LIST);
        }
        Task doneTask = list.get(t);
        doneTask.setDone();
        System.out.println(Logo.divider + MESSAGE_TASK_COMPLETE + doneTask.getDescription());
    }

    public int getListSize(){
        return list.size();
    }
}
