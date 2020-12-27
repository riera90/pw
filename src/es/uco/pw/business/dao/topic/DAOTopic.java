package es.uco.pw.business.dao.topic;

import es.uco.pw.business.Connectors.FileConn;
import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.business.dao.post.PostBuilder;
import es.uco.pw.data.dto.post.DTOPost;
import es.uco.pw.data.dto.topic.DTOTopic;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type Topic controller.
 */
public class DAOTopic {
    private DBConn conn;

    /**
     * Instantiates a new Topic controller.
     */
    public DAOTopic(){
        try {
            this.conn = new DBConn();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<DTOTopic> get(){
        LinkedList<DTOTopic> topics = new LinkedList<>();
        String query = null;
        try {
            query = SqlQuery.getQuery("selectAllTopics");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = this.conn.execQuery(query);
            while (rs.next()){
                topics.add(TopicBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        String query = null;
        try {
            query = SqlQuery.getQuery("selectTopicBy_"+key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, value);
            ResultSet rs = this.conn.execQuery(stmt);
            while (rs.next()){
                topics.add(TopicBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        String query = null;
        try {
            query = SqlQuery.getQuery("selectTopicLike_"+key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, value);
            ResultSet rs = this.conn.execQuery(stmt);
            while (rs.next()){
                topics.add(TopicBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        String query = null;
        try {
            query = SqlQuery.getQuery("selectTopicBy_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = this.conn.execQuery(stmt);
            rs.next();
            return TopicBuilder.build(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new DTOTopic();
    }

    /**
     * Post topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public Integer post(DTOTopic topic){
        String query = null;
        try {
            query = SqlQuery.getQuery("insertTopic");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, topic.getId());
            stmt.setString(2, topic.getName());
            return this.conn.execStatement(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -2;
    }

    /**
     * Put topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public DTOTopic put(DTOTopic topic){
        if (topic.getId() == null){ // creates the user if it does not have an id
            return this.get(this.post(topic));
        }
        String query = null;
        try {
            query = SqlQuery.getQuery("updateTopic");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, topic.getName());
            stmt.setInt(2, topic.getId());
            this.conn.execStatement(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.get(topic.getId());
    }

    /**
     * Patch topic.
     *
     * @param topic the topic
     * @return the topic
     */
    public DTOTopic patch(DTOTopic topic){
        if (topic.getId() == null)
            return new DTOTopic();
        DTOTopic oldTopic = this.get(topic.getId());
        DTOTopic newTopic = TopicBuilder.build(TopicBuilder.toJson(oldTopic).replace('}',' ') + TopicBuilder.toJson(topic).replace('{', ' '));
        return put(newTopic);
    }
}
