package uco.i62rorid.pw.Views.CLI.Topic.Show;

import uco.i62rorid.pw.Controlers.TopicController;
import uco.i62rorid.pw.Entities.Topic;

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
        LinkedList<Topic> topics = topicController.get();
        ShowTopics.init(topics);
    }
}
