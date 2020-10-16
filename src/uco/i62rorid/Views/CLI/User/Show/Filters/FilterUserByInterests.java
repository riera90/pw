package uco.i62rorid.Views.CLI.User.Show.Filters;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Utils.UserInput;

import java.util.LinkedList;

/**
 * The type Filter user by interests.
 */
public class FilterUserByInterests {
    /**
     * Init.
     */
    public static void init() {
        System.out.print("now enter the new interests indexes, input -1 to stop adding more\n");
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
    }
}
