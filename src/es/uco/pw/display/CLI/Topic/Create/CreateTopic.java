package es.uco.pw.display.CLI.Topic.Create;

import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.data.dto.topic.DTOTopic;
import es.uco.pw.business.Utils.UserInput;

/**
 * The type Create topic.
 */
public class CreateTopic {
    /**
     * Init.
     */
    public static void init(){
        DTOTopic topic = new DTOTopic();

        UserInput.clear();

        System.out.print("Topic Name: ");
        topic.setName(UserInput.getStringFromUser());

        DAOTopic topicController = new DAOTopic();
        topicController.post(topic);
        System.out.print("\nTopic successfully Created\n");
        UserInput.pause();
    }
}
