package userManagement;

import FileManager.FileReader;
import exceptions.FileException;
import tasks.TaskList;

import java.io.File;

/**
 * This class manages all users' information and helps the Userserver to find its client task list,
 * if this client used Duke before.
 */
public class UserManager {

    public boolean userExisted(String userName) {
        return new FileReader(userName).fileExists();
    }

    /**
     * Loads the user's task list if the user has used Duke before. If he/she is a new user, it returns null.
     * @param userName The name of user.
     * @return Returns a list of user's task as TaskList object. If it is a new user, is returns null.
     */
    public TaskList loadUser(String userName) throws FileException{
        TaskList tasksList = loadTaskList(userName);
        return tasksList;
    }

    private TaskList loadTaskList(String userName) throws FileException {
        FileReader reader = new FileReader(userName);
        if (!reader.fileExists()) {
            return null;
        }
        return reader.restore();
    }
}
