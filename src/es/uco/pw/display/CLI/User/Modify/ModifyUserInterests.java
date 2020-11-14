package es.uco.pw.display.CLI.User.Modify;

import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.data.dto.user.DTOUser;
import es.uco.pw.business.Utils.UserInput;

import java.util.LinkedList;

/**
 * The type Modify user interests.
 */
public class ModifyUserInterests {
    /**
     * Init.
     *
     * @param user the user
     */
    public static void init(DTOUser user){
        System.out.print("now enter the interests indexes, input -1 to stop adding more\n");
        UserInput.pause();
        DAOTopic topicController = new DAOTopic();
        System.out.println(topicController.get());
        int index;
        LinkedList<Integer> interests = new LinkedList<>();
        do {
            System.out.print("Interest id: ");
            index = UserInput.getIdFromUser();
            if (index < 0) break;
            DAOUser userController = new DAOUser();
            if (userController.get(index).getId() != null)
                interests.add(index);
            else
                System.out.print("not a valid id\n");


        }while(true);
        user.setInterests(interests);

        DAOUser userController = new DAOUser();
        userController.patch(user);
        System.out.print("\nUser successfully Created\n");
        UserInput.pause();
    }
}
