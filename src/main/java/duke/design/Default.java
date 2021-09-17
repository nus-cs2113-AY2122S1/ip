package duke.design;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Credit: The pictures are generated with the help of an online picture to ASCII symbols converter
 * The website available: "https://manytools.org/hacker-tools/convert-images-to-ascii-art/"
 */
public abstract class Default {
    public static final String CURR_VERSION = "Version 7.0";

    //Corner symbols for to-do list frames
    public static final String TOP_LEFT_CORNER = "/";
    public static final String TOP_RIGHT_CORNER = "\\";
    public static final String BOTTOM_LEFT_CORNER = "\\";
    public static final String BOTTOM_RIGHT_CORNER = "/";

    //Pictures for view -p
    public static final String PICTURE_1 = "***********************************************************************************************************************\n" +
            "**                                                                                                                   **\n" +
            "**       (((((((((((((((((((((*#//////*///////*#/////*///////**//*****//******/*****///*,//((((((((((((((((((        **\n" +
            "**       ((((((((((((((((((/##///////////////*#///,//*///////***//******/,*******/*****/***,(((((((((((((((((        **\n" +
            "**       ((((((((((((((((*#(///////*//////*/*#////*//*///////*****,**************************,(((((((((((((((        **\n" +
            "**       ((((((((((((((*#(*///////*//////***###/#/*//*/(/////***************,********,********,((((((((((((((        **\n" +
            "**       ((((((((((((/##/////////*//#((/**/(##(///,//////////******.*********,*********,********/((((((((((((        **\n" +
            "**       (((((((((((/#(////(((((*//////****#//////*///*//////******,**********,*********,*********(((((((((((        **\n" +
            "**       ((((((((((######//////,//////****((///////*//*//////******************,******************,((((((((((        **\n" +
            "**       (((((((((###(////////,////*//****#//*//////*//*//*//*******,***********,**********,*******,(((((((((        **\n" +
            "**       (((((((/##//*///////*/////*/*****#//*///////*////*//***,***.***********************.*,******((((((((        **\n" +
            "**       (((((((##//*///*///**/////*/****,(//**///////*///**********,,***********,***********.*,****,((((((((        **\n" +
            "**       ((((((##///*//**//**,/////*******//,***////////*//*****,****,,,**********,****,******,,*****,(((((((        **\n" +
            "**       (((((*#///////**/***,/////********//****//////////,****%****.,,,**********.***,,******,.**,*,(((((((        **\n" +
            "**       ((((/#///////*******,/*///*******%//,****//////////****/%***,,,,,,*****,***.***,,******,**,,**((((((        **\n" +
            "**       ((((*(//*////********//////****,/%#//,****,/////////*,**%%,**,,,,,,*****,***,,**,,*********,*,((((((        **\n" +
            "**       (((((//*,/////*******,/,///*****/%(@*/,******////////***/&&(*,,,,,,,,***,,***,,,,,,,*******.,,((((((        **\n" +
            "**       (((((/***//,/,*******%%/#*//*,**,%%&@%/*@(*****,//////***(%@&,*@*,,.,,,**,,.**.,,.,,,******,,.((((((        **\n" +
            "**       (((///**//,/*//******%%@**@#,.........@@*/@@@@&/***,*///**,@&.........,.,*,,,.*,,,,.,,,****.,,((((((        **\n" +
            "**       (((///*,/////*,*,***,,,....,/,......&&@@@@@@@@@@@@@@@@@@@&%/,*...... ......,,,.,,.,,,,.,,***.,((((((        **\n" +
            "**       ((((/**,/,/////,**,.,,&&&&&&,.......,&@@@@@@@@@@@@@@@@@@@@@@&.........,&&&&#,,.*****,,..,..,**,(((((        **\n" +
            "**       ((((***/,/////////**%&&&&&&,.....,@@@@@@@@@@@@@@@@@@@@@@@@@@%......,@@@@&&&&&/*********,*,*****(((((        **\n" +
            "**       ((((,,,,/////////***&&&&&&&*****.***//@@@@@@@@@@@@@@@@@@@@@@&***/**/****&&&&&%*******,**,******(((((        **\n" +
            "**       ((((,**//*////*//***@@@&&&&*///(((///&@@@@@@@@@@@@@@@@@@@@@@&*/////////&&@@%%%*******.***,.****,((((        **\n" +
            "**       (((,*.//*/////*/***/@@@@@@@@/((((((/@@@@@@@@@@@@@@@@@@@@@@@@@&/((((((/@&%(%%%%(******.***,,*,,**/(((        **\n" +
            "**       (((/////*/*///*/***%@@@@@@@@@@@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%*****,,****.*,,**,(((        **\n" +
            "**       ##*/*//,//*///*/***&@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%*****,******,,,**,(((        **\n" +
            "**       ##,*//*//***//*/***@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&%%%%%%%%****,,******.,,.*,(((        **\n" +
            "**       ##,*/*/,/***//*****@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%*****,.*******,*,*,(((        **\n" +
            "**       ##.////,/***///.**,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&%%%%%%%/%***,.*****,**.#,*(#*#        **\n" +
            "**       //,/,//,/****///,**%&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%*%#**,,,*****.**/,,#####        **\n" +
            "**       //*/,./**/****//**,*/,@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@&%%%%%%%,,,,*,,,****,.**.,******,        **\n" +
            "**       ///*,***,**,****/*,,,.,#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&%%%%%%#,,.,,,.*****,***.*********        **\n" +
            "**       (///********,*****/.,,,,.&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&%%%%%%,,,,,,,,******.*,***********        **\n" +
            "**       (((((,********,**************&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&%%%%%%,,.,,,.*****..,.**************        **\n" +
            "**       ((((((#******,////*****////////////%&&&@@@@@@@@@@@@@@@@@@@@@@&%%%%%*,,,,,,*,,,**********************        **\n" +
            "**       (((((((((((((((((((((/////////////////////&&@@@@@@@@@&@#*/////////////////////////***********///////        **\n" +
            "**       ((((((((((((((((((((((((((((//////////////////%@&@(*/////////////////////////////////*//////////////        **\n" +
            "**       &##((((((((((((((((((((((((((((((///////////////*////////////////////////(((((((((((((//////////////        **\n" +
            "**       &&%%%&#(((((((((((((((((((((((((((((((////////////*/////////(((((((((((((((((((((((((////////////(##        **\n" +
            "**       %%%%%%%%&%&%#(((((((((((((((((((((((((((((/////////**(((((((((((((((((((((((/////////////((########*        **\n" +
            "**       %%%%%%%%%%%%%%&%%%%#((((((((((((((((((((((((((//////**,(((/////////////////(((#################(#*,,        **";

