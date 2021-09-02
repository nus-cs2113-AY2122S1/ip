class TaskList {
    private Task[] tasks = new Task[100];
    private int totalTasks = 0;

    public int addList(String userInput) {
        Task newTask = getTask(userInput);
        System.out.println("       " + newTask);
        this.tasks[totalTasks] = newTask;
        totalTasks ++;
        return totalTasks;
    }

    public void listTasks() {
        for (int i = 0; i < totalTasks; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i].toString());
        }
    }

    public void markAsDone(int index) {
        this.tasks[index].setCompleted();
        System.out.println("       " + this.tasks[index]);
    }

    private Task getTask(String userInput) {
        int taskTypeIndex = userInput.indexOf(' ');
        int deadlineIndex = userInput.indexOf('/');
        String taskType = userInput.substring(0, taskTypeIndex);
        String taskName, deadline;

        if (taskType.equals("todo")) {
            taskName = userInput.substring(taskTypeIndex + 1);
            return new Todo(taskName, false);
        } else if (taskType.equals("deadline")) {
            deadline = userInput.substring(deadlineIndex + 1);
            taskName = userInput.substring(taskTypeIndex + 1, deadlineIndex);
            return new Deadline(taskName, deadline, false);
        }
        deadline = userInput.substring(deadlineIndex + 1);
        taskName = userInput.substring(taskTypeIndex + 1, deadlineIndex);
        return new Event(taskName, deadline, false);
    }

}
