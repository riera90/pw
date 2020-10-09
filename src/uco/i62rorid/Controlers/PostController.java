package uco.i62rorid.Controlers;

import uco.i62rorid.Connectors.FileConnector;
import uco.i62rorid.Entities.Post;

import java.util.LinkedList;

public class PostController {
    private FileConnector conn;

    public PostController(){
        this.conn = new FileConnector("user.dat");
    }

    private Integer getNextId() {
        LinkedList<String> all = this.conn.readAll();
        if (all.size() == 0) return 0;
        Post lastPost = new Post(all.getLast());
        return lastPost.getId()+1;
    }

    public LinkedList<Post> get(){
        LinkedList<Post> posts = new LinkedList<>();
        for (String postJson:conn.readAll()){
            posts.add(new Post(postJson));
        }
        return posts;
    }

    public LinkedList<Post> getByField(String key, String value){
        LinkedList<Post> posts = new LinkedList<>();
        for (String postJson:conn.getLineByField(key, value)){
            posts.add(new Post(postJson));
        }
        return posts;
    }

    public LinkedList<Post> getByFieldLike(String key, String value){
        LinkedList<Post> posts = new LinkedList<>();
        for (String postJson:conn.getLineByFieldLike(key, value)){
            posts.add(new Post(postJson));
        }
        return posts;
    }

    public Post get(int id){
        return new Post(conn.read(id));
    }

    public Post postPost(Post post){
        this.conn.append(post.toJson());
        return new Post(this.conn.read(post.getId()));
    }

    public Post put(Post post){
        post.setId(getNextId());
        this.conn.update(post.toJson());
        return new Post(this.conn.read(post.getId()));
    }

    public Post patch(Post post){
        this.conn.update(this.conn.read(post.getId()) + post.toJson());
        return new Post(this.conn.read(post.getId()));
    }

    public Boolean delete(Post post){
        return this.conn.delete(post.getId());
    }
}
