package uco.i62rorid.UserInterface.CLI.User.Show.Filters;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.UserInterface.CLI.User.Show.ShowUsers;
import uco.i62rorid.Utils.UserInput;

public class FilterUserByFirstName {
    public static void init() {
        String firstName;
        UserController userController = new UserController();
        UserInput.clear();
        System.out.print("enter the first name: ");
        firstName = UserInput.getStringFromUser();
        ShowUsers.init(userController.getByFieldLike("firstName", '"' + firstName + '"'));
        UserInput.pause();
    }
}
