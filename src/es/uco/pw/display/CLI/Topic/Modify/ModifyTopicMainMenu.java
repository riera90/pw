package es.uco.pw.display.CLI.Topic.Modify;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.Post.Show.ShowPostsWithTopic;

import java.security.NoSuchAlgorithmException;

/**
 * The type Modify topic main menu.
 */
public class ModifyTopicMainMenu {
    /**
     * Init.
     *
     * @param topic the topic
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(DAOTopic topic) throws NoSuchAlgorithmException {
        String menu = "\t1) Modify name of the topic\n"+
                "\t2) Show posts with this topic\n"+
                "\t3) DELETE\n"+
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            TopicController topicController = new TopicController();
            topic = topicController.get(topic.getId());
            UserInput.clear();
            System.out.print(topic+"\n\n");
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    ModifyTopicName.init(topic);
                    break;
                case 2:
                    ShowPostsWithTopic.init(topic.getId());
                    break;
                case 3:
                    DeleteTopic.init(topic);
                    break;
                default:
                    System.out.print("Not a valid option");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
