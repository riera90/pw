package es.uco.pw.display.CLI.Topic.Show;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.display.CLI.Topic.Modify.ModifyTopicMainMenu;
import es.uco.pw.business.Utils.UserInput;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Show topics.
 */
public class ShowTopics {
    /**
     * Init.
     *
     * @param topics the topics
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(LinkedList<DAOTopic> topics) throws NoSuchAlgorithmException {
        TopicController topicController = new TopicController();
        for (DAOTopic topic:topics) {
            System.out.println(topic);
        }
        System.out.print("\tDo you want to modify anything?\n\nselect the id or press enter to skip: ");
        Integer id = UserInput.getIdFromUser();
        if (id < 0) return;
        DAOTopic topic = topicController.get(id);
        if (topic.getId() != null)
            ModifyTopicMainMenu.init(topic);
        else {
            System.out.print("Id not valid\n");
            UserInput.pause();
        }
    }
}