    public static final String PICTURE_2 = "***********************************************************************************************************************\n" +
            "**                                                                                                                   **\n" +
            "**       //////,***,*/,////////#/////**#/(///*****////*//////***************.,,,,,,,,,,,,,,,,,**#########(,,,        **\n" +
            "**       /////****,//*////////*##(///**/#////****.**//**/////*****************,,,,,,,,,,,,,,,,.**(#########,,        **\n" +
            "**       ////,**,(////////*//**##(#///*,///*//*,******/,**/////*****************.,,,,,,,,,,,,,,.**(########(,        **\n" +
            "**       ////**/////*/(///*/***#//////**,//**/**.*,*****,***////****************,*,,,,,,,,,,,,,,.,*#########,        **\n" +
            "**       ///**#(//////////*/***/////////**//**/**.**%**********//*******,*********.,,,,,,,,,,,,,,.,*#########        **\n" +
            "**       ///*###(#////////*/****#///*//////*/*****.*,%&***********************,*****.,,,,,,,,,,,,,.,*########        **\n" +
            "**       //*(#(////*//////***,**.//,/**///////,****,**&&(%****,************.*****.****,,,,,,,,,,,,,,,*#######        **\n" +
            "**       //,#//////*///////**,**,(//,//**///////,*****,,%@@#&,**,.,**********,*.*****.***.,,,,,,,,,.,*#######        **\n" +
            "**       ///#///*//**//////******(&*//,/***///////*****@@#..   ........ ..,***,,**////**.,,,,,,,,,,,,,(######        **\n" +
            "**       //((///**/**,/,////******&%@,/*@//****////////,.@@...........&&&&&//****,////***.,,,,,,,,,,,,*######        **\n" +
            "**       //,/////**,**(/%%///******%%@@@*%@@@@@@@@@@@@@@@@@....  ...@@@&&@&(//****/////***.,,,,,,,,,,,*######        **\n" +
            "**       /////,///**.**&*&@@@/....... @@@@@@@@@@@@@@@@@@@@&****/.////**&&&%%*//***/////***,.,,,,,,,,.,,######        **\n" +
            "**       //////*////*,*,%@## .  .......(@@@@@@@@@@@@@@@@@@@@////((((//,@%%%%&//***/////*****%%%%,,,,.,*.#####        **\n" +
            "**       /////*/,/////*.,.#@&@....  .@#*@@@@@@@@@@@@@@@@@@@@@@*((((/&&&&%%%%%*//**/////**.*.%%%%%,,,,,#######        **\n" +
            "**       ///////,//**///*,,&&....*..////&@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&%%%%#//*,//////*,**%%*%%/,,,,#######        **\n" +
            "**       ///////////,/*@@@&&&***///(((((@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&%%%%%//*//*/*/*****/(#%%%,.,######((        **\n" +
            "**       //////////.//**#@@@@@@*//(((/@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&%%%%,///./*//*,**,*((%%%,.*##(,****        **\n" +
            "**       ////////,////***(@@@@@@&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@&@@&&&@@@@@&%%%%*//./**/*,*,,*,%%%,,.,*********        **\n" +
            "**       ////////,/////***#&&&&&&&&&&&&&@@@@@@@@/(((##########@@@@@@@@@@@%%%%%%/*/**/*,,,,,,#%.*********,**,,        **\n" +
            "**       ////////./////,*.*&&&&&&&&&&&&&@@@@@@((((##############&@@@@@@@&%%%%%///*,/,,,,,.,,************,,,,*        **\n" +
            "**       ///////////*///****&&&&&&&&&&@@@@@@@/(((#################%@@@@@%%%%(/*,,,,,,,,,,,,******************        **\n" +
            "**       (/////,,////**//,**@&&&&&@@@@@@@@@@@/((###################&@&@%%%//*,,,,,,,,,,,,********************        **\n" +
            "**       /////*//////,****/*,@@@@@@@@@@@@@@@@@(####################*@@%%**,,,,.,,,,,,,,**//****/*************        **\n" +
            "**       ////*//////////,*****/@@@@@@@@@@@@@@@&####################(&%*,.,,,,,,,,,,,//*/*********************        **\n" +
            "**       ////(((///////////////////*@@@@@@@@@@@@##################((*,,,,,,,../((((((((///****************/((        **\n" +
            "**       ///((((((///////////,//////////*,,/&@@@@@(##########,****///////((((((((((((((((((***********(((((((        **\n" +
            "**       ///(((((((((((((///////////////////////****.%@&&/*****/////((((((((((((((((((((((/****/((((((((((/((        **\n" +
            "**       ///*#((((((((((((((((((///////////////////******,,**///((((((((((((((((((((((%%((((((((((((((,((((((        **\n" +
            "**       ////&%((((((((((((((((((((((((((((///////////*******.((((((((((((((((#%%%%%%#(((((((((((*(((((((((((        **\n" +
            "**       /////%%%#(((((((((((((((((((((((((((((((((//////******,,/(((((((%%%%%%%%%#((((((((((((((((((((((((,.        **\n" +
            "**       //////%%%%%(((((((((((((((((((((((((((((((((((((/********,,(%%%%%%##((((((((((((((((((((((,,........        **\n" +
            "**       //////(#%%%%%%%((((((((((((((((((((((((((((((((((((/***,,,,,,/((((((((((((((*,,,....................        **\n" +
            "**       /////,%%###%%%%%%%%%%%%%#(((((((((((((((((((((((((((*******,,,,.((,,,,..............................        **\n" +
            "**       /////,*%%%%#####%%%%%%%%%%%%%%%%%%%%%((((((((((((((((***********,..................................,        **\n" +
            "**       /////.***%%%%%%%####%%%%%#######,#%%%%%%%%%%%%(((((((**************..................,,,,,,,,,,,,,,,        **\n" +
            "**       (////*******%%%%%%%%%%%%%%%######(##(*(###%%%%%%%%%#(/****************,,,,,,,,,,,,,,,,,,,,,,,,,,....        **\n" +
            "**       (/////./********(%%%%%%%%%%%%%%%#((((((((,(((((((((((((((((******************..,,,,,,...............        **\n" +
            "**       ((/////,**/******,,*****/#%%%%%%%#(//,,,,,,,*,(((((((((((((((((((********,...,,,,,,.................        **\n" +
            "**       ,(((((///,,****,*,,,,,,,,,,,,,,,,,,,,,,,,,,..........,(((((((((((((((......,,,,,,...................        **\n" +
            "**       #((((((((/(,*,,*,**,,,,,,,,,,,,,,..........,,,,,,,,,.******((((((.... ...,,,,,,.....................        **";

