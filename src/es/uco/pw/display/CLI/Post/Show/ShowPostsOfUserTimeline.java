package es.uco.pw.display.CLI.Post.Show;

import es.uco.pw.business.Controlers.PostController;
import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.business.dao.user.DAOUser;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Show posts of user timeline.
 */
public class ShowPostsOfUserTimeline {
    /**
     * Init.
     *
     * @param user the user
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(DAOUser user) throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        LinkedList<DAOPost> filteredPosts = new LinkedList<>();
        LinkedList<DAOPost> allPosts = postController.get();
        for (DAOPost post:allPosts) {
            if (post.getState()==null || !post.getState().equals("published")) continue;
            if (!post.getType().equals("targeted")){
                if (post.getOwner().equals(user.getId())) filteredPosts.add(post);
                else {
                    for (Integer topicId:user.getInterests()){
                        if (post.getTopics() != null && post.getTopics().contains(topicId)){
                            filteredPosts.add(post);
                            break;
                        }
                    }
                }
            }else if (post.getSentTo() != null && post.getSentTo().contains(user.getId())) filteredPosts.add(post);
        }
        ShowPosts.init(filteredPosts);
    }
}
