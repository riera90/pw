package uco.i62rorid.pw.Entities;

import uco.i62rorid.pw.Utils.JSONParser;

import java.util.Date;
import java.util.LinkedList;

/**
 * The type Post.
 */
public class Post {
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
    private Integer owner;
    private LinkedList<Integer> sentTo;
    private String type;
    private String state;
    private LinkedList<Integer> topics;
    private Boolean isDeleted; // unused

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
     * Gets owner.
     *
     * @return the owner
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    /**
     * Gets sent to.
     *
     * @return the sent to
     */
    public LinkedList<Integer> getSentTo() {
        return sentTo;
    }

    /**
     * Sets sent to.
     *
     * @param sentTo the sent to
     */
    public void setSentTo(LinkedList<Integer> sentTo) {
        this.sentTo = sentTo;
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
     * @param topics the topics
     */
    public void setTopics(LinkedList<Integer> topics) {
        this.topics = topics;
    }

    /**
     * Gets deleted.
     *
     * @return the deleted
     */
    public Boolean getDeleted() {
        return isDeleted;
    }

    /**
     * Sets deleted.
     *
     * @param deleted the deleted
     */
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Post" + toJson();
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        return '{' +
                (id==null ? "":("id:"+id+",")) +
                (title==null ? "":("title:\""+title+"\",")) +
                (body==null ? "":("body:\""+body+"\",")) +
                (createdAt==null ? "":("createdAt:"+JSONParser.getDateAsString(createdAt)+",")) +
                (publishedAt==null ? "":("publishedAt:"+JSONParser.getDateAsString(publishedAt)+",")) +
                (deletedAt==null ? "":("deletedAt:"+JSONParser.getDateAsString(deletedAt)+",")) +
                (owner==null ? "":("owner:"+owner+",")) +
                (sentTo==null ? "":("sentTo:"+sentTo+",")) +
                (type==null ? "":("type:\""+type+"\",")) +
                (state==null ? "":("state:\""+state+"\",")) +
                (topics==null ? "":("topics:"+topics+",")) +
                (isDeleted==null ? "":("isDeleted:"+isDeleted+",")) +
                '}';
    }

    /**
     * Instantiates a new Post.
     */
    public Post(){
        this.isDeleted = false;
        this.createdAt = JSONParser.getNow();
    }

    /**
     * Instantiates a new Post.
     *
     * @param json the json
     */
    public Post(String json) {
        this.isDeleted = false;
        this.createdAt = JSONParser.getNow();
        JSONParser jsonParser = new JSONParser(json);
        while(jsonParser.getError()==0 && jsonParser.gotoNextField()){
            if (jsonParser.getKey().equals("id")) this.id = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("title")) this.title = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("body")) this.body = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("createdAt")) this.createdAt = jsonParser.getValueAsDate();
            else if (jsonParser.getKey().equals("publishedAt")) this.publishedAt = jsonParser.getValueAsDate();
            else if (jsonParser.getKey().equals("deletedAt")) this.deletedAt = jsonParser.getValueAsDate();
            else if (jsonParser.getKey().equals("owner")) this.owner = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("sentTo")) this.sentTo = jsonParser.getValueAsIntegerLinkedList();
            else if (jsonParser.getKey().equals("type")) this.type = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("state")) this.state = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("topics")) this.topics = jsonParser.getValueAsIntegerLinkedList();
            else if (jsonParser.getKey().equals("isDeleted")) this.isDeleted = jsonParser.getValueAsBoolean();
        }
    }
}
