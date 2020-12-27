package es.uco.pw.data.dto.post;

import es.uco.pw.business.Utils.JSONParser;
import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.business.dao.post.PostBuilder;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 * The type Post.
 */
public class DTOPost {
    /**
     * The Types.
     */
    public static String[] TYPES = {
            "general",
            "themed",
            "targeted",
            "flash",
    };

    /**
     * The States.
     */
    public static String[] STATES = {
            "edited", // deprecated
            "waiting",
            "published",
            "deleted",
    };


    private Integer id;
    private String title;
    private String body;
    private Date createdAt;
    private Date publishedAt;
    private Date deletedAt;
    private Integer ownerId;
    private LinkedList<Integer> sentTo;
    private String type;
    private String state;
    private LinkedList<Integer> topics;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets published at.
     *
     * @return the published at
     */
    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets published at.
     *
     * @param publishedAt the published at
     */
    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * Gets deleted at.
     *
     * @return the deleted at
     */
    public Date getDeletedAt() {
        return deletedAt;
    }

    /**
     * Sets deleted at.
     *
     * @param deletedAt the deleted at
     */
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     * Gets owner id.
     *
     * @return the owner
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * Sets owner id.
     *
     * @param owner_id the owner
     */
    public void setOwnerId(Integer owner_id) {
        this.ownerId = owner_id;
    }

    /**
     * Gets sent to.
     *
     * @return the sent to
     */
    public LinkedList<Integer> getSentTo() {
        return sentTo;
    }

    public void addSentTo(Integer sentToId) {
        this.sentTo.add(sentToId);
    }

    public void removeSentTo(Integer sentToId) {
        this.sentTo.remove(sentToId);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets topics.
     *
     * @return the topics
     */
    public LinkedList<Integer> getTopics() {
        return topics;
    }

    /**
     * Sets topics.
     *
     */
    public void addTopic(Integer topicId) {
        this.topics.add(topicId);
    }

    public void removeTopic(Integer topicId) {
        this.topics.remove(topicId);
    }


    @Override
    public String toString() {
        return "Post" + PostBuilder.toJson(this);
    }


    public DTOPost(){
        this.createdAt = JSONParser.getNow();
        this.topics = new LinkedList<>();
        this.sentTo = new LinkedList<>();
    }

}
