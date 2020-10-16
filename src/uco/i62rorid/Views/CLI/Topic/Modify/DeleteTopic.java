package uco.i62rorid.Views.CLI.Topic.Modify;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Entities.Topic;
import uco.i62rorid.Utils.UserInput;

/**
 * The type Delete topic.
 */
public class DeleteTopic {
    /**
     * Init.
     *
     * @param topic the topic
     */
    public static void init(Topic topic){
        TopicController topicController = new TopicController();
        if (topicController.delete(topic))
            System.out.print("Topic deleted successfully");
        else
            System.out.print("Topic not found");
        UserInput.pause();
    }
}
