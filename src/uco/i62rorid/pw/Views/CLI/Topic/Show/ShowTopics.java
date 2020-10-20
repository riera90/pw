package uco.i62rorid.pw.Views.CLI.Topic.Show;

import uco.i62rorid.pw.Controlers.TopicController;
import uco.i62rorid.pw.Entities.Topic;
import uco.i62rorid.pw.Views.CLI.Topic.Modify.ModifyTopicMainMenu;
import uco.i62rorid.pw.Utils.UserInput;

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
    public static void init(LinkedList<Topic> topics) throws NoSuchAlgorithmException {
        TopicController topicController = new TopicController();
        for (Topic topic:topics) {
            System.out.println(topic);
        }
        System.out.print("\tDo you want to modify anything?\n\nselect the id or press enter to skip: ");
        Integer id = UserInput.getIdFromUser();
        if (id < 0) return;
        Topic topic = topicController.get(id);
        if (topic.getId() != null)
            ModifyTopicMainMenu.init(topic);
        else {
            System.out.print("Id not valid\n");
            UserInput.pause();
        }
    }
}
