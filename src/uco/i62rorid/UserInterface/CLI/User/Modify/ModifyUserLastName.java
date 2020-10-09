package uco.i62rorid.UserInterface.CLI.User.Modify;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.User;
import uco.i62rorid.Utils.UserInput;

public class ModifyUserLastName {
    public static void init(User user){
        UserController userController = new UserController();
        System.out.print("new Last Name: ");
        String input = UserInput.getStringFromUser();
        if (input.equals("")) {
            System.out.print("Input is not valid\n");
            UserInput.pause();
            return;
        }
        user.setLastName(input);
        userController.patch(user);
    }
}
