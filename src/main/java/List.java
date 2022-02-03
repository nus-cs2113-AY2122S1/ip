//@@author ZhangErliCarl-reused
//Wrong submission of others' codes


public class List {
    private static String[] currentList = new String[100];
    public static int listSize = 0;
    private static int[] isDone = new int[100];

    public static void addToList(String input) {
        currentList[listSize] =  input;
        listSize++;
        System.out.println("____________________________________________________________\n"
                + "Added: " + input + "\n"
                + "____________________________________________________________\n");
    }

    public static void printItems() {
        if (listSize == 0) {
            System.out.println("The list is empty.");
        } else {
            for (int i = 1; i <= listSize; i++) {
                System.out.print(i + ". " );
                System.out.print("[");
                if (isDone[i-1] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
                System.out.print("] " + currentList[i-1] + "\n");
            }
        }
    }

    public static void mark(int number) {
        isDone[number-1] = 1;
    }

    public static void markedItems() {
        for (int i = 0; i < isDone.length; i++) {
            if (isDone[i] == 1) {
                System.out.println("[X] " + currentList[i]);
            }
        }
    }
}

//@@author