    public static final String PICTURE_3 = "***********************************************************************************************************************\n" +
            "**                                                                                                                   **\n" +
            "**       ((((((((((((((((*//*///////,/////////////*//////,//*///////%.//////////////////#%(##################        **\n" +
            "**       ((((((((((((((,////*//////.//////////////,/(%(((/*/,/(#%/%%%%///#/%/////%%/#%//#%%%#################        **\n" +
            "**       (((((#((#####.*////*/////*//////////////////////////,///////%*//////////*/////////#%(###########%#%%        **\n" +
            "**       ############.,/////**/////*/////////////*/////*/////////////%.///////////*/////////%%*%%%%%%%%%%%%%%        **\n" +
            "**       ###########./ *////,,*/*/*/////*////////,/////*/////////////#*////////////,*////////%%#%%%%%%%%%%%%%        **\n" +
            "**       ########## ,* ,/////,, /**////*//*//////,////***/////*//////(#*////////////**////////%%%%%%%%%%%%%%%        **\n" +
            "**       ####%%%%#*,,,.,,*////.//,*////,//*//////*////***//////*//////%***,*////////.**///////#%,%%%%%%%%%%%%        **\n" +
            "**       %%%%%%%%%,,,,,,,,*,////***//////***//////*///*.**////////.///(.,*///////////***///////#%#%%%%%%%%%%%        **\n" +
            "**       %%%%%%%%#,,,, *,,,*,,//*,,//////***//////.///*****/////////.//(***//////////,***///////%#&%&&&&&&&&&        **\n" +
            "**       %%%%%%%% ,,,,**, *,, //,***/////****//////,//.******///////////,****///*/////,***///,///%*&&&&&&&&&&        **\n" +
            "**       %%%%%%%%.,,,,,,,,,,, //*,**/////*****////////,*******///////,//*.****///.*////****///,///#&&&&&&&&&&        **\n" +
            "**       &&&&&&&&, ,,,,,,.,,*,///**,*//*//,****/////.*/,*.******/////////,&*,***//,*////******/////*&&&&&&&&&        **\n" +
            "**       &&&&&&&&,,,,,,,,,,,**,//.,****////*****///.*%./.**,*******///////*@#    .* ***//.******* .//&&&&&&&&        **\n" +
            "**       &&&&&&&& ,,,.,,,,,,,**///,,,**,////******///@&&/@***,********//%//       ...  . ,//,////&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&& ,,, ......,*,*//.,****,**// **,.*** &./%#@@@.*.********.@@@        &&&&@@%**///%&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&  ,,,,.,,,,*,,,,,/.***,***,.           (@@@@@@@@@@@@@@@@@@@@      .,&&&&@@.**///,&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&/' .,,,,,,,,,,,,,*/'//  ,,**(&         &@@@@@@@@@@@@@@@@@@@&,** ,***@&@@&@***/// &&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&....,,,,,,, ,,,,*///////&&&&&      &@@%@@@@@@@@@@@@@@@@@@@@&*/////#@@@@@,***/// &&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&.... .., .,.,,,,*///////&@@&&(,,******#@@@@@@@@@@@@@@@@@@@@@@////&@@@@@@.***/// &&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&& .....*%%%&,,,,*////// %%&@@@@,//////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,****// &&&&&&@&&&&        **\n" +
            "**       &&&&&&&&&&& ....%,&%% ,,,,//*///#&&%%%@@@@@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*****//#&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&& ...%%%*.%,,,,//,,//&%@%%%&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*****//@&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&(..(%%%#, ,,,//, //(%&@.%%&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%&@@@@#*,**//.&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&&&../%%%%%,,,**, /,%(%%&@*%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,****//@&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&&&&&. ..%%/,,,*, /.%%&%%%@@&%&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,*,**//(&&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&&&&&&& ...%.,,*, /.%%%%%%%@@@&/&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.#**,*//.&&&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&&&&&&&&&& .. ,,,,/.#%%%%%%%%&@@@@@*@@@@@@@@@@@@@@@@@@@@@@@@@@@(&&/*.*//.&&&&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&&&&&&&&&&&&&& ,,,/.%%%%%%%%%%%%%&@@@@@(@@@@@@@@@@@@@@@@@@@@,&&&&&,**//,&&&&&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&&&&&&&&&&&&&&&(,,*,%%%%%%%%%%%%%%%%%@@@@@,@@@@@@@@@@@@@@/&&&&&&&&,///&&&&&&&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&&&&&&&&&&&&&&&&&,,/%%%%%%%%%%%%%%%%%%%%@@@&%&@@@@@@@(. %&&&&&&&&&//&&&&&&&&&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&&&&&&&(  ... ....... . . #%%%%%%%%%%%%%%%%%%@@,&%,.,//....,,,, &&&#/%&&&&&&&&&&&&&&&&&&&&&        **\n" +
            "**       &&&&&&&&&&.......&@&............ ..... %%%%%%%%%%%%%%%&@(%%%%%.%,...,,,,,,, ,,.*&&&&&&&&&&&&&&&&&&&&        **\n" +
            "**       &&&&&&(%%%%%%%%....@@@..........,. . %%%%,%%%%%%%%%%%%%@&&@.%%%%%..,,,,,,,,,,,,,,,,,,..%&&&&&&&&&&&&        **\n" +
            "**       &&&&(%%%%%%%%/%%% ...@@@............%%%%%%%%*&%%%%%%%%%&*%%&&&@%%,.,,,,,,,,,,,,,,,,,,,,,&@.,,&%/*&&&        **\n" +
            "**       &%&#%%%%%%%%&&&&(%% .. @@#........./%%%%%%%%%%&&/#%%%%%&,%%%%&&&&,,,,,,,,,,,,,,,,,,,,,,%@ ,,.&@&@@&,        **\n" +
            "**       %%%%%%%%%%%&&&&&&&.%%...&@@......(%%%%%%%*%%%&&&&&&&&&/(&%%%%%&&&&.,,,,,,,,,,,,,,,,,,,#@ ,,.&&@&&&&&        **\n" +
            "**       %%,%%%%%%%&&&&&&&&&&(%... @@/' %%%.%%%%%%%%%%%&&&&&&&&&&&&&&&&&&&&.,,,,,,,,,,,,,,,,,,%@ ,,,&&&@&&&&&        **\n" +
            "**       %%#%%%%%%&&&%&&&&&&&&%%%...&@%%%%%%*%%%%%%%%%%&&&&%&&&&&&&&&&&&&&@,,,,,,,,,,,,,,,,,,@@ ,,#%&&&&&&&&&        **\n" +
            "**       %%#%%%%%%&%%&&&&&&&&&&&*% . %%%%%%%&%%#%%%%%%%&&#,#&(.*%&&&&&#&&& ,.,,,,,,,,,,,,,,.@@,,.%%&&&%&&&&&&        **\n" +
            "**       %%%#%%%%%%%%&&&&&&&&&&&&,%.%%%%%%%&&,%&&&,%%****..,/&@&&&@@@@@*#@@%,   .,,,,,,,,,.@(,,.%%&&&&*&&&&&&        **";

