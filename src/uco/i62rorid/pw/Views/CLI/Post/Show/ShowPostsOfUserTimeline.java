package uco.i62rorid.pw.Views.CLI.Post.Show;

import uco.i62rorid.pw.Controlers.PostController;
import uco.i62rorid.pw.Entities.Post;
import uco.i62rorid.pw.Entities.User;

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
    public static void init(User user) throws NoSuchAlgorithmException {
        PostController postController = new PostController();
        LinkedList<Post> filteredPosts = new LinkedList<>();
        LinkedList<Post> allPosts = postController.get();
        for (Post post:allPosts) {
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
