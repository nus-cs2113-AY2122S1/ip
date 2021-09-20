package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

//    public void addTask(String request) throws Exception {
//        Task newTask = Parser.getTask(request);
//        loadTask(newTask);
//        System.out.printf("Got it. I've added this task:\n" +
//                "  %s\nNow you have %d task in the list\n"
//                ,newTask, tasks.size());
//    }

    public void addTask(Task task){
        tasks.add(task);
    }



//    public void doneTask(String request) {
//        try {
//            int taskIndex = Parser.getTaskIndex(request.trim());
//            Task task = tasks.get(taskIndex);
//            if (task.isDone()) {
//                System.out.println("This task is already done!");
//            } else {
//                tasks.get(taskIndex).setDone();
//                System.out.printf("Nice! I've marked this task as done:\n" +
//                        "  %s\n", tasks.get(taskIndex));
//            }
//        } catch (ArrayIndexOutOfBoundsException ex) {
//            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The format for 'done' is 'done <task number>'");
//        } catch (IndexOutOfBoundsException ex) {
//            throw new IndexOutOfBoundsException("☹ OOPS!!! The list does not have that many task ><");
//        }
//    }

    public Task doneTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.setDone();
        return task;
    }

    public Task deleteTask(int taskIndex) {
        try {
            return tasks.remove(taskIndex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The format for 'delete' is 'delete <task number>'");
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The list does not have that many task ><");
        }
    }

