package es.uco.pw.display.CLI.Post.Show;
import es.uco.pw.business.Controlers.PostController;
import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.Post.Modify.ModifyPostMainMenu;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Show posts.
 */
public class ShowPosts {
    /**
     * Init.
     *
     * @param posts the posts
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(LinkedList<DAOPost> posts) throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        for (DAOPost post:posts) {
            System.out.println(post);
        }
        System.out.print("\tDo you want to select a post?\n\nselect the id or press enter to skip: ");
        Integer id = UserInput.getIdFromUser();
        if (id < 0) return;
        DAOPost post = postController.get(id);
        if (post.getId() != null)
            ModifyPostMainMenu.init(post);
        else {
            System.out.print("Id not valid\n");
            UserInput.pause();
        }

    }
}
