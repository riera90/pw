package es.uco.pw.display.CLI.Topic.Show;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.dao.topic.DAOTopic;

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
        TopicController topicController = new TopicController();
        LinkedList<DAOTopic> topics = topicController.get();
        ShowTopics.init(topics);
    }
}
