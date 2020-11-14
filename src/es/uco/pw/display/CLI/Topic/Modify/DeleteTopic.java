package es.uco.pw.display.CLI.Topic.Modify;

import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.data.dto.topic.DTOTopic;
import es.uco.pw.business.Utils.UserInput;

/**
 * The type Delete topic.
 */
public class DeleteTopic {
    /**
     * Init.
     *
     * @param topic the topic
     */
    public static void init(DTOTopic topic){
        DAOTopic topicController = new DAOTopic();
        if (topicController.delete(topic))
            System.out.print("Topic deleted successfully");
        else
            System.out.print("Topic not found");
        UserInput.pause();
    }
}
