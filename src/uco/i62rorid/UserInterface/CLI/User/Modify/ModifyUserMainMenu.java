package uco.i62rorid.UserInterface.CLI.User.Modify;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.User;
import uco.i62rorid.Utils.UserInput;

public class ModifyUserMainMenu {
    public static void init(User user){
        String menu = "\t1) Modify First Name\n"+
                "\t2) Modify Last Name\n"+
                "\t3) Modify email\n" +
                "\t4) Modify role [TODO]\n" +
                "\t5) Modify interests\n" +
                "\t6) DELETE\n" +
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            UserController userController = new UserController();
            user = userController.get(user.getId());
            UserInput.clear();
            System.out.print(user+"\n\n");
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    ModifyUserFirstName.init(user);
                    break;
                case 2:
                    ModifyUserLastName.init(user);
                    break;
                case 3:
                    ModifyUserEmail.init(user);
                    break;
                case 4:
                    break;
                case 5:
                    ModifyUserInterests.init(user);
                    break;
                case 6:
                    DeleteUser.init(user);
                    return;
                default:
                    System.out.print("Not a valid option");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
