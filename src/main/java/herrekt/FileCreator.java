package herrekt;

public class FileCreator {
    public static void main(String[] args) {
        java.io.File f = new java.io.File("text-ui-test/test.txt");
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }
}
