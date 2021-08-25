public class compareNames {
    public static void main(String[] args) {
        String x = args[0];
        String y = args[1];
        System.out.println("Words given: " + x + ", " + y);
        boolean isSame = x.equals(y);
        System.out.print("They are the same: " + isSame);
//        if (isSame) {
//            System.out.println(true);
//        } else {
//            System.out.println(false);
//        }
    }
}