    /**
     * Print the logo and greeting message
     */
    public static void printLogo() {
        //print the logo when the program starts
        System.out.println("\t              ##*                                 ");
        System.out.println("\t   .      . .&####%                               ");
        System.out.println("\t    #%%%%((&%######%**.                           ");
        System.out.println("\t    %#############%#%&&%%%%%##(*.                 ");
        System.out.println("\t . .%%#######%%%%%##############%%%%#(.           ");
        System.out.println("\t/##&@%####&%####&#####%&&&&&&&&&%%%####&/  .      ");
        System.out.println("\t&######%%######%&%%%#################%%%%&#       ");
        System.out.println("\t(@####&#####%&%########################%###@(     ");
        System.out.println("\t,&%#%%%%##%%############%%%%%%%%%%%##########%.   ");
        System.out.println("\t*&#&%%&#%&########&%#(%#/((((((/****#&%&%#####%,  ");
        System.out.println("\t  ,&#%%%%#####%%#(%(((&#(&(((((((((/%#(((%##%%#%. ");
        System.out.println("\t  (%##%%####%%(((/%&((%&#%##((((((((&#(((%#(((&&, ");
        System.out.println("\t  (%#%&####%#((##(#(/###.%#.*(##(((%, /##((%#(#( .");
        System.out.println("\t  *&(&%###%#(((((#/@%%%&,. ...     &%%&%  (&###*  ");
        System.out.println("\t . (%%%###%(((((#/#####&, .. .... /&(#(&,.%(((#(  ");
        System.out.println("\t    *&&##%%(((((%*(%###%. .. .. . *%(##&, (%((#(  ");
        System.out.println("\t      (&#%%((((#%/ .**,. ..... . ...,,,. . (#(%(  ");
        System.out.println("\t        .(%(((((&/ .   .  ...... .. .. . .(%/#%,  ");
        System.out.println("\t          .(%(((#&*,,.           . .,*/*%#((#&*   ");
        System.out.println("\t              ,/(#(    .,,******,.     ####%*     ");
    }

