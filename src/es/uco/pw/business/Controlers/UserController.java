package es.uco.pw.business.Controlers;

import es.uco.pw.business.Connectors.FileConn;
import es.uco.pw.business.dao.user.DAOUser;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type User controller.
 */
public class UserController {
    private FileConn conn;

    /**
     * Instantiates a new User controller.
     */
    public UserController(){
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
        DAOUser lastUser = new DAOUser(all.getLast());
        return lastUser.getId()+1;
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<DAOUser> get(){
        LinkedList<DAOUser> users = new LinkedList<>();
        for (String userJson:conn.readAll()){
            users.add(new DAOUser(userJson));
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
    public LinkedList<DAOUser> getByField(String key, String value){
        LinkedList<DAOUser> users = new LinkedList<>();
        for (String userJson:conn.getLineByField(key, value)){
            users.add(new DAOUser(userJson));
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
    public LinkedList<DAOUser> getByFieldLike(String key, String value){
        LinkedList<DAOUser> users = new LinkedList<>();
        for (String userJson:conn.getLineByFieldLike(key, value)){
            users.add(new DAOUser(userJson));
        }
        return users;
    }

    /**
     * Get user.
     *
     * @param id the id
     * @return the user
     */
    public DAOUser get(int id){
        return new DAOUser(conn.read(id));
    }

    /**
     * Post user.
     *
     * @param user the user
     * @return the user
     */
    public DAOUser post(DAOUser user){
        user.setId(getNextId());
        this.conn.append(user.toJson());
        return new DAOUser(this.conn.read(user.getId()));
    }

    /**
     * Put user.
     *
     * @param user the user
     * @return the user
     */
    public DAOUser put(DAOUser user){
        this.conn.update(user.toJson());
        return new DAOUser(this.conn.read(user.getId()));
    }

    /**
     * Patch user.
     *
     * @param user the user
     * @return the user
     */
    public DAOUser patch(DAOUser user){
        DAOUser oldUser = new DAOUser(this.conn.read(user.getId()));
        DAOUser newUser = new DAOUser(oldUser.toJson() + user.toJson());
        this.conn.update(newUser.toJson());
        return new DAOUser(this.conn.read(user.getId()));
    }

    /**
     * Delete boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public Boolean delete(DAOUser user){
        return this.conn.delete(user.getId());
    }
}
