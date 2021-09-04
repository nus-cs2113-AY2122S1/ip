public class EmptyParameterException extends Exception {

    public EmptyParameterException(String parameterName) {
        System.out.println("The field for " + parameterName + " cannot be empty!");
    }

}
