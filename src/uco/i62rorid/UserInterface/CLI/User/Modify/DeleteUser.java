package uco.i62rorid.UserInterface.CLI.User.Modify;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.User;
import uco.i62rorid.Utils.UserInput;

public class DeleteUser {
    public static void init(User user){
        UserController userController = new UserController();
        if (userController.delete(user))
            System.out.print("User deleted successfully");
        else
            System.out.print("User not found");
        UserInput.pause();
    }
}
