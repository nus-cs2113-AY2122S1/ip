package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.DukeException.InvalidCommandException;
import Duke.DukeException.IndexMissingException;

import java.time.LocalDate;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String line = "____________________________________________________________\n";
    public static final String path = "..\\tasks.txt";
    public static final int MAX_NUM_OF_TASKS = 100;
    public static final int TODO_POS = 5;
    public static final int BY_POS = 4;
    public static final int DEADLINE_POS = 9;
    public static final int AT_POS = 4;
    public static final int EVENT_POS = 6;
    public static final int TYPE_POS = 4;
    public static final int TASK_POS = 10;

    public static void main(String[] args) {

        greeting();
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            tasks = getDatafromFile();
        } catch (FileNotFoundException f) {
            System.out.println("No tasks can be loaded.\n");
        }

        Scanner input = new Scanner(System.in);
        while (true) {
            String inputString = input.nextLine();
            String findCommand = inputString.split(" ")[0];

            if (inputString.trim().equals("bye")) {
                saveDataToFile("tasks", tasks);
                exit();
                break;
            } else if (inputString.trim().equals("list")) {
                    try {
                        if (tasks.size() > 0) {
                            System.out.println(line
                                    + "Here are the tasks in your list: ");
                        }
                        printList(tasks);
                        System.out.println(line);
                    } catch (IndexOutOfBoundsException i) {
                        System.out.print(line
                                + "The list is empty.\n"
                                + line);
                    }
            } else if (findCommand.contains("done") == true) {
                try {
                    setDone(inputString, tasks);
                } catch (IndexOutOfBoundsException i) {
                    System.out.println(line
                            + "OOPS!!! The index is out of range.\n"
                            + line);
                } catch (NumberFormatException n) {
                    System.out.println(line
                            + "OOPS!!! The index of done indstruction should be a number.\n"
                            + line);
                } catch (IndexMissingException m) {
                    m.printMessage();
                }
            } else if (findCommand.contains("delete") == true) {
                try {
                    deleteTask(inputString, tasks);
                } catch (IndexOutOfBoundsException i) {
                    System.out.println(line
                            + "OOPS!!! The index is out of range.\n"
                            + line);
                } catch (NumberFormatException n) {
                    System.out.println(line
                            + "OOPS!!! The index of delete indstruction should be a number.\n"
                            + line);
                }
            } else if (inputString.trim().equals("clear") == true) {
                clearList(tasks);
            } else if (findCommand.contains("find") == true) {
                try {
                    findTask(inputString, tasks);
                } catch (IndexOutOfBoundsException i) {
                    System.out.println(line
                            + "Sorry, please tell me which task you want to find?\n"
                            + line);
                }
            } else {
                try {
                    addTask(inputString, tasks);
                } catch (InvalidCommandException c) {
                    c.printMessage();
                } catch(IndexOutOfBoundsException i) {
                    String type = "";
                    if (inputString.contains("todo")) {
                        type = "todo";
                    } else if (inputString.contains("deadline")) {
                        type = "deadline";
                    } else if (inputString.contains("event")) {
                        type = "event";
                    }
                    System.out.println(line
                            + "OOPS!!! The description of a " + type + " cannot be empty.\n"
                            + line);
                    }
                }
            }
        }

    public static final void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(line
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + line);
    }

    public static final void exit() {
        System.out.println(line
                + "Bye. Hope to see you again soon!\n"
                + line);
    }

    public static final void printList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            Task temp = tasks.get(0);
        } else {
            for (int index = 0; index < tasks.size(); index++) {
                System.out.println((index + 1) + ". "
                        + tasks.get(index).toString());
            }
        }
    }

    public static final void setDone(String input, ArrayList<Task> tasks) throws IndexMissingException {
        String num = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c) == true) {
                num += c;
            }
        }
        if (input.trim().equals("done")) {
            throw new IndexMissingException();
        }

        int index = Integer.parseInt(num);
        tasks.get(index - 1).markedAsDone();
        System.out.println(line
                + "Nice! I've marked this task as done: \n"
                + tasks.get(index - 1) + "\n"
                + line);
    }

    public static final void addTask(String input, ArrayList<Task> tasks) throws InvalidCommandException {
        if (input.contains("todo") == true) {
            tasks.add(new Todo(input.substring(TODO_POS)));
        } else if (input.contains("deadline") == true) {
            String by = input.substring(input.indexOf("/") + BY_POS);
            tasks.add(new Deadline(input.substring(DEADLINE_POS,
                    input.indexOf("/")), by));
        } else if (input.contains("event") == true) {
            String at = input.substring(input.indexOf("/") + AT_POS);
            tasks.add(new Event(input.substring(EVENT_POS,
                    input.indexOf("/")), at));
        } else {
            try {
                System.out.print(tasks.get(tasks.size() + 1).toString());
            } catch (IndexOutOfBoundsException i) {
                throw new InvalidCommandException();
            }
        }

        System.out.println(line
                + "Got it. I've added this task: \n"
                + tasks.get(tasks.size() - 1) + "\n"
                + "Now you have " + tasks.size()
                + " tasks in the list\n"
                + line);
    }

    public static final void deleteTask(String input, ArrayList<Task> tasks) {
        String num = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c) == true) {
                num += c;
            }

        }

        int index = Integer.parseInt(num);
        Task removedTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println(line
                + "Noted. I've removed this task: \n"
                + removedTask.toString()
                + "\nNow you have " + tasks.size()
                + " tasks in the list.\n" + line);
    }

    public static final void clearList(ArrayList<Task> tasks) {
        while (tasks.size() > 0) {
            tasks.remove(0);
        }

        System.out.println(line
                + "The list has been cleared. Now the list is empty.\n"
                + line);
    }

    public static final void findTask(String input, ArrayList<Task> tasks) {
        boolean found = false;
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            String target = input.substring(5);
            if (task.getDescription().contains(target) == true) {
                foundTasks.add(task);
                found = true;
            }
        }

        if (!found) {
            System.out.println(line
                    + "Sorry, I cannot find any matching task in your list.\n"
                    + line);
        } else {
            System.out.println(line
                    + "Here are the matching tasks in your list:");
            printList(foundTasks);
            System.out.println(line);
        }
    }

    public static final ArrayList<Task> getDatafromFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        BufferedReader reader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = "";
            Task task = new Task("null");

            while ((tempString = reader.readLine()) != null) {
                if (tempString.charAt(TYPE_POS) == 'T') {
                    task = (new Todo(tempString.substring(TASK_POS)));
                } else if (tempString.charAt(TYPE_POS) == 'E') {
                    String event = tempString.substring(TASK_POS,
                            tempString.indexOf("(") - 1);
                    String at = tempString.substring(tempString.indexOf(":") + 2,
                            tempString.indexOf(")"));
                    task = (new Event(event, at));
                } else if (tempString.charAt(TYPE_POS) == 'D') {
                    String deadline = tempString.substring(TASK_POS,
                            tempString.indexOf("(") - 1);
                    String by = tempString.substring(tempString.indexOf(":") + 2,
                            tempString.indexOf(")"));
                    task = (new Deadline(deadline, by));
                }

                if (tempString.charAt(7) == 'X') {
                    task.markedAsDone();
                }

                tasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (tasks.size() > 0) {
            System.out.println("Tasks have been loaded.\n" + line);
        } else {
            System.out.println("The current list is empty.\n" + line);
        }
        return tasks;
    }

    public static final void saveDataToFile(String fileName, ArrayList<Task> tasks) {
        BufferedWriter writer = null;
        File file = new File("..\\"+ fileName + ".txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            for (Task task : tasks) {
                int i = 0;
                writer.write(i + 1 + ". " + task.toString());
                i++;
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The list of tasks have been saved！");
    }
}