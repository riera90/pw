package uco.i62rorid.UserInterface.CLI.Topic.Modify;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Entities.Topic;
import uco.i62rorid.Utils.UserInput;

public class ModifyTopicMainMenu {
    public static void init(Topic topic){
        String menu = "\t1) Modify name of the topic\n"+
                "\t2) DELETE\n"+
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            TopicController topicController = new TopicController();
            topic = topicController.get(topic.getId());
            UserInput.clear();
            System.out.print(topic+"\n\n");
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    ModifyTopicName.init(topic);
                    break;
                case 2:
                    DeleteTopic.init(topic);
                    break;
                default:
                    System.out.print("Not a valid option");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
