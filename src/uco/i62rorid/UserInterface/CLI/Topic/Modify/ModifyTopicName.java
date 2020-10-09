package uco.i62rorid.UserInterface.CLI.Topic.Modify;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Entities.Topic;
import uco.i62rorid.Utils.UserInput;

public class ModifyTopicName {
    public static void init(Topic topic){
        TopicController topicController = new TopicController();
        System.out.print("new Topic name: ");
        String input = UserInput.getStringFromUser();
        if (input.equals("")) {
            System.out.print("Input is not valid\n");
            UserInput.pause();
            return;
        }
        topic.setName(input);
        topicController.patch(topic);
    }
}
