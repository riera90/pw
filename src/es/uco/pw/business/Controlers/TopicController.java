package es.uco.pw.business.Controlers;

import es.uco.pw.business.Connectors.FileConn;
import es.uco.pw.business.dao.topic.DAOTopic;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type Topic controller.
 */
public class TopicController {
    private FileConn conn;

    /**
     * Instantiates a new Topic controller.
     */
    public TopicController(){
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
        DAOTopic lastTopic = new DAOTopic(all.getLast());
        return lastTopic.getId()+1;
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<DAOTopic> get(){
        LinkedList<DAOTopic> topics = new LinkedList<>();
        for (String topicJson:conn.readAll()){
            topics.add(new DAOTopic(topicJson));
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
    public LinkedList<DAOTopic> getByField(String key, String value){
        LinkedList<DAOTopic> topics = new LinkedList<>();
        for (String topicJson:conn.getLineByField(key, value)){
            topics.add(new DAOTopic(topicJson));
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
    public LinkedList<DAOTopic> getByFieldLike(String key, String value){
        LinkedList<DAOTopic> topics = new LinkedList<>();
        for (String topicJson:conn.getLineByFieldLike(key, value)){
            topics.add(new DAOTopic(topicJson));
        }
        return topics;
    }

    /**
     * Get topic.
     *
     * @param id the id
     * @return the topic
     */
    public DAOTopic get(int id){
        return new DAOTopic(conn.read(id));
    }

    /**
     * Post topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public DAOTopic post(DAOTopic topic){
        topic.setId(getNextId());
        this.conn.append(topic.toJson());
        return new DAOTopic(this.conn.read(topic.getId()));
    }

    /**
     * Put topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public DAOTopic put(DAOTopic topic){
        this.conn.update(topic.toJson());
        return new DAOTopic(this.conn.read(topic.getId()));
    }

    /**
     * Patch topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public DAOTopic patch(DAOTopic topic){
        DAOTopic oldTopic = new DAOTopic(this.conn.read(topic.getId()));
        DAOTopic newTopic = new DAOTopic(oldTopic.toJson() + topic.toJson());
        this.conn.update(newTopic.toJson());
        return new DAOTopic(this.conn.read(topic.getId()));
    }

    /**
     * Delete boolean.
     *
     * @param topic the topic
     * @return the boolean
     */
    public Boolean delete(DAOTopic topic){
        return this.conn.delete(topic.getId());
    }
}
