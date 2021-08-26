
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // You can add more methods here
    public static String[] filter(String[] items){
        String[] results = new String[items.length];
        int matchCount = 0;
        for(String item: items){
            if (item.contains("$")){
                results[matchCount] = item;
                matchCount++;
            }
        }
        return Arrays.copyOf(results, matchCount);
    }

    public static double totalExpenses(String[] prices) {
       double total = 0;
        for (String price : prices) {
            total += Double.parseDouble(price.trim().replace("$"," "));
        }
        return total;
    }
    public static double exchangeRate(double overseas) {
        return overseas * 1.70;
    }
    public static String[] splitSentence(String sentence) {
        return sentence.split(" ");
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.print("Your expenses while overseas?");
        // TODO: add your code here
        line = in.nextLine();
        String[] amounts = filter(splitSentence(line));
        System.out.print("Expenses in overseas currency:");
        System.out.println(Arrays.toString(amounts));
        System.out.print("Total in local currency: ");
        System.out.println("$" + String.format("%.2f", exchangeRate(totalExpenses(amounts))));
    }
}