package uco.i62rorid.Views.CLI.Post.Show;

import uco.i62rorid.Controlers.PostController;
import uco.i62rorid.Entities.Post;

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
        LinkedList<Post> filteredPosts = new LinkedList<>();
        LinkedList<Post> allPosts = postController.get();
        for (Post post:allPosts) {
            if (post.getState()==null || !post.getState().equals("published")) continue;
            if (post.getTopics()!=null){
                if (post.getTopics().contains(topicId)) filteredPosts.add(post);
            }
        }
        ShowPosts.init(filteredPosts);
    }
}
