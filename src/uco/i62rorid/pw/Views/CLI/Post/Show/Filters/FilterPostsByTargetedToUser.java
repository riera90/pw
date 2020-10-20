package uco.i62rorid.pw.Views.CLI.Post.Show.Filters;

import uco.i62rorid.pw.Controlers.PostController;
import uco.i62rorid.pw.Entities.Post;
import uco.i62rorid.pw.Entities.User;
import uco.i62rorid.pw.Utils.UserInput;
import uco.i62rorid.pw.Views.CLI.Post.Show.ShowPosts;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Filter posts by targeted to user.
 */
public class FilterPostsByTargetedToUser {
    /**
     * Init.
     *
     * @param user the user
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(User user) throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        LinkedList<Post> filteredPosts = new LinkedList<>();
        LinkedList<Post> allPosts = postController.get();
        for (Post post:allPosts) {
            if (post.getType().equals("targeted") && post.getSentTo() != null && post.getSentTo().contains(user.getId())) filteredPosts.add(post);
        }
        ShowPosts.init(filteredPosts);
        UserInput.pause();
    }
}
