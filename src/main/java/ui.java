public class ui {
    String LINE = "_______________________________________________________________\n";
    public void welcome() {
        System.out.println(LINE + "Hello! I'm Duke\n" + "Whatchu want\n" + LINE);
    }

    public void listExists() {
        System.out.println("Looks like you already have some tasks. Type 'list' to view them!\n" + LINE);
    }

    public void taskDone(Task word) {
        System.out.println(LINE + "Ok! I've marked this task as done:\n" + word +
                "\nYou can add more tasks or view existing ones by typing 'list'!\n"+ LINE);
    }

    public void deleteTask(Task word) {
        System.out.println(LINE + "Ok! I've removed this task:\n" + word +
                "\nYou can add more tasks or view existing ones by typing 'list'!\n" + LINE);
    }

    public void addedTask(String firstWord, Task word, int count) {
        System.out.println(LINE + "Ok! I've added this " + firstWord + ":\n" +
                word + "\n" + "Now you have " + count + (count == 1 ? " task":" tasks") + " in the list.\n" +
                "Type 'list' to save / view your tasks!\n" + LINE);
    }

    public void list() {
        System.out.print(LINE + "You can mark tasks as done by typing 'done' + task number!\n" );
        System.out.println("Here are the tasks in your list:");
    }

    public void bye() {
        System.out.println(LINE + "Bye. Hope to see you again soon!\n" + LINE);
    }

    public void wordNotFound() {
        System.out.println("Task with given keyword does not exist!\n");
    }
}
