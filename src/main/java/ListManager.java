import java.util.ArrayList;

public class ListManager {
    private static ArrayList<Task> list;

    public ListManager(ArrayList<Task> list){
        this.list = list;
    }

    public void printList(){
        for(int i = 0; i < list.size(); i++){
            int itemIndex= i + 1;
            System.out.println(itemIndex + ". " + list.get(i).toString());
        }
    }

    public void completeTask(){
        printList();
    }

    private void printAddItem(Task t){
        System.out.println("Got it. I've added this task: " + System.lineSeparator()
                            + t.toString() + System.lineSeparator() + "Now you have "
                            + list.size() +" tasks in the list.");
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
}
