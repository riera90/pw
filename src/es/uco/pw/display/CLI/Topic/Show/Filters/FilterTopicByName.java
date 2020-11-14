package es.uco.pw.display.CLI.Topic.Show.Filters;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.display.CLI.Topic.Show.ShowTopics;
import es.uco.pw.business.Utils.UserInput;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Filter topic by name.
 */
public class FilterTopicByName {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        TopicController topicController = new TopicController();
        UserInput.clear();
        System.out.print("enter name of the topic: ");
        String value = UserInput.getStringFromUser();
        LinkedList<DAOTopic> topics = topicController.getByField("topic", '"'+value+'"');
        ShowTopics.init(topics);
    }
}
