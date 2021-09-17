package userManagement;

import FileManager.FileReader;
import tasks.TaskList;

public class UserManager {

    public boolean userExisted(String userName) {
        return new FileReader(userName).fileExists();
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