    /**
     * Print the welcome message and the ASCII art when the program starts
     */
    public static void printWelcomeMessage() {
        System.out.println("   __          ________ _      _____ ____  __  __ ______    ");
        System.out.println("   \\ \\        / /  ____| |    / ____/ __ \\|  \\/  |  ____|  ");
        System.out.println("    \\ \\  /\\  / /| |__  | |   | |   | |  | | \\  / | |__     ");
        System.out.println("     \\ \\/  \\/ / |  __| | |   | |   | |  | | |\\/| |  __|    ");
        System.out.println("      \\  /\\  /  | |____| |___| |___| |__| | |  | | |____   ");
        System.out.println("     __\\/_ \\/   |______|______\\_____\\____/|_|  |_|______|_ ");
        System.out.println("    / ____| |  | |_   _|  \\/  |   /\\   |  __ \\|_   _| \\ | |");
        System.out.println("   | (___ | |__| | | | | \\  / |  /  \\  | |__) | | | |  \\| |");
        System.out.println("    \\___ \\|  __  | | | | |\\/| | / /\\ \\ |  _  /  | | | . ` |");
        System.out.println("    ____) | |  | |_| |_| |  | |/ ____ \\| | \\ \\ _| |_| |\\  |");
        System.out.println("   |_____/|_|  |_|_____|_|  |_/_/    \\_\\_|  \\_\\_____|_| \\_|");
        //greeting
        System.out.println("\nHi there! My name is Shima Rin and I am a chat robot that can help you do some wonderful jobs!");
    }

