package herrekt;

public class Ui {

    void printWelcomeMessage() {
        String greeting = "Hi! I'm Herrick, your task manager." + "\n"
                + "What would you like to add to your timetable?";
        System.out.println(greeting);
    }

    void printFarewellMessage() {
        System.out.println("Bye. Hope you will complete everything for today!");
    }

    void printNumberOfTasks(TaskList tasks) {
        if (tasks.getSize() <= 1) {
            System.out.println("For now, you have "
                    + tasks.getSize()
                    + " task on the list");
        } else {
            System.out.println("For now, you have "
                    + tasks.getSize()
                    + " tasks on the list");
        }
    }

    void printMarkTaskAsDone(int taskNumber, TaskList tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(taskNumber - 1).toString());
    }

    void printTaskList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("You have no outstanding tasks. Good Job!");
        } else {
            StringBuilder toPrint = new StringBuilder("Here are the tasks in your list:" + "\n");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                if (i == tasks.getTasks().size() - 1) {
                    toPrint.append(i + 1)
                            .append(". ")
                            .append(tasks.getTasks().get(i).toString());
                    break;
                }
                toPrint.append(i + 1)
                        .append(". ")
                        .append(tasks.getTasks().get(i).toString())
                        .append("\n");
            }
            System.out.println(toPrint);
        }
    }

    void printTaskDeleted(int taskNumber, TaskList tasks) {
        int index = taskNumber - 1;
        System.out.println("Aite. I've removed this task: "
                + "\n" + "  " + tasks.getTask(index).toString());
        System.out.println("Now you have "
                + (tasks.getSize() - 1)
                + " tasks left to do");
    }



    void printLoadingError() {
        System.out.println("ERROR! Could not locate save.txt.");
    }

    void printInputBiggerThanTaskList(TaskList tasks) {
        System.out.println("ERROR! You only have " + tasks.getSize() + " current tasks");
    }

    void printInvalidInputError(String phrase) {
        System.out.println("ERROR! Please follow format in README.md");
        System.out.println("Your input: " + phrase);
    }

    void printNoTaskError(String phrase) {
        System.out.println("ERROR! What is the task?");
        System.out.println("Your input: " + phrase);
    }

    void printIncorrectFormatError(String phrase) {
        System.out.println("ERROR! I tried to "
                + "Please separate deadline/event and time with '/by' and '/at' respectively.");
        System.out.println("Your input: " + phrase);
    }
}
