public class Storage {
    private static final String[] inputText = new String[100];
    private static int inputTextSize = 0;

    public static void storeInput(String input) {
        inputText[inputTextSize] = input;
        inputTextSize++;
        Response.echo("added: " + input);
    }

    public static void list() {
        System.out.println(Response.getLine());

        for (int i = 0; i < inputTextSize; i++) {
            System.out.println((i + 1) + ". " + inputText[i]);
        }

        System.out.println(Response.getLine());

    }
}