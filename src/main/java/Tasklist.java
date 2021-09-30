public class Tasklist {
    /**
     * This function is used to show the task list
     */
    public static void showList() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < Data.descriptions.size(); i++) {
            String description = Data.descriptions.get(i);
            String date = Data.dates.get(i);
            String type = Data.types.get(i);
            boolean isDone = Data.dones.get(i);
            System.out.printf("     %d.", i + 1);
            switch (type) {
            case "T":
                Todo todo = new Todo(description);
                System.out.println(todo.toString(isDone));
                break;
            case "D":
                Deadline deadline = new Deadline(description, date);
                System.out.println(deadline.toString(isDone));
                break;
            case "E":
                Event event = new Event(description, date);
                System.out.println(event.toString(isDone));
                break;
            default:
                System.out.println("       Unknown Type");
            }
        }
        Ui.showLine();
    }

    /**
     * @param number number = index + 1
     */
    public static void markAsDone(int number) {
        Ui.showLine();
        System.out.println("     Nice! I've marked this task as done:");
        int index = number - 1;
        String description = Data.descriptions.get(index);
        String date = Data.dates.get(index);
        String type = Data.types.get(index);
        // mark as done
        Data.dones.set(index, true);
        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            System.out.println("       " + todo.toString(true));
            break;
        case "D":
            Deadline deadline = new Deadline(description, date);
            System.out.println("       " + deadline.toString(true));
            break;
        case "E":
            Event event = new Event(description, date);
            System.out.println("       " + event.toString(true));
            break;
        default:
            System.out.println("       Unknown Type");
        }
        Ui.showLine();
    }

    /**
     * @param number number = index + 1
     */
    public static void deleteTask(int number) {
        Ui.showLine();
        System.out.println("     Noted. I've removed this task:");
        int index = number - 1;
        String description = Data.descriptions.get(index);
        String date = Data.dates.get(index);
        String type = Data.types.get(index);
        boolean done = Data.dones.get(index);
        // mark as done
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
        Data.descriptions.remove(index);
        Data.dates.remove(index);
        Data.types.remove(index);
        Data.dones.remove(index);
        System.out.println("     Now you have " + Data.descriptions.size() + " tasks in the list.");
        Ui.showLine();
    }
}