import java.util.Scanner;
import java.util.ArrayList;

public class Kate {
    public static void main(String[] args) {
        String logo = "     _  __     _         \n"
                + "    | |/ /__ _| |_ ___   \n"
                + "    | ' </ _` |  _/ -_)  \n"
                + "    |_|\\_\\__,_|\\__\\___|  \n";
        System.out.println(logo);

        String greetMessage = "     This is Kate, your personal assistant ;)\n"
                + "     How can I help you?\n";
        printMessage(greetMessage);

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();

            if (userInput.toUpperCase().equals("BYE")) {
                break;
            }

            if (userInput.toUpperCase().startsWith("TODO ")) {
                String taskDescription = userInput.substring(5).strip();
                if (taskDescription.isEmpty()) {
                    printMessage("     Please specify a task with \"todo [description]\"!\n");
                    continue;
                }

                tasks.add(new ToDo(taskDescription));
                printAddedTask(tasks);
            }

            if (userInput.toUpperCase().startsWith("DEADLINE ")) {
                String taskInfo = userInput.substring(9).strip();
                boolean isEmptyField = emptyFieldChecker(taskInfo, " /by ");
                if (isEmptyField) {
                    printMessage("     Please specify a task with \"deadline [description] /by [deadline]\"\n");
                    continue;
                }


                String[] infoArr = taskInfo.split(" /by ");
                String taskDescription = infoArr[0].strip();
                String deadline = infoArr[1].strip();

                tasks.add(new Deadline(taskDescription, deadline));
                printAddedTask(tasks);
            }

            if (userInput.toUpperCase().startsWith("EVENT ")) {
                String taskInfo = userInput.substring(6).strip();
                boolean isEmptyField = emptyFieldChecker(taskInfo, " /at ");
                if (isEmptyField) {
                    printMessage("     Please specify a task with \"event [description] /at [time frame]\"\n");
                    continue;
                }

                String[] infoArr = taskInfo.split(" /at ");
                String taskDescription = infoArr[0].strip();
                String timeFrame = infoArr[1].strip();

                tasks.add(new Event(taskDescription, timeFrame));
                printAddedTask(tasks);
            }

            if (userInput.toUpperCase().equals("LIST")) {
                printTasks(tasks);
                continue;
            }

            if (userInput.toUpperCase().startsWith("DONE")) {
                String[] inputArr = userInput.split(" ");
                boolean isValid = intChecker(inputArr, tasks.size());

                if (!isValid) {
                    continue;
                }

                int taskNumber = Integer.parseInt(inputArr[1]);
                Task curTask = tasks.get(taskNumber - 1);
                curTask.setDone();

                String doneMessage = "     Nice! I've marked this task as done:\n"
                        + "       " + curTask.printTaskInfo() + "\n";
                printMessage(doneMessage);

            }
        }

        String byeMessage = "     Leaving already? Oh well see you again soon!\n";
        printMessage(byeMessage);
    }

    public static void printMessage(String msg) {
        String formattedMsg = "";
        String underscores = "    ___________________________________________________________\n";
        formattedMsg += underscores + msg + underscores;
        System.out.println(formattedMsg);
    }

    public static void printAddedTask(ArrayList<Task> tasks) {
        String formattedMsg = "";
        String underscores = "    ___________________________________________________________\n";
        formattedMsg += underscores + "     Okay, I have added this task!\n"
                + "       " + getAddedTask(tasks).printTaskInfo() + "\n"
                + "     You currently have (" + tasks.size()
                + ") tasks in your list :)\n" + underscores;
        System.out.println(formattedMsg);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        String formattedMsg = "";
        String underscores = "    ___________________________________________________________\n";
        formattedMsg += underscores + "     Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            Task curTask = tasks.get(i);
            int numberedBullets = i + 1;
            formattedMsg += "     " + numberedBullets + ". "
                    + curTask.printTaskInfo() + "\n";
        }
        formattedMsg += underscores;
        System.out.println(formattedMsg);
    }

    private static Task getAddedTask(ArrayList<Task> tasks) {
        return tasks.get(tasks.size() - 1);
    }

    public static boolean intChecker(String[] inputArr, int size) {
        try {
            int taskNumber = Integer.parseInt(inputArr[1]);
            if ((taskNumber > size) || (taskNumber < 1) || inputArr.length != 2) {
                printMessage("     Please input a valid task number!\n");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            printMessage("     Please input an INTEGER as your task number!\n");
        }
        return false;
    }

    public static boolean emptyFieldChecker(String userInput, String delim) {
        String[] inputArr = userInput.split(delim, 2);
        try {
            String firstArrayField = inputArr[0];
            String secondArrayField = inputArr[1];
            if (firstArrayField.isEmpty() || secondArrayField.isEmpty()) {
                return true;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }


}
