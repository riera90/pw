package uco.i62rorid.Views.CLI.Post.Show;

import uco.i62rorid.Controlers.PostController;
import uco.i62rorid.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Show posts of user.
 */
public class ShowPostsOfUserMenu {
    /**
     * Init.
     *
     * @param userId the user id
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(Integer userId) throws NoSuchAlgorithmException {
        UserInput.clear();
        PostController postController = new PostController();
        ShowPosts.init(postController.getByField("owner", userId.toString()));
    }
}
