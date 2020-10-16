package uco.i62rorid.Controlers;

import uco.i62rorid.Connectors.FileConn;
import uco.i62rorid.Entities.Topic;

import java.util.LinkedList;

/**
 * The type Topic controller.
 */
public class TopicController {
    private FileConn conn;

    /**
     * Instantiates a new Topic controller.
     */
    public TopicController(){
        this.conn = new FileConn("topic.db");
    }

    private Integer getNextId() {
        LinkedList<String> all = this.conn.readAll();
        if (all.size() == 0) return 0;
        Topic lastTopic = new Topic(all.getLast());
        return lastTopic.getId()+1;
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<Topic> get(){
        LinkedList<Topic> topics = new LinkedList<>();
        for (String topicJson:conn.readAll()){
            topics.add(new Topic(topicJson));
        }
        return topics;
    }

    /**
     * Get by field linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<Topic> getByField(String key, String value){
        LinkedList<Topic> topics = new LinkedList<>();
        for (String topicJson:conn.getLineByField(key, value)){
            topics.add(new Topic(topicJson));
        }
        return topics;
    }

    /**
     * Get by field like linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<Topic> getByFieldLike(String key, String value){
        LinkedList<Topic> topics = new LinkedList<>();
        for (String topicJson:conn.getLineByFieldLike(key, value)){
            topics.add(new Topic(topicJson));
        }
        return topics;
    }

    /**
     * Get topic.
     *
     * @param id the id
     * @return the topic
     */
    public Topic get(int id){
        return new Topic(conn.read(id));
    }

    /**
     * Post topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public Topic post(Topic topic){
        topic.setId(getNextId());
        this.conn.append(topic.toJson());
        return new Topic(this.conn.read(topic.getId()));
    }

    /**
     * Put topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public Topic put(Topic topic){
        this.conn.update(topic.toJson());
        return new Topic(this.conn.read(topic.getId()));
    }

    /**
     * Patch topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public Topic patch(Topic topic){
        Topic oldTopic = new Topic(this.conn.read(topic.getId()));
        Topic newTopic = new Topic(oldTopic.toJson() + topic.toJson());
        this.conn.update(newTopic.toJson());
        return new Topic(this.conn.read(topic.getId()));
    }

    /**
     * Delete boolean.
     *
     * @param topic the topic
     * @return the boolean
     */
    public Boolean delete(Topic topic){
        return this.conn.delete(topic.getId());
    }
}
