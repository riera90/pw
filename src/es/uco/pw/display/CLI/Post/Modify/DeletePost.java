package es.uco.pw.display.CLI.Post.Modify;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.data.dto.post.DTOPost;

/**
 * The type Delete post.
 */
public class DeletePost {
    public static void init(DTOPost post) {
        DAOPost postController = new DAOPost();
        postController.delete(post);
    }
}
