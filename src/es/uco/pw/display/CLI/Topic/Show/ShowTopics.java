package es.uco.pw.display.CLI.Topic.Show;

import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.data.dto.topic.DTOTopic;
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
    public static void init(LinkedList<DTOTopic> topics) throws NoSuchAlgorithmException {
        DAOTopic topicController = new DAOTopic();
        for (DTOTopic topic:topics) {
            System.out.println(topic);
        }
        System.out.print("\tDo you want to modify anything?\n\nselect the id or press enter to skip: ");
        Integer id = UserInput.getIdFromUser();
        if (id < 0) return;
        DTOTopic topic = topicController.get(id);
        if (topic.getId() != null)
            ModifyTopicMainMenu.init(topic);
        else {
            System.out.print("Id not valid\n");
            UserInput.pause();
        }
    }
}
