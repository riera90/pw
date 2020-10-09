package uco.i62rorid.UserInterface.CLI.User.Show.Filters;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.UserInterface.CLI.User.Show.ShowUsers;
import uco.i62rorid.Utils.UserInput;

public class FilterUserByLastName {
    public static void init() {
        String lastName;
        UserController userController = new UserController();
        UserInput.clear();
        System.out.print("enter the last name: ");
        lastName = UserInput.getStringFromUser();
        ShowUsers.init(userController.getByFieldLike("lastName", '"' + lastName + '"'));
        UserInput.pause();
    }
}
