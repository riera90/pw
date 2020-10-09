package uco.i62rorid.UserInterface.CLI.Topic.Create;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Entities.Topic;
import uco.i62rorid.Utils.UserInput;

public class CreateTopic {
    public static void init(){
        Topic topic = new Topic();

        UserInput.clear();

        System.out.print("Topic Name: ");
        topic.setName(UserInput.getStringFromUser());

        TopicController topicController = new TopicController();
        topicController.post(topic);
        System.out.print("\nTopic successfully Created\n");
        UserInput.pause();
    }
}
