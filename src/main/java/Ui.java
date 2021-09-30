public class Ui {

    public void welcomeMessage() {
        System.out.println("Hello Bbygirl! I'm Your Boyfriend <3");
        System.out.println("How can I help you today? ;)");
    }

    public void goodbyeMessage() {
        System.out.println("Goodbye. I will miss you sooo much :(");
    }

    public void showLoadingError() {
        System.out.println("You need to input a correct number BB... ;'( try typing again");
    }

    public void showTypingError() {
        System.out.println("You have a typo BB.. ;'( try typing again");
    }

    public void showAddItemError() {
        System.out.println("You need to add an item BB.. ;'( try typing again");
    }

    public void deadlineFormatError() {
        System.out.println("You need to input deadline with '/by' ... ;'( try typing again");
    }

    public void eventFormatError() {
        System.out.println("You need to input event with '/at' ... ;'( try typing again");
    }

    public void showRecentTask(TaskList taskList) {
        System.out.println("Ok! I've added this task:");
        int size = taskList.size();
        System.out.println(taskList.get(size - 1));
        System.out.println("Now you have " + size + " tasks in your list uwu");
    }


    public void showDone(Task doneTask) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(doneTask);

    }

    public void showDeleted(Task delTask, TaskList taskList) {
        int size = taskList.size();
        System.out.println("Okies! I've removed this task <3 :");
        System.out.println(delTask);
        System.out.println("Now you have " + size + " tasks in your list uwu");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    }

    public void showTaskList(TaskList taskList) {
        int pos = 0;
        System.out.println("Here are the tasks in your list:");
        while (pos < taskList.size()) {
            pos += 1;
            System.out.println(pos + ". " + taskList.get(pos - 1));
        }
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    }

    public void showFoundTasks(TaskList taskList) {
        int pos = 0;
        System.out.println("Here are the matching tasks in your list:");
        while (pos < taskList.size()) {
            pos += 1;
            System.out.println(pos + ". " + taskList.get(pos - 1));
        }
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    }

    public void showNoTask() {
        System.out.println("There is no matching tasks for your search bby :( ");
    }

}
