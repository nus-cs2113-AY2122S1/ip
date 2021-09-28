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

    /**
     * prints message to greet user.
     */
    public void greetMessage() {
        System.out.println(LOGO + GREETING);
    }

    /**
     * Retrieves bye Message.
     *
     * @return bye Message.
     */
    public String byeMessage() {
        return BYE;
    }

    /**
     * Acknowledge marking task as done.
     *
     * @param completedTask Task targeted to be marked.
     * @return Message on completion of marking done.
     */
    public String doneMessage(String completedTask) {
        if (completedTask.equals("Done Previously")) {
            return (LINE
                    + "This task has previously been completed\n"
                    + LINE);
        }
        return (LINE
                + "Nice, I've marked this task as Done:\n"
                + "\t\t" + completedTask + "\n"
                + LINE);
    }

    /**
     * Acknowledge deletion of task from list of tasks.
     *
     * @param taskList ArrayList of Task.
     * @param taskNumber Index number of target Task.
     * @param targetTask Task to remove.
     * @return Acknowledgement message.
     */
    public String deleteMessage(ArrayList<Task> taskList, int taskNumber, Task targetTask){
        return (LINE + "Noted! I've removed this task:" +
                String.format("\n\t%d.", taskNumber) + targetTask + String.format("\n\tNow you have %d tasks in the list.\n", taskList.size()) + LINE);
     }

    /***
     * Retrieves list in String form.
     *
     * @param taskList current TaskList.
     * @return String of list.
     */
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
             outputString += String.format("\n\tAs of now, %d task(s) remain not marked Done.\n", taskList.notDoneCount()) + LINE;
         }
         return outputString;
     }

    /**
     * Retrieve acknowledgement message of new tasks to list.
     *
     * @param taskArrayList ArrayList of tasks.
     * @return Acknowledgement of addition message.
     */
    public String acknowledgeAddition(ArrayList<Task> taskArrayList) {
         return (LINE
                 + "Got it. I've added this task:\n"
                 + String.format("\t%d.", taskArrayList.size()) + taskArrayList.get(taskArrayList.size() - 1) + "\n"
                 + String.format("\tNow you have %d tasks in the list.\n", taskArrayList.size())
                 + LINE);
     }

     public String helpList() {
        return (LINE +
                "Here are the list of commands Available in Duke:\n" +
                LINE +
                "bye : closes Programme\n\t" +
                "list : lists out every Task stored in the savefile\n\t" +
                "todo : Adds a new ToDo to the Task List\n\t" +
                "deadline : Adds a new Deadline to the Task List\n\t" +
                "event : Adds a new Event to the Task List\n\t" +
                "find : Lists all tasks that contains a keyword\n" +
                LINE);
     }

    /**
     * Retrieves list of tasks containing keywords.
     *
     * @param keyword Extracted keyword from user.
     * @param taskList Current TaskList object.
     * @return list of tasks containing keywords.
     */
     public String findResults(String keyword, TaskList taskList) {
        String results = taskList.find(keyword);
        return (LINE +
                "Find Results of [" + keyword + "]:\n" +
                results +
                LINE);
     }

    /**
     * Retrieves correct format for Date/Time input.
     *
     * @return Correct format for Date/Time input.
     */
    public String dateTimeFormat() {
        return ("\t____________________________________________________________\n\t"
                + "Date / Time input format should be: dd/mm/yyyy hhmmss"
                + "\n\t____________________________________________________________\n\t");
     }

}
