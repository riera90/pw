package es.uco.pw.business.Factories;

import es.uco.pw.data.dto.post.DTOPost;

/**
 * The type Post factory.
 */
public class PostFactory {
    private String type;
    private DTOPost post;

    /**
     * Instantiates a new Post factory.
     *
     * @param type the type
     */
    public PostFactory(String type){
        this.post = new DTOPost();
        for (String valid_type: DTOPost.TYPES) {
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
    public DTOPost getPost(){
        if (this.type == null) return null;
        DTOPost post = new DTOPost();
        post.setType(this.type);
        return post;
    }
}
