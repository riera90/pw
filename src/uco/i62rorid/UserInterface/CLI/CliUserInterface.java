package uco.i62rorid.UserInterface.CLI;

import uco.i62rorid.UserInterface.CLI.Topic.TopicMainMenu;
import uco.i62rorid.UserInterface.CLI.User.UserMainMenu;
import uco.i62rorid.Utils.UserInput;

public class CliUserInterface {

    public static void init(){
        String menu = "\t1) Users\n" +
                "\t2) Posts [TODO]\n"+
                "\t3) Topics\n" +
                "\t0) Exit" +
                "\n" +
                "Select an option: ";

        do {
            UserInput.clear();
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    UserMainMenu.init();
                    break;
                case 2:
                    break;
                case 3:
                    TopicMainMenu.init();
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
