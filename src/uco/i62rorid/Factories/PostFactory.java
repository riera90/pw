package uco.i62rorid.Factories;

import uco.i62rorid.Entities.Post;

/**
 * The type Post factory.
 */
public class PostFactory {
    private String type;
    private Post post;

    /**
     * Instantiates a new Post factory.
     *
     * @param type the type
     */
    public PostFactory(String type){
        this.post = new Post();
        for (String valid_type: Post.TYPES) {
            if (type.equals(valid_type)){
                this.type = type;
                break;
            }
        }
    }

    /**
     * Get post post.
     *
     * @return the post
     */
    public Post getPost(){
        if (this.type == null) return null;
        Post post = new Post();
        post.setType(this.type);
        return post;
    }
}
