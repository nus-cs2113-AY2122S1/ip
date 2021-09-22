package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {
    }

    /** Prints welcome message. */
    public void printWelcomeMessage() {
        println("─── .o * : *. ¤ .* : ¤ o. ───");
        println("Patchi: Hello! I'm Patchi the tasks fairy Œ(ˊᵕˋ)B\n " +
                "What can I do for you today? Œ(ˊVˋ)B");
    }

    /** Prints goodbye message. */
    public void printGoodbyeMessage() {
        println("Patchi: Bye! Hope to see you again soon! Œ(~ˊᵕˋ~)B");
        println("─── .o * : *. ¤ .* : ¤ o. ───");
    }

    /** Prints transition. */
    public void printTransition() {
        println("─── .o * : typing... : ¤ o. ───");
        print("Me: ");
    }

    /** Prints message if task added successfully. */
    public void printAddTaskMessage(TaskList tasks) {
        ArrayList<Task> tasksArrList = tasks.getTasks();
        println("Patchi: Got it! I have added " + tasksArrList.get((tasksArrList.size() - 1)).toString() +
                " to your task list! Œ(ˆOˆ)B");
        println("Patchi: You have " + tasksArrList.size() + " tasks now" +
                "! Too much work... Œ(ˊnˋ)B");
    }

    /** Prints message if task marked as done successfully. */
    public void printMarkTaskAsDoneMesage() {
        println("Patchi: Good job! I've marked this task as done on your list. " +
                "Time for a break? Œ(ˊwˋ)B");
    }

    /** Prints message if task deleted successfully. */
    public void printDeleteTaskMessage(TaskList tasks) {
        ArrayList<Task> tasksArrList = tasks.getTasks();
        println("Patchi: Whoosh! I've magicked that task away! Œ(ˊwˋ)B");
        println("Patchi: You now have " + tasksArrList.size() + " tasks! Œ(ˊwˋ)B");
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }
}
