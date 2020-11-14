package es.uco.pw.display.CLI.Post.Show.Filters;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.data.dto.post.DTOPost;
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
        DAOPost postController = new DAOPost();
        UserInput.clear();
        System.out.print("enter the publication date: [dd/mm/yyyy] ");
        String date = UserInput.getStringFromUser();
        LinkedList<DTOPost> posts = postController.getByFieldLike("publishedAt", date);
        ShowPosts.init(posts);
        UserInput.pause();
    }
}
