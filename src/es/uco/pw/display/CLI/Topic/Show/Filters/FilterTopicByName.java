package es.uco.pw.display.CLI.Topic.Show.Filters;

import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.data.dto.topic.DTOTopic;
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
        DAOTopic topicController = new DAOTopic();
        UserInput.clear();
        System.out.print("enter name of the topic: ");
        String value = UserInput.getStringFromUser();
        LinkedList<DTOTopic> topics = topicController.getByField("topic", '"'+value+'"');
        ShowTopics.init(topics);
    }
}
