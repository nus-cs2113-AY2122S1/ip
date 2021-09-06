import java.util.ArrayList;

public class ArtBot implements ArtInterface{

    private final String userInput;

    public ArtBot(String userInput){
        this.userInput = userInput;
    }

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

    public void drawArt(){
        String[] charArray = userInput.split("(?!^)");
        ArrayList<String[]> artArray = new ArrayList<>();
        String[] mergeString = new String[5];
        for(String s:charArray){
            String[] letterInArtForm = getLogo(s);
            artArray.add(letterInArtForm);
        }
        for(int i = 0; i < 5; i++){
            StringBuilder sb = new StringBuilder();
            for (String[] strings : artArray) {
                String temp = strings[i];
                sb.append(temp);
            }
            mergeString[i] = sb.toString();
        }
        for(int i = 0; i < 5; i++){
            System.out.println(mergeString[i]);
        }
    }
}
