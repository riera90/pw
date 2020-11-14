package es.uco.pw.display.CLI.Post.Show;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.business.Utils.UserInput;

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
        DAOPost postController = new DAOPost();
        ShowPosts.init(postController.getByField("owner", userId.toString()));
    }
}
