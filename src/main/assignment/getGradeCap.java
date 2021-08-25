public class getGradeCap {

    public static double getGradeCap(String grade) {
        switch (grade) {
            case "A+":
            case "A": return 5.0;
            case "A-": return 4.5;
            case "B+": return 4.0;
            case "B": return 3.5;
            case "B-": return 3.0;
            case "C": return 2.5;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("A+: " + getGradeCap("A+"));
        System.out.println("B: " + getGradeCap("B"));
    }
}
