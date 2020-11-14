package es.uco.pw.display.CLI.Post.Show;

import es.uco.pw.business.Controlers.PostController;
import es.uco.pw.business.dao.post.DAOPost;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Show posts with topic.
 */
public class ShowPostsWithTopic {
    /**
     * Init.
     *
     * @param topicId the topic id
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(Integer topicId) throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        LinkedList<DAOPost> filteredPosts = new LinkedList<>();
        LinkedList<DAOPost> allPosts = postController.get();
        for (DAOPost post:allPosts) {
            if (post.getState()==null || !post.getState().equals("published")) continue;
            if (post.getTopics()!=null){
                if (post.getTopics().contains(topicId)) filteredPosts.add(post);
            }
        }
        ShowPosts.init(filteredPosts);
    }
}
