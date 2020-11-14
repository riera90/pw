package es.uco.pw.display.CLI.Post.Show;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.data.dto.post.DTOPost;

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
        DAOPost postController = new DAOPost();
        LinkedList<DTOPost> filteredPosts = new LinkedList<>();
        LinkedList<DTOPost> allPosts = postController.get();
        for (DTOPost post:allPosts) {
            if (post.getState()==null || !post.getState().equals("published")) continue;
            if (post.getTopics()!=null){
                if (post.getTopics().contains(topicId)) filteredPosts.add(post);
            }
        }
        ShowPosts.init(filteredPosts);
    }
}
