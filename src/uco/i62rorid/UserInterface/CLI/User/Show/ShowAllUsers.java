package uco.i62rorid.UserInterface.CLI.User.Show;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Utils.UserInput;

public class ShowAllUsers {
    public static void init(){
        UserInput.clear();
        UserController userController = new UserController();
        ShowUsers.init(userController.get());
    }
}
