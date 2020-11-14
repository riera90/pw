package es.uco.pw.display.CLI.Post.Show;
import es.uco.pw.business.Controlers.PostController;
import es.uco.pw.business.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Show all posts.
 */
public class ShowAllPosts {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        UserInput.clear();
        PostController postController = new PostController();
        ShowPosts.init(postController.get());
    }
}
