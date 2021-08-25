import java.util.*;

public class TaskList {
    LinkedList<Task> savedTasks = new LinkedList<Task>();

    public TaskList(LinkedList<Task> savedTasks) {
        this.savedTasks = savedTasks;
    }

    public LinkedList<Task> getSavedTasks() {
        return savedTasks;
    }

    public void setSavedTasks(LinkedList<Task> savedTasks) {
        this.savedTasks = savedTasks;
    }

    public void addTasks(Task userInput){
        savedTasks.addLast(userInput);
    }

    public void deleteTasks(Object userInput){
        savedTasks.remove(userInput);
        System.out.println("______________________________\n");
        System.out.println(userInput + " has been removed\n");
        System.out.println("______________________________\n");
    }

    public void listTasks(){
        for(int i = 0; i < savedTasks.size(); i++){
            System.out.print(i + ": " + savedTasks.get(i).taskName + "\n");
        }
    }
}
