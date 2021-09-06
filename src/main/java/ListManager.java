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

    public void printToDo(){
        for(int i = 0; i < list.size(); i++){
            int itemIndex= i + 1;
            if(list.get(i) instanceof ToDo ) {
                System.out.println(itemIndex + MESSAGE_SPACER + list.get(i).toString());
            }
        }
    }

    public void printEvent(){
        for(int i = 0; i < list.size(); i++){
            int itemIndex= i + 1;
            if(list.get(i) instanceof Event ) {
                System.out.println(itemIndex + MESSAGE_SPACER + list.get(i).toString());
            }
        }
    }

    public void printDeadline(){
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

    public void completeTask(int t){
        Task doneTask = list.get(t);
        doneTask.setDone();
        System.out.println(Logo.divider + MESSAGE_TASK_COMPLETE + doneTask.getDescription());
    }
}
