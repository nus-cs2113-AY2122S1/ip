import java.util.Scanner;

public class Duke {

    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printList(Task[] list, int size) {
        int position = 1;
        System.out.println("    Here are the tasks in your list:");
        for( int i = 0 ; i < size ; i ++ ) {
            System.out.println( "    " + position  + "." + list[i]);
            position ++;
        }
    }

    public static void markTaskDone(Task[] list , String word) {
        //convert string number to int number
        int position = Integer.parseInt(word);
        //if task does not exist, return
        if (position > list.length) {
            System.out.println("    Sorry! No such task!");
            return;
        }
        //if task exist, mark task as done
        Task task = list[position - 1];
        task.setDone();
        //print notification
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public static void addTask(Task[] list, Task newTask, int size) {
        //add task to list
        list[size] = newTask;
        //print statements
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + list[size]);
        System.out.println("    Now you have " + (size + 1) + " tasks in the list.");
    }

    public static Deadlines createNewDeadline(String[] words) {
        int deadlineIndex = 0;
        String by = "";
        String deadlineDescription = "";
        for(int i = 1 ; i < words.length ; i ++) {
            if(words[i].equals("/by")) {
                deadlineIndex = i + 1;
                by = words[deadlineIndex];
                break;
            }
            deadlineDescription = deadlineDescription + " " + words[i];
        }
        Deadlines newDeadline = new Deadlines(deadlineDescription.substring(1), by);
        return newDeadline;
    }

    public static Events createNewEvent(String[] words) {
        int eventIndex = 0;
        String timeAllocation = "";
        String eventDescription = "";
        for(int i = 1 ; i < words.length ; i ++) {
            if(words[i].equals("/at")) {
                eventIndex = i + 1;
                for(int j = eventIndex ; j < words.length ; j++) {
                    timeAllocation = timeAllocation + " " + words[j];
                }
                break;
            }
            eventDescription = eventDescription + " " + words[i];
        }
        Events newEvent = new Events(eventDescription.substring(1), timeAllocation.substring(1));
        return newEvent;
    }

    public static int distinguishCommand(String command , Task[] list, int size) {
        //split into word array
        String[] words = command.split(" ");
        String firstWord = words[0];
        int taskCount = size;

        //determine command
        switch (firstWord) {
        case "list":
            printList(list, size);
            break;
        case "done":
            markTaskDone(list, words[1]);
            break;
        case "todo":
            ToDos newToDo = new ToDos(command.substring(5));
            addTask(list, newToDo, taskCount);
            taskCount ++;
            break;
        case "deadline":
            //extract out deadline description and by
            Deadlines newDeadline = createNewDeadline(words);
            addTask(list, newDeadline, size);
            taskCount ++;
            break;
        case "event":
            //extract out event description and timeAllocation
            Events newEvent = createNewEvent(words);
            addTask(list, newEvent, size);
            taskCount ++;
            break;
        default:
                System.out.println("    Unknown Command");
            break;
        }

        return taskCount;
    }



    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        showWelcomeMessage();
        line = in.nextLine();

        Task[] list = new Task[100];
        int taskCount = 0;

        while (!line.equals("bye")) {
            taskCount = distinguishCommand(line, list, taskCount);
            line = in.nextLine();
        }

        System.out.println("    Bye. Hope to see you again soon!");
    }
}
