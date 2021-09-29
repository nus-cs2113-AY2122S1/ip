
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Manager {
    ArrayList<Task> schedule = new ArrayList<>();
    int totalTasks = 0;

    Storage s = new Storage();

    public Manager() {
    }

    public String findCommand(String line) {
        int spaceIndex = line.indexOf(" ");
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
            System.out.println(schedule.get(i));
        }

    }

    public void handleDone(String line) {
        int number = Character.getNumericValue(line.charAt(5));
        schedule.get(number - 1).markAsDone();
    }

    public void addTodo(String content) throws IOException {
        Check c = new Check();
        boolean b = c.handleTodoException(content);
        if (b) {
            Todo t = new Todo(content);
            schedule.add(totalTasks, t);
            ++this.totalTasks;
            this.gotItMessage(t);
        }
        s.checkFile();
        s.saveFile(schedule, totalTasks);
    }

    public static Deadline initDeadline(String content) {
        int byIndex = content.indexOf("/by");
        String description = content.substring(0, byIndex - 1);
        String by = content.substring(byIndex + 4);
        Deadline d = new Deadline(description, by);
        return d;
    }

    public void addDeadline(String content) throws IOException {
        Check c = new Check();
        boolean b = c.handleDeadlineException(content);
        if (b) {
            Deadline d = initDeadline(content);
            schedule.add(totalTasks, d);
            ++this.totalTasks;
            this.gotItMessage(d);
        }
        s.checkFile();
        s.saveFile(schedule, totalTasks);
    }

    public static Event initEvent(String content) {
        int byIndex = content.indexOf("/at");
        String description = content.substring(0, byIndex - 1);
        String eventTime = content.substring(byIndex + 4);
        Event e = new Event(description, eventTime);
        return e;
    }

    public void addEvent(String content) throws IOException {
        Check c = new Check();
        boolean b = c.handleEventException(content);
        if (b) {
            Event e = initEvent(content);
            schedule.add(totalTasks, e);
            ++this.totalTasks;
            this.gotItMessage(e);
        }
        s.checkFile();
        s.saveFile(schedule, totalTasks);
    }

    public void gotItMessage(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + this.totalTasks + " tasks in the list.");
    }

    public void delete(String line) {
        int number = Character.getNumericValue(line.charAt(7));
        Task t = schedule.get(number - 1);
        schedule.remove(t);
        totalTasks--;

        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + this.totalTasks + " tasks in the list.");
    }


    public void finding(String word) {
        ArrayList<Task> found = new ArrayList<>();

        int totalFound = 0;

        for (int i = 0; i < totalTasks; i ++) {
            String description = schedule.get(i).getDescription();
            if (description.contains(word)) {
                found.add(totalFound, schedule.get(i));
                totalFound++;
            }
        }

        printFound(found, totalFound);
    }

    public void printFound(ArrayList<Task> found, int totalFound) {
        System.out.println("Here are matching tasks in your list: ");

        for(int i = 0; i < totalFound; ++i) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(found.get(i));
        }

    }
}
