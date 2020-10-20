package uco.i62rorid.pw.Views.CLI.Post.Show.Filters;

import uco.i62rorid.pw.Controlers.PostController;
import uco.i62rorid.pw.Entities.Post;
import uco.i62rorid.pw.Utils.UserInput;
import uco.i62rorid.pw.Views.CLI.Post.Show.ShowPosts;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Filter post by publication date.
 */
public class FilterPostByPublicationDate {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        UserInput.clear();
        System.out.print("enter the publication date: [dd/mm/yyyy] ");
        String date = UserInput.getStringFromUser();
        LinkedList<Post> posts = postController.getByFieldLike("publishedAt", date);
        ShowPosts.init(posts);
        UserInput.pause();
    }
}
