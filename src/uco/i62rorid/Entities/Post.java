package uco.i62rorid.Entities;

import uco.i62rorid.Utils.JSONParser;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Post {
    private Integer id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime publishedAt;
    private Integer owner;
    private LinkedList<Integer> sentTo;
    private String type;
    private String state;
    private LinkedList<Integer> topics;
    private Boolean isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public LinkedList<Integer> getSentTo() {
        return sentTo;
    }

    public void setSentTo(LinkedList<Integer> sentTo) {
        this.sentTo = sentTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LinkedList<Integer> getTopics() {
        return topics;
    }

    public void setTopics(LinkedList<Integer> topics) {
        this.topics = topics;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    @Override
    public String toString() {
        return "Post" + toJson();
    }

    public String toJson() {
        return '{' +
                (id==null ? "":("id:"+id+",")) +
                (title==null ? "":("title:\""+title+"\",")) +
                (body==null ? "":("body:\""+body+"\",")) +
                (createdAt==null ? "":("createdAt:\""+createdAt+"\",")) +
                (publishedAt==null ? "":("publishedAt:\""+publishedAt+"\",")) +
                (owner==null ? "":("owner:"+owner+",")) +
                (sentTo==null ? "":("sentTo:"+sentTo+",")) +
                (type==null ? "":("type:\""+type+"\",")) +
                (state==null ? "":("state:"+state+",")) +
                (topics==null ? "":("topics:"+topics+",")) +
                (isDeleted==null ? "":("isDeleted:"+isDeleted+",")) +
                '}';
    }
    public Post(){

    }

    public Post(String json) {
        JSONParser jsonParser = new JSONParser(json);
        while(jsonParser.getError()==0 && jsonParser.gotoNextField()){
            if (jsonParser.getKey().equals("id")) this.id = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("title")) this.title = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("body")) this.body = jsonParser.getValueAsString();
            //else if (jsonParser.getKey().equals("createdAt")) this.createdAt = jsonParser.getValue();
            //else if (jsonParser.getKey().equals("publishedAt")) this.publishedAt = jsonParser.getValue();
            else if (jsonParser.getKey().equals("owner")) this.owner = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("sentTo")) this.sentTo = jsonParser.getValueAsIntegerLinkedList();
            else if (jsonParser.getKey().equals("type")) this.type = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("state")) this.state = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("topics")) this.topics = jsonParser.getValueAsIntegerLinkedList();
            else if (jsonParser.getKey().equals("isDeleted")) this.isDeleted = jsonParser.getValueAsBoolean();
        }
    }
}
