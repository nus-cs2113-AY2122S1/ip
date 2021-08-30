public class Task {
    private static int numberOfTasks = 0;

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        setNumberOfTasks();
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : " ");  // mark done task with V
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static void setNumberOfTasks() {
        Task.numberOfTasks++;
    }

    public void markAsDone() {
        this.isDone = true;
        printMarkAsDoneMessage();
    }

    public void printNewTaskAddedMessage() {
        drawDivider();
        System.out.println("Karlett remembers: " + this.description);
        drawDivider();
    }

    public static void printList(Task[] list) {
        drawDivider();
        if (numberOfTasks == 0) {
            System.out.println("You have done everything! Time to relax with Karlett meow ʕ♡ﻌ♡ʔ");
        } else {
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println("ฅ" + (i + 1) + " [" + list[i].getStatusIcon() + "] "
                        + list[i].getDescription());
            }
        }
        drawDivider();
    }

    private void printMarkAsDoneMessage() {
        drawDivider();
        System.out.println("Meow~ Karlett has marked this task as done:\n" +
                "  [" + this.getStatusIcon() + "] "
                + this.getDescription());
        drawDivider();
    }

    public static void exit() {
        drawDivider();
        System.out.println("Bye~Bye~ヾ(￣▽￣) Hope to see you again soon meow.");
        drawDivider();
        System.exit(0);
    }

    private static void drawDivider() {
        int n = 4;
        while (n > 0) {
            System.out.print("ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ");
            n--;
        }
        System.out.println();
    }
}
