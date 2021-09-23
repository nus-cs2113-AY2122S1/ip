//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Manager {
    Task[] schedule = new Task[100];
    int totalTasks = 0;

    public Manager() {
    }

    public String findCommand(String line) {
        int spaceIndex = line.indexOf(32);
        return spaceIndex == -1 ? line : line.substring(0, spaceIndex);
    }

    public String findContent(String line) {
        int spaceIndex = line.indexOf(" ");
        return spaceIndex == -1 ? line : line.substring(spaceIndex);
    }

    public void printBye() {
        System.out.println("Bye! Cya next time!");
    }

    public void printList() {
        System.out.println("Here are the tasks in your list: ");

        for(int i = 0; i < this.totalTasks; ++i) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(this.schedule[i]);
        }

    }

    public void handleDone(String line) {
        int number = Character.getNumericValue(line.charAt(5));
        this.schedule[number - 1].markAsDone();
    }

    public void addTodo(String content) {
        Check c = new Check();
        boolean b = c.handleTodoException(content);
        if (b) {
            Todo t = new Todo(content);
            this.schedule[this.totalTasks] = t;
            ++this.totalTasks;
            this.gotItMessage();
        }

    }

    public static Deadline initDeadline(String content) {
        int byIndex = content.indexOf("/by");
        String description = content.substring(0, byIndex - 1);
        String by = content.substring(byIndex + 4);
        Deadline d = new Deadline(description, by);
        return d;
    }

    public void addDeadline(String content) {
        Check c = new Check();
        boolean b = c.handleDeadlineException(content);
        if (b) {
            Deadline d = initDeadline(content);
            this.schedule[this.totalTasks] = d;
            ++this.totalTasks;
            this.gotItMessage();
        }

    }

    public static Event initEvent(String content) {
        int byIndex = content.indexOf("/at");
        String description = content.substring(0, byIndex - 1);
        String eventTime = content.substring(byIndex + 4);
        Event e = new Event(description, eventTime);
        return e;
    }

    public void addEvent(String content) {
        Check c = new Check();
        boolean b = c.handleEventException(content);
        if (b) {
            Event e = initEvent(content);
            this.schedule[this.totalTasks] = e;
            ++this.totalTasks;
            this.gotItMessage();
        }

    }

    public void gotItMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println(this.schedule[this.totalTasks - 1]);
        System.out.println("Now you have " + this.totalTasks + " tasks in the list.");
    }
}
