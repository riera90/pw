package es.uco.pw.display.CLI.Post.Show.Filters;

import es.uco.pw.business.Controlers.PostController;
import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.Post.Show.ShowPosts;

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
        LinkedList<DAOPost> posts = postController.getByFieldLike("publishedAt", date);
        ShowPosts.init(posts);
        UserInput.pause();
    }
}
