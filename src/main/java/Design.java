import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Credit: The pictures are generated with the help of an online picture to ASCII symbols converter
 * The website available: "https://manytools.org/hacker-tools/convert-images-to-ascii-art/"
 */
public abstract class Design {
    public static final String CURR_VERSION = "Version 5.0";

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
        List<String> versionDescriptions = new ArrayList<>();
        int maxDescriptionsLength = 0;
        versionDescriptions.add("* " + CURR_VERSION);
        versionDescriptions.add("* To know more about me, you can view my profile by typing the command \"view -p\"");
        versionDescriptions.add("* For now I am a note bot that can help you note down any tasks and create a to-do list for you :)");
        versionDescriptions.add("* In addition, you can mark any task in the to-do list as done!");
        versionDescriptions.add("* You can type \"todo\" or \"deadline\" or \"event\" to create a task and I will help you save it automatically!");
        versionDescriptions.add("* You can type \"list\" or \"ls\" to list all the tasks that are waiting to do");
        versionDescriptions.add("* You can type \"done i\" where i is the index of the task to mark the specific task as done");
        versionDescriptions.add("* You can type \"exit\" or \"bye\" to stop me and exit the program");
        versionDescriptions.add("* For more information about how to use me (YES the bot), you can type \"help\" or \"view-h\"");
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
     * Prints the ASCII art image of the robot and the description of the personality of the robot
     */
    public static void printPersonality() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
        case 0:
            System.out.println(Design.PICTURE_1);
            break;
        case 1:
            System.out.println(Design.PICTURE_2);
            break;
        case 2:
            System.out.println(Design.PICTURE_3);
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

    public static void printHelpMenu() {
        System.out.println("************************************************************************************************************************************************************************");
        System.out.println("Help Menu Version " + CURR_VERSION + "\n");

        System.out.println("\tTo add a new to-do task, use the command \"todo\" with syntax:\n\t\ttodo [YOUR_TASK_DESCRIPTION]");
        System.out.println("\t\teg. todo read book\t<-- will add the task \"read book\" to your to-do list");
        System.out.println();
        System.out.println("\tTo add a new task specified with deadline, use the command \"deadline\" with syntax:\n\t\tdeadline [YOUR_TASK_DESCRIPTION] /[DEADLINE]");
        System.out.println("\t\teg. deadline submit assignment /by Friday 6pm\t<-- will add the task \"submit assignment\" with deadline \"Friday 6pm");
        System.out.println();
        System.out.println("\tTo add an event specified with start time and end time, use the command \"event\" with syntax:\n\t\t" +
                "event [YOUR_TASK_DESCRIPTION] /[START_TIME]-[END_TIME]");
        System.out.println("\t\teg. event attend tutorial /at Friday 2 - 4pm\t<-- will add the event \"attend tutorial\" with the event day Friday " +
                "and start time 2pm and end time 4pm");
        System.out.println();
        System.out.println("\tTo print the to-do list, use the command \"list\" or \"ls\", it will then show you the to-do list");
        System.out.println();
        System.out.println("\tTo mark the task as done, use the command \"done\" with syntax:\n\t\tdone [TASK_INDEX]");
        System.out.println("\t\teg. done 1\t<-- will mark the 1st task as completed");
        System.out.println("\t\t    done 1 2 3\t<-- will mark the 1st, 2nd and 3rd tasks as completed");
        System.out.println();
        System.out.println("\tTo exit the program, use the command \"exit\" or \"bye\"");
        System.out.println();
        System.out.println("************************************************************************************************************************************************************************");
    }
}