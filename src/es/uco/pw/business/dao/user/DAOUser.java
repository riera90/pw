package es.uco.pw.business.dao.user;

import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.data.dto.user.DTOUser;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * The type Dao user.
 */
public class DAOUser {
    private DBConn conn;

    /**
     * Instantiates a new Dao user.
     */
    public DAOUser() {
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
    public LinkedList<DTOUser> get(){
        LinkedList<DTOUser> users = new LinkedList<>();
        String query = null;
        try {
            query = SqlQuery.getQuery("selectAllUserapp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = this.conn.execQuery(query);
            while (rs.next()){
                users.add(UserBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    /**
     * Get by field linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<DTOUser> getByField(String key, String value){
        LinkedList<DTOUser> users = new LinkedList<>();
        String query = null;
        try {
            query = SqlQuery.getQuery("selectUserappBy_"+key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, value);
            ResultSet rs = this.conn.execQuery(stmt);
            while (rs.next()){
                users.add(UserBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    /**
     * Get by field like linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<DTOUser> getByFieldLike(String key, String value){
        LinkedList<DTOUser> users = new LinkedList<>();
        String query = null;
        try {
            query = SqlQuery.getQuery("selectUserappLike_"+key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, value);
            ResultSet rs = this.conn.execQuery(stmt);
            while (rs.next()){
                users.add(UserBuilder.build(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    /**
     * Get dto user.
     *
     * @param id the id
     * @return the dto user
     */
    public DTOUser get(int id){
        String query = null;
        try {
            query = SqlQuery.getQuery("selectUserappBy_id");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = this.conn.execQuery(stmt);
            rs.next();
            return UserBuilder.build(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new DTOUser();
    }

    private DTOUser get__(int id){
        String query = null;
        try {
            query = SqlQuery.getQuery("selectUserappByIdIncludingDeleted");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = this.conn.execQuery(stmt);
            rs.next();
            return UserBuilder.build(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new DTOUser();
    }

    /**
     * Post integer.
     *
     * @param user the user
     * @return the integer
     */
    public Integer post(DTOUser user){
        String query = null;
        try {
            query = SqlQuery.getQuery("insertUserapp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setDate(4, new java.sql.Date(user.getBornAt().getTime()));
            stmt.setInt(5, user.getRoleId());
            stmt.setString(6, user.getPassword());
            return this.conn.execStatement(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Integer interest_id : user.getInterests()){
            try {
                query = SqlQuery.getQuery("insertUserappTopic");
                PreparedStatement ps = this.conn.prepareStatement(query);
                ps.setInt(1, user.getId());
                ps.setInt(1, interest_id);
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
     * Put dto user.
     *
     * @param user the user
     * @return the dto user
     */
    public DTOUser put(DTOUser user){
        if (user.getId() == null){ // creates the user if it does not have an id
            return this.get__(this.post(user));
        }
        String query = null;
        try {
            query = SqlQuery.getQuery("updateUserapp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setDate(4, new java.sql.Date(user.getBornAt().getTime()));
            stmt.setInt(5, user.getRoleId());
            stmt.setString(6, user.getPassword());
            stmt.setBoolean(7, user.getDeleted());
            stmt.setInt(8, user.getId());
            this.conn.execStatement(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // interests
        LinkedList<Integer> idsToRemove = new LinkedList<Integer>();
        LinkedList<Integer> idsToAdd = new LinkedList<Integer>();
        LinkedList<Integer> dbIds = new LinkedList<Integer>();

        try {
            query = SqlQuery.getQuery("selectUserappTopics");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user.getId());
            ResultSet rs_topics =  conn.execQuery(ps);
            while (rs_topics.next()) {
                dbIds.add(rs_topics.getInt("topic_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // filter
        idsToRemove = dbIds;
        idsToAdd = user.getInterests();
        for (Integer id : user.getInterests()){
            if (dbIds.contains(id)){
                idsToRemove.remove(id);
                idsToAdd.remove(id);
            }
        }
        for (Integer id : idsToAdd){
            try {
                query = SqlQuery.getQuery("insertUserappTopic");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, user.getId());
                ps.setInt(1, id);
                if (conn.execStatement(ps) < 0){
                    return new DTOUser();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        for (Integer id : idsToRemove){
            try {
                query = SqlQuery.getQuery("deleteUserappTopic");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, user.getId());
                ps.setInt(1, id);
                if (conn.execStatement(ps) < 0){
                    return new DTOUser();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return this.get__(user.getId());
    }


    /**
     * Patch dto user.
     *
     * @param user the user
     * @return the dto user
     */
    public DTOUser patch(DTOUser user){
        if (user.getId() == null)
            return new DTOUser();
        DTOUser oldUser = this.get__(user.getId());
        DTOUser newUser = UserBuilder.build(UserBuilder.toJson(oldUser).replace('}',' ') + UserBuilder.toJson(user).replace('{', ' '));
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
            query = SqlQuery.getQuery("deleteUserapp");
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
