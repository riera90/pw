package uco.i62rorid.pw.Views.CLI.Post.Show.Filters;

import uco.i62rorid.pw.Controlers.PostController;
import uco.i62rorid.pw.Utils.UserInput;
import uco.i62rorid.pw.Views.CLI.Post.Show.ShowPosts;

import java.security.NoSuchAlgorithmException;

/**
 * The type Filter post by title.
 */
public class FilterPostByTitle {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        System.out.print("enter the post name: ");
        String title = UserInput.getStringFromUser();
        ShowPosts.init(postController.getByFieldLike("title", title));
    }
}
