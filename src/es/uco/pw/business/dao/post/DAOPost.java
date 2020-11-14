package es.uco.pw.business.dao.post;

import es.uco.pw.business.Connectors.FileConn;
import es.uco.pw.data.dto.post.DTOPost;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type Post controller.
 */
public class DAOPost {
    private FileConn conn;

    /**
     * Instantiates a new Post controller.
     */
    public DAOPost() {
        try {
            InputStream in = getClass().getResourceAsStream("/.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Properties p = new Properties();
            p.load(reader);
            this.conn = new FileConn(p.getProperty("FILE_BASE_DIR")+p.getProperty("POST_TABLE_NAME"));
        } catch (NullPointerException e){
            try {
                FileReader reader=new FileReader(".properties");
                Properties p = new Properties();
                p.load(reader);
                this.conn = new FileConn(p.getProperty("FILE_BASE_DIR") + p.getProperty("POST_TABLE_NAME"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer getNextId() {
        LinkedList<String> all = this.conn.readAll();
        if (all.size() == 0) return 0;
        DTOPost lastPost = new DTOPost(all.getLast());
        return lastPost.getId()+1;
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<DTOPost> get(){
        LinkedList<DTOPost> posts = new LinkedList<>();
        for (String postJson:conn.readAll()){
            posts.add(new DTOPost(postJson));
        }
        return posts;
    }

    /**
     * Get by field linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<DTOPost> getByField(String key, String value){
        LinkedList<DTOPost> posts = new LinkedList<>();
        for (String postJson:conn.getLineByField(key, value)){
            posts.add(new DTOPost(postJson));
        }
        return posts;
    }

    /**
     * Get by field like linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<DTOPost> getByFieldLike(String key, String value){
        LinkedList<DTOPost> posts = new LinkedList<>();
        for (String postJson:conn.getLineByFieldLike(key, value)){
            posts.add(new DTOPost(postJson));
        }
        return posts;
    }

    /**
     * Get post.
     *
     * @param id the id
     * @return the post
     */
    public DTOPost get(int id){
        return new DTOPost(conn.read(id));
    }

    /**
     * Post post post.
     *
     * @param post the post
     * @return the post
     */
    public DTOPost post(DTOPost post){
        post.setId(this.getNextId());
        this.conn.append(post.toJson());
        return new DTOPost(this.conn.read(post.getId()));
    }

    /**
     * Put post.
     *
     * @param post the post
     * @return the post
     */
    public DTOPost put(DTOPost post){
        post.setId(getNextId());
        this.conn.update(post.toJson());
        return new DTOPost(this.conn.read(post.getId()));
    }

    /**
     * Patch post.
     *
     * @param post the post
     * @return the post
     */
    public DTOPost patch(DTOPost post){
        DTOPost oldPost = new DTOPost(this.conn.read(post.getId()));
        DTOPost newPost = new DTOPost(oldPost.toJson() + post.toJson());
        this.conn.update(newPost.toJson());
        return new DTOPost(this.conn.read(post.getId()));
    }

    /**
     * Delete boolean.
     *
     * @param post the post
     * @return the boolean
     */
    public Boolean delete(DTOPost post){
        return this.conn.delete(post.getId());
    }
}
