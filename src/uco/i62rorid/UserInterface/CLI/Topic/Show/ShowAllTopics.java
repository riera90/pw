package uco.i62rorid.UserInterface.CLI.Topic.Show;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Entities.Topic;

import java.util.LinkedList;

public class ShowAllTopics {
    public static void init(){
        TopicController topicController = new TopicController();
        LinkedList<Topic> topics = topicController.get();
        ShowTopics.init(topics);
    }
}
