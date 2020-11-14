package es.uco.pw.display.CLI.Post.Create;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.data.dto.post.DTOPost;
import es.uco.pw.business.Factories.PostFactory;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.UserSessionStateSingleton;

import java.util.Date;
import java.util.LinkedList;

/**
 * The type Create post.
 */
public class CreatePost {
    /**
     * Init.
     */
    public static void init(){
        int typeIndex;
        do {
            System.out.print("\t1) general\n"+
            "\t2) themed\n"+
            "\t3) targeted\n"+
            "\t4) flash\n");
            System.out.print("Select the post type: ");
            typeIndex = UserInput.getIdFromUser();
            if (typeIndex > 0 & typeIndex < 5) break;
            else System.out.print("not a valid id\n");
        }while(true);

        PostFactory postFactory = new PostFactory(DTOPost.TYPES[typeIndex-1]);
        DTOPost post = postFactory.getPost();

        UserInput.clear();
        System.out.print("\tEnter the following fields.\n\n");

        post.setOwner(UserSessionStateSingleton.getInstance().LoggedUser.getId());

        System.out.print("Title: ");
        post.setTitle(UserInput.getStringFromUser());

        System.out.print("body: ");
        post.setBody(UserInput.getStringFromUser());

        post.setState("published");



        switch (post.getType()){
            case "general":
                break;

            case "themed":
                {
                    System.out.print("now enter the topics indexes, input -1 to stop adding more\n");
                    UserInput.pause();
                    DAOTopic topicController = new DAOTopic();
                    System.out.println(topicController.get());

                    int index;
                    LinkedList<Integer> topicsIndexes = new LinkedList<>();
                    do {
                        System.out.print("Topic id: ");
                        index = UserInput.getIdFromUser();
                        if (index < 0) break;
                        DAOUser userController = new DAOUser();
                        if (userController.get(index).getId() != null)
                            topicsIndexes.add(index);
                        else
                            System.out.print("not a valid id\n");


                    }while(true);
                    post.setTopics(topicsIndexes);
                    break;
                }

            case "targeted":
                {
                    System.out.print("now enter the users ids, input -1 to stop adding more\n");
                    UserInput.pause();
                    DAOUser userController = new DAOUser();

                    int index;
                    LinkedList<Integer> usersIndexes = new LinkedList<>();
                    do {
                        System.out.print("User id: ");
                        index = UserInput.getIdFromUser();
                        if (index < 0) break;
                        if (userController.get(index).getId() != null)
                            usersIndexes.add(index);
                        else
                            System.out.print("not a valid id\n");


                    }while(true);
                    post.setSentTo(usersIndexes);
                    break;
                }

            case "flash":
                {
                    System.out.print("now enter the publication and removal dates\n");
                    UserInput.pause();
                    DAOUser userController = new DAOUser();
                    System.out.println(userController.get());

                    Date date = null;
                    LinkedList<Integer> usersIndexes = new LinkedList<>();
                    do {
                        System.out.print("publishing date: ");
                        date = UserInput.getDateFromUser();
                    }while(date == null);
                    post.setPublishedAt(date);
                    date=null;
                    do {
                        System.out.print("removal date: ");
                        date = UserInput.getDateFromUser();
                    }while(date == null);
                    post.setDeletedAt(date);
                    post.setState("waiting");
                    break;
                }

            default:
                System.out.println("unhandled error in post type");
                UserInput.pause();
        }

        DAOPost postController = new DAOPost();
        postController.post(post);
        System.out.print("\nPost successfully Created\n");
        UserInput.pause();
    }
}
