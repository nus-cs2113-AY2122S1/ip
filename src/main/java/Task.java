import java.util.Objects;

public class Task {
    protected static String description;
    protected static boolean isDone;
    protected static int number;
    public Task(String description) {
        Task.description = description;
        isDone = false;
        number = -1;
    }

    public static int getListNumber(){
        if (Add.getAddString().contains("done")) {
            number = Integer.parseInt(Add.getAddString().substring(5));
        }
        return number;
    }
    public static void taskDone(String description) {

        isDone = true;
        //List.listIcon[getListNumber()] = 1;
        System.out.println("Nice! I've marked this task as done:" );
        System.out.println("["+getStatusIcon()+"]"+description);
        System.out.println();
    }

    public static void taskNotDone(String description){
        isDone = false;
    }




    public static String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

}
