package uco.i62rorid.Views.CLI.Post;

import uco.i62rorid.Utils.UserInput;
import uco.i62rorid.Views.CLI.Auth.AuthMainMenu;
import uco.i62rorid.Views.CLI.Post.Create.CreatePost;
import uco.i62rorid.Views.CLI.Post.Show.Filters.FilterPostMainMenu;
import uco.i62rorid.Views.CLI.Post.Show.ShowPostsOfUserMenu;
import uco.i62rorid.Views.CLI.Post.Show.ShowPostsOfUserTimeline;
import uco.i62rorid.Views.CLI.UserSessionStateSingleton;

import java.security.NoSuchAlgorithmException;

/**
 * The type Post main menu.
 */
public class PostMainMenu {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String menu = "\t1) Show my timeline (interest based)\n"+
                "\t2) Search posts\n"+
                "\t3) Create new post\n" +
                "\t4) My posts\n" +
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            UserInput.clear();
            if (UserSessionStateSingleton.getInstance().LoggedUser==null){
                AuthMainMenu.init();
            }else{
                System.out.println("logged as "+ UserSessionStateSingleton.getInstance().LoggedUser.getEmail());
            }
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1: // timeline
                    ShowPostsOfUserTimeline.init(UserSessionStateSingleton.getInstance().LoggedUser);
                    break;
                case 2: // search
                    FilterPostMainMenu.init();
                    break;
                case 3: // create
                    CreatePost.init();
                    break;
                case 4: // my posts
                    ShowPostsOfUserMenu.init(UserSessionStateSingleton.getInstance().LoggedUser.getId());
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
