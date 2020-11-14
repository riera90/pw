package es.uco.pw.display.CLI.Topic.Create;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.business.Utils.UserInput;

/**
 * The type Create topic.
 */
public class CreateTopic {
    /**
     * Init.
     */
    public static void init(){
        DAOTopic topic = new DAOTopic();

        UserInput.clear();

        System.out.print("Topic Name: ");
        topic.setName(UserInput.getStringFromUser());

        TopicController topicController = new TopicController();
        topicController.post(topic);
        System.out.print("\nTopic successfully Created\n");
        UserInput.pause();
    }
}
