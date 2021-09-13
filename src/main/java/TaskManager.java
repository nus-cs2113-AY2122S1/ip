import java.util.*;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public int getTasklistLength() {
        return tasks.size();
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        Task currTask;
        Iterator<Task> it = tasks.iterator();
        int counter = 1;

        while(it.hasNext()) {
            currTask = it.next();
            sb.append(String.format("%d.[%s][%s] %s\n",
                    counter++,
                    currTask.getTypeIcon(),
                    currTask.getStatusIcon(),
                    currTask));
        }

        return sb.toString();
    }

    public Task doTask(int index) {
        index -= 1; //to match array index

        try {
            tasks.get(index).setDone();
        } catch(IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return tasks.get(index);
    }

    public Task addTask(HashMap<String, String> params) throws IllegalArgumentException{
        String type = params.get("type");
        String task = params.get("task");
        String done = params.get("done");
        Task newTask;

        if(task.strip().equals(""))
            throw new IllegalArgumentException("Task not specified");

        switch(type) {
            case "todo":
                newTask = new ToDoTask(task);
                break;
            case "deadline":
                String deadline = params.get("by");
                if(deadline == null)
                    throw new IllegalArgumentException("/by flag value not specified");

                newTask = new DeadlineTask(task, deadline);
                break;
            case "event":
                String datetime = params.get("at");
                if(datetime == null)
                    throw new IllegalArgumentException("/at flag value not specified");

                newTask = new EventTask(task, datetime);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type");
        }

        if(done != null && (done.equals("1") || done.equals("Y")) ) {
            newTask.setDone();
        }

        tasks.add(newTask);
        return newTask;
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        Task currTask;
        Iterator<Task> it = tasks.iterator();
        int counter = 1;
        String statusIcon, fileStatusIcon;

        while(it.hasNext()) {
            currTask = it.next();

            fileStatusIcon = currTask.getDone() ? "1" : "0";

            sb.append(String.format("%s;%s;%s\n",
                    currTask.getTypeIcon(),
                    fileStatusIcon,
                    currTask.toFileString()));
        }

        return sb.toString();
    }
}
