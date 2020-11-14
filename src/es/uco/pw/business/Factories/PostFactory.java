package es.uco.pw.business.Factories;

import es.uco.pw.business.dao.post.DAOPost;

/**
 * The type Post factory.
 */
public class PostFactory {
    private String type;
    private DAOPost post;

    /**
     * Instantiates a new Post factory.
     *
     * @param type the type
     */
    public PostFactory(String type){
        this.post = new DAOPost();
        for (String valid_type: DAOPost.TYPES) {
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
    public DAOPost getPost(){
        if (this.type == null) return null;
        DAOPost post = new DAOPost();
        post.setType(this.type);
        return post;
    }
}
