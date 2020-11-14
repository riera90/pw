package es.uco.pw.display.CLI.Post.Show.Filters;

import es.uco.pw.business.Controlers.PostController;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.Post.Show.ShowPosts;

import java.security.NoSuchAlgorithmException;

/**
 * The type Filter post by title.
 */
public class FilterPostByTitle {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        System.out.print("enter the post name: ");
        String title = UserInput.getStringFromUser();
        ShowPosts.init(postController.getByFieldLike("title", title));
    }
}