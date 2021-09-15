import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________________";
        String curCommand = "";

        readFromFile();
        writeIntoFile();

        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        while (!curCommand.equals("bye")) {
            curCommand = input.nextLine();
            System.out.println(line);
            handleCommand(curCommand);
            System.out.println(line);
        }
    }

    // Handle command
    public static void handleCommand(String curCommand) {
        String[] commands = curCommand.split(" ", 2);
        if (commands.length > 1) {
            handleMultiCommands(commands);
        } else {
            handleSingleCommand(curCommand);
        }
    }

    // Handle multiple commands
    public static void handleMultiCommands(String[] commands) {
        switch (commands[0]) {
        case "done":
            int doneIndex = getPositiveNumeric(commands[1]);
            if (doneIndex > 0 && doneIndex <= tasks.size()) {
                tasks.get(doneIndex - 1).setDone(true);
            }
            System.out.println(doneIndex > 0 && doneIndex <= tasks.size()
                    ? "Nice! I've marked this task as done:\n " + MessageManager.taskToString(tasks.get(doneIndex - 1))
                    : "Formatting error");
            writeIntoFile();
            break;
        case "todo":
            tasks.add(new Task(commands[1], "", 'T', false));
            MessageManager.printNewTask(tasks.get(tasks.size() - 1), tasks.size());
            writeIntoFile();
            break;
        case "deadline":
            String[] deadin = commands[1].split(" /by ", 2);
            if (deadin.length > 1) {
                tasks.add(new Task(deadin[0], deadin[1], 'D', false));
                MessageManager.printNewTask(tasks.get(tasks.size() - 1), tasks.size());
            }
            else {
                System.out.println("Formatting error");
            }
            writeIntoFile();
            break;
        case "event":
            String[] evin = commands[1].split(" /at ", 2);
            if (evin.length > 1) {
                tasks.add(new Task(evin[0], evin[1], 'E', false));
                MessageManager.printNewTask(tasks.get(tasks.size() - 1), tasks.size());
            }
            else {
                System.out.println("Formatting error");
            }
            writeIntoFile();
            break;
        case "help":
            MessageManager.printHelp(commands[1]);
            break;
        default:
            System.out.println("Input error");
        }
    }

    // Handle single command
    public static void handleSingleCommand(String curCommand) {
        switch (curCommand) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + MessageManager.taskToString(tasks.get(i)));
            }
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case "help":
            MessageManager.printHelp("help");
            break;
        default:
            System.out.println("Input error");
        }
    }

    // Checks if a string is a positive numeric value
    public static int getPositiveNumeric(String str) {
        try {
            return Integer.parseInt(str);
        } catch(NumberFormatException e){
            return -1;
        }
    }

    // Write into file
    public static void writeIntoFile() {
        try {
            String dir = System.getProperty("user.dir") ;
            File newDir = new File(dir + "\\data");
            newDir.mkdir();

            File newFile = new File(dir + "\\data\\duke.txt");
            newFile.createNewFile();

            FileWriter myWriter = new FileWriter(dir + "\\data\\duke.txt");

            for (Task t : tasks) {
                myWriter.write(t.getTask()
                        + " / " + t.getTrail()
                        + " / " + t.getType()
                        + " / " + (t.isDone() ? 1 : 0)
                        + "\n"
                );
            }

            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read from file
    public static void readFromFile() {
        try {
            String dir = System.getProperty("user.dir");
            File file = new File(dir + "\\data\\duke.txt");
            file.createNewFile();

            FileReader fileReader = null;
            fileReader = new FileReader(file);

            BufferedReader bufferedreader = new BufferedReader(fileReader);
            String strLine;

            while ((strLine = bufferedreader.readLine()) != null) {
                String[] saved = strLine.split(" / ", 4);
                tasks.add(new Task(saved[0], saved[1], saved[2].charAt(0), saved[3].equals("1")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
