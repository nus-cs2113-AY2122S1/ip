/**
 * Represents a Task.
 */
public class Task {
    private String name;

    /**
     * Create Task with specified name.
     * @param name The name of task.
     */
    public Task(String name){
        setName(name);
    }

    /**
     * Gets the task's name.
     * @return A string representing the task's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set's the task's last name.
     * @param name A string containing the task's name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
