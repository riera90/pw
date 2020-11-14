package es.uco.pw.business.dao.user;

import es.uco.pw.business.Connectors.FileConn;
import es.uco.pw.data.dto.user.DTOUser;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type User controller.
 */
public class DAOUser {
    private FileConn conn;

    /**
     * Instantiates a new User controller.
     */
    public DAOUser(){
        try {
            InputStream in = getClass().getResourceAsStream("/.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Properties p = new Properties();
            p.load(reader);
            this.conn = new FileConn(p.getProperty("FILE_BASE_DIR")+p.getProperty("USER_TABLE_NAME"));
        } catch (NullPointerException e){
            try {
                FileReader reader=new FileReader(".properties");
                Properties p = new Properties();
                p.load(reader);
                this.conn = new FileConn(p.getProperty("FILE_BASE_DIR") + p.getProperty("USER_TABLE_NAME"));
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
        DTOUser lastUser = new DTOUser(all.getLast());
        return lastUser.getId()+1;
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<DTOUser> get(){
        LinkedList<DTOUser> users = new LinkedList<>();
        for (String userJson:conn.readAll()){
            users.add(new DTOUser(userJson));
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
        for (String userJson:conn.getLineByField(key, value)){
            users.add(new DTOUser(userJson));
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
        for (String userJson:conn.getLineByFieldLike(key, value)){
            users.add(new DTOUser(userJson));
        }
        return users;
    }

    /**
     * Get user.
     *
     * @param id the id
     * @return the user
     */
    public DTOUser get(int id){
        return new DTOUser(conn.read(id));
    }

    /**
     * Post user.
     *
     * @param user the user
     * @return the user
     */
    public DTOUser post(DTOUser user){
        user.setId(getNextId());
        this.conn.append(user.toJson());
        return new DTOUser(this.conn.read(user.getId()));
    }

    /**
     * Put user.
     *
     * @param user the user
     * @return the user
     */
    public DTOUser put(DTOUser user){
        this.conn.update(user.toJson());
        return new DTOUser(this.conn.read(user.getId()));
    }

    /**
     * Patch user.
     *
     * @param user the user
     * @return the user
     */
    public DTOUser patch(DTOUser user){
        DTOUser oldUser = new DTOUser(this.conn.read(user.getId()));
        DTOUser newUser = new DTOUser(oldUser.toJson() + user.toJson());
        this.conn.update(newUser.toJson());
        return new DTOUser(this.conn.read(user.getId()));
    }

    /**
     * Delete boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public Boolean delete(DTOUser user){
        return this.conn.delete(user.getId());
    }
}
