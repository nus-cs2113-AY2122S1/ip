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
        case 2:
            System.out.println("Failed to load or create a save file");
            break;
        case 3:
            System.out.println("Failed to read save file");
            break;
        case 4:
            System.out.println("Failed to write into save file");
            break;
        default:
            break;
        }
    }
}
