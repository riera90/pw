package es.uco.pw.business.dao.topic;

import es.uco.pw.business.Connectors.FileConn;
import es.uco.pw.data.dto.topic.DTOTopic;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type Topic controller.
 */
public class DAOTopic {
    private FileConn conn;

    /**
     * Instantiates a new Topic controller.
     */
    public DAOTopic(){
        try {
            InputStream in = getClass().getResourceAsStream("/.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Properties p = new Properties();
            p.load(reader);
            this.conn = new FileConn(p.getProperty("FILE_BASE_DIR")+p.getProperty("TOPIC_TABLE_NAME"));
        } catch (NullPointerException e){
            try {
                FileReader reader=new FileReader(".properties");
                Properties p = new Properties();
                p.load(reader);
                this.conn = new FileConn(p.getProperty("FILE_BASE_DIR") + p.getProperty("TOPIC_TABLE_NAME"));
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
        DTOTopic lastTopic = new DTOTopic(all.getLast());
        return lastTopic.getId()+1;
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<DTOTopic> get(){
        LinkedList<DTOTopic> topics = new LinkedList<>();
        for (String topicJson:conn.readAll()){
            topics.add(new DTOTopic(topicJson));
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
    public LinkedList<DTOTopic> getByField(String key, String value){
        LinkedList<DTOTopic> topics = new LinkedList<>();
        for (String topicJson:conn.getLineByField(key, value)){
            topics.add(new DTOTopic(topicJson));
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
    public LinkedList<DTOTopic> getByFieldLike(String key, String value){
        LinkedList<DTOTopic> topics = new LinkedList<>();
        for (String topicJson:conn.getLineByFieldLike(key, value)){
            topics.add(new DTOTopic(topicJson));
        }
        return topics;
    }

    /**
     * Get topic.
     *
     * @param id the id
     * @return the topic
     */
    public DTOTopic get(int id){
        return new DTOTopic(conn.read(id));
    }

    /**
     * Post topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public DTOTopic post(DTOTopic topic){
        topic.setId(getNextId());
        this.conn.append(topic.toJson());
        return new DTOTopic(this.conn.read(topic.getId()));
    }

    /**
     * Put topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public DTOTopic put(DTOTopic topic){
        this.conn.update(topic.toJson());
        return new DTOTopic(this.conn.read(topic.getId()));
    }

    /**
     * Patch topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public DTOTopic patch(DTOTopic topic){
        DTOTopic oldTopic = new DTOTopic(this.conn.read(topic.getId()));
        DTOTopic newTopic = new DTOTopic(oldTopic.toJson() + topic.toJson());
        this.conn.update(newTopic.toJson());
        return new DTOTopic(this.conn.read(topic.getId()));
    }

    /**
     * Delete boolean.
     *
     * @param topic the topic
     * @return the boolean
     */
    public Boolean delete(DTOTopic topic){
        return this.conn.delete(topic.getId());
    }
}