    /**
     * Show the current version's functionality of the bot
     */
    public static void printVersionDescription() {
        //Uses list to store all the version information
        ArrayList<String> versionDescriptions = new ArrayList<>();
        int maxDescriptionsLength = 0;
        versionDescriptions.add("* " + CURR_VERSION);
        versionDescriptions.add("* To know more about me, you can view my profile by typing the command \"view -p\"");
        versionDescriptions.add("* I can help you create a to-do list, you can perform tasks addition, deletion, and mark tasks as done");
        versionDescriptions.add("* In addition, I will memorise all the tasks that you entered, so that you can refer back when you revisit me :)");
        versionDescriptions.add("* You can type \"todo\" or \"deadline\" or \"event\" to create a task and I will help you save it automatically!");
        versionDescriptions.add("* You can type \"list\" or \"ls\" to list all the tasks that are waiting to do");
        versionDescriptions.add("* You can type \"done i\" where i is the index of the task to mark the specific task as done");
        versionDescriptions.add("* You can type \"delete i\" where i is the index of the task to delete the task whenever necessary");
        versionDescriptions.add("* You can type \"exit\" or \"bye\" to stop me and exit the program");
        versionDescriptions.add("* You can type \"help\" or \"view -h\" for more information about how to use me (YES the bot), ");
        //Finds the length of the longest description to align all '*' displayed
        for (String str : versionDescriptions) {
            if (str.length() > maxDescriptionsLength) {
                maxDescriptionsLength = str.length();
            }
        }
        //Draws the frame for the version description
        for (int i = 0; i < maxDescriptionsLength + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (String str : versionDescriptions) {
            //Uses format string to print the '*' at the correct position after each sentence is completed
            System.out.printf("%1$-" + (maxDescriptionsLength + 1) + "s", str);
            System.out.println("*");
        }
        for (int i = 0; i < maxDescriptionsLength + 2; i++) {
            System.out.print("-");
        }
    }

    /**
     * Prints the ASCII art image of the robot and the description of the personality of the robot
     */
    public static void printPersonality() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
        case 0:
            System.out.println(Default.PICTURE_1);
            break;
        case 1:
            System.out.println(Default.PICTURE_2);
            break;
        case 2:
            System.out.println(Default.PICTURE_3);
            break;
        }
        System.out.println("**                                                                                                                   **");
        System.out.println("**       Greeting!                                                                                                   **");
        System.out.println("**      -My name is Shima Rin, I am a Japanese anime character that comes from the anime \"Yuru Camp\" :P              **");
        System.out.println("**      -I love physical camping, especially during the winter season!                                               **");
        System.out.println("**      -I also love food (especially hot pot!) and hot spring! :P                                                   **");
        System.out.println("**      -By the way, I am a dog lover!                                                                               **");
        System.out.println("**                                                                                                                   **");
        System.out.println("***********************************************************************************************************************");
    }

    /**
     * Prints the help menu to the console
     */
    public static void printHelpMenu() {
        ArrayList<String> helpLists = createHelpMenu();
        System.out.println("************************************************************************************************************************************************************************");
        System.out.println("Help Menu Version " + CURR_VERSION + "\n");
        for (int i = 0; i < helpLists.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + helpLists.get(i));
        }
        System.out.println("************************************************************************************************************************************************************************");
    }

    /**
     * Creates the help menu descriptions by adding the strings into the array list
     *
     * @return Returns an array list of string with all the help menu descriptions
     */
    private static ArrayList<String> createHelpMenu() {
        ArrayList<String> helpLists = new ArrayList<>();
        //To-do command
        helpLists.add("To add a new to-do task, use the command \"todo\" with syntax:\n\t\ttodo [YOUR_TASK_DESCRIPTION]\n\t\t" +
                "eg. todo read book\t<-- will add the task \"read book\" to your to-do list\n");
        //Deadline command
        helpLists.add("To add a new task specified with deadline, use the command \"deadline\" with syntax:\n\t\tdeadline [YOUR_TASK_DESCRIPTION] /[DEADLINE]\n\t\t" +
                "eg. deadline submit assignment /by Friday 6pm\t<-- will add the task \"submit assignment\" with deadline \"Friday 6pm\n");
        //Event command
        helpLists.add("To add an event specified with start time and end time, use the command \"event\" with syntax:\n\t\tevent [YOUR_TASK_DESCRIPTION] /[START_TIME]-[END_TIME]" +
                "\n\t\teg. event attend tutorial /at Friday 2 - 4pm\t<-- will add the event \"attend tutorial\" with the event day Friday and start time 2pm and end time 4pm\n");
        //List command
        helpLists.add("To print the to-do list, use the command \"list\" or \"ls\", it will then show you the to-do list\n");
        //Done command
        helpLists.add("To mark the task as done, use the command \"done\" with syntax:\n\t\tdone [TASK_INDEX]\n\t\teg. done 1\t<-- will mark the 1st task as completed\n\t" +
                "\t\tdone 1 2 3\t<-- will mark the 1st, 2nd and 3rd tasks as completed\n");
        //Delete command
        helpLists.add("To delete task, use the command \"delete\" with syntax:\n\t\tdelete [TASK_INDEX]\n\t\teg. delete 1\t<--will delete the 1st task\n\t" +
                "\t\tdelete 1 2 3\t<-- will delete the tasks with index 1, 2 and 3\n\t\t\tdelete all\t<-- will delete all the tasks in the list\n");
        //Exit command
        helpLists.add("To exit the program, use the command \"exit\" or \"bye\"\n");
        return helpLists;
    }

    /**
     * Prints the to-do list with frames
     *
     * @param tasks the array of class Task instance which stores all the tasks added by the user
     */
    public static void printToDoList(ArrayList<Task> tasks, int longestTaskDescription) {
        final int MIN_LENGTH = " My to-do list: ".length();
        //if longestTaskDescription is shorter than the length of the string "My to-do list: ", sets it to the length of the string
        if (longestTaskDescription < MIN_LENGTH) {
            longestTaskDescription = MIN_LENGTH;
        }
        //Prints the to-do list
        drawUpperFrame(longestTaskDescription);
        printTasks(tasks, longestTaskDescription);
        drawLowerFrame(longestTaskDescription);
    }

    /**
     * Prints the bottom frame of the to-do list and the guide for reading the to-do list
     *
     * @param longestTaskDescription the length of the longest task description string stored in the tasks array
     */
    private static void drawLowerFrame(int longestTaskDescription) {
        System.out.print("\t" + BOTTOM_LEFT_CORNER);
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println(BOTTOM_RIGHT_CORNER);
        //Shows the guide for understanding the to-do list
        System.out.println("\tFor your knowledge, ");
        System.out.println("\tthe first [ ] indicates the type of the task ('T' for to-do, 'D' for deadline, 'E' for event)");
        System.out.println("\tthe second [ ] indicates whether the task is completed:\n" +
                "\t[X] when the task is marked completed\t[ ] when the task is not done.");
    }

    /**
     * Prints the tasks stored in the array, the frame starts with '|' and ends with '|', the ending frame is always located at the position of the longest task description
     *
     * @param tasks                  the array that stores all the tasks
     * @param longestTaskDescription the length of the longest task description stored in the tasks array
     */
    private static void printTasks(ArrayList<Task> tasks, int longestTaskDescription) {
        for (int i = 0; i < tasks.size(); i++) {
            //Fill the first [] with class type, and the second [] with a 'X' if the task is completed
            if (tasks.get(i).getDone()) {
                System.out.print("\t| [" + tasks.get(i).getClassType() + "][X] " + (i + 1) + ". ");
            } else {
                System.out.print("\t| [" + tasks.get(i).getClassType() + "][ ] " + (i + 1) + ". ");
            }
            //Calculates the required spacing for the current task as compared to the longest task description to print '|'
            int distanceToClosingFrame = longestTaskDescription + "| [ ][ ] 100. ".length() - ("| [ ][ ] " + (i + 1) + ". ").length() + 1;
            System.out.printf("%1$-" + distanceToClosingFrame + "s", tasks.get(i));
            System.out.println("|");
        }
    }

    /**
     * Prints the upper frame of the to-do list and its default display string
     *
     * @param longestTaskDescription the length of the longest task description string stored in the tasks array
     */
    private static void drawUpperFrame(int longestTaskDescription) {
        System.out.print("\t" + TOP_LEFT_CORNER); //the top left corner
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length(); i++) {
            System.out.print("-");
        }
        System.out.println(TOP_RIGHT_CORNER);
        //Print default string " My to-do list: "
        System.out.print("\t| My to-do list: ");
        for (int i = 0; i < longestTaskDescription + "| [ ][ ] 100. ".length() - "| My to-do list: ".length() + 1; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    /**
     * Shows the formatted message string
     *
     * @param message The message to print
     */
    public static void showMessage(String message) {
        System.out.print("\t@");
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print("-");
        }
        System.out.println("@");
        System.out.println("\t   " + message);
        System.out.print("\t@");
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print("-");
        }
        System.out.println("@");
    }
}