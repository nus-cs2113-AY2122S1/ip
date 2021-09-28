import java.util.Scanner;

public class Duke {

    //public static String filePath = Paths.get(System.getProperty("user.dir"), "data/duke.txt").normalize().toString();

    public static void executeRequest() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!Parser.parseCommand(line).equals("bye")) {
            System.out.println(Ui.LINE);
            try {
                switch (Parser.parseCommand(line)) {
                case "list":
                    TaskList.printTaskList();
                    break;
                case "done":
                    TaskHandler.markTaskAsDone(line);
                    Storage.saveData();
                    break;
                case "deadline":
                    TaskHandler.addDeadline(line);
                    Storage.saveData();
                    break;
                case "event":
                    TaskHandler.addEvent(line);
                    Storage.saveData();
                    break;
                case "todo":
                    TaskHandler.addTodo(line);
                    Storage.saveData();
                    break;
                case "help":
                    Ui.showHelp();
                    break;
                case "delete":
                    TaskHandler.deleteTask(line);
                    Storage.saveData();
                    break;
                default:
                    TaskHandler.handleWrongCommand();
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Ui.LINE);
            line = in.nextLine();
        }
    }

    public static void main(String[] args) {
        Storage.loadData();
        Ui.printGreetMessage();
        executeRequest();
        Ui.printFarewellMessage();
    }
}
