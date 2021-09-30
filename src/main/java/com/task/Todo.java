package com.task;


/**
 * Represents a task todo.
 */
public class Todo extends Task {

    protected String type = "[T]";


    /**
     * Constructor of the Todo, stores the description.
     *
     * @param description String object representing the information about the task.
     */
    public Todo(String description) {
        super(description);
        this.isDone = false;
    }


    public String toString() {
        String status = null;

        if (this.isDone){
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return this.type + status +  super.toString();
    }

}