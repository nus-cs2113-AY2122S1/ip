package task;

import parser.command.AddCommand;

import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public String listAllTasks() {
        return listTasks(tasks);
    }

    public String listTasks(ArrayList<Task> listOfTasks) {
        if(listOfTasks.size() == 0) {
            return "No task available\n";
        }

        StringBuilder sb = new StringBuilder();
        Task currTask;
        Iterator<Task> it = listOfTasks.iterator();
        int counter = 1;

        while(it.hasNext()) {
            currTask = it.next();
            sb.append(String.format("%d. %s\n",
                    counter++,
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

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public Task removeTask(int index){
        index -= 1; //to match array index

        try {
            return tasks.remove(index);
        } catch(IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
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

    public Task createNewTask(HashMap<String, String> params) throws IllegalArgumentException{
        Task newTask;

        String type = params.get("type");
        String taskDesc = params.get("main");
        String done = params.get("done");

        if(taskDesc == null || taskDesc.equals("")) {
            throw new IllegalArgumentException("Task not specified");
        }

        switch(type) {
            case "todo":
                newTask = new ToDoTask(taskDesc);
                break;
            case "deadline":
                String deadline = params.get("by");
                if(deadline == null)
                    throw new IllegalArgumentException("/by flag value not specified");

                newTask = new DeadlineTask(taskDesc, deadline);
                break;
            case "event":
                String datetime = params.get("at");
                if(datetime == null)
                    throw new IllegalArgumentException("/at flag value not specified");

                newTask = new EventTask(taskDesc, datetime);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type");
        }

        if(done != null && (done.equals("1") || done.equals("Y")) ) {
            newTask.setDone();
        }

        return newTask;
    }

    public void loadTasks(String[] fileOutput) {
        String delimiter = ";";
        String input, type, done, name, otherArgs;
        HashMap<String, String> params;


        for(String line : fileOutput) {
            params = new HashMap<>();
            String[] splitInput = line.split(delimiter);

            type = splitInput[0];
            done = splitInput[1];
            name = splitInput[2];

            if(splitInput.length > 3) {
                otherArgs = splitInput[3];
            }
            else {
                otherArgs = null;
            }

            params.put("main", name);
            params.put("done", done);

            switch(type) {
                case "T":
                    params.put("type", "todo");
                    break;
                case "D":
                    params.put("type", "deadline");
                    params.put("by", otherArgs);
                    break;
                case "E":
                    params.put("type", "event");
                    params.put("at", otherArgs);
                    break;
                default:
                    break;
            }
            Task newTask = createNewTask(params);
            tasks.add(newTask);
        }
    }

    public String getOverdueTasks(LocalDateTime timeToCheck) {
        ArrayList<Task> overdueTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task instanceof TimedTask) {
                LocalDateTime taskDueTime = ((TimedTask) task).getTime();
                if (!task.getDone() && taskDueTime.isBefore(timeToCheck)) {
                    overdueTasks.add(task);
                }
            }
        }

        return listTasks(overdueTasks);
    }
}
