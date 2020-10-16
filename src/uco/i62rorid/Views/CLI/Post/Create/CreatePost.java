package uco.i62rorid.Views.CLI.Post.Create;

import uco.i62rorid.Controlers.PostController;
import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.Post;
import uco.i62rorid.Entities.User;
import uco.i62rorid.Factories.PostFactory;
import uco.i62rorid.Utils.Algo;
import uco.i62rorid.Utils.UserInput;
import uco.i62rorid.Views.CLI.UserSessionStateSingleton;

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

        PostFactory postFactory = new PostFactory(Post.TYPES[typeIndex-1]);
        Post post = postFactory.getPost();

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
                    TopicController topicController = new TopicController();
                    System.out.println(topicController.get());

                    int index;
                    LinkedList<Integer> topicsIndexes = new LinkedList<>();
                    do {
                        System.out.print("Topic id: ");
                        index = UserInput.getIdFromUser();
                        if (index < 0) break;
                        UserController userController = new UserController();
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
                    UserController userController = new UserController();

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
                    UserController userController = new UserController();
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

        PostController postController = new PostController();
        postController.post(post);
        System.out.print("\nPost successfully Created\n");
        UserInput.pause();
    }
}
