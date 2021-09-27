package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LINE = "\t____________________________________________________________\n\t";
    public static final String BYE = LINE
            + "Bye. try not to procrastinate!\n"
            + LINE;
    public static final String GREETING = LINE
            + "Hello! I'm Anderson\n\t"
            + "What do you need to do?\n"
            + LINE;

    public void greetMessage() {
        System.out.println(LOGO + GREETING);
    }

    public String byeMessage() {
        return BYE;
    }

    public String doneMessage(Task completedTask) {
        return (LINE
                + "Nice, I've marked this task as Done:\n"
                + "\t\t" + completedTask + "\n"
                + LINE);
    }

    public String deleteMessage(ArrayList<Task> taskList, int taskNumber, Task targetTask){
        return (LINE + "Noted! I've removed this task:" +
                String.format("\n\t%d.", taskNumber) + targetTask + String.format("\n\tNow you have %d tasks in the list.\n", taskList.size()) + LINE);
     }

    public String printList(TaskList taskList) {
         int count = 0;
         String outputString = "";
         if (taskList.getList().size() == 0) {
             outputString = (LINE + "\tThere is no task in List\n" + LINE);
         } else {
             outputString = (LINE + "Here are the tasks in your list:\n");
             for (Task task : taskList.getList()) {
                 count++;
                 outputString += ("\t" + count + "." + task + "\n");
             }
             outputString += LINE;
         }
         return outputString;
     }

    public String acknowledgeAddition(ArrayList<Task> taskArrayList) {
         return (LINE
                 + "Got it. I've added this task:\n"
                 + String.format("\t%d.", taskArrayList.size()) + taskArrayList.get(taskArrayList.size() - 1) + "\n"
                 + String.format("\tNow you have %d tasks in the list.\n", taskArrayList.size())
                 + LINE);
     }

}
