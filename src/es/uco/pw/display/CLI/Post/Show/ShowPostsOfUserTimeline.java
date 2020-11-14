package es.uco.pw.display.CLI.Post.Show;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.data.dto.post.DTOPost;
import es.uco.pw.data.dto.user.DTOUser;

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
    public static void init(DTOUser user) throws NoSuchAlgorithmException {
        DAOPost postController = new DAOPost();
        LinkedList<DTOPost> filteredPosts = new LinkedList<>();
        LinkedList<DTOPost> allPosts = postController.get();
        for (DTOPost post:allPosts) {
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
