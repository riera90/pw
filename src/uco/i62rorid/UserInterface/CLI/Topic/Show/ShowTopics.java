package uco.i62rorid.UserInterface.CLI.Topic.Show;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Entities.Topic;
import uco.i62rorid.UserInterface.CLI.Topic.Modify.ModifyTopicMainMenu;
import uco.i62rorid.Utils.UserInput;

import java.util.LinkedList;

public class ShowTopics {
    public static void init(LinkedList<Topic> topics){
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
