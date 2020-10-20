package uco.i62rorid.pw.Views.CLI.Post.Modify;

import uco.i62rorid.pw.Controlers.PostController;
import uco.i62rorid.pw.Entities.Post;

/**
 * The type Delete post.
 */
public class DeletePost {
    public static void init(Post post) {
        PostController postController = new PostController();
        postController.delete(post);
    }
}
