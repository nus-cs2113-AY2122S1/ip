public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTodo(String description){
        String todoDescription = description.substring(4, description.length()).trim();
        tasks[taskCount] = new Todo(todoDescription);
        taskCount += 1;
        Duke.printLine();
        System.out.println("\tadded todo: " + todoDescription);
        Duke.printLine();
    }

    public void printInvalid() {
        Duke.printLine();
        System.out.println("\tHey bud, the command you printed is invalid.");
        Duke.printLine();
    }


    public void addDeadline(String description){
        int slashIndex = description.indexOf('/');
        if (slashIndex > 10) {
            String deadlineDescription = description.substring(8, slashIndex).trim();
            String deadlineBy = description.substring(slashIndex + 3, description.length()).trim();
            tasks[taskCount] = new Deadline(deadlineDescription, deadlineBy);
            taskCount += 1;
            Duke.printLine();
            System.out.println("\tadded deadline: " + deadlineDescription + " (by: " + deadlineBy + ')');
            Duke.printLine();
        }
        else {
            printInvalid();
        }
    }

    public void listTasks() {
        Duke.printLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i += 1) {
            System.out.print('\t');
            System.out.print(i+1 + ". ");
            System.out.print(tasks[i].toString() + System.lineSeparator());

        }
        Duke.printLine();
    }

    public void markAsDone(int index) {
        tasks[index].setAsDone();
        Duke.printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  [X] " + tasks[index].getDescription());
        Duke.printLine();
    }
}
