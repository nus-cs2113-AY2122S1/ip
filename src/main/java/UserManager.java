import FileManager.FileReader;
import InputHandle.Tasks.TaskList;

import java.util.Scanner;

public class UserManager {
    private static final String GREETINGS = "     Hello! I'm Duke\n" + "     Can I get your name?\n";
    private static final String DIVISIONLINE = "    ____________________________________________________________\n";

    public String getUserName() {
        System.out.println(DIVISIONLINE + GREETINGS + DIVISIONLINE);
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        return userName;
    }

    public TaskList loadUser(String userName) {
        TaskList tasksList = loadTaskList(userName);
        return tasksList;
    }

    private TaskList loadTaskList(String userName) {
        FileReader reader = new FileReader(userName);
        if (!reader.fileExists()) {
            return null;
        }
        return reader.restore();
    }
}
