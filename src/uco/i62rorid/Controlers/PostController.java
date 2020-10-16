package uco.i62rorid.Controlers;

import uco.i62rorid.Connectors.FileConn;
import uco.i62rorid.Entities.Post;

import java.util.LinkedList;

/**
 * The type Post controller.
 */
public class PostController {
    private FileConn conn;

    /**
     * Instantiates a new Post controller.
     */
    public PostController(){
        this.conn = new FileConn("post.db");
    }

    private Integer getNextId() {
        LinkedList<String> all = this.conn.readAll();
        if (all.size() == 0) return 0;
        Post lastPost = new Post(all.getLast());
        return lastPost.getId()+1;
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<Post> get(){
        LinkedList<Post> posts = new LinkedList<>();
        for (String postJson:conn.readAll()){
            posts.add(new Post(postJson));
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
    public LinkedList<Post> getByField(String key, String value){
        LinkedList<Post> posts = new LinkedList<>();
        for (String postJson:conn.getLineByField(key, value)){
            posts.add(new Post(postJson));
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
    public LinkedList<Post> getByFieldLike(String key, String value){
        LinkedList<Post> posts = new LinkedList<>();
        for (String postJson:conn.getLineByFieldLike(key, value)){
            posts.add(new Post(postJson));
        }
        return posts;
    }

    /**
     * Get post.
     *
     * @param id the id
     * @return the post
     */
    public Post get(int id){
        return new Post(conn.read(id));
    }

    /**
     * Post post post.
     *
     * @param post the post
     * @return the post
     */
    public Post post(Post post){
        post.setId(this.getNextId());
        this.conn.append(post.toJson());
        return new Post(this.conn.read(post.getId()));
    }

    /**
     * Put post.
     *
     * @param post the post
     * @return the post
     */
    public Post put(Post post){
        post.setId(getNextId());
        this.conn.update(post.toJson());
        return new Post(this.conn.read(post.getId()));
    }

    /**
     * Patch post.
     *
     * @param post the post
     * @return the post
     */
    public Post patch(Post post){
        Post oldPost = new Post(this.conn.read(post.getId()));
        Post newPost = new Post(oldPost.toJson() + post.toJson());
        this.conn.update(newPost.toJson());
        return new Post(this.conn.read(post.getId()));
    }

    /**
     * Delete boolean.
     *
     * @param post the post
     * @return the boolean
     */
    public Boolean delete(Post post){
        return this.conn.delete(post.getId());
    }
}
