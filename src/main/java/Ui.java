import duke.task.Task;

public class Ui {
    public static void greet(){
        String greetings = " Hello! I'm Duke\n" + " What can I do for you?\n";
        System.out.println(greetings);
    }

    public static void bye(){
        String str = "Bye. Hope to see you again soon!";
        printString(str);
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
        int length = Math.max(t.getLength(), str.length());
        System.out.print("      ");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + str);
        for(int n = 0; n < length - str.length(); n++) System.out.print(" ");
        System.out.println("|");
        switch(t.getType()){
        case TODO:
            System.out.print("     |[T][" + t.getStatusIcon() + "]"+ t.getDescription());
            break;
        case DEADLINE:
            System.out.print("     |[D][" + t.getStatusIcon() + "]" + t.getDescription() + "(by: " + t.getBy() + ")");
            break;
        case EVENT:
            System.out.print("     |[E][" + t.getStatusIcon() + "]" + t.getDescription() + "(at: " + t.getBy() + ")");
            break;
        }
        for(int n = 0; n < length - t.getLength(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printDelete(Task t, int num){
        String str = "Noted. I've removed this task: ";
        String str2 = "Now you have " + num + " tasks in the list.";
        int length = Math.max(t.getLength(), Math.max(str.length(), str2.length()));
        System.out.print("      ");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + str);
        for(int n = 0; n < length - str.length(); n++) System.out.print(" ");
        System.out.println("|");
        switch(t.getType()){
        case TODO:
            System.out.print("     |[T][" + t.getStatusIcon() + "]"+ t.getDescription());
            break;
        case DEADLINE:
            System.out.print("     |[D][" + t.getStatusIcon() + "]" + t.getDescription() + "(by: " + t.getBy() + ")");
            break;
        case EVENT:
            System.out.print("     |[E][" + t.getStatusIcon() + "]" + t.getDescription() + "(at: " + t.getBy() + ")");
            break;
        }
        for(int n = 0; n < length - t.getLength(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |" + str2);
        for(int n = 0; n < length - str2.length(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printFoundTask(TaskList list, String str, int num){
        String listing = "Here are the matching tasks in your list:";
        if(listing.length() > num) num = listing.length();
        TaskList foundTasks = new TaskList();
        for(int i = 1; i <= list.size(); i++){
            if(list.get(i - 1).getDescription().contains(str)){
                foundTasks.add(list.get(i - 1));
            }
        }
       if(foundTasks.size() > 0) {
           printList(foundTasks, num);
       } else {
           printString("OOPS! failed to find");
       }
    }

    public static void printList(TaskList list, int num){
        String listing = "Here are the tasks in your list:";
        if(listing.length() > num) num = listing.length();
        System.out.print("      ");
        for(int i = 0; i < num; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + listing);
        for(int n = 0; n < num - listing.length(); n++) System.out.print(" ");
        System.out.println("|");
        for(int j = 1; j <= list.size(); j++) {
            switch(list.get(j - 1).getType()){
            case TODO:
                System.out.print("     |" + j + ". [T][" + list.get(j - 1).getStatusIcon() + "]"+ list.get(j - 1).getDescription());
                break;
            case DEADLINE:
                System.out.print("     |" + j + ". [D][" + list.get(j - 1).getStatusIcon() + "]" + list.get(j - 1).getDescription() + "(by: " + list.get(j - 1).getBy() + ")");
                break;
            case EVENT:
                System.out.print("     |" + j + ". [E][" + list.get(j - 1).getStatusIcon() + "]" + list.get(j - 1).getDescription() + "(at: " + list.get(j - 1).getBy() + ")");
                break;
            }
            for(int n = 0; n < num - list.get(j - 1).getLength() - 3 - (int)Math.log10(j); n++) System.out.print(" ");
            System.out.println("|");
        }
        System.out.print("     |");
        for(int i = 0; i < num; i++) System.out.print("_");
        System.out.println("|");
    }

    public static void printTask(Task t, int num){
        String str = "Got it. I've added this task: ";
        String str2 = "Now you have " + num + " tasks in the list.";
        int length = Math.max(t.getLength(), Math.max(str.length(), str2.length()));
        System.out.print("      ");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("");
        System.out.print("     |" + str);
        for(int n = 0; n < length - str.length(); n++) System.out.print(" ");
        System.out.println("|");
        switch(t.getType()){
        case TODO:
            System.out.print("     |[T][" + t.getStatusIcon() + "]"+ t.getDescription());
            break;
        case DEADLINE:
            System.out.print("     |[D][" + t.getStatusIcon() + "]" + t.getDescription() + "(by: " + t.getBy() + ")");
            break;
        case EVENT:
            System.out.print("     |[E][" + t.getStatusIcon() + "]" + t.getDescription() + "(at: " + t.getBy() + ")");
            break;
        }
        for(int n = 0; n < length - t.getLength(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |" + str2);
        for(int n = 0; n < length - str2.length(); n++) System.out.print(" ");
        System.out.println("|");
        System.out.print("     |");
        for(int i = 0; i < length; i++) System.out.print("_");
        System.out.println("|");
    }
}
