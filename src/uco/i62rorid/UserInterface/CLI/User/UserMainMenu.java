package uco.i62rorid.UserInterface.CLI.User;

import uco.i62rorid.UserInterface.CLI.User.Create.CreateUser;
import uco.i62rorid.UserInterface.CLI.User.Show.Filters.FilterUserMainMenuUI;
import uco.i62rorid.UserInterface.CLI.User.Show.ShowAllUsers;
import uco.i62rorid.Utils.UserInput;

public class UserMainMenu {

    public static void init(){
        String menu = "\t1) Show all users\n"+
                "\t2) Search user\n"+
                "\t3) Create new user\n" +
                "\t0) Go back\n" +
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
                    ShowAllUsers.init();
                    break;
                case 2:
                    FilterUserMainMenuUI.init();
                    break;
                case 3:
                    CreateUser.init();
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
