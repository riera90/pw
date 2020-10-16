package uco.i62rorid.Views.CLI.Post.Show;
import uco.i62rorid.Controlers.PostController;
import uco.i62rorid.Utils.UserInput;

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
