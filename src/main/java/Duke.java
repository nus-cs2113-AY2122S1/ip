import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean isContinue = true;
        Headers.printBanner();
        String line;
        Scanner in = new Scanner(System.in);

        while (isContinue) {
            System.out.print("> ");
            line = in.nextLine();
            Command cmd = getCommand(line);
            switch(cmd) {
            case BYE:
                Headers.printExit();
                isContinue = false;
                break;
            case LIST:
                Headers.printSeparator();
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i+1) + ". " + tasks.get(i).printStatus());
                }
                Headers.printSeparator();
                break;
            case TODO:
                try {
                    ToDo newToDo = new ToDo(line.substring(6));
                    tasks.add(newToDo);
                    System.out.println("  (+) Added: "+ newToDo.printStatus());
                    System.out.println("  (i) You have " + tasks.size() + " tasks in the list");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("  (!) Todo description cannot be empty!");
                    System.out.println("  (!) Format: /todo <description>");
                }
                break;
            case DEADLINE:
                try {
                    String description = line.substring(10, line.indexOf("-by")).strip();
                    String time = line.substring(line.indexOf("-by") + 4).strip();
                    Deadline newDeadline = new Deadline(description, time);
                    tasks.add(newDeadline);
                    System.out.println("  (+) Added: "+ newDeadline.printStatus());
                    System.out.println("  (!) You have " + tasks.size() + " tasks in the list");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("  (!) Invalid/missing values");
                    System.out.println("  (!) Format: /deadline <description> -by <date>");
                }
                break;
            case EVENT:
                try {
                    String des = line.substring(7, line.indexOf("-from")).strip();
                    String from = line.substring(line.indexOf("-from") + 6, line.indexOf("-to")).strip();
                    String to = line.substring(line.indexOf("-to") + 4).strip();
                    Event newEvent = new Event(des, from , to);
                    tasks.add(newEvent);
                    System.out.println(" (+) Added: "+ newEvent.printStatus());
                    System.out.println(" (!) You have " + tasks.size() + " tasks in the list");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("  (!) Invalid/missing values");
                    System.out.println("  (!) Format: /event <description> -from <date> -to <date>");
                }
                break;
            case DONE:
                String[] values = line.split(" ");
                int value = Integer.parseInt(values[1]);
                if (value > tasks.size() || value < 1) {
                    System.out.println(" (!) Invalid Value! ");
                } else {
                    tasks.get(value - 1).setStatus(true);
                    System.out.println("____________________________________________________________");
                    System.out.println(" (+) Marked as Done: "+ tasks.get(value - 1).printStatus());
                    System.out.println("____________________________________________________________");
                }
                break;
            default:
                System.out.println(" (!) Unrecognised Command! ");
            }
        }
    }

    public static Command getCommand(String input){
        Command cmd = Command.UNKNOWN;
        if (input.equals("/bye")) {
            cmd = Command.BYE;
        } else if (input.equals("/list")) {
            cmd = Command.LIST;
        } else if (input.startsWith("/todo")) {
            cmd = Command.TODO;
        } else if (input.startsWith("/deadline")) {
            cmd = Command.DEADLINE;
        } else if (input.startsWith("/event")) {
            cmd = Command.EVENT;
        } else if (input.startsWith("/done")) {
            cmd = Command.DONE;
        }
        return cmd;
    }
}
