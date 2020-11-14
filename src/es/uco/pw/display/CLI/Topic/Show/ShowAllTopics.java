package es.uco.pw.display.CLI.Topic.Show;

import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.data.dto.topic.DTOTopic;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Show all topics.
 */
public class ShowAllTopics {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        DAOTopic topicController = new DAOTopic();
        LinkedList<DTOTopic> topics = topicController.get();
        ShowTopics.init(topics);
    }
}
