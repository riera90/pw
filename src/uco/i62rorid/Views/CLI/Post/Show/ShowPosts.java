package uco.i62rorid.Views.CLI.Post.Show;
import uco.i62rorid.Controlers.PostController;
import uco.i62rorid.Entities.Post;
import uco.i62rorid.Utils.UserInput;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Show posts.
 */
public class ShowPosts {
    /**
     * Init.
     *
     * @param posts the posts
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(LinkedList<Post> posts) throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        for (Post post:posts) {
            System.out.println(post);
        }
        System.out.print("\tDo you want to select a post?\n\nselect the id or press enter to skip: ");
        Integer id = UserInput.getIdFromUser();
        if (id < 0) return;
        Post post = postController.get(id);
        if (post.getId() != null)
            ShowPost.init(post);
        else {
            System.out.print("Id not valid\n");
            UserInput.pause();
        }

    }
}
