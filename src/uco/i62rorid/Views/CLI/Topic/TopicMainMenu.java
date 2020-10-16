package uco.i62rorid.Views.CLI.Topic;

import uco.i62rorid.Views.CLI.Auth.AuthMainMenu;
import uco.i62rorid.Views.CLI.Topic.Create.CreateTopic;
import uco.i62rorid.Views.CLI.Topic.Show.Filters.FilterTopicMainMenu;
import uco.i62rorid.Views.CLI.Topic.Show.ShowAllTopics;
import uco.i62rorid.Utils.UserInput;
import uco.i62rorid.Views.CLI.UserSessionStateSingleton;

import java.security.NoSuchAlgorithmException;

/**
 * The type Topic main menu.
 */
public class TopicMainMenu {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String menu = "\t1) Show all topics\n"+
                "\t2) Search topic\n"+
                "\t3) Create new topic\n" +
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
                case 1:
                    ShowAllTopics.init();
                    break;
                case 2:
                    FilterTopicMainMenu.init();
                    break;
                case 3:
                    CreateTopic.init();
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
