package es.uco.pw.business.Daemons;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.data.dto.post.DTOPost;

import java.util.Date;
import java.util.LinkedList;

/**
 * The type Flash post daemon.
 */
public class FlashPostDaemon extends Thread {
    private Thread t;
    private String name;
    private boolean exit;

    /**
     * Instantiates a new Flash post daemon.
     *
     * @param name the name
     */
    public FlashPostDaemon(String name) {
        this.name = name;
        this.exit = false;
    }

    public void run() {
        for (; !exit;){
            try {
                Thread.sleep(5000);
                iteration();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();//preserve the message
            }
        }
    }

    public static void iteration(){
        DAOPost postController = new DAOPost();
        LinkedList<DTOPost> posts = postController.getByField("type", "\"flash\"");
        Date now = new Date();
        for (DTOPost post: posts){
            if (post.getState()==null) continue;
            if (post.getState().equals("waiting") && post.getPublishedAt().before(now)){
                post.setState("published");
                postController.patch(post);
            }else if (post.getState().equals("published") && post.getDeletedAt().after(now)){
                post.setState("deleted");
                postController.patch(post);
            }
        }
    }

    public void start () {
        if (t == null) {
            t = new Thread(this, name);
            t.start ();
        }
    }

    /**
     * Stop thread.
     */
    public void stopThread(){
        exit = true;
    }
}
