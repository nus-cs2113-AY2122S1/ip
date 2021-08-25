import java.util.*;

public class Task {
    protected String taskName;
    protected boolean completed;

    public Task(){}

    public Task(String description, boolean completed) {
        this.taskName = description;
        this.completed = completed;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the name of the task.
     *
     * @param taskName Task name.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Get the status of the task.
     *
     * @return true if status is complete, false if not.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Set the task status as complete.
     *
     * @param completed status is true if completed.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns Icon to show that the task has been completed.
     *
     * @return "X" if task is completed, " " if it is not.
     */
    public String completedTaskIcon() {
        String completedIcon = "X";
        if(completed){
            return completedIcon;
        }
        else{
            return " ";
        }
    }

    /**
     * When User input marks a task as done,
     * function handles the status of task.
     * Prints the congratulatory note.
     */
    public void markTaskAsDone(){
        completed = true;
        congratulatoryNote();
    }

    /**
     * Congratulate the user for completing his task.
     */
    public void congratulatoryNote() {
        System.out.println("______________________________\n");
        System.out.println("Good Job! Keep striving! This task has been marked as completed: \n");
        System.out.println("[" + completedTaskIcon() + "]" + taskName + "\n");
        System.out.println("______________________________\n");
    }
}
