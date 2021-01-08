package es.uco.pw.business.dao.post;

import es.uco.pw.business.Connectors.FileConn;
import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.business.dao.user.UserBuilder;
import es.uco.pw.data.dto.post.DTOPost;
import es.uco.pw.data.dto.user.DTOUser;

import java.io.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type Dao post.
 */
public class DAOPost {
    private DBConn conn;

    /**
     * Instantiates a new Dao post.
     */
    public DAOPost() {
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
    public LinkedList<DTOPost> get(){
        LinkedList<DTOPost> posts = new LinkedList<>();
        String query = null;
        try {
            query = SqlQuery.getQuery("selectAllPosts");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = this.conn.execQuery(query);
            while (rs.next()){
                posts.add(PostBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    /**
     * Get by field linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<DTOPost> getByField(String key, String value){
        LinkedList<DTOPost> posts = new LinkedList<>();
        String query = null;
        try {
            query = SqlQuery.getQuery("selectPostBy_"+key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, value);
            ResultSet rs = this.conn.execQuery(stmt);
            while (rs.next()){
                posts.add(PostBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    /**
     * Get by field like linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<DTOPost> getByFieldLike(String key, String value){
        LinkedList<DTOPost> posts = new LinkedList<>();
        String query = null;
        try {
            query = SqlQuery.getQuery("selectPostLike_"+key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, value);
            ResultSet rs = this.conn.execQuery(stmt);
            while (rs.next()){
                posts.add(PostBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    /**
     * Get dto post.
     *
     * @param id the id
     * @return the dto post
     */
    public DTOPost get(int id){
        String query = null;
        try {
            query = SqlQuery.getQuery("selectPostBy_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = this.conn.execQuery(stmt);
            rs.next();
            return PostBuilder.build(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new DTOPost();
    }

    private DTOPost get__(int id){
        String query = null;
        try {
            query = SqlQuery.getQuery("selectPostByIdIncludingDeleted");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = this.conn.execQuery(stmt);
            rs.next();
            return PostBuilder.build(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new DTOPost();
    }

    /**
     * Post integer.
     *
     * @param post the post
     * @return the integer
     */
    public Integer post(DTOPost post){
        String query = null;
        try {
            query = SqlQuery.getQuery("insertPost");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getBody());
            stmt.setInt(3, post.getOwnerId());
            stmt.setString(4, post.getState());
            stmt.setString(5, post.getType());
            stmt.setDate(6, new java.sql.Date(post.getCreatedAt().getTime()));
            stmt.setDate(7, new java.sql.Date(post.getPublishedAt().getTime()));
            stmt.setDate(8, new java.sql.Date(post.getDeletedAt().getTime()));
            return this.conn.execStatement(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Integer topic_id : post.getTopics()){
            try {
                query = SqlQuery.getQuery("insertPostTopic");
                PreparedStatement ps = this.conn.prepareStatement(query);
                ps.setInt(1, post.getId());
                ps.setInt(1, topic_id);
                if (conn.execStatement(ps) < 0){
                    return -3; // error, could not insert
                }
            } catch (IOException | SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        for (Integer sent_to_id : post.getSentTo()){
            try {
                query = SqlQuery.getQuery("insertPostUserapp");
                PreparedStatement ps = this.conn.prepareStatement(query);
                ps.setInt(1, post.getId());
                ps.setInt(1, sent_to_id);
                if (conn.execStatement(ps) < 0){
                    return -3; // error, could not insert
                }
            } catch (IOException | SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -2;
    }

    /**
     * Put dto post.
     *
     * @param post the post
     * @return the dto post
     */
    public DTOPost put(DTOPost post){
        if (post.getId() == null){ // creates the user if it does not have an id
            return this.get__(this.post(post));
        }
        String query = null;
        try {
            query = SqlQuery.getQuery("updatePost");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getBody());
            stmt.setInt(3, post.getOwnerId());
            stmt.setString(4, post.getState());
            stmt.setString(5, post.getType());
            stmt.setDate(6, new java.sql.Date(post.getCreatedAt().getTime()));
            if (post.getPublishedAt() == null)
                stmt.setNull(7, 1);
            else
                stmt.setDate(7, new java.sql.Date(post.getPublishedAt().getTime()));
            if (post.getDeletedAt() == null)
                stmt.setNull(8, 1);
            else
                stmt.setDate(8, new java.sql.Date(post.getDeletedAt().getTime()));
            stmt.setInt(9, post.getId());
            this.conn.execStatement(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.get__(post.getId());
    }

    /**
     * Patch dto post.
     *
     * @param post the post
     * @return the dto post
     */
    public DTOPost patch(DTOPost post){
        if (post.getId() == null)
            return new DTOPost();
        DTOPost oldUser = this.get__(post.getId());
        DTOPost newUser = PostBuilder.build(PostBuilder.toJson(oldUser).replace('}',' ') + PostBuilder.toJson(post).replace('{', ' '));
        return put(newUser);
    }

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean delete(Integer id){
        String query = null;
        try {
            query = SqlQuery.getQuery("deletePost");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            this.conn.execStatement(stmt);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
