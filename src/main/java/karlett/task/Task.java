package karlett.task;

import karlett.storage.TaskListEncoder;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class Task {

    protected String description;
    protected boolean isDone;

    /* constructor used for user input */
    public Task(String description) throws IOException {
        this.description = description;
        this.isDone = false;
        TaskList.increaseNumberOfTasks();
        TaskListEncoder.appendNewTaskToFile(this);
        TextUi.printNewTaskAddedMessage(this);
    }

    /* constructor used for loading file data */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        TaskList.increaseNumberOfTasks();
    }

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : " ");  // mark done task with V
    }

    public void markAsDone(int index) throws IOException {
        this.isDone = true;
        TaskListEncoder.modifyTaskStatusInFile(index);
        TextUi.printMarkAsDoneMessage(this);
    }

    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public static void exit() {
        TextUi.drawDivider();
        System.out.println("Bye~Bye~ヾ(￣▽￣) Hope to see you again soon meow.");
        TextUi.drawDivider();
        System.exit(0);
    }
}
