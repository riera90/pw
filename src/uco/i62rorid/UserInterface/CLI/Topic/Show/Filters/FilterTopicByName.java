package uco.i62rorid.UserInterface.CLI.Topic.Show.Filters;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Entities.Topic;
import uco.i62rorid.UserInterface.CLI.Topic.Show.ShowTopics;
import uco.i62rorid.Utils.UserInput;

import java.util.LinkedList;

public class FilterTopicByName {
    public static void init(){
        TopicController topicController = new TopicController();
        UserInput.clear();
        System.out.print("enter name of the topic: ");
        String value = UserInput.getStringFromUser();
        LinkedList<Topic> topics = topicController.getByField("topic", '"'+value+'"');
        ShowTopics.init(topics);
    }
}
