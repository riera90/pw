package uco.i62rorid.pw.Views.CLI.Topic.Show.Filters;

import uco.i62rorid.pw.Controlers.TopicController;
import uco.i62rorid.pw.Entities.Topic;
import uco.i62rorid.pw.Views.CLI.Topic.Show.ShowTopics;
import uco.i62rorid.pw.Utils.UserInput;

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
        LinkedList<Topic> topics = topicController.getByField("topic", '"'+value+'"');
        ShowTopics.init(topics);
    }
}
