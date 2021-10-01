import exceptions.NoDateDescException;
import exceptions.NoDateException;
import exceptions.NoDescException;

/**
 * This class checks the input given by the users, throwing exceptions if the input is not valid.
 */
public class Check {
    public Check() {
    }


    public static void checkTodo(String content) throws NoDescException {
        if (content.equalsIgnoreCase("todo")) {
            throw new NoDescException();
        }
    }

    /**
     * Checks whether the todo command is valid by
     * detecting whether it is accompanied by a description.
     * If invalid, then the user has to re-enter command based on whatever element is missing.
     *
     * @param content specifications from user
     * @return the validity of the todo specifications, in boolean form
     */
    public boolean handleTodoException(String content) {
        try {
            checkTodo(content);
            return true;
        } catch (NoDescException var3) {
            System.out.println("There is no description in the todo input!");
            return false;
        }
    }

    public static void checkDeadline(String content) throws NoDescException, NoDateException, NoDateDescException {
        int byIndex = content.indexOf("/by");
        if (content.equalsIgnoreCase("deadline")) {
            throw new NoDateDescException();
        } else if (byIndex == -1) {
            throw new NoDateException();
        } else if (content.substring(0, byIndex - 1).equalsIgnoreCase("")) {
            throw new NoDescException();
        }
    }

    /**
     * Checks whether the deadline command is valid by
     * detecting whether it is accompanied by both a description and a date.
     * If invalid, then the user has to re-enter command based on whatever element is missing.
     *
     * @param content specifications from user
     * @return the validity of the deadline specifications, in boolean form
     */
    public boolean handleDeadlineException(String content) {
        try {
            checkDeadline(content);
            return true;
        } catch (NoDescException var3) {
            System.out.println("There is no description in the deadline input!");
            return false;
        } catch (NoDateException var4) {
            System.out.println("There is no date in the deadline input!");
            return false;
        } catch (NoDateDescException var5) {
            System.out.println("There is no date and description in the deadline input!");
            return false;
        }
    }

    public static void checkEvent(String content) throws NoDescException, NoDateException, NoDateDescException {
        int atIndex = content.indexOf("/at");
        if (content.equalsIgnoreCase("event")) {
            throw new NoDateDescException();
        } else if (atIndex == -1) {
            throw new NoDateException();
        } else if (content.substring(0, atIndex - 1).equalsIgnoreCase("")) {
            throw new NoDescException();
        }
    }

    /**
     * Checks whether the event command is valid by
     * detecting whether it is accompanied by both a description and a date.
     * If invalid, then the user has to re-enter command based on whatever element is missing.
     *
     * @param content specifications from user
     * @return the validity of the event specifications, in boolean form
     */
    public boolean handleEventException(String content) {
        try {
            checkEvent(content);
            return true;
        } catch (NoDescException var3) {
            System.out.println("There is no description in the event input!");
            return false;
        } catch (NoDateException var4) {
            System.out.println("There is no date in the event input!");
            return false;
        } catch (NoDateDescException var5) {
            System.out.println("There is no date and description in the event input!");
            return false;
        }
    }
}
