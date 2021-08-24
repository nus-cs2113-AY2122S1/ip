import java.util.*;

public class TaskList {
    LinkedList<String> savedTasks = new LinkedList<String>();

    public TaskList(LinkedList<String> savedTasks) {
        this.savedTasks = savedTasks;
    }

    public LinkedList<String> getSavedTasks() {
        return savedTasks;
    }

    public void setSavedTasks(LinkedList<String> savedTasks) {
        this.savedTasks = savedTasks;
    }

    public void addTasks(String userInput){
        savedTasks.add(userInput);
        System.out.println("______________________________\n");
        System.out.println(userInput + " added into your To-Do list.\n");
        System.out.println("______________________________\n");
    }

    public void deleteTasks(String userInput){
        savedTasks.remove(userInput);
        System.out.println("______________________________\n");
        System.out.println(userInput + " has been removed\n");
        System.out.println("______________________________\n");
    }

    public void listTasks(){
        for(int i = 0; i < savedTasks.size(); i++){
            System.out.print(i + ": " + savedTasks.get(i) + "\n");
        }
    }
}
