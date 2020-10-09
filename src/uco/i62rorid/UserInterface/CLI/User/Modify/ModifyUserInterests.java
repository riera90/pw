package uco.i62rorid.UserInterface.CLI.User.Modify;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.User;
import uco.i62rorid.Utils.UserInput;

import java.util.LinkedList;

public class ModifyUserInterests {
    public static void init(User user){
        System.out.print("now enter the interests indexes, input -1 to stop adding more\n");
        UserInput.pause();
        TopicController topicController = new TopicController();
        System.out.println(topicController.get());
        int index;
        LinkedList<Integer> interests = new LinkedList<>();
        do {
            System.out.print("Interest id: ");
            index = UserInput.getIdFromUser();
            if (index < 0) break;
            UserController userController = new UserController();
            if (userController.get(index).getId() != null)
                interests.add(index);
            else
                System.out.print("not a valid id\n");


        }while(true);
        user.setInterests(interests);

        UserController userController = new UserController();
        userController.patch(user);
        System.out.print("\nUser successfully Created\n");
        UserInput.pause();
    }
}
