public class list {
    private static String[] l = new String[100];
    public static int listSize = 0;
    private static String[] isDone = new String[100];
    private static String[] type = new String[100];

    public static void initializing() {
        for (int i = 0; i < 100; i++) {
            isDone[i] = " ";
            type[i] = " ";
        }
    }

    public static void addToList(String input) {
        if (input.contains("todo")) {
            l[listSize] = input.substring(5);
            type[listSize] = "T";
        } else if (input.contains("deadline")) {
            String by = input.substring(input.indexOf("/") + 4);
            l[listSize] = input.substring(9, input.indexOf("/")) + " (by: " + by
                    + ")";
            type[listSize] = "D";
        } else if (input.contains("event")) {
            String at = input.substring(input.indexOf("/") + 4);
            l[listSize] = input.substring(6, input.indexOf("/")) + "(at: " + at
                    + ")";
            type[listSize] = "E";
        }
        listSize++;
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task: \n"
                + "  [" + type[listSize - 1] + "]"
                + "[" + isDone[listSize - 1] + "]"
                + l[listSize - 1] + "\n"
                + "Now you have " + listSize + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }

    public static void printItems(){
        if (listSize == 0) {
            System.out.println("The list is empty.");
            System.out.println("The list is empty.");
            return;
        } else {
            for (int i = 1; i <= listSize; i++){
                System.out.print(i + ". " );
                System.out.print("[" + type[i - 1] + "]" + "["
                        + isDone[i - 1] + "] " + l[i-1] + "\n");
            }
        }
    }

    public static void mark(int number){
        isDone[number-1] = "X";
    }

    public static void printMarkedItems(){
        for (int i=0;i<isDone.length;i++){
            if (isDone[i] == "X") {
                System.out.println("[" + type[i] + "]"
                    + "[" + isDone[i] + "] " + l[i]);
            }
        }
    }
}
