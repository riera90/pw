package uco.i62rorid.UserInterface.CLI.Topic;

import uco.i62rorid.UserInterface.CLI.Topic.Create.CreateTopic;
import uco.i62rorid.UserInterface.CLI.Topic.Show.Filters.FilterTopicMainMenu;
import uco.i62rorid.UserInterface.CLI.Topic.Show.ShowAllTopics;
import uco.i62rorid.Utils.UserInput;

public class TopicMainMenu {
    public static void init(){
        String menu = "\t1) Show all topics\n"+
                "\t2) Search topic\n"+
                "\t3) Create new topic\n" +
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            UserInput.clear();
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
