import java.util.Locale;

//possible to make this class an inner private class in TodoList
public class Task {
    private String description;
    private boolean isDone;

    private static int count = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.count++;
    }

    public static int getCount(){
        return count;
    }

    public static boolean isEmpty(){
        return Task.getCount() == 0;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean status() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString(){
        String done = this.status()? "X" : " ";
        String taskType = this.getClass().getName().substring(0,1).toUpperCase();
        return String.format("[%s][%s] %s",taskType, done, this.getDescription());
    }
}
