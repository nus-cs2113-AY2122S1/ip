package TypeOfTasks;

public class Deadline extends Task{
    protected String by;
    protected String tag = "D";
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    
    /**
     * Returns either a by or at description depending on task type
     * If the task is a Deadline returns the by description, if the task is Event returns the at description
     *
     * @return description for at/by in Event/Deadline respectively.
     */
    public String getInfo() {
        return by;
    }
    
    
    /**
     * Returns the task's tag to identify type of task
     * If the task is a Todo,Deadline,Event it returns T,D and E accordingly
     *
     * @return task's tag
     */
    public String getTag() {
        return tag;
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[D][" + (theTask.getStatus()) + "] "+ theTask.getDescription() + "(by: " + by + ")");
    }
}
