package duke.ArtBot;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import java.util.ArrayList;

/**
 * Convert string into graffiti form and print it
 */
public class ArtBot implements ArtInterface {

    private static final int SIZE_OF_ARRAY = 5;
    private final String userInput;

    /**
     * Constructor for ArtBot
     * Assign string to draw
     *
     * @param userInput String to be drawn
     */
    public ArtBot(String userInput){
        this.userInput = userInput;
    }

    /**
     * Each Drawn version of a letter take up 5 line
     * Convert letter into drawn version of string stored in Logo Class
     * Split each drawn version into array of 5 string separated by each line
     *
     * @param letter Letter to convert into array of string
     * @return Array of string representing a letter
     */
    private String[] getLogo(String letter){
        String[] letterArt;
        switch(letter) {
        case "A":
            letterArt = Logo.A.split("\n");
            break;
        case "B":
            letterArt = Logo.B.split("\n");
            break;
        case "C":
            letterArt = Logo.C.split("\n");
            break;
        case "D":
            letterArt = Logo.D.split("\n");
            break;
        case "E":
            letterArt = Logo.E.split("\n");
            break;
        case "F":
            letterArt = Logo.F.split("\n");
            break;
        case "G":
            letterArt = Logo.G.split("\n");
            break;
        case "H":
            letterArt = Logo.H.split("\n");
            break;
        case "I":
            letterArt = Logo.I.split("\n");
            break;
        case "J":
            letterArt = Logo.J.split("\n");
            break;
        case "K":
            letterArt = Logo.K.split("\n");
            break;
        case "L":
            letterArt = Logo.L.split("\n");
            break;
        case "M":
            letterArt = Logo.M.split("\n");
            break;
        case "N":
            letterArt = Logo.N.split("\n");
            break;
        case "O":
            letterArt = Logo.O.split("\n");
            break;
        case "P":
            letterArt = Logo.P.split("\n");
            break;
        case "Q":
            letterArt = Logo.Q.split("\n");
            break;
        case "R":
            letterArt = Logo.R.split("\n");
            break;
        case "S":
            letterArt = Logo.S.split("\n");
            break;
        case "T":
            letterArt = Logo.T.split("\n");
            break;
        case "U":
            letterArt = Logo.U.split("\n");
            break;
        case "V":
            letterArt = Logo.V.split("\n");
            break;
        case "W":
            letterArt = Logo.W.split("\n");
            break;
        case "X":
            letterArt = Logo.X.split("\n");
            break;
        case "Y":
            letterArt = Logo.Y.split("\n");
            break;
        case "Z":
            letterArt = Logo.Z.split("\n");
            break;
        case "0":
            letterArt = Logo.ZERO.split("\n");
            break;
        case "1":
            letterArt = Logo.One.split("\n");
            break;
        case "2":
            letterArt = Logo.Two.split("\n");
            break;
        case "3":
            letterArt = Logo.THREE.split("\n");
            break;
        case "4":
            letterArt = Logo.FOUR.split("\n");
            break;
        case "5":
            letterArt = Logo.FIVE.split("\n");
            break;
        case "6":
            letterArt = Logo.SIX.split("\n");
            break;
        case "7":
            letterArt = Logo.SEVEN.split("\n");
            break;
        case "8":
            letterArt = Logo.EIGHT.split("\n");
            break;
        case "9":
            letterArt = Logo.NINE.split("\n");
            break;
        default:
            letterArt = null;
            break;
        }
        return letterArt;
    }

    /**
     * Convert each letter of string into drawn form
     *
     * @param inputArrayOfLetter array of Char(letters)
     * @return list of string array where each string array is drawn version of a letter
     * @throws CommandException if char is anything other than A-Z,0-9
     */
    private ArrayList<String[]> getLetterInArtForm(String[] inputArrayOfLetter) throws CommandException{
        ArrayList<String[]> listToStoreLetterInArtForm = new ArrayList<>();
        for(String s:inputArrayOfLetter){
            String[] letterInArtForm = getLogo(s);
            if(letterInArtForm == null){
                throw new CommandException(ErrorStaticString.ERROR_ARTBOT_LETTER_NOT_FOUND);
            }
            listToStoreLetterInArtForm.add(letterInArtForm);
        }
        return listToStoreLetterInArtForm;
    }

    /**
     * Merge same index of string in each string array
     * First String in First String Array with First String in Second String Array and so on...
     *
     * @param arrayToMerge list of String Array to merge
     * @return String Array of 5 String after all have been merged
     */
    private String[] mergeArray(ArrayList<String[]> arrayToMerge){
        String[] arrayToReturn = new String[5];
        for(int i = 0; i < 5; i++){
            StringBuilder sb = new StringBuilder();
            for (String[] strings : arrayToMerge) {
                String temp = strings[i];
                sb.append(temp);
            }
            arrayToReturn[i] = sb.toString();
        }
        return arrayToReturn;
    }

    /**
     * Print a array of 5 Strings
     *
     * @param array Array to print
     */
    private void printArray(String[] array){
        for(int i = 0; i < SIZE_OF_ARRAY; i++){
            System.out.println(array[i]);
        }
    }

    /**
     * Handle interaction of other classes to echo a string in drawn form
     */
    public void drawArt() {
        String[] letterArray = userInput.split("(?!^)");
        ArrayList<String[]> letterInArtFormList = null;
        try {
            letterInArtFormList = getLetterInArtForm(letterArray);
        } catch (CommandException e) {
            e.handleException();
        }
        String[] mergeLetterHorizontallyArray = mergeArray(letterInArtFormList);
        printArray(mergeLetterHorizontallyArray);
    }
}
