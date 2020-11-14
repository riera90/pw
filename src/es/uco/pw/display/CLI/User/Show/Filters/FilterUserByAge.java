package es.uco.pw.display.CLI.User.Show.Filters;

import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.data.dto.user.DTOUser;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.User.Show.ShowUsers;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;

/**
 * The type Filter user by age.
 */
public class FilterUserByAge {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        Integer age;
        DAOUser userController = new DAOUser();
        UserInput.clear();
        System.out.print("enter the user's age: ");
        age = UserInput.getIdFromUser();
        LinkedList<DTOUser> allUsers = userController.get();
        LinkedList<DTOUser> filteredUsers = new LinkedList<>();
        for (DTOUser user:allUsers){
            if (user.getBornAt() == null) continue;
            long ageInMillis = new Date().getTime() - user.getBornAt().getTime();
            if (((ageInMillis/1000)/31556952) == age){
                filteredUsers.add(user);
            }
        }
        ShowUsers.init(filteredUsers);
        UserInput.pause();
    }
}
