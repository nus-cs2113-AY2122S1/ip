import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greetings = " Hello! I'm Duke\n" + " What can I do for you?\n";
        System.out.println(greetings);
        Task[] Tasks = new Task[100];
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        boolean flag = true;
        int maxlength = 0;
        for(int j = 0; j < 100 && flag;){
            String str= sc.nextLine();
            if(str.equals("bye")){
                str = "Bye. Hope to see you again soon!";
                printString(str);
                flag = false;
            }else if(str.equals("list")){
                printList(Tasks, maxlength);
            }else if(str.contains("done")) {
                String numberOnly = str.replaceAll("[^0-9]", "");
                int num = Integer.parseInt(numberOnly);
                    if(num <= j) {
                    Tasks[num - 1].setDone(true);
                    printDone(Tasks[num - 1]);
                }else{
                    str = "not assigned yet";
                    printString(str);
                }
            }else{
                if(str.length()>maxlength) maxlength = str.length();
                Task t = new Task(str);
                addToList(t, Tasks, j);
                printTask(str);
                j++;
            }
        }
    }

    public static void addToList(Task t, Task[] list, int num){
        list[num] = t;
    }

    public static void printString(String str){
        int length = str.length();
        System.out.print("      ");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("");
        System.out.println("     |" + str + "|");
        System.out.print("     |");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printDone(Task t){
        String str = "Nice! I've marked this task as done: ";
        int length = Math.max(t.getDescription().length() + 5, str.length());
        System.out.print("      ");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + str);
        for(int n = 0; n < length - str.length(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |[" + t.getStatusIcon() + "]" + t.getDescription());
        for(int n = 0; n < length - t.getDescription().length() - 3; n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printList(Task[] list, int num){
        String listing = "Here are the tasks in your list:";
        if(listing.length() > num + 7) num = listing.length() - 7;
        System.out.print("      ");
        for(int i = 0; i < num + 7; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + listing);
        for(int n = 0; n < num + 7 - listing.length() && num > listing.length() - 7; n++) System.out.print(" ");
        System.out.println("|");
        for(int j = 1; list[j-1] != null; j++) {
            System.out.print("     |" + j + ". " + "[" + list[j-1].getStatusIcon() + "]" + list[j-1].getDescription());
            for(int n = 0; n < num + 1 - list[j-1].getDescription().length() - (int)Math.log10(j); n++) System.out.print(" ");
            System.out.println("|");
        }
        System.out.print("     |");
        for(int i = 0; i < num + 7; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printTask(String str){
        int length = str.length();
        System.out.print("      ");
        for(int i = 0; i < length + 7; i++) System.out.print("_");
        System.out.println("");
        System.out.println("     |added: " + str + "|");
        System.out.print("     |");
        for(int i = 0; i < length + 7; i++) System.out.print("_");
        System.out.println("|");
    }
}
