package uco.i62rorid.pw.Views.CLI.Topic.Modify;

import uco.i62rorid.pw.Controlers.TopicController;
import uco.i62rorid.pw.Entities.Topic;
import uco.i62rorid.pw.Utils.UserInput;

/**
 * The type Modify topic name.
 */
public class ModifyTopicName {
    /**
     * Init.
     *
     * @param topic the topic
     */
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
