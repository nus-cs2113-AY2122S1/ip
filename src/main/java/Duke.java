import java.util.Scanner;

public class Duke {

    private static void lineBreak() {
        String lineBreak = "..........................." +
                ".......................................";
        System.out.println(lineBreak);
    }

    private static void IntroductoryMessage() {
        String logo = "  /\\_/\\\n"
                + " | @ @ |    Welcome to IKAROS!\n"
                + " | uWu |  Your one and only butler\n"
                + " |_____|";
        lineBreak();

        System.out.println(logo);
        lineBreak();
        System.out.println("What assistance do you require?");
        lineBreak();
    }

    private static void done(Task[] list, String response) {
        int i = Integer.parseInt(response.substring(5)) - 1;
        list[i].markAsDone();
        System.out.println("Nice! i have marked this task as done:\n ["
                + list[i].getStatusIcon() + "] " + list[i].getDescription());
        lineBreak();
    }

    private static int addToList(Task task, int listSize, Task[] list, String response) {
        list[listSize] = task;
        System.out.println("Task added: " + task);
        System.out.println("Total no. of Tasks = " + (listSize + 1));
        lineBreak();
        listSize += 1;
        return listSize;
    }

    public static void echo() {
        System.out.println("Life is a mirror and will reflect back to "
                + "the thinker what\nhe thinks into it, echoing commencing");
        lineBreak();
        Scanner in = new Scanner(System.in);
        String response;
        response = in.nextLine();
        lineBreak();
        while (!response.equals("exit")) {
            System.out.println(response);
            lineBreak();
            response = in.nextLine();
            lineBreak();
        }
    }

    public static void printList(Task[] list, int listSize) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listSize; i++) {
            System.out.println(i + ".[" + list[i - 1].getTaskType() + "]" +
                    "[" + list[i - 1].getStatusIcon() +
                    "] " + list[i - 1].description + list[i - 1].getDate());
        }
    }

    public static void main(String[] args) {
        int listSize = 0;
        String item;
        Task[] list = new Task[100];

        Scanner in = new Scanner(System.in);
        String response = null;
        IntroductoryMessage();

        String[] command;
        boolean isRunning = true;
        while (isRunning) {
            response = in.nextLine();
            lineBreak();
            command = response.split(" ", 10);
            switch(command[0]) {
            case "echo":
                echo();
                break;
            case "todo":
                ToDo task = new ToDo(response.substring(5));
                listSize = addToList(task, listSize, list, response);
                break;
            case "list":
                printList(list, listSize);
                lineBreak();
                break;
            case "done":
                done(list, response);
                break;
            case "deadline":
                String date = response.substring(response.indexOf("/") + 4);
                Deadlines work= new Deadlines(response.substring(9, response.indexOf("/") - 1),
                        date);
                listSize = addToList(work, listSize, list, response);
                break;
            case "event":
                String timing = response.substring(response.indexOf("/") + 4);
                Event event = new Event(response.substring(6, response.indexOf("/") - 1), timing);
                listSize = addToList(event, listSize, list, response);
                break;
            case "bye":
                isRunning = false;
                break;
            default:
                System.out.println("bad command");
                lineBreak();
                break;
            }
        }
        System.out.println("GoodBye, Ikaros awaits for future commands");
        lineBreak();
    }

}
