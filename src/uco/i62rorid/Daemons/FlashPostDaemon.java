package uco.i62rorid.Daemons;

import uco.i62rorid.Controlers.PostController;
import uco.i62rorid.Entities.Post;

import java.util.Date;
import java.util.LinkedList;

/**
 * The type Flash post daemon.
 */
public class FlashPostDaemon extends Thread {
    private Thread t;
    private String name;

    /**
     * Instantiates a new Flash post daemon.
     *
     * @param name the name
     */
    public FlashPostDaemon(String name) {
        this.name = name;
    }

    public void run() {
        for (;;){
            try {
                Thread.sleep(5000);
                PostController postController = new PostController();
                LinkedList<Post> posts = postController.getByField("type", "\"flash\"");
                Date now = new Date();
                for (Post post: posts){
                    if (post.getState()==null) continue;
                    if (post.getState().equals("waiting") && post.getPublishedAt().before(now)){
                        post.setState("published");
                        postController.patch(post);
                    }else if (post.getState().equals("published") && post.getDeletedAt().after(now)){
                        post.setState("deleted");
                        post.setDeleted(true);
                        postController.patch(post);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start () {
        if (t == null) {
            t = new Thread(this, name);
            t.start ();
        }
    }
}
