import java.util.*;

public class Task {
    protected String taskName;
    protected boolean completed;
    LinkedList<String> savedTasks = new LinkedList<String>();

    public Task(){}

    public Task(String description, boolean completed) {
        this.taskName = description;
        this.completed = completed;
    }

    public String completedTaskIcon() {
        String completedIcon = "X";
        if(completed){
            return completedIcon;
        }
        else{
            return " ";
        }
    }

    public void markTaskAsDone(){
        completed = true;
        congratulatoryNote();
    }

    public void congratulatoryNote() {
        System.out.println("______________________________\n");
        System.out.println("Good Job! Keep striving! This task has been marked as completed: \n");
        System.out.println("[" + completedTaskIcon() + "]" + taskName + "\n");
        System.out.println("______________________________\n");
    }
}
