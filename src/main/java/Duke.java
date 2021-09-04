import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_VIEW_LIST = "list";
    private static final String COMMAND_COMPLETE_TASK = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ADD_DEADLINE = "deadline";

    private static void startDuke(){
        System.out.println(Logo.logo +Logo.divider + "Hello! I'm Duke\n" + " What can I do for you?\n" + Logo.divider);
    }

    private static void endDuke(){
        System.out.println(Logo.divider + "Bye. Hope to see you again soon!\n" + Logo.divider + Logo.bye);
    }

    public static void main(String[] args) {
        startDuke();
        Scanner in = new Scanner(System.in);
        ArrayList <Task> list = new ArrayList<Task>();
        ListManager listManager = new ListManager(list);
        while(true) {
            String echo = in.nextLine();
            String echoLower = echo.toLowerCase();
            if (echoLower.equals(COMMAND_EXIT)) {
                endDuke();
                break;
            }else if(echoLower.equals(COMMAND_VIEW_LIST)) {
                listManager.printList();
            }else if(echoLower.startsWith(COMMAND_COMPLETE_TASK)) {
                listManager.completeTask();
            }else if(echoLower.startsWith(COMMAND_ADD_EVENT)){
                int startOfTime = echoLower.indexOf("/");
                String description = echoLower.substring(6,startOfTime);
                String time = echoLower.substring(startOfTime + 1);
                listManager.addEvent(description,time);
            }else if (echoLower.startsWith(COMMAND_ADD_TODO)){
                String description = echoLower.substring(5);
                listManager.addTodo(description);
            }else if(echoLower.startsWith(COMMAND_ADD_DEADLINE)){
                int startOfDeadline = echoLower.indexOf("/");
                String description = echoLower.substring(9,startOfDeadline);
                String deadline = echoLower.substring(startOfDeadline + 1);
                listManager.addDeadline(description,deadline);
            }
        }
    }
}
