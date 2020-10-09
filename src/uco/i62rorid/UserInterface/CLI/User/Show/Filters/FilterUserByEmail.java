package uco.i62rorid.UserInterface.CLI.User.Show.Filters;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.UserInterface.CLI.User.Show.ShowUsers;
import uco.i62rorid.Utils.UserInput;

public class FilterUserByEmail {
    public static void init() {
        String email;
        UserController userController = new UserController();
        UserInput.clear();
        System.out.print("enter the email: ");
        email = UserInput.getStringFromUser();
        ShowUsers.init(userController.getByFieldLike("email", '"' + email + '"'));
        UserInput.pause();
    }
}
