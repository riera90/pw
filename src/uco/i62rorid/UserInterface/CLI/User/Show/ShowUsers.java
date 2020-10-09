package uco.i62rorid.UserInterface.CLI.User.Show;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.User;
import uco.i62rorid.UserInterface.CLI.User.Modify.ModifyUserMainMenu;
import uco.i62rorid.Utils.UserInput;

import java.util.LinkedList;


public class ShowUsers {
    public static void init(LinkedList<User> users){
        UserController userController = new UserController();
        for (User user:users) {
            System.out.println(user);
        }
        System.out.print("\tDo you want to modify anything?\n\nselect the id or press enter to skip: ");
        Integer id = UserInput.getIdFromUser();
        if (id < 0) return;
        User user = userController.get(id);
        if (user.getId() != null)
            ModifyUserMainMenu.init(user);
        else {
            System.out.print("Id not valid\n");
            UserInput.pause();
        }

    }
}
