package es.uco.pw.display.CLI.Topic.Modify;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.business.Utils.UserInput;

/**
 * The type Modify topic name.
 */
public class ModifyTopicName {
    /**
     * Init.
     *
     * @param topic the topic
     */
    public static void init(DAOTopic topic){
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
