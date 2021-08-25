import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskManager taskmanager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(taskmanager, scanner);
        ui.start();
    }
}
