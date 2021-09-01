import java.util.LinkedList;

public class TaskList {
    LinkedList<Task> savedTasks = new LinkedList<Task>();

    public TaskList(LinkedList<Task> savedTasks) {
        this.savedTasks = savedTasks;
    }

    /**
     * Getter for linked list savedTasks.
     *
     * @return savedTasks.
     */
    public LinkedList<Task> getSavedTasks() {
        return savedTasks;
    }

    /**
     * Setter for linked list savedTasks.
     *
     * @param savedTasks
     */
    public void setSavedTasks(LinkedList<Task> savedTasks) {
        this.savedTasks = savedTasks;
    }

    /**
     * Add task objects into taskList linked list.
     *
     * @param userInput is the task object the user inputted.
     */
    public void addTasks(Task userInput){
        savedTasks.addLast(userInput);
    }

    /**
     * Iterate through the savedTasks linked list.
     * Prints every object in the linked list.
     */
    public void listTasks(){
        for(int i = 0; i < savedTasks.size(); i++){
            System.out.print(i + ": " + savedTasks.get(i).toString() + "\n");
        }
    }

    /**
     * Returns a specific task from the linked list.
     *
     * @param taskListIndex is the index indicated by user.
     * @return task object which user wants.
     */
    public Task findTask(int taskListIndex){
        return savedTasks.get(taskListIndex);
    }

    /**
     * Iterates through the linkedlist to find the number of objects in the linked list.
     *
     * @return number of objects in linked list in int format.
     */
    public int countTaskInList(){
        int counter = 0;
        for(int i = 0; i < savedTasks.size(); i++){
            counter++;
        }
        return counter;
    }
}
