package karen.tasklist.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    protected String taskDescription;
    protected boolean isDone;

    /**
     * This class constructor is used to instantiate a new Task object with a given task description
     * and done status set as false by default.
     *
     * @param taskDescription task description of the Task object
     */
    public Task(String taskDescription){
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Returns a String to represent whether the Task object has been completed.
     *
     * @return "X" or " " to represent status of Task object
     */
    public String getStatusIcon(){
        if (isDone){
            return "X";
        }
        else {
            return " ";
        }
    }

    /**
     * Returns the task description of the Task object, eg. "Finish Homework"
     *
     * @return task description of the Task object as a String
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Returns the completion status of the Task object.
     *
     * @return boolean to check if Task object is done.
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * This method sets the Task object status to completed/ done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Returns "Task" as the task type
     *
     * @return String to represent task type of Task object
     */
    public String getType(){
        return "Task";
    }

    /**
     * Returns task date if any.
     *
     * @return task date if any as a LocalDate
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Returns task time if any.
     *
     * @return task time if any as a LocalTime
     */
    public LocalTime getTime() {
        return null;
    }

    /**
     * Returns the taskDescription of the Task object, eg. "Finish Homework".
     *
     * @return task description of the Task object as a String
     */
    public String getFormattedDescription() {
        return this.taskDescription;
    }

    /**
     * Returns the task description of the Task object as a String to be
     * saved in the storage file
     *
     * @return task description of the Task object as String
     */
    public String getFormattedFileDescription() {
        return this.taskDescription;
    }
}
