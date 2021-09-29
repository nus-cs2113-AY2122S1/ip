package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String filePath = "C:/Users/kairo/Documents/ip/src/main/java/duke/DukeTaskData.txt";

    /**
     * Loads pre-existing tasks found in DukeTaskData.txt.
     * If there is no DukeTaskData.txt, a new file will be created.
     * There is no output if tasks are loaded successfully.
     * @param tasks is an instance of the public class TaskList,
     *              where the list of tasks and counter for number of tasks
     *              can be found and is updated based on user inputs.
     */
    public static void loadTasks(TaskList tasks) {

        File DukeTaskData = new File(filePath);
        Scanner scan;
        String[] execute;

        System.out.println("Currently Loading List of Tasks");

        try {
            scan = new Scanner(DukeTaskData);
            if (DukeTaskData.exists()) {
                while (scan.hasNext()) {
                    execute = scan.nextLine().split("/");
                    processLoading(tasks, execute);
                }
            } else {
                FileWriter f = new FileWriter(DukeTaskData.getAbsoluteFile());
                f.close();
            }
        } catch (IOException e) {
            System.out.println("There is an issue with the data file.");
            System.out.println("A new file will be created.");
            System.out.println("No action is required. Cheers!");
        } finally {
            System.out.println("--------------------");
        }

    }

    private static void processLoading(TaskList tasks, String[] execute) {
        switch (execute[0]) {
        case "T":
            ToDo taskTodo = new ToDo(execute[2]);
            tasks.list.add(taskTodo);
            tasks.counter += 1;
            break;
        case "D":
            Deadline taskDeadline = new Deadline(execute[2], execute[3]);
            tasks.list.add(taskDeadline);
            tasks.counter += 1;
            break;
        case "E":
            Event taskEvent = new Event(execute[2], execute[3]);
            tasks.list.add(taskEvent);
            tasks.counter += 1;
            break;
        default:
            System.out.println("Error With File");
        }
        if (execute[1].equalsIgnoreCase("X")) {
            tasks.list.get(tasks.list.size() - 1).setDone();
        }
    }

    /**
     * Saves existing tasks into DukeTaskData.txt.
     * There is no output if tasks are saved successfully.
     * @param tasks is an instance of the public class TaskList,
     *              where the list of tasks and counter for number of tasks
     *              can be found and is updated based on user inputs.
     */
    public static void saveTasks(TaskList tasks) throws IOException {

        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i <= tasks.counter - 1; i += 1) {
                fw.write(tasks.list.get(i).getLetter() + "/"
                        + tasks.list.get(i).getStatusIcon() + "/"
                        + tasks.list.get(i).getDescription() + "/"
                        + tasks.list.get(i).getDateOnly() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("There is an error" + e.getMessage());
        }
    }

}
