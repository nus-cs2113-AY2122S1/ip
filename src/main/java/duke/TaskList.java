package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size(){
        return tasks.size();
    }

    public void loadTask(Task task){
        tasks.add(task);
    }

    public String saveTask(int taskIndex){
        Task task = tasks.get(taskIndex);
        return task.saveFormat();
    }

    public void addTask(String request) throws Exception {
        Task newTask = RequestParser.getTask(request);
        loadTask(newTask);
        System.out.printf("Got it. I've added this task:\n" +
                "  %s\nNow you have %d task in the list\n"
                ,newTask, tasks.size());
    }

    public void doneTask(String request) {
        try {
            int taskIndex = RequestParser.getTaskIndex(request.trim());
            Task task = tasks.get(taskIndex);
            if (task.isDone()) {
                System.out.println("This task is already done!");
            } else {
                tasks.get(taskIndex).setDone();
                System.out.printf("Nice! I've marked this task as done:\n" +
                        "  %s\n", tasks.get(taskIndex));
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The format for 'done' is 'done <task number>'");
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The list does not have that many task ><");
        }
    }

    public void deleteTask(String request) {
         try {
             int taskIndex = RequestParser.getTaskIndex(request.trim());
             System.out.printf("Noted. I've removed this task:\n" +
                     "  %s\nNow you have %d task in the list.\n", tasks.remove(taskIndex), tasks.size());
         } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The format for 'delete' is 'delete <task number>'");
         } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The list does not have that many task ><");
         }
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Take a chill pill! Your todo list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }
}
