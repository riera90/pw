package uco.i62rorid.Controlers;

import uco.i62rorid.Connectors.FileConnector;
import uco.i62rorid.Entities.Topic;

import java.util.LinkedList;

public class TopicController {
    private FileConnector conn;

    public TopicController(){
        this.conn = new FileConnector("topic.dat");
    }

    private Integer getNextId() {
        LinkedList<String> all = this.conn.readAll();
        if (all.size() == 0) return 0;
        Topic lastTopic = new Topic(all.getLast());
        return lastTopic.getId()+1;
    }

    public LinkedList<Topic> get(){
        LinkedList<Topic> topics = new LinkedList<>();
        for (String topicJson:conn.readAll()){
            topics.add(new Topic(topicJson));
        }
        return topics;
    }

    public LinkedList<Topic> getByField(String key, String value){
        LinkedList<Topic> topics = new LinkedList<>();
        for (String topicJson:conn.getLineByField(key, value)){
            topics.add(new Topic(topicJson));
        }
        return topics;
    }

    public LinkedList<Topic> getByFieldLike(String key, String value){
        LinkedList<Topic> topics = new LinkedList<>();
        for (String topicJson:conn.getLineByFieldLike(key, value)){
            topics.add(new Topic(topicJson));
        }
        return topics;
    }

    public Topic get(int id){
        return new Topic(conn.read(id));
    }

    public Topic post(Topic topic){
        topic.setId(getNextId());
        this.conn.append(topic.toJson());
        return new Topic(this.conn.read(topic.getId()));
    }

    public Topic put(Topic topic){
        this.conn.update(topic.toJson());
        return new Topic(this.conn.read(topic.getId()));
    }

    public Topic patch(Topic topic){
        this.conn.update(this.conn.read(topic.getId()) + topic.toJson());
        return new Topic(this.conn.read(topic.getId()));
    }

    public Boolean delete(Topic topic){
        return this.conn.delete(topic.getId());
    }
}
