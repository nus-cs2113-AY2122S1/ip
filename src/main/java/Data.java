import java.util.ArrayList;

/**
 * Duke Data Class
 * @author husysg
 * @version 1.1
 */
public class Data {
    public static ArrayList<String> descriptions = new ArrayList<>();
    public static ArrayList<Boolean> dones = new ArrayList<>();
    public static ArrayList<String> types = new ArrayList<>();
    public static ArrayList<String> dates = new ArrayList<>();

    /**
     * @param keyword keyword for search tasks
     */
    public static void search(String keyword) {
        Ui.showLine();
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < descriptions.size(); i++) {
            if (descriptions.get(i).contains(keyword)) {
                String description = descriptions.get(i);
                String date = dates.get(i);
                String type = types.get(i);
                boolean done = dones.get(i);
                System.out.printf("     %d.", i + 1);
                switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    System.out.println("       " + todo.toString(done));
                    break;
                case "D":
                    Deadline deadline = new Deadline(description, date);
                    System.out.println("       " + deadline.toString(done));
                    break;
                case "E":
                    Event event = new Event(description, date);
                    System.out.println("       " + event.toString(done));
                    break;
                default:
                    System.out.println("       Unknown Type");
                }
            }
        }
        Ui.showLine();
    }
}
