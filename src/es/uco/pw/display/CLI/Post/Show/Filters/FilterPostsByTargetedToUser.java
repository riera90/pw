package es.uco.pw.display.CLI.Post.Show.Filters;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.data.dto.post.DTOPost;
import es.uco.pw.data.dto.user.DTOUser;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.Post.Show.ShowPosts;

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
    public static void init(DTOUser user) throws NoSuchAlgorithmException {
        DAOPost postController = new DAOPost();
        LinkedList<DTOPost> filteredPosts = new LinkedList<>();
        LinkedList<DTOPost> allPosts = postController.get();
        for (DTOPost post:allPosts) {
            if (post.getType().equals("targeted") && post.getSentTo() != null && post.getSentTo().contains(user.getId())) filteredPosts.add(post);
        }
        ShowPosts.init(filteredPosts);
        UserInput.pause();
    }
}
