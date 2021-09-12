import java.util.Arrays;

public class choose {
    protected static Task[] list = new Task[100];
    private static int tasksAdded = 0;
    private static final int LINE_WIDTH = 60;
    private static Task[] List = new Task[100];
    private static int listSize=0;


    public static void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    public static void addTask(String line) {
        List[listSize] = new Task(line);
       // listlast();
        listSize++;
    }

    public static void list() {
        for (int i = 0; i < listSize; i++) {
            System.out.println(i+1 + "." + List[i]);
        }
    }
    public static void listLast() {
            System.out.println(List[listSize]);
    }

    public static void setDone(int doneNumber){
        List[doneNumber-1].setDone("X");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(List[doneNumber-1]);
    }

    public static void setDeadline(String item, String timing){
        List[listSize] = new Deadline(item, timing);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have "+ listSize +" tasks in the list.");
    }
    public static void setEvent(String item, String timing){
        List[listSize] = new Event(item, timing);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have "+ listSize +" tasks in the list.");
    }
    public static void setTodo(String item){
        List[listSize] = new Todo(item);
        System.out.println("Got it. I've added this task:");
        listLast();
        listSize++;
        System.out.println(" Now you have "+ listSize +" tasks in the list.");
    }

//    public static void checkDoneTask(int taskNumber) {
//        int taskIndex = taskNumber - 1;
//        list[taskIndex].markAsDone();
//    }


//    public static void printWelcomeMessage() {
//        printHorizontalLine();
//        System.out.println("     Hello! I'm Duke");
//        System.out.println("     What can I do for you?");
//        printHorizontalLine();
//    }
//
//
//    public static void printGoodbyeMessage() {
//        printHorizontalLine();
//        System.out.println("     Bye. Hope to see you again soon!");
//        printHorizontalLine();
//    }


    public static void printList() {

        printHorizontalLine();
        if (tasksAdded != 0) {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasksAdded; i++) {
                int numbering = i + 1;
                // need to replace this with to string
                System.out.println("     " + numbering + ". " + list[i]);
            }
        } else {
            System.out.println("     You have no tasks in the list at the moment. Please add a new task to begin.");
        }
        printHorizontalLine();
    }

    public static void printAddNewTask(Task newTask) {
        printHorizontalLine();
        System.out.println("     " + newTask);
        System.out.println("     Now you have " + tasksAdded + " tasks in the list.");
        printHorizontalLine();
    }



    public static String[] taskLister(String[] todos) {
        int todoCount = 0;
        String[] result = new String[100];
        for (String todo : todos) {
            //System.out.println(word);
            if (todo != null) {
                result[todoCount] = todo;
                todoCount++;
            } else {
                break;
            }
        }
        return Arrays.copyOf(result, todoCount);
    }

    public static String[] checkMarker(String[] tobeCheckeds, int doneNumber, int listedcount) {
        String[] checkedMarks = new String[tobeCheckeds.length];
        for (int i = 0; i < listedcount; i++) {
            if (tobeCheckeds[i] == null) { //&& !tobeCheckeds[i].equals("X")
                checkedMarks[i] = " ";
            } else {
                checkedMarks[i] = tobeCheckeds[i];
            }
        }
        checkedMarks[doneNumber] = "X";
        return checkedMarks;
    }
}



