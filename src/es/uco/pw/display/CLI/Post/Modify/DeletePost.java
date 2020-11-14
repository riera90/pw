package es.uco.pw.display.CLI.Post.Modify;

import es.uco.pw.business.Controlers.PostController;
import es.uco.pw.business.dao.post.DAOPost;

/**
 * The type Delete post.
 */
public class DeletePost {
    public static void init(DAOPost post) {
        PostController postController = new PostController();
        postController.delete(post);
    }
}
