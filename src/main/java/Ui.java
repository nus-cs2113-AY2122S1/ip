public class Ui {

    public void welcomeMessage() {
        System.out.println("Hello Bbygirl! I'm Your Boyfriend <3");
        System.out.println("How can I help you today? ;)");
    }

    public void goodbyeMessage() {
        System.out.println("Goodbye. I will miss you sooo much :(");
    }

    /**
     * Message is printed to user when they input an incorrect number for "delete" and "done" command.
     */
    public void showLoadingError() {
        System.out.println("You need to input a correct number BB... ;'( try typing again");
    }

    /**
     * Message is shown when there is an invalid command. Prompts user to reenter the command.
     */
    public void showTypingError() {
        System.out.println("You have a typo BB.. ;'( try typing again");
    }

    /**
     * Reminds user to use correct format for "deadline" command.
     */
    public void deadlineFormatError() {
        System.out.println("You need to input deadline with '/by' ... ;'( try typing again");
    }

    /**
     * Reminds user to use correct format for "event" command.
     */
    public void eventFormatError() {
        System.out.println("You need to input event with '/at' ... ;'( try typing again");
    }

    /**
     * Prints out the newly added task to the Task List. Notifies user how many tasks they have currently in the list.
     * @param taskList List of tasks that the user has.
     */
    public void showRecentTask(TaskList taskList) {
        System.out.println("Ok! I've added this task:");
        int size = taskList.size();
        System.out.println(taskList.get(size - 1));
        System.out.println("Now you have " + size + " tasks in your list uwu");
    }


    /**
     * Prints out the task that user has marked as done.
     * @param doneTask Task that has been completed.
     */
    public void showDone(Task doneTask) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(doneTask);

    }

    /**
     * Prints out recently deleted task and shows how many tasks are left in the list.
     * @param delTask Task to be deleted.
     * @param taskList List of tasks that the user has.
     */
    public void showDeleted(Task delTask, TaskList taskList) {
        int size = taskList.size();
        System.out.println("Okies! I've removed this task <3 :");
        System.out.println(delTask);
        System.out.println("Now you have " + size + " tasks in your list uwu");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    }

    /**
     * Prints out the entire task list of the user.
     * @param taskList List of tasks that the user has.
     */
    public void showTaskList(TaskList taskList) {
        int pos = 0;
        System.out.println("Here are the tasks in your list:");
        while (pos < taskList.size()) {
            pos += 1;
            System.out.println(pos + ". " + taskList.get(pos - 1));
        }
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    }

    /**
     * Prints out the tasks that match what the user is searching
     * @param taskList List of tasks which match the user's search.
     */
    public void showFoundTasks(TaskList taskList) {
        int pos = 0;
        System.out.println("Here are the matching tasks in your list:");
        while (pos < taskList.size()) {
            pos += 1;
            System.out.println(pos + ". " + taskList.get(pos - 1));
        }
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    }

    /**
     * Prints out message to notify user when there is no task in the description.
     */
    public void showInvalidTask() {
        System.out.println("You have not inputted a task bby >:(  angry uwu");
    }

    /**
     * Prints out message when there is no task in the list that matches the user's search.
     */
    public void showNoTask() {
        System.out.println("There is no matching tasks for your search bby :( ");
    }

}
