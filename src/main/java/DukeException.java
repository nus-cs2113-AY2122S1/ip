public class DukeException extends Exception {

    /**
     * Constructor for class DukeException.
     * @param mode Error mode.
     */
    public DukeException(int mode) {
        super();
        switch (mode) {
        case 0:
            System.out.println("Formatting error");
            break;
        case 1:
            System.out.println("Input error");
            break;
        default:
            break;
        }
    }
}
