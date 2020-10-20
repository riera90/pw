package uco.i62rorid.pw.Views.CLI.User.Show.Filters;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Entities.User;
import uco.i62rorid.pw.Utils.UserInput;
import uco.i62rorid.pw.Views.CLI.User.Show.ShowUsers;

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
        UserController userController = new UserController();
        UserInput.clear();
        System.out.print("enter the user's age: ");
        age = UserInput.getIdFromUser();
        LinkedList<User> allUsers = userController.get();
        LinkedList<User> filteredUsers = new LinkedList<>();
        for (User user:allUsers){
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
