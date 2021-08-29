public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void printInvalid() {
        Duke.printLine();
        System.out.println("\tHey bud, the command you printed is invalid.");
        System.out.println("\tHere's some examples of valid commands:");
        System.out.println("\t- todo Read book");
        System.out.println("\t- event Book club /at Monday 2pm");
        System.out.println("\t- deadline Return book /by Friday");
        Duke.printLine();
    }

    public void addTodo(String description){
        String todoDescription = description.substring(4, description.length()).trim();
        tasks[taskCount] = new Todo(todoDescription);
        taskCount += 1;
        Duke.printLine();
        System.out.println("\tAdded todo: " + todoDescription);
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
            System.out.println("\tAdded deadline: " + deadlineDescription + " (by: " + deadlineBy + ')');
            Duke.printLine();
        }
        else {
            printInvalid();
        }
    }

    public void addEvent(String description){
        int slashIndex = description.indexOf('/');
        if (slashIndex > 8) {
            String eventDescription = description.substring(5, slashIndex).trim();
            String eventAt = description.substring(slashIndex + 3, description.length()).trim();
            tasks[taskCount] = new Event(eventDescription, eventAt);
            taskCount += 1;
            Duke.printLine();
            System.out.println("\tAdded event: " + eventDescription + " (at: " + eventAt + ')');
            Duke.printLine();
        }
        else {
            printInvalid();
        }
    }

    public void listTasks() {
        Duke.printLine();
        if (taskCount > 0) {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i += 1) {
                System.out.print('\t');
                System.out.print(i + 1 + ". ");
                System.out.print(tasks[i].toString() + System.lineSeparator());
            }
        } else {
            System.out.println("\tYou have no tasks.");
        }
        Duke.printLine();
    }

    public void markAsDone(int index) {
        tasks[index].setAsDone();
        Duke.printLine();
        System.out.println("\tNice! You completed this task:");
        System.out.println("\t  [X] " + tasks[index].getDescription());
        Duke.printLine();
    }
}
